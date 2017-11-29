package com.example.ogan.codedenim.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.ogan.codedenim.NetworkConnectivity
import com.example.ogan.codedenim.R
import com.example.ogan.codedenim.ServiceGenerator
import com.example.ogan.codedenim.adapters.HomeAdapter
import com.example.ogan.codedenim.gson.LearningPath
import com.example.ogan.codedenim.sessionManagement.UserSessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var newsRv: RecyclerView
    private var homeAdapter: HomeAdapter? = null
    private lateinit var linearLayout: LinearLayout
    private lateinit var progressBar: ProgressBar
    private var email: String? = null

    internal var categories: ArrayList<LearningPath>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        activity?.title = "Home"

        // get user data from session
        val user = UserSessionManager.getUserDetails()

        // get email
        email = user[UserSessionManager.KEY_EMAIL]

        linearLayout = v.findViewById(R.id.linearView)
        progressBar = v.findViewById(R.id.loading_spinner)
        val swipeRefreshLayout = v.findViewById<SwipeRefreshLayout>(R.id.home_swipe)

        newsRv = v.findViewById(R.id.rv_News)
        val newsLayoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        newsRv.layoutManager = newsLayoutManager

        getData()

        swipeRefreshLayout.setOnRefreshListener {
            homeAdapter = null
            progressBar.visibility = View.VISIBLE
            getData()
            swipeRefreshLayout.isRefreshing = false
        }

        return v
    }

    private fun getData() {
        if (NetworkConnectivity.checkNetworkConnecttion(activity?.applicationContext)) {
            //GET categories
            ServiceGenerator.apiMethods.getCategories(email).enqueue(object : Callback<ArrayList<LearningPath>> {
                override fun onResponse(call: Call<ArrayList<LearningPath>>, response: Response<ArrayList<LearningPath>>?) {

                    progressBar.visibility = View.GONE

                    if (response != null) {
                        System.err.println(response.message())
                        System.err.println(response.body())

                        if (response.isSuccessful) {
                            categories = response.body()
                            homeAdapter = HomeAdapter(categories)
                            newsRv.adapter = homeAdapter
                            linearLayout.visibility = View.VISIBLE

                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<LearningPath>>, t: Throwable?) {

                    progressBar.visibility = View.GONE
                    if (t != null) {
                        Log.e("categories_error", t.message)
                    }
                }
            })
        } else {
            progressBar.visibility = View.GONE
            NetworkConnectivity.showNoInternetMessage(activity?.applicationContext)
        }
    }
}// Required empty public constructor
