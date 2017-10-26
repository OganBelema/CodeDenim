package com.example.ogan.codedenim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ogan.codedenim.register.CorperRegisterActivity;
import com.example.ogan.codedenim.register.OthersRegisterActivity;
import com.example.ogan.codedenim.register.StudentRegisterActivity;

public class UserCategory extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Welcome");
        setContentView(R.layout.activity_user_category);

        Button corperBtn = findViewById(R.id.corperRegisterBtn);
        Button studentBtn = findViewById(R.id.studentRegisterBtn);
        Button othersBtn = findViewById(R.id.othersRegisterButton);



        corperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), CorperRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), StudentRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        othersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), OthersRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
