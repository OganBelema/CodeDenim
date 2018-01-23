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
import com.example.ogan.codedenim.adapters.CategoriesRvAdapter
import com.example.ogan.codedenim.gson.LearningPath
import com.example.ogan.codedenim.sessionManagement.UserSessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MyCourses : AppCompatActivity() {

    private var results: ArrayList<LearningPath>? = null
    private var myAdapter: CategoriesRvAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.general_recyclerview_layout)

        // get user data from session
        val user = UserSessionManager.getUserDetails()

        // get email
        try {
            email = user[UserSessionManager.KEY_EMAIL]
        } catch (e: Exception){
            e.printStackTrace()
        }


        progressBar = findViewById(R.id.general_pr)
        recyclerView = findViewById(R.id.general_recyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

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
        //GET apiMethods
        ServiceGenerator.apiMethods.getCourses(email).enqueue(object : Callback<ArrayList<LearningPath>> {
            override fun onResponse(call: Call<ArrayList<LearningPath>>, response: Response<ArrayList<LearningPath>>?) {
                if (response != null) {
                    if (response.isSuccessful) {
                        println(response.message())
                        println(response.body())
                        results = response.body()
                        myAdapter = CategoriesRvAdapter(results)
                        recyclerView.adapter = myAdapter
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    } else MyUtilClass.showAlert(this@MyCourses, "Sorry, an error occurred. Swipe down to refresh")
                }

            }

            override fun onFailure(call: Call<ArrayList<LearningPath>>, t: Throwable?) {

                progressBar.visibility = View.GONE
                MyUtilClass.showAlert(this@MyCourses, "Sorry, an error occurred. Swipe down to refresh")
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                super@MyCourses.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
