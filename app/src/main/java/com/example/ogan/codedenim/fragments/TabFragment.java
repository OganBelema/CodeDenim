package com.example.ogan.codedenim.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.adapters.TabAdapter;


public class TabFragment extends Fragment {


    public TabFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab, container, false);
        getActivity().setTitle("Home");
        ViewPager viewPager = v.findViewById(R.id.viewpager);

        //to change the title of the activity when a tab is selected
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        getActivity().setTitle("Home");
                        break;
                    case 1:
                        getActivity().setTitle("News");
                        break;
                    case 2:
                        getActivity().setTitle("Leads");
                        break;
                    case 3:
                        getActivity().setTitle("Pitch");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabAdapter myAdpter = new TabAdapter(getChildFragmentManager());
        viewPager.setAdapter(myAdpter);

        TabLayout tabLayout = v.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }


}
