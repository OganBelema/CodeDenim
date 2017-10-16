package com.example.ogan.codedenim.gson.Categories;

import com.example.ogan.codedenim.gson.Courses.CourseApus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by belema on 9/11/17.
 */

public interface GetCourseByCategory {
    @GET("api/Coursecategories/{id}")
    Call<ArrayList<CourseApus>> getCourses(@Path("id") int id);
}
