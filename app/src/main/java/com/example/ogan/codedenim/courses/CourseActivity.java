package com.example.ogan.codedenim.courses;

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
import com.example.ogan.codedenim.gson.Courses.CourseApus;
import com.example.ogan.codedenim.gson.Courses.GetCourses;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {

    private static final String url = "https://codedenim.azurewebsites.net/api/";
    GetCourses courses;
    ArrayList<CourseApus> results;
    CourseAdapter myAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_recyclerview_layout);
        setTitle("Courses");

        progressBar = (ProgressBar) findViewById(R.id.general_pr);
        recyclerView = (RecyclerView) findViewById(R.id.general_recyclerView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);




        courses = ServiceGenerator.createService(GetCourses.class);

        //GET courses
        Call<ArrayList<CourseApus>> call = courses.getCourses();
        call.enqueue(new Callback<ArrayList<CourseApus>>() {
            @Override
            public void onResponse(Call<ArrayList<CourseApus>> call, Response<ArrayList<CourseApus>> response) {
                if(response.isSuccessful()){
                    results = response.body();
                    myAdapter = new CourseAdapter(results);
                    recyclerView.setAdapter(myAdapter);
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
                CourseActivity.super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
