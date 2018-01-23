package com.example.ogan.codedenim

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class StarterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)

        val myThread = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3200)
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }
        myThread.start()
    }
}
