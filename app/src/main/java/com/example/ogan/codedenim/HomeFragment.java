package com.example.ogan.codedenim;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ogan.codedenim.Adapters.CategoriesRvAdaper;
import com.example.ogan.codedenim.Adapters.CoursesRvAdapter;
import com.example.ogan.codedenim.Courses.CourseActivity;
import com.example.ogan.codedenim.Gson.Categories.CategoriesApus;
import com.example.ogan.codedenim.Gson.Categories.GetCategories;
import com.example.ogan.codedenim.Gson.CourseGson.CoursesApi;
import com.example.ogan.codedenim.Gson.CourseGson.GetCourses;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
    ArrayList<CoursesApi> results;

    GetCategories getCategories;
    ArrayList<CategoriesApus> categoryApis;

    private static final String url = "https://codedenim.azurewebsites.net/api/";




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






        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).
                addConverterFactory(GsonConverterFactory.create()).build();

        courses = retrofit.create(GetCourses.class);

        getCategories = retrofit.create(GetCategories.class);

        //GET courses
        Call<ArrayList<CoursesApi>> call = courses.getCourses();
        call.enqueue(new Callback<ArrayList<CoursesApi>>() {
            @Override
            public void onResponse(Call<ArrayList<CoursesApi>> call, Response<ArrayList<CoursesApi>> response) {
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
            public void onFailure(Call<ArrayList<CoursesApi>> call, Throwable t) {

                Log.e("error", t.getMessage());
            }
        });

        //GET categories
        Call<ArrayList<CategoriesApus>> categoriesCall = getCategories.getCategories();
        categoriesCall.enqueue(new Callback<ArrayList<CategoriesApus>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoriesApus>> call, Response<ArrayList<CategoriesApus>> response) {
                if(response.isSuccessful()){
                    categoryApis = response.body();
                    categoriesRvAdaper = new CategoriesRvAdaper(categoryApis);
                    newsRv.setAdapter(categoriesRvAdaper);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoriesApus>> call, Throwable t) {

                Log.e("categories_error", t.getMessage());
            }
        });

        return v;
    }

}
