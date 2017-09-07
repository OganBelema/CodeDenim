package com.example.ogan.codedenim.Gson.CourseGson;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by belema on 9/6/17.
 */

public interface GetModule {
    @GET("modules")
    Call<ArrayList<Module>> getModuleList();
}
