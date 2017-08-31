package com.example.ogan.codedenim.Courses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ogan.codedenim.Adapters.CoursesActivityAdapter;
import com.example.ogan.codedenim.Adapters.CoursesRvAdapter;
import com.example.ogan.codedenim.Gson.CourseGson.CoursesApi;
import com.example.ogan.codedenim.Gson.CourseGson.GetCourses;
import com.example.ogan.codedenim.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseActivity extends AppCompatActivity {

    private static final String url = "https://codedenim.azurewebsites.net/api/";
    GetCourses courses;
    ArrayList<CoursesApi> results;
    CoursesActivityAdapter myAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        setTitle("Courses");

        progressBar = (ProgressBar) findViewById(R.id.pr_courses);
        recyclerView = (RecyclerView) findViewById(R.id.courses_rv);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).
                addConverterFactory(GsonConverterFactory.create()).build();

        courses = retrofit.create(GetCourses.class);

        //GET courses
        Call<ArrayList<CoursesApi>> call = courses.getCourses();
        call.enqueue(new Callback<ArrayList<CoursesApi>>() {
            @Override
            public void onResponse(Call<ArrayList<CoursesApi>> call, Response<ArrayList<CoursesApi>> response) {
                if(response.isSuccessful()){
                    results = response.body();
                    myAdapter = new CoursesActivityAdapter(results);
                    recyclerView.setAdapter(myAdapter);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CoursesApi>> call, Throwable t) {

                Log.e("error", t.getMessage());
            }
        });
    }
}
