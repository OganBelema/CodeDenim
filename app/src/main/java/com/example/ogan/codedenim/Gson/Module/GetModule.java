package com.example.ogan.codedenim.Gson.Module;


import com.example.ogan.codedenim.Gson.CourseGson.Module;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by belema on 9/6/17.
 */

public interface GetModule {
    @GET("modules/{id}")
    Call<ArrayList<ModuleApus>> getModuleList(@Path("id") int id);
}
