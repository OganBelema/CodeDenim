package com.example.ogan.codedenim.courses;

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

import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.ServiceGenerator;
import com.example.ogan.codedenim.adapters.CategoriesRvAdaper;
import com.example.ogan.codedenim.gson.LearningPath;
import com.example.ogan.codedenim.sessionManagement.UserSessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCourses extends AppCompatActivity {

    private ArrayList<LearningPath> results;
    private CategoriesRvAdaper myAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_recyclerview_layout);

        // get user data from session
        HashMap<String, String> user = UserSessionManager.getUserDetails();

        // get email
        email = user.get(UserSessionManager.KEY_EMAIL);

        progressBar = findViewById(R.id.general_pr);
        recyclerView = findViewById(R.id.general_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myAdapter = null;
                progressBar.setVisibility(View.VISIBLE);
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        getData();

    }

    private void getData(){
        //GET apiMethods
        ServiceGenerator.INSTANCE.getApiMethods().getCourses(email).enqueue(new Callback<ArrayList<LearningPath>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<LearningPath>> call, @Nullable Response<ArrayList<LearningPath>> response) {
                if (response != null){
                    if(response.isSuccessful()){
                        System.out.println(response.message());
                        System.out.println(response.body());
                        results = response.body();
                        myAdapter = new CategoriesRvAdaper(results);
                        recyclerView.setAdapter(myAdapter);
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<LearningPath>> call, @Nullable Throwable t) {

                progressBar.setVisibility(View.GONE);
                if (t != null) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("error", t.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                MyCourses.super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
