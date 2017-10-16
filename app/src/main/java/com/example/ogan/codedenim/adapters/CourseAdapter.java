package com.example.ogan.codedenim.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ogan.codedenim.courses.CourseDetailActivity;
import com.example.ogan.codedenim.gson.Courses.CourseApus;
import com.example.ogan.codedenim.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ogan on 8/28/17.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CoursesActivityHolder> {
    Context context;
    private ArrayList<CourseApus> courses;

    public CourseAdapter(ArrayList<CourseApus> courses){

        this.courses = courses;
    }


    @Override
    public CoursesActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.course_by_category, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CoursesActivityHolder vh = new CoursesActivityHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CoursesActivityHolder holder, final int position) {

        final String courseName = courses.get(position).getCourseName();
        final String courseDescription = courses.get(position).getCourseDescription();

        final String courseImageUrl = "https://codedenim.azurewebsites.net/MaterialUpload/" +courses.get(position).getFileLocation();
        holder.courses.setText(courseName);
        holder.courseDescription.setText(courseDescription);
        Picasso.with(context).load(courseImageUrl).into(holder.courseImage);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseImageUrl = "https://codedenim.azurewebsites.net/MaterialUpload/" +courses.get(position).getFileLocation();

                String courseCategory = courses.get(position).getCategoryName();
                String courseCode = courses.get(position).getCourseCode();
                int expectedTime = courses.get(position).getExpectedTime();
                int courseId = courses.get(position).getCourseId();

                Intent intent = new Intent(context, CourseDetailActivity.class);
                intent.putExtra("courseName", courseName);
                intent.putExtra("courseDescription", courseDescription);
                intent.putExtra("courseCategory", courseCategory);
                intent.putExtra("courseCode", courseCode);
                intent.putExtra("expectedTime", expectedTime);
                intent.putExtra("courseImageUrl", courseImageUrl);
                intent.putExtra("courseId", courseId);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class CoursesActivityHolder extends RecyclerView.ViewHolder{

        private TextView courses;
        private TextView courseDescription;
        private LinearLayout linearLayout;
        private ImageView courseImage;

        public CoursesActivityHolder(View view){
            super(view);
            context = view.getContext();
            courses = (TextView) view.findViewById(R.id.courses_txt);
            courseDescription = (TextView) view.findViewById(R.id.courses_description_txt);
            linearLayout = (LinearLayout) view.findViewById(R.id.course_linear_layout);
            courseImage = view.findViewById(R.id.course_image);
        }
    }
}
