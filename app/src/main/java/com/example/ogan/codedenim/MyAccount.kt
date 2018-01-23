package com.example.ogan.codedenim

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.ogan.codedenim.sessionManagement.UserSessionManager

class MyAccount : AppCompatActivity() {

    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        val userEmail = findViewById<TextView>(R.id.tv_email)
        // get user data from session
        val user = UserSessionManager.getUserDetails()

        try {
            // get email
            email = user[UserSessionManager.KEY_EMAIL]
        } catch (e: Exception){
            e.printStackTrace()
        }

        userEmail.text = resources.getString(R.string.user_detail_email, email)
    }
}
