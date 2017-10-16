package com.example.ogan.codedenim.gson.Categories;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ogan on 8/26/17.
 */

public interface GetCategories {

    @GET("api/Coursecategories")
    Call<ArrayList<CategoryApus>> getCategories();
}
