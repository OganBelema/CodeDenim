package com.example.ogan.codedenim

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.ogan.codedenim.gson.ForumQuestion
import com.example.ogan.codedenim.sessionManagement.UserSessionManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostForumActivity : AppCompatActivity() {

    private lateinit var titleEt: EditText
    private lateinit var questionEt: EditText
    private  var email: String? = null
    private var courseId: Int? = null
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_forum)
        // get user data from session
        val user = UserSessionManager.getUserDetails()

        title = "Post Question"

        try {
            // get email
            email = user[UserSessionManager.KEY_EMAIL]
        } catch (e: Exception){
            e.printStackTrace()
        }

        try {
            courseId = intent.getStringExtra("courseId").toInt()
        } catch (e: Exception){
            courseId = 0
            e.printStackTrace()
        }

        progress = findViewById(R.id.post_pb)
        titleEt = findViewById(R.id.post_title)
        questionEt = findViewById(R.id.forum_question)
        val button = findViewById<Button>(R.id.post_btn)
        button.setOnClickListener {
            postQuestion()
        }

    }

    private fun postQuestion(){
        progress.visibility = View.VISIBLE

        val title = titleEt.text.toString().trim()
        val question = questionEt.text.toString().trim()

        val forumQuestion = ForumQuestion()
        forumQuestion.Title = title
        forumQuestion.QuestionName = question
        forumQuestion.CourseId = courseId

        ServiceGenerator.apiMethods.postQuestion(email, forumQuestion)
                .enqueue(object: Callback<ResponseBody>{
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        progress.visibility = View.GONE
                        if (response != null){
                            if (response.isSuccessful){
                                clearInput()
                                Toast.makeText(this@PostForumActivity, "Post successful", Toast.LENGTH_LONG)
                                        .show()
                            } else Toast.makeText(this@PostForumActivity, response.message(), Toast.LENGTH_LONG)
                                    .show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                        progress.visibility = View.GONE
                        Toast.makeText(this@PostForumActivity, "An error occurred. Please try again ", Toast.LENGTH_LONG)
                                .show()
                    }
                } )


    }

    private fun clearInput(){
        titleEt.text.clear()
        questionEt.text.clear()
    }
}
