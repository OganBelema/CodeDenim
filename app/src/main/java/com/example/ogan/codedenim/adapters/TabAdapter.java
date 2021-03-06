package com.example.ogan.codedenim.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ogan.codedenim.fragments.HomeFragment;
import com.example.ogan.codedenim.fragments.MentorsFragment;
import com.example.ogan.codedenim.fragments.PitchFragment;
import com.example.ogan.codedenim.fragments.UpdateFragment;

/**
 * Created by ogan on 8/13/17.
 */

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new UpdateFragment();
            case 2:
                return new MentorsFragment();
            case 3:
                return new PitchFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Home";
            case 1:
                return "News";
            case 2:
                return "Leads";
            case 3:
                return "Pitch";
        }
        return null;
    }


}
