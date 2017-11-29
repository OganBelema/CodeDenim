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
import com.example.ogan.codedenim.adapters.TopicAdapter;
import com.example.ogan.codedenim.gson.Topic;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicActivity extends AppCompatActivity {

    private TopicAdapter topicAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Topic> results;
    private ProgressBar progressBar;
    private int moduleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_recyclerview_layout);

        recyclerView = findViewById(R.id.general_recyclerView);
        progressBar = findViewById(R.id.general_pr);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        moduleId = intent.getIntExtra("moduleId", 0);

        getData();

        final SwipeRefreshLayout refreshLayout = findViewById(R.id.swipe_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                topicAdapter = null;
                progressBar.setVisibility(View.VISIBLE);
                getData();
                refreshLayout.setRefreshing(false);
            }
        });
    }


    private void getData(){
        if (NetworkConnectivity.INSTANCE.checkNetworkConnecttion(getApplicationContext())) {
            //GET modules
            ServiceGenerator.INSTANCE.getApiMethods().getTopicList(moduleId).enqueue(new Callback<ArrayList<Topic>>() {
                @Override
                public void onResponse(@NonNull Call<ArrayList<Topic>> call, @Nullable Response<ArrayList<Topic>> response) {

                    if (response != null) {
                        System.out.println(response.message());
                        if (response.isSuccessful()) {

                            results = response.body();
                            topicAdapter = new TopicAdapter(results);
                            recyclerView.setAdapter(topicAdapter);
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);

                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ArrayList<Topic>> call, @Nullable Throwable t) {

                    progressBar.setVisibility(View.GONE);
                    if (t != null) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("error", t.getMessage());
                    }
                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            NetworkConnectivity.INSTANCE.showNoInternetMessage(TopicActivity.this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                TopicActivity.super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
