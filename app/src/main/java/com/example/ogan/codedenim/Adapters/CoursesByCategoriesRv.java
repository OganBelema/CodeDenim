package com.example.ogan.codedenim.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ogan.codedenim.Courses.CourseDetailActivity;
import com.example.ogan.codedenim.Gson.Categories.CategoriesApus;
import com.example.ogan.codedenim.Gson.Categories.Course;
import com.example.ogan.codedenim.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ogan on 8/28/17.
 */

public class CoursesByCategoriesRv extends RecyclerView.Adapter<CoursesByCategoriesRv.CoursesByCategoriesVH> {

    Context context;
    private ArrayList<Course> coursesByCategory;

    public CoursesByCategoriesRv(ArrayList<Course> coursesByCategory){

        this.coursesByCategory = coursesByCategory;
    }


    @Override
    public CoursesByCategoriesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.course_by_category, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CoursesByCategoriesVH vh = new CoursesByCategoriesVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CoursesByCategoriesVH holder, final int position) {

        final String courseName = coursesByCategory.get(position).getCourseName();
        final String courseDescription = coursesByCategory.get(position).getCourseDescription();
        holder.courses.setText(courseName);
        holder.courseDescription.setText(courseDescription);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseImageUrl = "https://codedenim.azurewebsites.net/MaterialUpload/" +coursesByCategory.get(position).getFileLocation();

                //String courseCategory = coursesByCategory.get(position).g();
                String courseCode = coursesByCategory.get(position).getCourseCode();
                int expectedTime = coursesByCategory.get(position).getExpectedTime();

                Intent intent = new Intent(context, CourseDetailActivity.class);
                intent.putExtra("courseName", courseName);
                intent.putExtra("courseDescription", courseDescription);
                //intent.putExtra("courseCategory", courseCategory);
                intent.putExtra("courseCode", courseCode);
                intent.putExtra("expectedTime", expectedTime);
                intent.putExtra("courseImageUrl", courseImageUrl);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return coursesByCategory.size();
    }


    public class CoursesByCategoriesVH extends RecyclerView.ViewHolder{

        private TextView courses;
        private TextView courseDescription;
        private LinearLayout linearLayout;

        public CoursesByCategoriesVH(View view){
            super(view);
            context = view.getContext();
            courses = (TextView) view.findViewById(R.id.courses_txt);
            courseDescription = (TextView) view.findViewById(R.id.courses_description_txt);
            linearLayout = (LinearLayout) view.findViewById(R.id.course_linear_layout);
        }
    }

}
