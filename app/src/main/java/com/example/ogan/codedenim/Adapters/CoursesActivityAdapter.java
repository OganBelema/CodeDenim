package com.example.ogan.codedenim.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ogan.codedenim.Courses.CourseDetailActivity;
import com.example.ogan.codedenim.Gson.Categories.Course;
import com.example.ogan.codedenim.Gson.CourseGson.CoursesApi;
import com.example.ogan.codedenim.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ogan on 8/28/17.
 */

public class CoursesActivityAdapter extends RecyclerView.Adapter<CoursesActivityAdapter.CoursesActivityHolder> {
    Context context;
    private ArrayList<CoursesApi> courses;

    public CoursesActivityAdapter(ArrayList<CoursesApi> courses){

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
        holder.courses.setText(courseName);
        holder.courseDescription.setText(courseDescription);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseCategory = courses.get(position).getCourseCategory().getCategoryName();
                String courseCode = courses.get(position).getCourseCode();
                int expectedTime = courses.get(position).getExpectedTime();
                List<Object> instructors = courses.get(position).getInstructors();

                Intent intent = new Intent(context, CourseDetailActivity.class);
                intent.putExtra("courseName", courseName);
                intent.putExtra("courseDescription", courseDescription);
                intent.putExtra("courseCategory", courseCategory);
                intent.putExtra("courseCode", courseCode);
                intent.putExtra("expectedTime", expectedTime);

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

        public CoursesActivityHolder(View view){
            super(view);
            context = view.getContext();
            courses = (TextView) view.findViewById(R.id.courses_txt);
            courseDescription = (TextView) view.findViewById(R.id.courses_description_txt);
            linearLayout = (LinearLayout) view.findViewById(R.id.course_linear_layout);
        }
    }
}
