package com.example.ogan.codedenim.Courses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ogan.codedenim.Adapters.ModuleAdapter;
import com.example.ogan.codedenim.Gson.CourseGson.Module;
import com.example.ogan.codedenim.Gson.Module.GetModule;
import com.example.ogan.codedenim.Gson.Module.ModuleApus;
import com.example.ogan.codedenim.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModuleActivity extends AppCompatActivity {

    private static final String url = "https://codedenim.azurewebsites.net/api/";
    ModuleAdapter moduleAdapter;
    GetModule getModule;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<ModuleApus> results;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        recyclerView = (RecyclerView) findViewById(R.id.module_RV);
        progressBar = (ProgressBar) findViewById(R.id.module_pr);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        String courseName = intent.getStringExtra("courseName");
        int courseId = intent.getIntExtra("courseId", 0);

        if(getSupportActionBar() != null ){
            getSupportActionBar().setTitle(courseName + " Modules");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).
                addConverterFactory(GsonConverterFactory.create()).build();

        getModule = retrofit.create(GetModule.class);


        //GET modules
        Call<ArrayList<ModuleApus>> call = getModule.getModuleList(courseId);
        call.enqueue(new Callback<ArrayList<ModuleApus>>() {
            @Override
            public void onResponse(Call<ArrayList<ModuleApus>> call, Response<ArrayList<ModuleApus>> response) {


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
            public void onFailure(Call<ArrayList<ModuleApus>> call, Throwable t) {

                Log.e("error", t.getMessage());
            }
        });

    }
}
