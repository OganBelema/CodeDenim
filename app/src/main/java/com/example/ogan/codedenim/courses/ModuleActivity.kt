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
import com.example.ogan.codedenim.adapters.ModuleAdapter
import com.example.ogan.codedenim.gson.Module
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ModuleActivity : AppCompatActivity() {

    private var moduleAdapter: ModuleAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private var results: ArrayList<Module>? = null
    private lateinit var progressBar: ProgressBar
    private var courseId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.general_recyclerview_layout)

        recyclerView = findViewById(R.id.general_recyclerView)
        progressBar = findViewById(R.id.general_pr)
        val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        val intent = intent
        val courseName = intent.getStringExtra("courseName")
        courseId = intent.getIntExtra("courseId", 0)

        if (supportActionBar != null) {
            supportActionBar?.title = courseName + " Modules"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        getData()

        val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        refreshLayout.setOnRefreshListener {
            moduleAdapter = null
            progressBar.visibility = View.VISIBLE
            getData()
            refreshLayout.isRefreshing = false
        }

    }

    private fun getData() {
        if (MyUtilClass.checkNetworkConnection(applicationContext)) {
            //GET modules
            ServiceGenerator.apiMethods.getModuleList(courseId).enqueue(object : Callback<ArrayList<Module>> {
                override fun onResponse(call: Call<ArrayList<Module>>, response: Response<ArrayList<Module>>?) {

                    if (response != null) {
                        println(response.message())
                        if (response.isSuccessful) {

                            results = response.body()
                            println(response.body())
                            moduleAdapter = ModuleAdapter(results)
                            recyclerView.adapter = moduleAdapter
                            progressBar.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE

                        } else MyUtilClass.showAlert(this@ModuleActivity, "Sorry, an error occurred. Swipe down to refresh")
                    }
                }

                override fun onFailure(call: Call<ArrayList<Module>>, t: Throwable?) {

                    progressBar.visibility = View.GONE
                    MyUtilClass.showAlert(this@ModuleActivity, "Sorry, an error occurred. Swipe down to refresh")
                }
            })
        } else {
            progressBar.visibility = View.GONE
            MyUtilClass.showNoInternetMessage(this@ModuleActivity)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                super@ModuleActivity.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
