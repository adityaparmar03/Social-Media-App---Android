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
import java.util.List;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.Services.FriendRequestService;
import cmpe277.sjsu.edu.teamproject.adapter.SeeAllFriendsAdapter;
import cmpe277.sjsu.edu.teamproject.model.FriendRequest;
import cmpe277.sjsu.edu.teamproject.model.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SeeAllFriendsFragment extends Fragment {

    private SeeAllFriendsAdapter seeAllFriendsAdapter;

    private List<FriendRequest> allFriends = new ArrayList<>();

    private static SeeAllFriendsFragment fragment;

    public static SeeAllFriendsFragment getInstance() {
        if (fragment == null)
            fragment = new SeeAllFriendsFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_friend_requests, container, false);

        setHasOptionsMenu(true);

        fetchAllFriends();

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        seeAllFriendsAdapter = new SeeAllFriendsAdapter(getActivity(), allFriends);
        recyclerView.setAdapter(seeAllFriendsAdapter);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItem = menu.findItem(R.id.action_item);
        View view = MenuItemCompat.getActionView(menuItem);

        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(getString(R.string.all_friends));

        Button button = (Button) view.findViewById(R.id.done_btn);
        button.setVisibility(View.INVISIBLE);
        button.setClickable(false);
    }

    private void fetchAllFriends() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FriendRequestService friendRequestService = retrofit.create(FriendRequestService.class);

        Call<List<FriendRequest>> callFetchSentRequests = friendRequestService.fetchFriendsDtls(Session.LoggedEmail);

        callFetchSentRequests.enqueue(new Callback<List<FriendRequest>>() {
            @Override
            public void onResponse(Call<List<FriendRequest>> call, Response<List<FriendRequest>> response) {

                allFriends = response.body();
                seeAllFriendsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FriendRequest>> call, Throwable t) {

            }

        });

    }

}

