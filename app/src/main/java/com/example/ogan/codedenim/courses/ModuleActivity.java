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

import com.example.ogan.codedenim.adapters.ModuleAdapter;
import com.example.ogan.codedenim.gson.Module.GetModule;
import com.example.ogan.codedenim.gson.Module.Module;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModuleActivity extends AppCompatActivity {

    ModuleAdapter moduleAdapter;
    GetModule getModule;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Module> results;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_recyclerview_layout);

        recyclerView = (RecyclerView) findViewById(R.id.general_recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.general_pr);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        String courseName = intent.getStringExtra("courseName");
        int courseId = intent.getIntExtra("courseId", 0);

        if(getSupportActionBar() != null ){
            getSupportActionBar().setTitle(courseName + " Modules");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        getModule = ServiceGenerator.createService(GetModule.class);


        //GET modules
        Call<ArrayList<Module>> call = getModule.getModuleList(courseId);
        call.enqueue(new Callback<ArrayList<Module>>() {
            @Override
            public void onResponse(Call<ArrayList<Module>> call, Response<ArrayList<Module>> response) {


                System.out.println(response.message());
                if(response.isSuccessful()){

                    results = response.body();
                    moduleAdapter = new ModuleAdapter(results);
                    recyclerView.setAdapter(moduleAdapter);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Module>> call, Throwable t) {

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
                ModuleActivity.super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
