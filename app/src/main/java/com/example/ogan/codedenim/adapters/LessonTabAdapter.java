package com.example.ogan.codedenim.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ogan.codedenim.fragments.PDFFragment;
import com.example.ogan.codedenim.fragments.VideoFragment;

/**
 * Created by belema on 9/12/17.
 */

public class LessonTabAdapter extends FragmentPagerAdapter {

    public LessonTabAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new VideoFragment();
            case 1:
                return new PDFFragment();


        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Video";
            case 1:
                return "PDF";

        }
        return null;
    }
}
