package com.example.ogan.codedenim;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ogan.codedenim.adapters.CategoriesRvAdaper;
import com.example.ogan.codedenim.adapters.CoursesRvAdapter;
import com.example.ogan.codedenim.courses.CourseActivity;
import com.example.ogan.codedenim.gson.Categories.CategoryApus;
import com.example.ogan.codedenim.gson.Categories.GetCategories;
import com.example.ogan.codedenim.gson.Courses.CourseApus;
import com.example.ogan.codedenim.gson.Courses.GetCourses;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView coursesRv;
    RecyclerView newsRv;
    RecyclerView sponsorsRv;
    CoursesRvAdapter myAdapter;
    CategoriesRvAdaper categoriesRvAdaper;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager newsLayoutManager;
    LinearLayoutManager partnerLayoutManager;
    TextView seeMoreCourses;
    LinearLayout linearLayout;
    ProgressBar progressBar;
    GetCourses courses;
    ArrayList<CourseApus> results;
    SwipeRefreshLayout swipeRefreshLayout;

    GetCategories getCategories;
    ArrayList<CategoryApus> categoryApis;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_home, container, false);

        linearLayout = (LinearLayout) v.findViewById(R.id.linearView);
        progressBar = (ProgressBar) v.findViewById(R.id.loading_spinner);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.home_swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        seeMoreCourses = (TextView) v.findViewById(R.id.see_more_courses);
        seeMoreCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CourseActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        coursesRv = (RecyclerView) v.findViewById(R.id.rv_myCourses);
        newsRv = (RecyclerView) v.findViewById(R.id.rv_News);
        sponsorsRv = (RecyclerView) v.findViewById(R.id.rv_partners);
        linearLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);
        newsLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);
        partnerLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);
        newsRv.setLayoutManager(newsLayoutManager);
        coursesRv.setLayoutManager(linearLayoutManager);
        sponsorsRv.setLayoutManager(partnerLayoutManager);

        courses = ServiceGenerator.createService(GetCourses.class);

        getCategories = ServiceGenerator.createService(GetCategories.class);

        //GET courses
        Call<ArrayList<CourseApus>> call = courses.getCourses();
        call.enqueue(new Callback<ArrayList<CourseApus>>() {
            @Override
            public void onResponse(Call<ArrayList<CourseApus>> call, Response<ArrayList<CourseApus>> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    results = response.body();
                    myAdapter = new CoursesRvAdapter(results);
                    coursesRv.setAdapter(myAdapter);
                    sponsorsRv.setAdapter(myAdapter);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CourseApus>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(v.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", t.getMessage());
            }
        });

        //GET categories
        Call<ArrayList<CategoryApus>> categoriesCall = getCategories.getCategories();
        categoriesCall.enqueue(new Callback<ArrayList<CategoryApus>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryApus>> call, Response<ArrayList<CategoryApus>> response) {
                if(response.isSuccessful()){
                    categoryApis = response.body();
                    categoriesRvAdaper = new CategoriesRvAdaper(categoryApis);
                    newsRv.setAdapter(categoriesRvAdaper);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoryApus>> call, Throwable t) {

                Log.e("categories_error", t.getMessage());
            }
        });

        return v;
    }

}
