package com.example.ogan.codedenim.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ogan.codedenim.R
import com.example.ogan.codedenim.TopicContent
import com.example.ogan.codedenim.gson.Topic
import java.util.*

/**
 * Created by belema on 9/8/17.
 */

class TopicAdapter(private val topics: ArrayList<Topic>?) : RecyclerView.Adapter<TopicAdapter.TopicVH>() {

    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicVH {

        return TopicVH(LayoutInflater.from(
                parent.context).inflate(R.layout.course_by_category, parent, false))

    }

    override fun onBindViewHolder(holder: TopicVH, position: Int) {

        topics?.let {
            val topicName = topics[position].TopicName
            val expectedTime = topics[position].ExpectedTime.toString()
            val topicId = topics[position].TopicId!!
            holder.courses.text = context.resources.getString(R.string.topic_name, topicName)
            holder.courseDescription.text = context.resources.getString(R.string.topic_expected_time, expectedTime)
            holder.courseImage.visibility = View.GONE
            println(topicId)

            holder.linearLayout.setOnClickListener {
                val intent = Intent(context, TopicContent::class.java)
                intent.putExtra("topicId", topicId)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return topics?.size ?: 0
    }


    inner class TopicVH constructor(view: View) : RecyclerView.ViewHolder(view) {

         val courses: TextView
         val courseDescription: TextView
         val linearLayout: LinearLayout
         val courseImage: ImageView

        init {
            context = view.context
            courses = view.findViewById(R.id.courses_txt)
            courseDescription = view.findViewById(R.id.courses_description_txt)
            linearLayout = view.findViewById(R.id.course_linear_layout)
            courseImage = view.findViewById(R.id.course_image)
        }
    }
}
