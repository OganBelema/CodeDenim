package com.example.ogan.codedenim.Register;

import com.example.ogan.codedenim.Corper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ogan on 8/27/17.
 */

public interface CorperRegisterApi {

    @POST("accountapi/registercorper")
    Call<ResponseBody> registerCorper(@Body Corper corper);
}
