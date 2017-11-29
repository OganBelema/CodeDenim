package com.example.ogan.codedenim.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.TopicContent;
import com.example.ogan.codedenim.gson.Topic;

import java.util.ArrayList;

/**
 * Created by belema on 9/8/17.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicVH>{

    private Context context;
    private ArrayList<Topic> topics;

    public TopicAdapter(ArrayList<Topic> topics){

        this.topics = topics;
    }


    @Override
    public TopicVH onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TopicVH(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.course_by_category, parent, false));

    }

    @Override
    public void onBindViewHolder(TopicVH holder, final int position) {

        final String topicName = topics.get(position).getTopicName();
        final String expectedTime = String.valueOf(topics.get(position).getExpectedTime());
        final int topicId = topics.get(position).getTopicId();
        holder.courses.setText("Topic Name: " + topicName);
        holder.courseDescription.setText("Expected Time: " + expectedTime + " minutes");
        System.out.println(topicId);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TopicContent.class);
                intent.putExtra("topicId", topicId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }


    class TopicVH extends RecyclerView.ViewHolder{

        private TextView courses;
        private TextView courseDescription;
        private LinearLayout linearLayout;

        private TopicVH(View view){
            super(view);
            context = view.getContext();
            courses = view.findViewById(R.id.courses_txt);
            courseDescription = view.findViewById(R.id.courses_description_txt);
            linearLayout = view.findViewById(R.id.course_linear_layout);
        }
    }
}