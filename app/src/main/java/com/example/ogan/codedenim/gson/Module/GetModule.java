package com.example.ogan.codedenim.gson.Module;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by belema on 9/6/17.
 */

public interface GetModule {
    @GET("api/modules/{id}")
    Call<ArrayList<Module>> getModuleList(@Path("id") int id);
}
