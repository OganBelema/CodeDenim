package com.example.ogan.codedenim

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.ogan.codedenim.register.CorperRegisterActivity
import com.example.ogan.codedenim.register.OthersRegisterActivity
import com.example.ogan.codedenim.register.StudentRegisterActivity

class UserCategory : AppCompatActivity() {

    private var mIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Welcome"
        setContentView(R.layout.activity_user_category)

        val corperBtn = findViewById<Button>(R.id.corperRegisterBtn)
        val studentBtn = findViewById<Button>(R.id.studentRegisterBtn)
        val othersBtn = findViewById<Button>(R.id.othersRegisterButton)



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

    }

}
