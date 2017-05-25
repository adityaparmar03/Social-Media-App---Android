package cmpe277.sjsu.edu.teamproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import cmpe277.sjsu.edu.teamproject.activity.MainActivity;


public class LogoutFragment extends Fragment {
    private static LogoutFragment fragment;

    public static LogoutFragment getInstance() {
        if (fragment == null) {
            fragment = new LogoutFragment();
        }
        return fragment;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("logindata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(getActivity(),MainActivity.class);
        startActivity(i);
    }
}
