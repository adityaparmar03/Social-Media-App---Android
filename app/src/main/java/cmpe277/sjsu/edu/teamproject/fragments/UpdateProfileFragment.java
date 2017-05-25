package cmpe277.sjsu.edu.teamproject.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import cmpe277.sjsu.edu.teamproject.model.UserProfile;


public class UpdateProfileFragment extends Fragment {

    private Context context;

    private static UpdateProfileFragment fragment;
    private static UserProfile userProfile;

    public static UpdateProfileFragment getInstance(UserProfile userProfile) {
        if(fragment==null)
            fragment = new UpdateProfileFragment();
        UpdateProfileFragment.userProfile = userProfile;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_profile, container, false);
        setHasOptionsMenu(true);

        context = getActivity();

        init(view);

        return view;
    }

    private void init(View view) {

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.action_item);
        View actionView = MenuItemCompat.getActionView(menuItem);

        Button button = (Button) actionView.findViewById(R.id.done_btn);
        button.setVisibility(View.INVISIBLE);
        button.setClickable(false);

        TextView textView = (TextView) actionView.findViewById(R.id.title);
        textView.setText(getString(R.string.update_profile));
    }

}
