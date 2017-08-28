package com.example.ogan.codedenim.Gson.Categories;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by ogan on 8/26/17.
 */

public interface GetCategories {

    @GET("Coursecategories")
    Call<ArrayList<CategoriesApus>> getCategories();
}
