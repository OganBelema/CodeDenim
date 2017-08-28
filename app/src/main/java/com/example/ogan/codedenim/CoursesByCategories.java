package com.example.ogan.codedenim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ogan.codedenim.Adapters.CoursesByCategoriesRv;
import com.example.ogan.codedenim.Adapters.CoursesRvAdapter;
import com.example.ogan.codedenim.Gson.Categories.CategoriesApus;
import com.example.ogan.codedenim.Gson.Categories.Course;
import com.example.ogan.codedenim.Gson.Categories.GetCourses;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoursesByCategories extends AppCompatActivity {

    private static final String url = "https://codedenim.azurewebsites.net/api/";
    int categoryId;
    GetCourses getCourses;
    RecyclerView recyclerView;
    CoursesByCategoriesRv coursesByCategoriesAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Course> results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_by_categories);

        recyclerView = (RecyclerView) findViewById(R.id.cbbbbb);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        Intent intent = getIntent();
        categoryId = intent.getIntExtra("CategoryId", 0);
        String categoryName = intent.getStringExtra("categoryName");
        getSupportActionBar().setTitle("Courses in " + categoryName);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).
                addConverterFactory(GsonConverterFactory.create()).build();

        getCourses = retrofit.create(GetCourses.class);


        //GET courses
        Call<CategoriesApus> call = getCourses.getCourse(categoryId);
        call.enqueue(new Callback<CategoriesApus>() {
            @Override
            public void onResponse(Call<CategoriesApus> call, Response<CategoriesApus> response) {
               // System.out.println(response.body().getCourses().get(0).toString());
                System.out.println(response.message());
                if(response.isSuccessful()){

                    results = response.body().getCourses();
                    coursesByCategoriesAdapter = new CoursesByCategoriesRv(results);
                    recyclerView.setAdapter(coursesByCategoriesAdapter);

                }
            }

            @Override
            public void onFailure(Call<CategoriesApus> call, Throwable t) {

                Log.e("error", t.getMessage());
            }
        });

    }
}
