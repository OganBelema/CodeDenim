package com.example.ogan.codedenim.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.ogan.codedenim.LearningPathDetailActivity
import com.example.ogan.codedenim.MyUtilClass
import com.example.ogan.codedenim.R
import com.example.ogan.codedenim.ServiceGenerator
import com.example.ogan.codedenim.adapters.HomeAdapter
import com.example.ogan.codedenim.courses.CoursesByCategories
import com.example.ogan.codedenim.gson.CheckPayment
import com.example.ogan.codedenim.gson.LearningPath
import com.example.ogan.codedenim.gson.PaymentStatus
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
    private var categories: ArrayList<LearningPath>? = null
    private lateinit var v: View
    private var email: String? = null
    private lateinit var paymentCheckProgress: LinearLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false)

        activity?.title = "Home"

        linearLayout = v.findViewById(R.id.linearView)
        progressBar = v.findViewById(R.id.loading_spinner)
        newsRv = v.findViewById(R.id.rv_News)
        paymentCheckProgress = v.findViewById(R.id.payment_check)
        val newsLayoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, false)
        newsRv.layoutManager = newsLayoutManager

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        val swipeRefreshLayout = v.findViewById<SwipeRefreshLayout>(R.id.home_swipe)
        swipeRefreshLayout.setOnRefreshListener {
            homeAdapter = null
            progressBar.visibility = View.VISIBLE
            getData()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getData() {
        // get user data from session
        val user = UserSessionManager.getUserDetails()

        // get email
        try {
            email = user[UserSessionManager.KEY_EMAIL]
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (MyUtilClass.checkNetworkConnection(v.context)) {
            //GET categories
            ServiceGenerator.apiMethods.getCategories(email).enqueue(object : Callback<ArrayList<LearningPath>> {
                override fun onResponse(call: Call<ArrayList<LearningPath>>, response: Response<ArrayList<LearningPath>>?) {

                    progressBar.visibility = View.GONE

                    if (response != null) {
                        System.err.println(response.message())
                        System.err.println(response.body())

                        if (response.isSuccessful) {
                            categories = response.body()
                            if (categories != null) {

                                homeAdapter = HomeAdapter(categories, object : HomeAdapter.ClickListener {
                                    override fun onBtnClick(position: Int) {

                                        paymentCheckProgress.visibility = View.VISIBLE

                                        try {
                                            val courseCategoryId = categories!![position].CourseCategoryId.toString()
                                            val learningPathName = categories!![position].CategoryName
                                            if (MyUtilClass.checkNetworkConnection(v.context)) {
                                                ServiceGenerator.apiMethods.checkPayment(CheckPayment(email, courseCategoryId)).enqueue(object : Callback<PaymentStatus> {
                                                    override fun onResponse(call: Call<PaymentStatus>?, response: Response<PaymentStatus>?) {
                                                        paymentCheckProgress.visibility = View.GONE

                                                        if (response != null) {
                                                            println(response.body()?.status)

                                                            if (response.isSuccessful) {

                                                                val status = response.body()?.status
                                                                //val status = "Paid"

                                                                when (status) {
                                                                    "Not Paid" /*not paid*/ -> {

                                                                        val categoryName = categories!![position].CategoryName
                                                                        val categoryPrice = categories!![position].Amount.toString()
                                                                        val categoryDesc = categories!![position].CategoryDescription
                                                                        val imageLocation = "http://codedenim.azurewebsites.net/MaterialUpload/${categories!![position].ImageLocation}"

                                                                        val categoryId = categories!![position].CourseCategoryId
                                                                        val studentType = categories!![position].StudentType

                                                                        val intent = Intent(context, LearningPathDetailActivity::class.java)
                                                                        intent.putExtra("CategoryId", categoryId)
                                                                        intent.putExtra("CategoryName", categoryName)
                                                                        intent.putExtra("ImageLocation", imageLocation)
                                                                        intent.putExtra("CategoryDescription", categoryDesc)
                                                                        intent.putExtra("CategoryPrice", categoryPrice)
                                                                        intent.putExtra("StudentType", studentType)
                                                                        startActivity(intent)

                                                                    }
                                                                    "Paid" /*if paid*/ -> {
                                                                        val mIntent = Intent(activity?.applicationContext, CoursesByCategories::class.java)
                                                                        mIntent.putExtra("CategoryId", courseCategoryId.toInt())
                                                                        mIntent.putExtra("CategoryName", learningPathName)
                                                                        startActivity(mIntent)
                                                                    }
                                                                    "Pending" -> AlertDialog.Builder(v.context)
                                                                            .setMessage("Payment pending")
                                                                            .setNegativeButton("Close", null)
                                                                            .show()
                                                                }
                                                            }
                                                        }
                                                    }

                                                    override fun onFailure(call: Call<PaymentStatus>?, t: Throwable?) {
                                                        paymentCheckProgress.visibility = View.GONE

                                                        try {
                                                            MyUtilClass.showAlert(view?.context, "Sorry, an error occurred. Tap to try again")
                                                        } catch (e: Exception) {
                                                            e.printStackTrace()
                                                        }
                                                    }
                                                })
                                            } else {
                                                paymentCheckProgress.visibility = View.GONE
                                                MyUtilClass.showNoInternetMessage(v.context)
                                            }
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }


                                    }
                                })

                            }

                            newsRv.adapter = homeAdapter
                            linearLayout.visibility = View.VISIBLE

                        } else {
                            try {
                                MyUtilClass.showAlert(view?.context, "Sorry, an error occurred. Swipe down to try again")
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<LearningPath>>, t: Throwable?) {

                    progressBar.visibility = View.GONE
                    try {
                        MyUtilClass.showAlert(view?.context, "Sorry, an error occurred. Swipe down to try again")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
        } else {
            progressBar.visibility = View.GONE
            MyUtilClass.showNoInternetMessage(v.context)
        }
    }

}// Required empty public constructor
