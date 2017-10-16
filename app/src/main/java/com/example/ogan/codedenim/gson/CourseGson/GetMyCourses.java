package com.example.ogan.codedenim.gson.CourseGson;

import com.example.ogan.codedenim.gson.Courses.CourseApus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by belema on 9/9/17.
 */

public interface GetMyCourses {

    @GET("api/Students/{email}")
    Call<ArrayList<CourseApus>> getCourses(@Path("email") String email);
}
