package cmpe277.sjsu.edu.teamproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import cmpe277.sjsu.edu.teamproject.Services.TimelineFeedService;
import cmpe277.sjsu.edu.teamproject.adapter.TimelineRecyclerViewAdapter;
import cmpe277.sjsu.edu.teamproject.model.Post;
import cmpe277.sjsu.edu.teamproject.model.Session;
import cmpe277.sjsu.edu.teamproject.model.Timeline;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TimelineFragment extends Fragment {

    private Context context;

    private TimelineRecyclerViewAdapter recyclerViewAdapter;
    private List<Post> listOfPosts = new ArrayList<>();

    private static TimelineFragment fragment;

    public static TimelineFragment getInstance() {
        if (fragment == null)
            fragment = new TimelineFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity();
        setHasOptionsMenu(true);

        View root = inflater.inflate(R.layout.fragment_timeline, container, false);

        View createPostLayout = root.findViewById(R.id.create_post_layout);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

        getTimelineFeed();

        recyclerViewAdapter = new TimelineRecyclerViewAdapter(context, listOfPosts);
        recyclerView.setAdapter(recyclerViewAdapter);

        createPostLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment createPostFragment = CreatePostFragment.newInstance();
                if (createPostFragment != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(R.id.content_frame, createPostFragment).addToBackStack(getString(R.string.fragment_tag_create_post));
                    ft.commit();
                }
            }
        });

        return root;
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
        textView.setText(getString(R.string.timeline));
    }

    private void getTimelineFeed() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TimelineFeedService timelineFeedService = retrofit.create(TimelineFeedService.class);

        Call<Timeline> callGetTimelineFeed = timelineFeedService.getFeed(Session.LoggedEmail);

        callGetTimelineFeed.enqueue(new Callback<Timeline>() {

            @Override
            public void onResponse(Call<Timeline> call, Response<Timeline> response) {

                listOfPosts = response.body().getPosts();
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Timeline> call, Throwable t) {

            }

        });

    }

}
