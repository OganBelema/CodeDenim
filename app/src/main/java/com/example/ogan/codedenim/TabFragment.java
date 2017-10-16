package com.example.ogan.codedenim;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        TabAdapter myAdpter = new TabAdapter(getChildFragmentManager());
        viewPager.setAdapter(myAdpter);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }


}
