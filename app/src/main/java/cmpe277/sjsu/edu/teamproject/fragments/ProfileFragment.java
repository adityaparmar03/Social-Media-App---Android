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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.Services.ProfileService;
import cmpe277.sjsu.edu.teamproject.adapter.TimelineRecyclerViewAdapter;
import cmpe277.sjsu.edu.teamproject.model.Post;
import cmpe277.sjsu.edu.teamproject.model.Session;
import cmpe277.sjsu.edu.teamproject.model.UserProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment {

    private Context context;

    private ImageView profilePicImageView;
    private TextView locationTextView, professionTextView, screenNameTextView, aboutMeTextView,
            interestsTextView;

    private TimelineRecyclerViewAdapter timelineRecyclerViewAdapter;
    private UserProfile userProfile;
    private List<Post> userPosts = new ArrayList<>();

    private static ProfileFragment fragment;

    public static ProfileFragment getInstance() {
        if(fragment == null)
            fragment = new ProfileFragment();
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

        fetchUserProfile();

        init(view);

        return view;
    }

    private void init(View view) {

        View profileHeaderView = view.findViewById(R.id.layout_profile_header);

        profilePicImageView = (ImageView) profileHeaderView.findViewById(R.id.profile_pic_imageview);

        locationTextView = (TextView) profileHeaderView.findViewById(R.id.location_textview);
        professionTextView = (TextView) profileHeaderView.findViewById(R.id.profession_textview);
        aboutMeTextView = (TextView) profileHeaderView.findViewById(R.id.about_me_textview);
        interestsTextView = (TextView) profileHeaderView.findViewById(R.id.interests_textview);
        screenNameTextView = (TextView) profileHeaderView.findViewById(R.id.screen_name_textview);

        View optionOneLayout = view.findViewById(R.id.option_one_layout);
        optionOneLayout.setVisibility(View.INVISIBLE);

        // update info
        View viewUpdateInfo = view.findViewById(R.id.option_two_layout);
        viewUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = UpdateProfileFragment.getInstance(userProfile);
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
        TextView seeAllFriendsText = (TextView) view.findViewById(R.id.see_all_friends_textview);
        seeAllFriendsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = SeeAllFriendsFragment.getInstance();
                if (fragment != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(R.id.content_frame, fragment).addToBackStack(getString
                            (R.string.fragment_tag_see_all_friends));
                    ft.commit();
                }
            }
        });

        // user posts
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

        timelineRecyclerViewAdapter = new TimelineRecyclerViewAdapter(context, userPosts);
        recyclerView.setAdapter(timelineRecyclerViewAdapter);
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

    private void fetchUserProfile() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProfileService profileService = retrofit.create(ProfileService.class);

        Call<UserProfile> callFetchUserProfile = profileService.getUserProfile(Session.LoggedEmail);

        callFetchUserProfile.enqueue(new Callback<UserProfile>() {

            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {

                userProfile = response.body();

                professionTextView.setText(getString(R.string.profession, userProfile.getProfession()));
                locationTextView.setText(getString(R.string.location, userProfile.getLocation()));
                aboutMeTextView.setText(getString(R.string.about_me, userProfile.getAboutMe()));
                interestsTextView.setText(getString(R.string.interests, userProfile.getInterests()));
                screenNameTextView.setText(userProfile.getScreenName());

                Glide.with(context)
                        .load(userProfile.getProfileImageURL())
                        .error(R.mipmap.ic_launcher)
                        .into(profilePicImageView);

                userPosts = userProfile.getPosts();
                timelineRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }

        });

    }

}
