package com.example.ogan.codedenim.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.ogan.codedenim.*
import com.example.ogan.codedenim.sessionManagement.UserSessionManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    // User Session Manager Class
    private lateinit var session: UserSessionManager

    // UI references.
    private lateinit var mEmailView: AutoCompleteTextView
    private lateinit var mPasswordView: EditText
    private lateinit var mProgressView: View
    private lateinit var mLoginFormView: View
    private lateinit var mEmailSignInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // User Session Manager
        session = UserSessionManager(applicationContext)

        //title = "Sign In"

        // Set up the login form.
        mEmailView = findViewById(R.id.email)
        mPasswordView = findViewById(R.id.password)


        mEmailSignInButton = findViewById(R.id.email_sign_in_button)
        mEmailSignInButton.setOnClickListener {
            if (MyUtilClass.checkNetworkConnection(applicationContext)) {
                attemptLogin()
            } else {
                MyUtilClass.showNoInternetMessage(this@LoginActivity)
            }
        }

        val mRegisterTextView = findViewById<TextView>(R.id.register_tv)
        mRegisterTextView.setOnClickListener {
            val intent = Intent(applicationContext, UserCategory::class.java)
            startActivity(intent)
        }

        mLoginFormView = findViewById(R.id.login_form)
        mProgressView = findViewById(R.id.login_progress)
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {

        // Reset errors.
        mEmailView.error = null
        mPasswordView.error = null

        // Store values at the time of the login attempt.
        val email = mEmailView.text.toString()
        val password = mPasswordView.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.error = getString(R.string.error_field_required)
            focusView = mEmailView
            cancel = true
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailView.error = getString(R.string.error_invalid_email)
            focusView = mEmailView
            cancel = true
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.error = getString(R.string.error_field_required)
            focusView = mPasswordView
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress()
            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        ServiceGenerator.apiMethods.loginCorper(email, password, "password")
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?) {

                        hideProgress()

                        if (response != null) {
                            if (response.isSuccessful) {
                                session.createUserLoginSession(email, password)
                                finish()
                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)
                            } else
                                MyUtilClass.showErrorMessage(this@LoginActivity, response)

                            println(response.message())
                            println(response.code())
                        }


                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
                        hideProgress()
                        if (t != null)
                            Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_LONG)
                                    .show()

                    }
                })
    }

    private fun showProgress(){
        mProgressView.visibility = View.VISIBLE
        mEmailSignInButton.isEnabled = false
    }

    private fun hideProgress(){
        mProgressView.visibility = View.GONE
        mEmailSignInButton.isEnabled = true
    }
}

