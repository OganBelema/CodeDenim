package com.example.ogan.codedenim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ogan.codedenim.login.CorperLoginActivity;
import com.example.ogan.codedenim.login.OthersLoginActivity;
import com.example.ogan.codedenim.login.StudentLoginActivity;

public class StarterActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        //ActivityCompat.finishAffinity(this);

        Button corperBtn = findViewById(R.id.button_corper);
        Button studentBtn = findViewById(R.id.button_student);
        Button otherBtn = findViewById(R.id.others_login);


        corperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), CorperLoginActivity.class);
                startActivity(intent);
            }
        });

        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), StudentLoginActivity.class);
                startActivity(intent);
            }
        });

        otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), OthersLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
