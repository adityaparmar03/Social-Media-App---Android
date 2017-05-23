package cmpe277.sjsu.edu.teamproject.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cmpe277.sjsu.edu.teamproject.fragments.FriendRequestFragment;
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
                TimelineFragment timelineFragment = TimelineFragment.getInstance();
                return timelineFragment;

            case 1:
                FriendRequestFragment friendRequestFragment = FriendRequestFragment.getInstance();
                return friendRequestFragment;

            case 2:
                NotificationFragment notificationFragment = NotificationFragment.getInstance();
                return notificationFragment;

            case 3:
                NavigationFragment navigationFragment = NavigationFragment.getInstance();
                return navigationFragment;

            default:
                TimelineFragment defaultFragment = TimelineFragment.getInstance();
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
