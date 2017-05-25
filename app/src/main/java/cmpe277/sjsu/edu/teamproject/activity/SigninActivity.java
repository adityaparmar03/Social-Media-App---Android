package cmpe277.sjsu.edu.teamproject.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.Services.SignInService;
import cmpe277.sjsu.edu.teamproject.Services.SignUpService;
import cmpe277.sjsu.edu.teamproject.model.CurrentUserSessionModel;
import cmpe277.sjsu.edu.teamproject.model.SignInRequest;
import cmpe277.sjsu.edu.teamproject.model.SignUpRequest;
import cmpe277.sjsu.edu.teamproject.model.StatusForAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SigninActivity extends AppCompatActivity {

    private Button signInButton, signUpButton;
    private EditText emailEditText, passwordEditText;

    private TextView forgotPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


       if (getsharedpreferenceemail())
        {
            Intent i = new Intent(SigninActivity.this, MainActivity.class);
            startActivity(i);
        }
        else {
            init();
        }

    }

    private void init() {

        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);

        signInButton = (Button) findViewById(R.id.sign_in_button);
        signUpButton = (Button) findViewById(R.id.sign_up_button);



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignInRequest signInRequest = new SignInRequest();
                signInRequest.setEmailid(emailEditText.getText().toString());
                signInRequest.setPassword(passwordEditText.getText().toString());


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getString(R.string.ip))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();



                //SignUpClient signUpClient = retrofit.create(SignUpClient.class);

                SignInService signInClient = retrofit.create(SignInService.class);


                Call<StatusForAll> callsignin = signInClient.signin(signInRequest);




                callsignin.enqueue(new Callback<StatusForAll>() {
                   @Override
                   public void onResponse(Call<StatusForAll> call, Response<StatusForAll> response) {


                       if(response.body().getStatus().equals("200")) {
                           if(setsharedpreference(emailEditText.getText().toString(),passwordEditText.getText().toString()))
                           {
                               Toast.makeText(SigninActivity.this,"Successfully logged In.", Toast.LENGTH_LONG).show();
                               Intent i = new Intent(SigninActivity.this, MainActivity.class);
                               startActivity(i);
                               finish();

                           } else {
                               Toast.makeText(SigninActivity.this,"Something went wrong.", Toast.LENGTH_LONG).show();

                           }

                       }
                       else if(response.body().getStatus().equals("300")){

                           Toast.makeText(SigninActivity.this,"Verification Required.", Toast.LENGTH_LONG).show();

                       }
                       else{
                           Toast.makeText(SigninActivity.this,"Use right credentials.", Toast.LENGTH_LONG).show();
                       }


                   }

                   @Override
                   public void onFailure(Call<StatusForAll> call, Throwable t) {

                       Toast.makeText(SigninActivity.this,"Something went wrong,Try After sometime", Toast.LENGTH_LONG).show();


                   }
               });




            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignUpRequest signUpRequest = new SignUpRequest();
                signUpRequest.setEmailid(emailEditText.getText().toString());
                signUpRequest.setPassword(passwordEditText.getText().toString());


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getString(R.string.ip))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                SignUpService signUpService = retrofit.create(SignUpService.class);

                Call<StatusForAll> callsignup = signUpService.signup(signUpRequest);

                callsignup.enqueue(new Callback<StatusForAll>() {
                    @Override
                    public void onResponse(Call<StatusForAll> call, Response<StatusForAll> response) {


                        Toast.makeText(SigninActivity.this,response.body().getStatus(),Toast.LENGTH_LONG).show();

                        if(response.body().getStatus().equals("200"))
                        {
                            Toast.makeText(SigninActivity.this,"Account Created Succeessfully, Please LogIN",Toast.LENGTH_LONG).show();

                        }
                        else if(response.body().getStatus().equals("300"))
                        {
                            Toast.makeText(SigninActivity.this,"You already have an account, Please LogIN",Toast.LENGTH_LONG).show();

                        }
                        else{
                            Toast.makeText(SigninActivity.this,"something went wrong",Toast.LENGTH_LONG).show();
                        }



                    }

                    @Override
                    public void onFailure(Call<StatusForAll> call, Throwable t) {

                        Toast.makeText(SigninActivity.this,"something went wrong",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

    }

    public Boolean setsharedpreference(String email, String password)
    {

        try{
            SharedPreferences sharedPreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email",email);
            editor.putString("password",password);

            editor.apply();

            return true;
        }
        catch (Exception e)
        {
            return false;

        }


    }
    public Boolean getsharedpreferenceemail(){

        try
        {
            SharedPreferences sharedPreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);
            String email = sharedPreferences.getString("email","");

            if(!email.isEmpty()) {
                CurrentUserSessionModel.LoggedEmail=email;
                Log.d("LoggedEmail",CurrentUserSessionModel.LoggedEmail);

                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            return false;
        }

    }
}

