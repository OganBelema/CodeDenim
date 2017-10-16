package com.example.ogan.codedenim.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView courseName;
    private TextView courseDescription;
    private TextView courseCategory;
    private TextView courseCode;
    private TextView expectedTime;

    CourseRegisterApi corperRegisterApi;

    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        setTitle("Course Detail");

        // Session class instance
        session = new UserSessionManager(getApplicationContext());

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // get email
        final String email = user.get(UserSessionManager.KEY_EMAIL);

        courseName = (TextView) findViewById(R.id.course_name);
        courseDescription = (TextView) findViewById(R.id.course_description);
        courseCategory = (TextView) findViewById(R.id.course_category);
        courseCode = (TextView) findViewById(R.id.course_code);
        expectedTime = (TextView) findViewById(R.id.course_expected_time);
        Button button = (Button) findViewById(R.id.button_enroll);
        ImageView imageView = (ImageView) findViewById(R.id.course_detail_img);

        Intent intent = getIntent();
        final String courseName = intent.getStringExtra("courseName");
        String courseDescription = intent.getStringExtra("courseDescription");
        String courseCategory = intent.getStringExtra("courseCategory");
        String courseCode = intent.getStringExtra("courseCode");
        int expectedTime = intent.getIntExtra("expectedTime",0);
        String courseImageUrl = intent.getStringExtra("courseImageUrl");
        final int courseId = intent.getIntExtra("courseId", 0);



        Picasso.with(getApplicationContext()).load(courseImageUrl).into(imageView);

        if(courseCategory == null){
            this.courseCategory.setVisibility(View.GONE);
        } else {
            this.courseCategory.setText("Course Category: " + courseCategory);
        }


        this.courseName.setText("Course Name: " + courseName);
        this.courseDescription.setText("Course Description: " + courseDescription);
        this.courseCode.setText("Course Code: " + courseCode);
        this.expectedTime.setText(String.valueOf("Course Length: " + expectedTime));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.progressbar);


                corperRegisterApi = ServiceGenerator.createService(CourseRegisterApi.class);

                Call<ResponseBody> register = corperRegisterApi.registerCorper(courseId, new CourseRegister(email, String.valueOf(courseId)));
                register.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        System.out.println(response.message());
                        if(response.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), ModuleActivity.class);
                            intent.putExtra("courseId", courseId);
                            intent.putExtra("courseName", courseName);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        setContentView(R.layout.activity_course_detail);
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("error", t.getMessage());

                    }
                });


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
