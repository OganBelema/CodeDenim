package com.example.ogan.codedenim.Courses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ogan.codedenim.R;

public class CourseDetailActivity extends AppCompatActivity {

    private TextView courseName;
    private TextView courseDescription;
    private TextView courseCategory;
    private TextView courseCode;
    private TextView expectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        setTitle("Course Detail");

        courseName = (TextView) findViewById(R.id.course_name);
        courseDescription = (TextView) findViewById(R.id.course_description);
        courseCategory = (TextView) findViewById(R.id.course_category);
        courseCode = (TextView) findViewById(R.id.course_code);
        expectedTime = (TextView) findViewById(R.id.course_expected_time);

        Intent intent = getIntent();
        String courseName = intent.getStringExtra("courseName");
        String courseDescription = intent.getStringExtra("courseDescription");
        String courseCategory = intent.getStringExtra("courseCategory");
        String courseCode = intent.getStringExtra("courseCode");
        int expectedTime = intent.getIntExtra("expectedTime",0);

        this.courseName.setText("Course Name: " + courseName);
        this.courseDescription.setText("Course Description: " + courseDescription);
        this.courseCategory.setText("Course Category: " + courseCategory);
        this.courseCode.setText("Course Code: " + courseCode);
        this.expectedTime.setText(String.valueOf("Course Length: " + expectedTime));

    }
}
