package com.example.ogan.codedenim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ogan.codedenim.courses.CoursesByCategories;
import com.squareup.picasso.Picasso;

public class LearningPathDetailActivity extends AppCompatActivity {

    private String studentType;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_path_detail);

        ImageView image = findViewById(R.id.learningPathPic_img);
        TextView name = findViewById(R.id.learningPathName_tv);
        TextView description = findViewById(R.id.learningPathDescription_tv);
        TextView amount = findViewById(R.id.learningPathPrice_tv);
        Button enrollBtn = findViewById(R.id.learningPath_enroll_btn);

        Intent intent = getIntent();
        String imagePath = intent.getStringExtra("ImageLocation");
        final String learningPathName = intent.getStringExtra("CategoryName");
        String learningPathDesc = intent.getStringExtra("CategoryDescription");
        String learningPathPrice = intent.getStringExtra("CategoryPrice");
        studentType = intent.getStringExtra("StudentType");
        categoryId = intent.getIntExtra("CategoryId", 0);
        setTitle(learningPathName);

        Picasso.with(getApplicationContext()).load(imagePath).into(image);
        name.setText(learningPathName);
        description.setText(getResources().getString(R.string.learning_path_desc, learningPathDesc));
        amount.setText(getResources().getString(R.string.learning_path_price, learningPathPrice));
        if (studentType.equals("Corper")){
            enrollBtn.setText("Enroll");
        }

        enrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CoursesByCategories.class);
                intent.putExtra("CategoryId", categoryId);
                intent.putExtra("CategoryName", learningPathName);
                startActivity(intent);
            }
        });
    }
}
