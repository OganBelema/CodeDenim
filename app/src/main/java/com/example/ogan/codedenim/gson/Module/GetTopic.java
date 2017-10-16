package com.example.ogan.codedenim.gson.Module;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by belema on 9/8/17.
 */

public interface GetTopic {
    @GET("api/topics/{id}")
    Call<ArrayList<Topic>> getTopicList(@Path("id") int id);
}
