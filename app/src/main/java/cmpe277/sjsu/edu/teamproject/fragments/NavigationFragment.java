package cmpe277.sjsu.edu.teamproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cmpe277.sjsu.edu.teamproject.R;

public class NavigationFragment extends Fragment implements View.OnClickListener {

    private static NavigationFragment fragment;

    public static NavigationFragment getInstance() {

        if (fragment == null) {
            fragment = new NavigationFragment();

        }
        return fragment;
    }

    private TextView screenNameTextView, appSettingTextView, sentRequestsTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_navigation, container, false);

        setHasOptionsMenu(true);

        screenNameTextView = (TextView) rootView.findViewById(R.id.screen_name_textview);
        appSettingTextView = (TextView) rootView.findViewById(R.id.settings_textview);
        sentRequestsTextView = (TextView) rootView.findViewById(R.id.sent_requests_textview);


        screenNameTextView.setOnClickListener(this);
        appSettingTextView.setOnClickListener(this);
        sentRequestsTextView.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.action_item);
        View view = MenuItemCompat.getActionView(menuItem);

        Button button = (Button) view.findViewById(R.id.done_btn);
        button.setVisibility(View.INVISIBLE);
        button.setClickable(false);

        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(getString(R.string.app_options));
    }

    @Override
    public void onClick(View v) {

        //initializing the fragment object which is selected
        switch (v.getId()) {
            case R.id.screen_name_textview:

//                Fragment profileFragment = ProfileFragment.getInstance();
                Fragment profileFragment = new ProfileFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.content_frame, profileFragment)
                        .addToBackStack(getString(R.string.fragment_tag_profile));
                fragmentTransaction.commit();


                break;

            case R.id.settings_textview:

                Fragment settingsFragment = SettingsFragment.getInstance();

                if (settingsFragment != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(R.id.content_frame, settingsFragment)
                            .addToBackStack(getString(R.string.fragment_tag_settings));
                    ft.commit();
                }

                break;

            case R.id.sent_requests_textview:
                Fragment sentRequestsFragment = SentRequestsFragment.getInstance();

                if (sentRequestsFragment != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(R.id.content_frame, sentRequestsFragment)
                            .addToBackStack(getString(R.string.fragment_tag_sent_requests));
                    ft.commit();
                }

                break;

        }

    }

}

