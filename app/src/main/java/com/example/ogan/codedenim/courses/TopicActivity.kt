package com.example.ogan.codedenim.courses

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.example.ogan.codedenim.MyUtilClass
import com.example.ogan.codedenim.R
import com.example.ogan.codedenim.ServiceGenerator
import com.example.ogan.codedenim.adapters.TopicAdapter
import com.example.ogan.codedenim.gson.Topic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class TopicActivity : AppCompatActivity() {

    private var topicAdapter: TopicAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private var results: ArrayList<Topic>? = null
    private lateinit var progressBar: ProgressBar
    private var moduleId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.general_recyclerview_layout)

        recyclerView = findViewById(R.id.general_recyclerView)
        progressBar = findViewById(R.id.general_pr)
        val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager

        val intent = intent
        moduleId = intent.getIntExtra("moduleId", 0)

        getData()

        val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        refreshLayout.setOnRefreshListener {
            topicAdapter = null
            progressBar.visibility = View.VISIBLE
            getData()
            refreshLayout.isRefreshing = false
        }
    }


    private fun getData() {
        if (MyUtilClass.checkNetworkConnection(applicationContext)) {
            //GET modules
            ServiceGenerator.apiMethods.getTopicList(moduleId).enqueue(object : Callback<ArrayList<Topic>> {
                override fun onResponse(call: Call<ArrayList<Topic>>, response: Response<ArrayList<Topic>>?) {

                    if (response != null) {
                        println(response.message())
                        if (response.isSuccessful) {

                            results = response.body()
                            topicAdapter = TopicAdapter(results)
                            recyclerView.adapter = topicAdapter
                            progressBar.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE

                        } else MyUtilClass.showAlert(this@TopicActivity, "Sorry, an error occurred. Swipe down to refresh")
                    }
                }

                override fun onFailure(call: Call<ArrayList<Topic>>, t: Throwable?) {

                    progressBar.visibility = View.GONE
                    MyUtilClass.showAlert(this@TopicActivity, "Sorry, an error occurred. Swipe down to refresh")
                }
            })
        } else {
            progressBar.visibility = View.GONE
            MyUtilClass.showNoInternetMessage(this@TopicActivity)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                super@TopicActivity.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
