package com.example.ogan.codedenim.gson

import com.example.ogan.codedenim.courses.CourseRegister
import com.example.ogan.codedenim.user.Corp
import com.example.ogan.codedenim.user.Student
import com.example.ogan.codedenim.user.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiMethods {

    @get:GET("api/Courses")
    val courses: Call<ArrayList<Course>>

    @POST("api/accountapi/registercorper")
    fun registerCorper(@Body corp: Corp): Call<ResponseBody>

    @POST("api/AccountApi/RegisterUnderGraduate")
    fun registerStudent(@Body student: Student): Call<ResponseBody>

    @POST("api/AccountApi/OtherStudent")
    fun registerOtherStudent(@Body user: User): Call<ResponseBody>

    @FormUrlEncoded
    @POST("token")
    fun loginCorper(@Field("username") username: String, @Field("password") password: String, @Field("grant_type") grant_type: String): Call<ResponseBody>

    @GET("api/CourseCategories/CourseCategorys")
    fun getCategories(@Query("email") email: String?): Call<ArrayList<LearningPath>>

    @GET("api/Coursecategories/CourseCategory")
    fun getCourses(@Query("id") id: Int): Call<ArrayList<Course>>

    @GET("api/CourseCategories/MyCourses")
    fun getCourses(@Query("email") email: String?): Call<ArrayList<LearningPath>>

    @GET("api/modules/{id}")
    fun getModuleList(@Path("id") id: Int): Call<ArrayList<Module>>

    @GET("api/topics/{id}")
    fun getTopicList(@Path("id") id: Int): Call<ArrayList<Topic>>

    @POST("api/StudentAssignedCourses/{courseId}")
    fun registerStudent(@Path("courseId") courseId: Int, @Body courseRegister: CourseRegister): Call<ResponseBody>

    @GET("api/topicmaterialuploads")
    fun getMaterialUpload(@Query("id") topicID: String): Call<ArrayList<MaterialUpload>>

    @POST("api/CourseCategories/CheckPayment")
    fun checkPayment(@Body body: CheckPayment): Call<PaymentStatus>

    @GET("api/forumquestions")
    fun getForumQuestions(@Query("id") courseId: String?): Call<ArrayList<ForumQuestion>>

    @POST("api/forumquestions")
    fun postQuestion(@Query("email") email: String?, @Body body: ForumQuestion ): Call<ResponseBody>

    @GET("api/forumanswers")
    fun getAnswers(@Query("id") questionId: String?): Call<ArrayList<ForumAnswer>>

    @POST("api/forumanswers")
    fun postAnswer(@Query("email") email: String?, @Body body: ForumAnswer): Call<ResponseBody>

}
