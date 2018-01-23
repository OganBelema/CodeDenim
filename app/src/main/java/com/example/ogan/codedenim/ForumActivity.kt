package com.example.ogan.codedenim


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.ogan.codedenim.adapters.ForumQuestionAdapter
import com.example.ogan.codedenim.gson.ForumQuestion
import com.example.ogan.codedenim.sessionManagement.UserSessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class ForumActivity : AppCompatActivity() {

    private var results: ArrayList<ForumQuestion>? = null
    private var myAdapter: ForumQuestionAdapter? = null
    private var courseId: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat)

        // get user data from session
        val user = UserSessionManager.getUserDetails()

        try {
            // get email
            email = user[UserSessionManager.KEY_EMAIL]
        } catch (e: Exception){
            e.printStackTrace()
        }


        courseId = intent.getIntExtra("courseId", 0).toString()

        progressBar = findViewById(R.id.question_pr)
        recyclerView = findViewById(R.id.question_rv)
        val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        val decorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decorator)

        getData()

        val button = findViewById<FloatingActionButton>(R.id.post_question_btn)
        button.setOnClickListener{
            val mIntent = Intent(this@ForumActivity, PostForumActivity::class.java)
            mIntent.putExtra("courseId", courseId)
            startActivity(mIntent)
        }


        val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.forum_swipe)
        refreshLayout.setOnRefreshListener {
            myAdapter = null
            progressBar.visibility = View.VISIBLE
            getData()
            refreshLayout.isRefreshing = false
        }
    }

    private fun getData() {
        //GET apiMethods
        ServiceGenerator.apiMethods.getForumQuestions(courseId).enqueue(object : Callback<ArrayList<ForumQuestion>> {
            override fun onResponse(call: Call<ArrayList<ForumQuestion>>, response: Response<ArrayList<ForumQuestion>>?) {
                progressBar.visibility = View.GONE
                if (response != null) {
                    if (response.isSuccessful) {
                        println(response.message())
                        println(response.body())
                        results = response.body()
                        myAdapter = ForumQuestionAdapter(results, object: ForumQuestionAdapter.ForumClickListener{
                            override fun onBtnClick(position: Int) {

                            }
                        } )
                        recyclerView.adapter = myAdapter
                        recyclerView.visibility = View.VISIBLE
                    } //else MyUtilClass.showErrorMessage(this@ForumActivity, response, )
                }

            }

            override fun onFailure(call: Call<ArrayList<ForumQuestion>>, t: Throwable?) {

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
                super@ForumActivity.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
