package com.example.ogan.codedenim

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.widget.ImageView

class StarterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)

        val imageView = findViewById<ImageView>(R.id.starter_image)
        imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.pulse))

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
