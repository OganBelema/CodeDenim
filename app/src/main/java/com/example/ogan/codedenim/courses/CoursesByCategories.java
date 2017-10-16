package com.example.ogan.codedenim.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ogan.codedenim.adapters.CourseAdapter;
import com.example.ogan.codedenim.gson.Categories.GetCourseByCategory;
import com.example.ogan.codedenim.gson.Courses.CourseApus;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesByCategories extends AppCompatActivity {


    int categoryId;
    GetCourseByCategory getCourses;
    RecyclerView recyclerView;
    CourseAdapter coursesByCategoriesAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<CourseApus> results;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_recyclerview_layout);

        recyclerView = (RecyclerView) findViewById(R.id.general_recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.general_pr);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        Intent intent = getIntent();
        categoryId = intent.getIntExtra("CategoryId", 0);
        String categoryName = intent.getStringExtra("categoryName");
        getSupportActionBar().setTitle("Courses in " + categoryName);
        if(getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getCourses = ServiceGenerator.createService(GetCourseByCategory.class);


        //GET courses
        Call<ArrayList<CourseApus>> call = getCourses.getCourses(categoryId);
        call.enqueue(new Callback<ArrayList<CourseApus>>() {
            @Override
            public void onResponse(Call<ArrayList<CourseApus>> call, Response<ArrayList<CourseApus>> response) {
               // System.out.println(response.body().getCourses().get(0).toString());
                System.out.println(response.message());
                if(response.isSuccessful()){

                    results = response.body();
                    coursesByCategoriesAdapter = new CourseAdapter(results);
                    recyclerView.setAdapter(coursesByCategoriesAdapter);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<CourseApus>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error", t.getMessage());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                CoursesByCategories.super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
