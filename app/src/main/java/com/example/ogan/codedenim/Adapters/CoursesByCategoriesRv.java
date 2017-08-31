package com.example.ogan.codedenim.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(CoursesByCategoriesVH holder, int position) {

        String courseName = coursesByCategory.get(position).getCourseName();
        String courseDescription = coursesByCategory.get(position).getCourseDescription();
        holder.courses.setText(courseName);
        holder.courseDescription.setText(courseDescription);
    }

    @Override
    public int getItemCount() {
        return coursesByCategory.size();
    }


    public class CoursesByCategoriesVH extends RecyclerView.ViewHolder{

        private TextView courses;
        private TextView courseDescription;

        public CoursesByCategoriesVH(View view){
            super(view);
            context = view.getContext();
            courses = (TextView) view.findViewById(R.id.courses_txt);
            courseDescription = (TextView) view.findViewById(R.id.courses_description_txt);
        }
    }

}
