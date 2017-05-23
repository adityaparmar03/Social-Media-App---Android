package cmpe277.sjsu.edu.teamproject.fragments;


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
import android.widget.Toast;

import cmpe277.sjsu.edu.teamproject.R;


public class CreatePostFragment extends Fragment {

    private static CreatePostFragment fragment;

    public static CreatePostFragment newInstance() {
        if(fragment == null)
            fragment = new CreatePostFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem actionViewItem = menu.findItem(R.id.action_item);
        View v = MenuItemCompat.getActionView(actionViewItem);

        Button button = (Button) v.findViewById(R.id.done_btn);
        button.setVisibility(View.VISIBLE);
        button.setClickable(true);
        button.setText(getString(R.string.post));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), getString(R.string.post_success), Toast.LENGTH_SHORT).show();

                if (fragment != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.remove( fragment);
                    ft.commit();
                }
            }
        });

        TextView cancel = (TextView) v.findViewById(R.id.title);
        cancel.setText(getString(android.R.string.cancel));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fragment != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.remove( fragment);
                    ft.commit();
                }
            }
        });

    }

}
