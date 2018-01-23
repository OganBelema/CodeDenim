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
import com.example.ogan.codedenim.courses.CourseDetailActivity
import com.example.ogan.codedenim.gson.Course
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by ogan on 8/28/17.
 */

class CourseAdapter(private val courses: ArrayList<Course>?) : RecyclerView.Adapter<CourseAdapter.CoursesActivityHolder>() {
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesActivityHolder {
        return CoursesActivityHolder(LayoutInflater.from(
                parent.context).inflate(R.layout.course_by_category, parent, false))
    }

    override fun onBindViewHolder(holder: CoursesActivityHolder, position: Int) {

        courses?.let {
            val courseName = courses[holder.adapterPosition].CourseName
            val courseDescription = courses[holder.adapterPosition].CourseDescription

            val courseImageUrl = "https://codedenim.azurewebsites.net/MaterialUpload/${courses[position].FileLocation}"
            holder.courses.text = courseName
            holder.courseDescription.text = courseDescription
            Picasso.with(context).load(courseImageUrl).into(holder.courseImage)

            holder.linearLayout.setOnClickListener {
                val courseCode = courses[holder.adapterPosition].CourseCode
                val expectedTime = courses[holder.adapterPosition].ExpectedTime
                val courseId = courses[holder.adapterPosition].CourseId

                val intent = Intent(context, CourseDetailActivity::class.java)
                intent.putExtra("courseName", courseName)
                intent.putExtra("courseDescription", courseDescription)
                intent.putExtra("courseCode", courseCode)
                intent.putExtra("expectedTime", expectedTime)
                intent.putExtra("courseImageUrl", courseImageUrl)
                intent.putExtra("courseId", courseId)

                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return courses?.size ?: 0
    }

    inner class CoursesActivityHolder(view: View) : RecyclerView.ViewHolder(view) {

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
