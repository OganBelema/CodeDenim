package com.example.ogan.codedenim.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.ogan.codedenim.R;

/**
 * A login screen that offers login via email/password.
 */
public class StudentRegisterActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);


        mLoginFormView = findViewById(R.id.student_register_form);
        mProgressView = findViewById(R.id.student_login_progress);
    }
}

