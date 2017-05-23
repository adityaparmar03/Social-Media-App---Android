package cmpe277.sjsu.edu.teamproject.fragments;

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


public class NotificationFragment extends Fragment {

    private static NotificationFragment fragment;

    public static NotificationFragment getInstance() {
        if (fragment == null)
            fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem actionViewItem = menu.findItem(R.id.action_item);
        View view = MenuItemCompat.getActionView(actionViewItem);

        Button button = (Button) view.findViewById(R.id.done_btn);
        button.setClickable(false);
        button.setVisibility(View.INVISIBLE);

        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(getString(R.string.noticfications));
    }

}

