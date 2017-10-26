package com.example.ogan.codedenim.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.ogan.codedenim.R;

/**
 * A login screen that offers login via email/password.
 */
public class OthersRegisterActivity extends AppCompatActivity {

    // UI references.
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_register);
        // Set up the login form.


        mLoginFormView = findViewById(R.id.other_register_form);
        mProgressView = findViewById(R.id.others_login_progress);
    }


}


