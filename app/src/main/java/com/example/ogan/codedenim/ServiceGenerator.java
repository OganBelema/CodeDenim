package com.example.ogan.codedenim;

import com.example.ogan.codedenim.gson.ApiMethods;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by belema on 9/11/17.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "https://codedenim.azurewebsites.net/";


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static ApiMethods apiMethods = retrofit.create(ApiMethods.class);

    /*public ApiMethods getApiMethods(){
        return apiMethods;
    }

    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    } */
}

