package com.example.ogan.codedenim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;

import com.example.ogan.codedenim.Login.CorperLoginActivity;
import com.example.ogan.codedenim.Login.StudentLoginActivity;

public class StarterActivity extends AppCompatActivity {

    private SwitchCompat switchCompat;
    private Button getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        switchCompat = (SwitchCompat) findViewById(R.id.userPick);
        getStarted = (Button) findViewById(R.id.button);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchCompat.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), StudentLoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), CorperLoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
