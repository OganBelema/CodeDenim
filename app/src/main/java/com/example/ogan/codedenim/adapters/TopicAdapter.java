package com.example.ogan.codedenim.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ogan.codedenim.gson.Module.Topic;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.TopicContent;

import java.util.ArrayList;

/**
 * Created by belema on 9/8/17.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicVH>{

    Context context;
    private ArrayList<Topic> topics;

    public TopicAdapter(ArrayList<Topic> topics){

        this.topics = topics;
    }


    @Override
    public TopicVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.course_by_category, parent, false);
        // set the view's size, margins, paddings and layout parameters
        TopicVH vh = new TopicVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TopicVH holder, final int position) {

        final String topicName = topics.get(position).getTopicName();
        final String expectedTime = topics.get(position).getExpectedTime().toString();
        holder.courses.setText("Topic Name: " + topicName);
        holder.courseDescription.setText("Expected Time: " + expectedTime + " minutes");

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TopicContent.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }


    public class TopicVH extends RecyclerView.ViewHolder{

        private TextView courses;
        private TextView courseDescription;
        private LinearLayout linearLayout;

        public TopicVH(View view){
            super(view);
            context = view.getContext();
            courses = (TextView) view.findViewById(R.id.courses_txt);
            courseDescription = (TextView) view.findViewById(R.id.courses_description_txt);
            linearLayout = (LinearLayout) view.findViewById(R.id.course_linear_layout);
        }
    }
}
