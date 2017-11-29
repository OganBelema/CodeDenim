package com.example.ogan.codedenim

import com.example.ogan.codedenim.gson.ApiMethods

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by belema on 9/11/17.
 */

object ServiceGenerator {

    private val BASE_URL = "https://codedenim.azurewebsites.net/"


    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = builder.build()

    val apiMethods = retrofit.create(ApiMethods::class.java)

    /*public ApiMethods getApiMethods(){
        return apiMethods;
    }

    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    } */
}

