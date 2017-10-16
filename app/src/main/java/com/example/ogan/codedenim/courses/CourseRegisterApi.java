package com.example.ogan.codedenim.courses;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by belema on 9/8/17.
 */

public interface CourseRegisterApi {
    @POST("api/StudentAssignedCourses/{courseId}")
    Call<ResponseBody> registerCorper(@Path("courseId") int courseId,@Body CourseRegister courseRegister);
}
