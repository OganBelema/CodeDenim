package com.example.ogan.codedenim

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ogan.codedenim.login.LoginActivity
import com.example.ogan.codedenim.register.CorperRegisterActivity
import com.example.ogan.codedenim.register.OthersRegisterActivity
import com.example.ogan.codedenim.register.StudentRegisterActivity

class UserCategory : AppCompatActivity() {

    private var mIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Welcome"
        setContentView(R.layout.activity_user_category)

        val corperBtn = findViewById<LinearLayout>(R.id.corperRegisterBtn)
        val studentBtn = findViewById<LinearLayout>(R.id.studentRegisterBtn)
        val othersBtn = findViewById<LinearLayout>(R.id.othersRegisterButton)



        corperBtn.setOnClickListener {
            mIntent = Intent(applicationContext, CorperRegisterActivity::class.java)
            startActivity(mIntent)
        }

        studentBtn.setOnClickListener {
            mIntent = Intent(applicationContext, StudentRegisterActivity::class.java)
            startActivity(mIntent)
        }

        othersBtn.setOnClickListener {
            mIntent = Intent(applicationContext, OthersRegisterActivity::class.java)
            startActivity(mIntent)
        }

        val loginPage = findViewById<TextView>(R.id.sign_in_tv)
        loginPage.setOnClickListener {
            val intent = Intent(this@UserCategory, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

    }

}
