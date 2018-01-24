package com.example.ogan.codedenim.courses

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.ogan.codedenim.R
import com.example.ogan.codedenim.ServiceGenerator
import com.example.ogan.codedenim.gson.ForumAnswer
import com.example.ogan.codedenim.sessionManagement.UserSessionManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostReplyActivity : AppCompatActivity() {

    private lateinit var questionEt: EditText
    private var questionId: String? = null
    private var email: String? = null
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_reply)

        val user = UserSessionManager.getUserDetails()

        title = "Post Answer"
        try {
            // get email
            email = user[UserSessionManager.KEY_EMAIL]
        } catch (e: Exception) {
            e.printStackTrace()
        }

        questionId = intent.getStringExtra("questionId")

        progress = findViewById(R.id.post_reply_pb)
        questionEt = findViewById(R.id.forum_answer)
        val button = findViewById<Button>(R.id.post_reply__btn)
        button.setOnClickListener {
            postQuestion()
        }

    }

    private fun postQuestion(){
        val answer = questionEt.text.toString().trim()

        if (answer.isNullOrEmpty()){
            questionEt.error = "Can't be empty"
            questionEt.requestFocus()
            return
        }

        progress.visibility = View.VISIBLE

        val forumAnswer = ForumAnswer()
        forumAnswer.Answer = answer
        forumAnswer.ForumQuestionId = questionId?.toInt()

        ServiceGenerator.apiMethods.postAnswer(email, forumAnswer)
                .enqueue(object: Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        progress.visibility = View.GONE
                        if (response != null){
                            if (response.isSuccessful){
                                clearInput()
                                Toast.makeText(this@PostReplyActivity, "Post successful", Toast.LENGTH_LONG)
                                        .show()
                            } else Toast.makeText(this@PostReplyActivity, response.message(), Toast.LENGTH_LONG)
                                    .show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                        progress.visibility = View.GONE
                        Toast.makeText(this@PostReplyActivity, "An error occurred. Please try again ", Toast.LENGTH_LONG)
                                .show()
                    }
                } )


    }

    private fun clearInput(){
        questionEt.text.clear()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                super@PostReplyActivity.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
