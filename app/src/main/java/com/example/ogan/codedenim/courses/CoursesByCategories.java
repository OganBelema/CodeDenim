package com.example.ogan.codedenim.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ogan.codedenim.NetworkConnectivity;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.ServiceGenerator;
import com.example.ogan.codedenim.adapters.CourseAdapter;
import com.example.ogan.codedenim.gson.Course;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesByCategories extends AppCompatActivity {


    private int categoryId;
    private RecyclerView recyclerView;
    private CourseAdapter coursesByCategoriesAdapter;
    private ArrayList<Course> results;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_recyclerview_layout);

        recyclerView = findViewById(R.id.general_recyclerView);
        progressBar = findViewById(R.id.general_pr);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        categoryId = intent.getIntExtra("CategoryId", 0);
        String categoryName = intent.getStringExtra("CategoryName");

        if(getSupportActionBar() != null ){
            getSupportActionBar().setTitle("Courses in " + categoryName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getData();

        final SwipeRefreshLayout refreshLayout = findViewById(R.id.swipe_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                coursesByCategoriesAdapter = null;
                progressBar.setVisibility(View.VISIBLE);
                getData();
                refreshLayout.setRefreshing(false);
            }
        });

    }

    private void getData(){
        //GET apiMethods
        if (NetworkConnectivity.INSTANCE.checkNetworkConnecttion(getApplicationContext())) {
            ServiceGenerator.INSTANCE.getApiMethods().getCourses(categoryId).enqueue(new Callback<ArrayList<Course>>() {
                @Override
                public void onResponse(@NonNull Call<ArrayList<Course>> call, @Nullable Response<ArrayList<Course>> response) {
                    // System.out.println(response.body().apiMethods().get(0).toString());
                    if (response != null) {
                        System.out.println(response.message());
                        if (response.isSuccessful()) {

                            results = response.body();
                            coursesByCategoriesAdapter = new CourseAdapter(results);
                            recyclerView.setAdapter(coursesByCategoriesAdapter);
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);

                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ArrayList<Course>> call, @Nullable Throwable t) {

                    progressBar.setVisibility(View.GONE);
                    if (t!=null) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("error", t.getMessage());
                    }
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            NetworkConnectivity.INSTANCE.showNoInternetMessage(CoursesByCategories.this);
        }
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
