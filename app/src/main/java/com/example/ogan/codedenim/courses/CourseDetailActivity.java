package com.example.ogan.codedenim.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ogan.codedenim.ForumActivity;
import com.example.ogan.codedenim.NetworkConnectivity;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.ServiceGenerator;
import com.example.ogan.codedenim.sessionManagement.UserSessionManager;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDetailActivity extends AppCompatActivity {

    private String courseName;
    private int courseId;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        setTitle("Course Detail");

        // get user data from session
        HashMap<String, String> user = UserSessionManager.getUserDetails();

        // get email
        email = user.get(UserSessionManager.KEY_EMAIL);

        TextView mCourseName = findViewById(R.id.course_name);
        TextView mCourseDescription= findViewById(R.id.course_description);
        TextView mCourseCategory = findViewById(R.id.course_category);
        TextView mCourseCode = findViewById(R.id.course_code);
        TextView mExpectedTime = findViewById(R.id.course_expected_time);
        Button button = findViewById(R.id.button_enroll);
        ImageView imageView = findViewById(R.id.course_detail_img);

        Intent intent = getIntent();
        courseName = intent.getStringExtra("courseName");
        String courseDescription = intent.getStringExtra("courseDescription");
        String courseCategory = intent.getStringExtra("courseCategory");
        String courseCode = intent.getStringExtra("courseCode");
        int expectedTime = intent.getIntExtra("expectedTime",0);
        String courseImageUrl = intent.getStringExtra("courseImageUrl");
        courseId = intent.getIntExtra("courseId", 0);



        Picasso.with(getApplicationContext()).load(courseImageUrl).into(imageView);

        if(courseCategory == null){
            mCourseCategory.setVisibility(View.GONE);
        } else {
            mCourseCategory.setText("Course Category: " + courseCategory);
        }


        mCourseName.setText("Course Name: " + courseName);
        mCourseDescription.setText("Course Description: " + courseDescription);
        mCourseCode.setText("Course Code: " + courseCode);
        mExpectedTime.setText(String.valueOf("Course Length: " + expectedTime));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetworkConnectivity.INSTANCE.checkNetworkConnecttion(getApplicationContext())) {
                    getData();
                } else {

                    NetworkConnectivity.INSTANCE.showNoInternetMessage(CourseDetailActivity.this);
                }
            }
        });

        //forum button setup
        FloatingActionButton forumBtn = findViewById(R.id.forum_btn);
        forumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForumActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData(){
        ServiceGenerator.INSTANCE.getApiMethods().registerCorper(courseId, new CourseRegister(email, String.valueOf(courseId)))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @Nullable Response<ResponseBody> response) {
                        if (response != null) {
                            System.out.println(response.message());
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), ModuleActivity.class);
                                intent.putExtra("courseId", courseId);
                                intent.putExtra("courseName", courseName);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @Nullable Throwable t) {

                        //setContentView(R.layout.activity_course_detail);
                        if (t != null) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("error", t.getMessage());
                        }
                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                CourseDetailActivity.super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
