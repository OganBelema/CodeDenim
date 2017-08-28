package com.example.ogan.codedenim.Gson.Categories;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ogan on 8/28/17.
 */

public interface GetCourses {

    @GET("Coursecategories/{id}")
    Call<CategoriesApus> getCourse(@Path("id") int id);
}
