package com.example.ogan.codedenim;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;

import com.example.ogan.codedenim.Login.CorperLoginActivity;
import com.example.ogan.codedenim.Login.StudentLoginActivity;

public class StarterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        //ActivityCompat.finishAffinity(this);

        Button corperBtn = (Button) findViewById(R.id.button_corper);
        Button studentBtn = (Button) findViewById(R.id.button_student);

        corperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CorperLoginActivity.class);
                startActivity(intent);
            }
        });

        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
