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
import android.widget.Toast;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.Services.SignInService;
import cmpe277.sjsu.edu.teamproject.Services.SignUpService;
import cmpe277.sjsu.edu.teamproject.Services.VerifyoptService;
import cmpe277.sjsu.edu.teamproject.model.GenericPostResponse;
import cmpe277.sjsu.edu.teamproject.model.Session;
import cmpe277.sjsu.edu.teamproject.model.SignInRequest;
import cmpe277.sjsu.edu.teamproject.model.SignUpRequest;
import cmpe277.sjsu.edu.teamproject.model.Verifyopt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SigninActivity extends AppCompatActivity {

    private Button signInButton, signUpButton;
    private EditText emailEditText, passwordEditText;
    Boolean ISverified=true;
    Boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (isEmailInSharedPreferences()) {

            startActivity(new Intent(SigninActivity.this, MainActivity.class));

        } else {

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

                if(!ISverified)
                {
                    checkverificationcode();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(getString(R.string.base_url))
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    SignInService signInService = retrofit.create(SignInService.class);

                    Call<GenericPostResponse> callSignIn = signInService.signIn(signInRequest);

                    callSignIn.enqueue(new Callback<GenericPostResponse>() {
                        @Override
                        public void onResponse(Call<GenericPostResponse> call, Response<GenericPostResponse> response) {

                            switch (response.body().getStatus()) {

                                case "200":
                                    if (didSetSharedPreference(emailEditText.getText().toString(), passwordEditText.getText().toString())) {

                                        Toast.makeText(SigninActivity.this, "Successfully logged In.", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(SigninActivity.this, MainActivity.class));
                                        finish();

                                    } else {
                                        Toast.makeText(SigninActivity.this, "Something went wrong.", Toast.LENGTH_LONG).show();
                                    }
                                    break;

                                case "300":
                                    Toast.makeText(SigninActivity.this, "Verification Required.", Toast.LENGTH_LONG).show();
                                    EditText edverify  = (EditText) findViewById(R.id.verification_code_edit_text);
                                    edverify.setVisibility(View.VISIBLE);
                                    ISverified = false;
                                    break;

                                default:
                                    Toast.makeText(SigninActivity.this, "Use right credentials.", Toast.LENGTH_LONG).show();
                                    break;

                            }

                        }

                        @Override
                        public void onFailure(Call<GenericPostResponse> call, Throwable t) {

                            Toast.makeText(SigninActivity.this,"Something went wrong,Try After sometime", Toast.LENGTH_LONG).show();
                        }

                    });
                }
                else
                {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(getString(R.string.base_url))
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    SignInService signInService = retrofit.create(SignInService.class);

                    Call<GenericPostResponse> callSignIn = signInService.signIn(signInRequest);

                    callSignIn.enqueue(new Callback<GenericPostResponse>() {
                        @Override
                        public void onResponse(Call<GenericPostResponse> call, Response<GenericPostResponse> response) {

                            switch (response.body().getStatus()) {

                                case "200":
                                    if (didSetSharedPreference(emailEditText.getText().toString(), passwordEditText.getText().toString())) {

                                        Toast.makeText(SigninActivity.this, "Successfully logged In.", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(SigninActivity.this, MainActivity.class));
                                        finish();

                                    } else {
                                        Toast.makeText(SigninActivity.this, "Something went wrong.", Toast.LENGTH_LONG).show();
                                    }
                                    break;

                                case "300":
                                    Toast.makeText(SigninActivity.this, "Verification Required.", Toast.LENGTH_LONG).show();
                                    EditText edverify  = (EditText) findViewById(R.id.verification_code_edit_text);
                                    edverify.setVisibility(View.VISIBLE);
                                    ISverified = false;
                                    break;

                                default:
                                    Toast.makeText(SigninActivity.this, "Use right credentials.", Toast.LENGTH_LONG).show();
                                    break;

                            }

                        }

                        @Override
                        public void onFailure(Call<GenericPostResponse> call, Throwable t) {

                            Toast.makeText(SigninActivity.this,"Something went wrong,Try After sometime", Toast.LENGTH_LONG).show();
                        }

                    });
                }



            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignUpRequest signUpRequest = new SignUpRequest();
                signUpRequest.setEmailid(emailEditText.getText().toString());
                signUpRequest.setPassword(passwordEditText.getText().toString());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getString(R.string.base_url))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                SignUpService signUpService = retrofit.create(SignUpService.class);

                Call<GenericPostResponse> callsignup = signUpService.signUp(signUpRequest);

                callsignup.enqueue(new Callback<GenericPostResponse>() {
                    @Override
                    public void onResponse(Call<GenericPostResponse> call, Response<GenericPostResponse> response) {

                        switch (response.body().getStatus()) {

                            case "200":
                                Toast.makeText(SigninActivity.this, "Account Created Succeessfully, Please LogIN", Toast.LENGTH_LONG).show();
                                break;

                            case "300":
                                Toast.makeText(SigninActivity.this, "You already have an account, Please LogIN", Toast.LENGTH_LONG).show();
                                break;

                            default:
                                Toast.makeText(SigninActivity.this, "something went wrong", Toast.LENGTH_LONG).show();
                                break;
                        }

                    }

                    @Override
                    public void onFailure(Call<GenericPostResponse> call, Throwable t) {

                        Toast.makeText(SigninActivity.this,"something went wrong",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

    }

    public Boolean didSetSharedPreference(String email, String password) {

        try{
            SharedPreferences sharedPreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email",email);
            editor.putString("password",password);
            Session.LoggedEmail=email;
            editor.apply();

            return true;

        } catch (Exception e) {

            return false;
        }

    }

    private Boolean isEmailInSharedPreferences() {

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);
            String email = sharedPreferences.getString("email","");

            if(!email.isEmpty()) {
                Session.LoggedEmail=email;
                Log.d("LoggedEmail", Session.LoggedEmail);

                return true;
            } else {

                return false;
            }

        } catch (Exception e) {

            return false;
        }
    }

    private Boolean checkverificationcode()
    {
        EditText edverify  = (EditText) findViewById(R.id.verification_code_edit_text);
        EditText email  = (EditText) findViewById(R.id.email_edit_text);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Verifyopt verifyopt =  new Verifyopt();
        verifyopt.setEmailid(email.getText().toString());
        verifyopt.setOtp(edverify.getText().toString());

        VerifyoptService verifyoptService = retrofit.create(VerifyoptService.class);
        Call<GenericPostResponse> callverifycode = verifyoptService.verify(verifyopt);

        callverifycode.enqueue(new Callback<GenericPostResponse>() {
            @Override
            public void onResponse(Call<GenericPostResponse> call, Response<GenericPostResponse> response) {

                switch (response.body().getStatus()) {

                    case "200":
                        Toast.makeText(SigninActivity.this, "Verification complete.", Toast.LENGTH_LONG).show();
                        result = true;
                        break;

                    default:
                        Toast.makeText(SigninActivity.this, "something went wrong", Toast.LENGTH_LONG).show();
                        result = false;
                        break;
                }

            }

            @Override
            public void onFailure(Call<GenericPostResponse> call, Throwable t) {

                Toast.makeText(SigninActivity.this,"something went wrong, Try again after sometime.",Toast.LENGTH_LONG).show();
            }
        });




        return result;
    }

}

