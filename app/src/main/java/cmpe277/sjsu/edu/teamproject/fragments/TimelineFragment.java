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

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.adapter.TimelineRecyclerViewAdapter;
import cmpe277.sjsu.edu.teamproject.model.PostModel;

public class TimelineFragment extends Fragment {

    private static TimelineFragment fragment;
    private Context context;
    private RecyclerView recyclerView;
    private TimelineRecyclerViewAdapter recyclerViewAdapter;
    private View createPostLayout;

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

        createPostLayout = root.findViewById(R.id.create_post_layout);

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

        ArrayList<PostModel> modelList = new ArrayList<>();

        // TODO: fetch data and add to list
        for (int i = 0; i < 10; i++) {
            PostModel model = new PostModel();
            model.setMessage("Sample FB post");
            model.setScreenname("Screen Name");
            model.setDatetime("5th may 2017");
            model.setMedia(" ");
            modelList.add(model);


            modelList.add(model);
        }

        recyclerViewAdapter = new TimelineRecyclerViewAdapter(context, modelList);
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

}
