package cmpe277.sjsu.edu.teamproject.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cmpe277.sjsu.edu.teamproject.fragments.FriendRequestsFragment;
import cmpe277.sjsu.edu.teamproject.fragments.NavigationFragment;
import cmpe277.sjsu.edu.teamproject.fragments.NotificationFragment;
import cmpe277.sjsu.edu.teamproject.fragments.TimelineFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public SectionsPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
//                TimelineFragment timelineFragment = TimelineFragment.getInstance();
                TimelineFragment timelineFragment = new TimelineFragment();
                return timelineFragment;

            case 1:
//                FriendRequestsFragment friendRequestsFragment = FriendRequestsFragment.getInstance();
                FriendRequestsFragment friendRequestsFragment = new FriendRequestsFragment();
                return friendRequestsFragment;

            case 2:
//                NotificationFragment notificationFragment = NotificationFragment.getInstance();
                NotificationFragment notificationFragment = new NotificationFragment();
                return notificationFragment;

            case 3:
//                NavigationFragment navigationFragment = NavigationFragment.getInstance();
                NavigationFragment navigationFragment = new NavigationFragment();
                return navigationFragment;

            default:
//                TimelineFragment defaultFragment = TimelineFragment.getInstance();
                TimelineFragment defaultFragment = new TimelineFragment();
                return defaultFragment;
        }
    }

    @Override
    public int getCount() {

        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return null;
            case 1:
                return null;
            case 2:
                return null;
            case 3:
                return null;
        }
        return null;
    }
}
