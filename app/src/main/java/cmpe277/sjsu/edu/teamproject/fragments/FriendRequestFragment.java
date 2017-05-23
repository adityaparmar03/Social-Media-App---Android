package cmpe277.sjsu.edu.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.adapter.FriendRequestAdapter;
import cmpe277.sjsu.edu.teamproject.model.FriendRequestModel;

public class FriendRequestFragment extends Fragment {

    private RecyclerView recyclerView;
    private FriendRequestAdapter friendRequestAdapter;

    private static FriendRequestFragment fragment;

    public static FriendRequestFragment getInstance()
    {
        if(fragment == null)
            fragment = new FriendRequestFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend_requests, container, false);

        setHasOptionsMenu(true);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<FriendRequestModel> friendRequestList = new ArrayList<>();

        // TODO: fetch from API; this is dummy data
        for(int i=0;i<6;i++)
        {
            FriendRequestModel model = new FriendRequestModel();
            model.setId(""+i);
            model.setName("New friend " + i);
            friendRequestList.add(model);
        }

        friendRequestAdapter = new FriendRequestAdapter(getActivity(), friendRequestList);
        recyclerView.setAdapter(friendRequestAdapter);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItem = menu.findItem(R.id.action_item);
        View view = MenuItemCompat.getActionView(menuItem);

        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(getString(R.string.friend_requests));

        Button button = (Button) view.findViewById(R.id.done_btn);
        button.setVisibility(View.INVISIBLE);
        button.setClickable(false);

    }

}
