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

import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.courses.CourseDetailActivity;
import com.example.ogan.codedenim.gson.Course;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ogan on 8/28/17.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CoursesActivityHolder> {
    private Context context;
    private ArrayList<Course> courses;

    public CourseAdapter(ArrayList<Course> courses){

        this.courses = courses;
    }


    @Override
    public CoursesActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CoursesActivityHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.course_by_category, parent, false));
    }

    @Override
    public void onBindViewHolder(final CoursesActivityHolder holder, int position) {

        final String courseName = courses.get(holder.getAdapterPosition()).getCourseName();
        final String courseDescription = courses.get(holder.getAdapterPosition()).getCourseDescription();

        final String courseImageUrl = "https://codedenim.azurewebsites.net/MaterialUpload/" +courses.get(position).getFileLocation();
        holder.courses.setText(courseName);
        holder.courseDescription.setText(courseDescription);
        Picasso.with(context).load(courseImageUrl).into(holder.courseImage);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseCode = courses.get(holder.getAdapterPosition()).getCourseCode();
                int expectedTime = courses.get(holder.getAdapterPosition()).getExpectedTime();
                int courseId = courses.get(holder.getAdapterPosition()).getCourseId();

                Intent intent = new Intent(context, CourseDetailActivity.class);
                intent.putExtra("courseName", courseName);
                intent.putExtra("courseDescription", courseDescription);
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

        CoursesActivityHolder(View view){
            super(view);
            context = view.getContext();
            courses = view.findViewById(R.id.courses_txt);
            courseDescription = view.findViewById(R.id.courses_description_txt);
            linearLayout = view.findViewById(R.id.course_linear_layout);
            courseImage = view.findViewById(R.id.course_image);
        }
    }
}
