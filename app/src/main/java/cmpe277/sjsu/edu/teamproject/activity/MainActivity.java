package cmpe277.sjsu.edu.teamproject.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.fragments.TabFragment;
import cmpe277.sjsu.edu.teamproject.model.Session;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(null);
        //Toast.makeText(this, Session.LoggedEmail,Toast.LENGTH_LONG).show();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void init(){
        Fragment fragment = TabFragment.getInstance();

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }
        didSetSharedPreference();
    }
    public Boolean didSetSharedPreference() {

        try{
            SharedPreferences sharedPreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("screenName","Aditya Parmar");
            editor.putString("profileUri","https://s3-us-west-2.amazonaws.com/cmpe277/POST20170525_112442288506");

            editor.apply();

            return true;

        } catch (Exception e) {

            return false;
        }

    }


    public void logout (View view){

        SharedPreferences sharedPreferences = getSharedPreferences("logindata",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(this, Session.LoggedEmail,Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, SigninActivity.class);
        startActivity(i);
        finish();
    }


}
