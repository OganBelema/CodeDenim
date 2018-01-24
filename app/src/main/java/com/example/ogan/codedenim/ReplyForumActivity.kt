package com.example.ogan.codedenim

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.ogan.codedenim.adapters.ForumReplyAdapter
import com.example.ogan.codedenim.courses.PostReplyActivity
import com.example.ogan.codedenim.gson.ForumAnswer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReplyForumActivity : AppCompatActivity() {

    private var results: ArrayList<ForumAnswer>? = null
    private var myAdapter: ForumReplyAdapter? = null
    private var questionId: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reply_forum)

        title = "Replies"

        val button = findViewById<FloatingActionButton>(R.id.post_reply_btn)

        questionId = intent.getIntExtra("questionId", 0).toString()
        progressBar = findViewById(R.id.reply_pr)
        recyclerView = findViewById(R.id.answer_rv)

        val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        val decorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decorator)
        val question = intent.getStringExtra("questionName")

        val questionTv = findViewById<TextView>(R.id.question_tv)
        questionTv.text = question

        getData()

        button.setOnClickListener{
            val mIntent = Intent(this@ReplyForumActivity, PostReplyActivity::class.java)
            mIntent.putExtra("questionId", questionId)
            startActivity(mIntent)
        }

        val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.reply_swipe)
        refreshLayout.setOnRefreshListener {
            myAdapter = null
            progressBar.visibility = View.VISIBLE
            getData()
            refreshLayout.isRefreshing = false
        }
    }

    private fun getData() {
        //GET apiMethods
        ServiceGenerator.apiMethods.getAnswers(questionId.toString()).enqueue(object : Callback<ArrayList<ForumAnswer>> {
            override fun onResponse(call: Call<ArrayList<ForumAnswer>>, response: Response<ArrayList<ForumAnswer>>?) {
                progressBar.visibility = View.GONE
                if (response != null) {
                    if (response.isSuccessful) {
                        println(response.message())
                        println(response.body())
                        results = response.body()
                        myAdapter = ForumReplyAdapter(results)
                        recyclerView.adapter = myAdapter
                        recyclerView.visibility = View.VISIBLE
                    } //else MyUtilClass.showErrorMessage(this@ForumActivity, response, )
                }

            }

            override fun onFailure(call: Call<ArrayList<ForumAnswer>>, t: Throwable?) {

                progressBar.visibility = View.GONE
                if (t != null) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    Log.e("error", t.message)
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                super@ReplyForumActivity.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
