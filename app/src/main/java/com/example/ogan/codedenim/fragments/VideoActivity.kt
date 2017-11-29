package com.example.ogan.codedenim.fragments


import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView
import com.example.ogan.codedenim.R

class VideoActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_video)

        videoView = findViewById(R.id.videoViewPlayer)
        val controller = MediaController(this)
        val progressBar = findViewById<ProgressBar>(R.id.videoLoader)
        val name = intent.getStringExtra("name")
        title = name
        println(name)
        val videoURL = "https://codedenim.azurewebsites.net/MaterialUpload/" + intent.getStringExtra("fileLocation")

        videoView.setVideoURI(Uri.parse(videoURL))
        videoView.requestFocus()
        videoView.setOnPreparedListener {
            progressBar.visibility = View.GONE
            controller.setAnchorView(videoView)
            videoView.setMediaController(controller)
        }

    }


    public override fun onStop() {
        super.onStop()
        videoView.stopPlayback()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                super@VideoActivity.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
