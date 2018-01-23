package com.example.ogan.codedenim.courses

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.ogan.codedenim.ForumActivity
import com.example.ogan.codedenim.MyUtilClass
import com.example.ogan.codedenim.R
import com.example.ogan.codedenim.ServiceGenerator
import com.example.ogan.codedenim.sessionManagement.UserSessionManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_course_detail.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseDetailActivity : AppCompatActivity() {

    private var courseName: String? = null
    private var courseId: Int = 0
    private var email: String? = null
    private lateinit var progressBar: ProgressBar
    private lateinit var courseDetailView: LinearLayout
    private lateinit var forumBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)
        title = "Course Detail"

        // get user data from session
        val user = UserSessionManager.getUserDetails()

        try {
            // get email
            email = user[UserSessionManager.KEY_EMAIL]
        } catch (e: Exception){
            e.printStackTrace()
        }


        val mCourseName = findViewById<TextView>(R.id.course_name)
        val mCourseDescription = findViewById<TextView>(R.id.course_description)
        val mCourseCategory = findViewById<TextView>(R.id.course_category)
        val mCourseCode = findViewById<TextView>(R.id.course_code)
        val mExpectedTime = findViewById<TextView>(R.id.course_expected_time)
        val button = findViewById<Button>(R.id.button_enroll)
        val imageView = findViewById<ImageView>(R.id.course_detail_img)
        progressBar = findViewById(R.id.cd_progressbar)
        courseDetailView = findViewById(R.id.course_detail)
        //forum button setup
        forumBtn = findViewById(R.id.forum_btn)

        val intent = intent
        courseName = intent.getStringExtra("courseName")
        val courseDescription = intent.getStringExtra("courseDescription")?: "No description"
        val courseCategory = intent.getStringExtra("courseCategory")
        val courseCode = intent.getStringExtra("courseCode")?: "No course code"
        val expectedTime = intent.getIntExtra("expectedTime", 0)
        val courseImageUrl = intent.getStringExtra("courseImageUrl")
        courseId = intent.getIntExtra("courseId", 0)
        println(courseId)



        Picasso.with(applicationContext).load(courseImageUrl).into(imageView)

        if (courseCategory == null) {
            mCourseCategory.visibility = View.GONE
        } else {
            mCourseCategory.text = resources.getString(R.string.course_category, courseCategory)
        }

        mCourseName.text = resources.getString(R.string.course_name, courseName)
        mCourseDescription.text = resources.getString(R.string.course_description, courseDescription)
        mCourseCode.text = resources.getString(R.string.course_code, courseCode)
        mExpectedTime.text = resources.getString(R.string.course_length, expectedTime)

        button.setOnClickListener {
            if (MyUtilClass.checkNetworkConnection(applicationContext)) {
                getData()
            } else {
                MyUtilClass.showNoInternetMessage(this@CourseDetailActivity)
            }
        }

        forumBtn.setOnClickListener {
            val mIntent = Intent(applicationContext, ForumActivity::class.java)
            mIntent.putExtra("courseId", courseId)
            startActivity(mIntent)
        }
    }

    private fun getData() {
        startLoading()
        ServiceGenerator.apiMethods.registerStudent(courseId, CourseRegister(email, courseId.toString()))
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?) {
                        doneLoading()
                        if (response != null) {
                            println(response.message())
                            println("response body: ${response.body()}")
                            if (response.isSuccessful) {
                                println(response.body())
                                val intent = Intent(applicationContext, ModuleActivity::class.java)
                                intent.putExtra("courseId", courseId)
                                intent.putExtra("courseName", courseName)
                                startActivity(intent)
                                finish()
                            } else MyUtilClass.showAlert(this@CourseDetailActivity, "Sorry, an error occurred. Tap button to retry")
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
                        doneLoading()
                        MyUtilClass.showAlert(this@CourseDetailActivity, "Sorry, an error occurred. Tap button to retry")
                    }
                })
    }

    private fun doneLoading(){
        progressBar.visibility = View.GONE
        courseDetailView.visibility = View.VISIBLE
        forumBtn.visibility = View.VISIBLE
        button_enroll.visibility = View.VISIBLE
    }

    private fun startLoading(){
        courseDetailView.visibility = View.INVISIBLE
        forumBtn.visibility = View.INVISIBLE
        button_enroll.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                super@CourseDetailActivity.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
