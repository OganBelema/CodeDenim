package com.example.ogan.codedenim;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.ogan.codedenim.fragments.LessonFragment;

public class TopicContent extends AppCompatActivity {


    FragmentTransaction fragmentTransaction;
    FragmentManager FM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_content);

        setTitle("Topic Content");

        FM = getSupportFragmentManager();
        fragmentTransaction = FM.beginTransaction();
        fragmentTransaction.replace(R.id.topicContent, new LessonFragment()).commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                TopicContent.super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
