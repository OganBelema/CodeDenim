package com.example.ogan.codedenim

import com.example.ogan.codedenim.gson.ApiMethods
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit




/**
 * Created by belema on 9/11/17.
 */

object ServiceGenerator {

    private val BASE_URL = "https://codedenim.azurewebsites.net/"

    var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

    var gson = GsonBuilder()
            .setLenient()
            .create()

    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))

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

