package com.example.ogan.codedenim.gson;

import com.example.ogan.codedenim.courses.CourseRegister;
import com.example.ogan.codedenim.user.Corper;
import com.example.ogan.codedenim.user.Student;
import com.example.ogan.codedenim.user.User;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiMethods {

    @POST("api/accountapi/registercorper")
    Call<ResponseBody> registerCorper(@Body Corper corper);

    @POST("api/AccountApi/RegisterUnderGraduate")
    Call<ResponseBody> registerStudent(@Body Student student);

    @POST("api/AccountApi/OtherStudent")
    Call<ResponseBody> registerOtherStudent(@Body User user);

    @FormUrlEncoded
    @POST("token")
    Call<ResponseBody> loginCorper(@Field("username") String username, @Field("password") String password, @Field("grant_type") String grant_type);

    @GET("api/CourseCategories/CourseCategorys")
    Call<ArrayList<LearningPath>> getCategories(@Query("email") String email);

    @GET("api/Coursecategories/CourseCategory")
    Call<ArrayList<Course>> getCourses(@Query("id") int id);

    @GET("api/CourseCategories/MyCourses")
    Call<ArrayList<LearningPath>> getCourses(@Query("email") String email);

    @GET("api/Courses")
    Call<ArrayList<Course>> getCourses();

    @GET("api/modules/{id}")
    Call<ArrayList<Module>> getModuleList(@Path("id") int id);

    @GET("api/topics/{id}")
    Call<ArrayList<Topic>> getTopicList(@Path("id") int id);

    @POST("api/StudentAssignedCourses/{courseId}")
    Call<ResponseBody> registerCorper(@Path("courseId") int courseId,@Body CourseRegister courseRegister);

    @GET("api/topicmaterialuploads")
    Call<ArrayList<MaterialUpload>> getMaterialUpload(@Query("id") String topicID);

}
