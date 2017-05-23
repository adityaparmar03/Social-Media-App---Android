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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.adapter.TimelineRecyclerViewAdapter;
import cmpe277.sjsu.edu.teamproject.model.PostModel;
import cmpe277.sjsu.edu.teamproject.model.ProfileModel;


public class ProfileFragment extends Fragment {

    private Context context;

    private ImageView coverPicImageView, profilePicImageView;
    private TextView locationTextView, professionTextView, screenNameTextView;
    private RecyclerView recyclerView;

    private static ProfileModel mProfileModel;
    private static ProfileFragment fragment;

    public static ProfileFragment getInstance(ProfileModel profileModel) {
        if(fragment == null)
            fragment = new ProfileFragment();
        mProfileModel = profileModel;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        setHasOptionsMenu(true);

        context = getActivity();

        init(view);

        professionTextView.setText(getString(R.string.profession, mProfileModel.getWorksAt()));
        locationTextView.setText(getString(R.string.location, mProfileModel.getLivesIn()));

        ArrayList<PostModel> modelList = new ArrayList<>();

        // dummy data
        for(int i=0;i<10;i++)
        {
            PostModel model = new PostModel();
            model.setTitle("Sample FB post");
            model.setAuthorName("Screen Name");
            model.setSharedTimeStamp("5th may 2017");
            model.setIsMediaAttached(false);
            modelList.add(model);
        }

        TimelineRecyclerViewAdapter adapter = new TimelineRecyclerViewAdapter(context, modelList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void init(View view) {

        View profileHeaderView = view.findViewById(R.id.layout_profile_header);

        profilePicImageView = (ImageView) profileHeaderView.findViewById(R.id.profile_pic_imageview);
        coverPicImageView = (ImageView) profileHeaderView.findViewById(R.id.cover_pic_imageview);

        locationTextView = (TextView) profileHeaderView.findViewById(R.id.location_textview);
        professionTextView = (TextView) profileHeaderView.findViewById(R.id.profession_textview);
        screenNameTextView = (TextView) profileHeaderView.findViewById(R.id.screen_name_textview);

        // update info
        View viewUpdateInfo = view.findViewById(R.id.option_two_layout);
        viewUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = UpdateProfileFragment.newInstance(mProfileModel);
                if (fragment != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(R.id.content_frame, fragment).addToBackStack(getString
                            (R.string.fragment_tag_update_profile));
                    ft.commit();
                }
            }
        });

        TextView updateInfoTextView = (TextView) viewUpdateInfo.findViewById(R.id.option_two_textview);
        updateInfoTextView.setText(getString(R.string.update_info));

        ImageView updateInfoImageView = (ImageView) viewUpdateInfo.findViewById(R.id.option_two_imageview);
        updateInfoImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.update_info));

        // view all friends
        View friendsView = view.findViewById(R.id.layout_profile_friends);
        TextView seeAllFriendsText = (TextView) friendsView.findViewById(R.id.see_all_friends_textview);
        seeAllFriendsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), getString(R.string.see_all_friends), Toast.LENGTH_SHORT).show();
            }
        });

        // friends count
        TextView friendsCount = (TextView)friendsView.findViewById(R.id.profile_friends_count);
        // dummy data
        friendsCount.setText("150");

        // user posts
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
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
        textView.setText(getString(R.string.user_profile));
    }

}
