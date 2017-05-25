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
import cmpe277.sjsu.edu.teamproject.adapter.FriendRequestAdapter;
import cmpe277.sjsu.edu.teamproject.model.FriendRequest;
import cmpe277.sjsu.edu.teamproject.model.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FriendRequestsFragment extends Fragment {

    private FriendRequestAdapter friendRequestAdapter;
    private List<FriendRequest> friendRequestList = new ArrayList<>();

    private static FriendRequestsFragment fragment;

    public static FriendRequestsFragment getInstance()
    {
        if(fragment == null)
            fragment = new FriendRequestsFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend_requests, container, false);

        setHasOptionsMenu(true);

        fetchPendingRequests();

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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

    private void fetchPendingRequests() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FriendRequestService friendRequestService = retrofit.create(FriendRequestService.class);

        Call<List<FriendRequest>> callFetchFriendRequests = friendRequestService.fetchPendingRequests(Session.LoggedEmail);

        callFetchFriendRequests.enqueue(new Callback<List<FriendRequest>>() {
            @Override
            public void onResponse(Call<List<FriendRequest>> call, Response<List<FriendRequest>> response) {

                friendRequestList = response.body();
                friendRequestAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FriendRequest>> call, Throwable t) {

            }

        });

    }

}

