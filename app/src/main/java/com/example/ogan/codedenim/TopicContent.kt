package com.example.ogan.codedenim

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.example.ogan.codedenim.adapters.MaterialUploadAdapter
import com.example.ogan.codedenim.gson.MaterialUpload
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class TopicContent : AppCompatActivity() {


    private var topicId: Int = 0
    private var results: ArrayList<MaterialUpload>? = null
    private var myAdapter: MaterialUploadAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.general_recyclerview_layout)

        title = "Topic Content"

        progressBar = findViewById(R.id.general_pr)
        recyclerView = findViewById(R.id.general_recyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        topicId = intent.getIntExtra("topicId", 0)
        println(topicId)

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            myAdapter = null
            progressBar.visibility = View.VISIBLE
            getData()
            swipeRefreshLayout.isRefreshing = false
        }

        getData()

    }

    private fun getData() {
        ServiceGenerator.apiMethods.getMaterialUpload(topicId.toString())
                .enqueue(object : Callback<ArrayList<MaterialUpload>> {
                    override fun onResponse(call: Call<ArrayList<MaterialUpload>>, response: Response<ArrayList<MaterialUpload>>?) {
                        if (response != null) {
                            println(response.message())
                            println(response.body())

                            if (response.isSuccessful) {

                                results = response.body()
                                myAdapter = MaterialUploadAdapter(results)
                                recyclerView.adapter = myAdapter
                                progressBar.visibility = View.GONE
                                recyclerView.visibility = View.VISIBLE
                            } else MyUtilClass.showAlert(this@TopicContent, "Sorry, an error occurred. Swipe down to try again")
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<MaterialUpload>>, t: Throwable?) {
                        progressBar.visibility = View.GONE
                        MyUtilClass.showAlert(this@TopicContent, "Sorry, an error occurred. Swipe down to try again")
                    }
                })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                super@TopicContent.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
