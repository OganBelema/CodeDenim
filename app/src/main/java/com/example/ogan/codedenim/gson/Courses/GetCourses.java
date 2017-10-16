package com.example.ogan.codedenim.gson.Courses;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ogan on 8/26/17.
 */

public interface GetCourses {
    @GET("api/Courses")
    Call<ArrayList<CourseApus>> getCourses();
}
