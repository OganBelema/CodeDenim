package com.example.ogan.codedenim.login;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ogan on 8/29/17.
 */

public interface CorperLoginApi {

    @FormUrlEncoded
    @POST("token")
    Call<ResponseBody> loginCorper(@Field("username") String username, @Field("password") String password, @Field("grant_type") String grant_type);
}
