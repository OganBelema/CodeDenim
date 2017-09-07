package com.example.ogan.codedenim.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ogan.codedenim.Courses.CourseDetailActivity;
import com.example.ogan.codedenim.Gson.CourseGson.CoursesApi;
import com.example.ogan.codedenim.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ogan on 8/13/17.
 */

public class CoursesRvAdapter extends RecyclerView.Adapter<CoursesRvAdapter.CoursesVH> {

    Context context;
    private ArrayList<CoursesApi> courseResult;

    public CoursesRvAdapter(ArrayList<CoursesApi> myDataset) {

        courseResult = myDataset;
    }

    @Override
    public CoursesRvAdapter.CoursesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.my_courses_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CoursesVH vh = new CoursesVH(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(CoursesRvAdapter.CoursesVH holder, final int position) {

        final String courseName = courseResult.get(position).getCourseName();
        holder.courseName.setText(courseName);

        final String courseImageUrl = "https://codedenim.azurewebsites.net/MaterialUpload/" +courseResult.get(position).getFileLocation();

       Picasso.with(context).
                load(courseImageUrl).into(holder.courseImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseCategory = courseResult.get(position).getCourseCategory().getCategoryName();
                String courseCode = courseResult.get(position).getCourseCode();
                int expectedTime = courseResult.get(position).getExpectedTime();
                String courseDescription = courseResult.get(position).getCourseDescription();

                Intent intent = new Intent(context, CourseDetailActivity.class);
                intent.putExtra("courseName", courseName);
                intent.putExtra("courseDescription", courseDescription);
                intent.putExtra("courseCategory", courseCategory);
                intent.putExtra("courseCode", courseCode);
                intent.putExtra("expectedTime", expectedTime);
                intent.putExtra("courseImageUrl", courseImageUrl);
                context.startActivity(intent);

            }
        });

       /* holder.seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        }); */

    }

    @Override
    public int getItemCount() {
        return courseResult.size();
    }

    //View Holders

    public class CoursesVH extends RecyclerView.ViewHolder{

        TextView courseName;
        ImageView courseImage;
        CardView cardView;




        public CoursesVH(View view){
            super(view);
            context = view.getContext();
            courseName = (TextView) view.findViewById(R.id.courseName);
            courseImage = (ImageView) view.findViewById(R.id.courseImage);
            cardView = (CardView) view.findViewById(R.id.course_card);

        }
    }


}
