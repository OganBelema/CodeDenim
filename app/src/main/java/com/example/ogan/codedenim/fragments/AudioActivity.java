package com.example.ogan.codedenim.fragments;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.example.ogan.codedenim.MyUtilClass;
import com.example.ogan.codedenim.R;

import java.io.IOException;

import at.markushi.ui.CircleButton;

public class AudioActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private String url;
    private SeekBar seekBar;
    private Handler handler;
    private Runnable runnable;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener(){

                public void onAudioFocusChange(int focusChange){
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mediaPlayer.pause();
                        //mediaPlayer.seekTo(0);
                    } else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mediaPlayer.start();
                    } else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        //releaseMediaPlayer();
                        mediaPlayer.pause();
                    }
                }
            };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_audio);

        //String name = getIntent().getStringExtra("name");
        String fileLocation = getIntent().getStringExtra("fileLocation");
        setTitle(fileLocation);
        url = "https://codedenim.azurewebsites.net/MaterialUpload/" + fileLocation;
        System.out.println(url);
        //requesting audio focus
        audioManager = (AudioManager) getApplicationContext()
                .getSystemService(Context.AUDIO_SERVICE);

        handler = new Handler();


        CircleButton playBtn = findViewById(R.id.play_button);
        CircleButton pauseBtn = findViewById(R.id.pause_button);
        seekBar = findViewById(R.id.seekBar);
        progressBar = findViewById(R.id.audio_progress_bar);
        linearLayout = findViewById(R.id.audio_container_view);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer == null){
                    loadMedia();
                    setupMedia();
                } else {
                    mediaPlayer.start();
                    playCycle();
                }

            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if (input){
                    try{
                        mediaPlayer.seekTo(progress);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void playCycle(){
        if (mediaPlayer != null) {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            if (mediaPlayer.isPlaying()) {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        playCycle();
                    }
                };
                handler.postDelayed(runnable, 1000);
            }
        }
    }

    private void releaseMediaPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null)
        mediaPlayer.pause();
    }


    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void setupMedia(){
        if (audioManager != null){
            int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                //Proceed with playing
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(url));
                } catch (IOException e){
                    e.printStackTrace();
                }
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        doneLoading();
                        seekBar.setMax(mediaPlayer.getDuration());
                        mediaPlayer.start();
                        playCycle();
                    }
                });

                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                        doneLoading();
                        MyUtilClass.INSTANCE.showAlert(AudioActivity.this, "Sorry, an error occurred. Please try again.");
                        return false;
                    }
                });

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });


            }
        }

    }

    private void loadMedia(){
        linearLayout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void doneLoading(){
        progressBar.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                AudioActivity.super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
