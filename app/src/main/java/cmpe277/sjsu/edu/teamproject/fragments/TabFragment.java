package cmpe277.sjsu.edu.teamproject.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cmpe277.sjsu.edu.teamproject.R;
import cmpe277.sjsu.edu.teamproject.adapter.SectionsPagerAdapter;

public class TabFragment extends Fragment {

    private Context context;
    private SectionsPagerAdapter sectionsPagerAdapter;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private static TabFragment tabFragment;

    public static TabFragment getInstance()
    {
        if (tabFragment == null)
            tabFragment = new TabFragment();
        return tabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        sectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), context);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager_container);
        viewPager.setAdapter(sectionsPagerAdapter);

        tabLayout = (TabLayout) view.findViewById(R.id.fragment_tabs);
        tabLayout.setupWithViewPager(viewPager);

        setUpIcons();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem actionViewItem = menu.findItem(R.id.action_item);
        View v = MenuItemCompat.getActionView(actionViewItem);
        Button button = (Button) v.findViewById(R.id.done_btn);
        button.setVisibility(View.INVISIBLE);
        button.setClickable(false);

        TextView textView = (TextView) v.findViewById(R.id.title);
        textView.setText(getString(R.string.timeline));
    }

    public void setUpIcons() {

        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.users);
        tabLayout.getTabAt(2).setIcon(R.drawable.ring);
        tabLayout.getTabAt(3).setIcon(R.drawable.menu_button);
    }

}
