package com.example.ogan.codedenim.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ogan.codedenim.CustomViewPager;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.adapters.LessonTabAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class LessonFragment extends Fragment {


    public LessonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lesson, container, false);
        CustomViewPager viewPager = v.findViewById(R.id.lesson_viewpager);
        LessonTabAdapter myAdpter = new LessonTabAdapter(getChildFragmentManager());
        viewPager.setPagingEnabled(false);


        viewPager.setAdapter(myAdpter);


        TabLayout tabLayout = v.findViewById(R.id.lesson_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }

}
