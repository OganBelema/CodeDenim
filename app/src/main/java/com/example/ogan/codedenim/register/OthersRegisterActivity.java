package com.example.ogan.codedenim.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ogan.codedenim.NetworkConnectivity;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.ServiceGenerator;
import com.example.ogan.codedenim.login.LoginActivity;
import com.example.ogan.codedenim.user.User;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class OthersRegisterActivity extends AppCompatActivity {

    // UI references.
    private View mProgressView;
    private View mLoginFormView;
    private Spinner genderSpinner;
    private TextInputEditText mFirstName;
    private TextInputEditText mLastName;
    private TextInputEditText mDateOfBirth;
    private TextInputEditText mPhoneNumber;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private TextInputEditText mConfirmPassword;


    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_register);
        // Set up the login form.
        genderSpinner = findViewById(R.id.spinner_gender);
        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.last_name);
        mDateOfBirth = findViewById(R.id.date_of_birth);
        mPhoneNumber = findViewById(R.id.mobile_number);
        mEmail = findViewById(R.id.others_email);
        mPassword = findViewById(R.id.other_password);
        mConfirmPassword = findViewById(R.id.other_confirm_password);

        mLoginFormView = findViewById(R.id.other_register_form);
        mProgressView = findViewById(R.id.others_login_progress);

        Button registerButton = findViewById(R.id.other_register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NetworkConnectivity.INSTANCE.checkNetworkConnecttion(getApplicationContext())){
                    attemptRegister();
                } else {
                    NetworkConnectivity.INSTANCE.showNoInternetMessage(OthersRegisterActivity.this);
                }
            }
        });

        setupSpinner();
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);
        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        // Apply the adapter to the spinner
        genderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    gender = selection;
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = "Select Gender";
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptRegister() {

        // Reset errors.
        mEmail.setError(null);
        mPassword.setError(null);
        mFirstName.setError(null);
        mLastName.setError(null);
        mDateOfBirth.setError(null);
        mPhoneNumber.setError(null);
        mConfirmPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String firstname = mFirstName.getText().toString().trim();
        String lastName = mLastName.getText().toString().trim();
        String dateOfBirth = mDateOfBirth.getText().toString().trim();
        String phoneNumber = mPhoneNumber.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPassword.setError(getString(R.string.error_field_required));
            focusView = mPassword;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        }


        // Check for a empty confirm password or if is the same with password
        if (TextUtils.isEmpty(confirmPassword) || !confirmPassword.equals(password)) {
            mConfirmPassword.setError(getString(R.string.error_incorrect_password));
            focusView = mConfirmPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmail.setError(getString(R.string.error_field_required));
            focusView = mEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmail.setError(getString(R.string.error_invalid_email));
            focusView = mEmail;
            cancel = true;
        }

        // Check for empty firstname
        if (TextUtils.isEmpty(firstname)) {
            mFirstName.setError(getString(R.string.error_field_required));
            focusView = mFirstName;
            cancel = true;
        }

        // Check for empty lastname
        if (TextUtils.isEmpty(lastName)) {
            mLastName.setError(getString(R.string.error_field_required));
            focusView = mLastName;
            cancel = true;
        }


        //check for empty date of birth
        if (TextUtils.isEmpty(dateOfBirth)) {
            mDateOfBirth.setError(getString(R.string.error_field_required));
            focusView = mDateOfBirth;
            cancel = true;
        }

        //check for empty number
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumber.setError(getString(R.string.error_field_required));
            focusView = mPhoneNumber;
            cancel = true;
        }

        if (gender.equals("Select Gender")) {
            Toast.makeText(getApplicationContext(), "Pick a gender", Toast.LENGTH_SHORT).show();
            focusView = genderSpinner;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            Call<ResponseBody> register = ServiceGenerator.INSTANCE.getApiMethods()
                    .registerOtherStudent(new User(firstname, lastName, gender, dateOfBirth, phoneNumber,
                            email, password, confirmPassword));

            register.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @Nullable Response<ResponseBody> response) {

                    showProgress(false);
                    if (response != null) {
                        System.out.println(response.message());
                        System.out.println(response.code());
                        System.out.println(response.errorBody());

                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                System.out.println(jObjError.getString("message"));
                                Toast.makeText(getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    showProgress(false);
                    System.out.println(t.getMessage());
                }
            });
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {

        return password.length() > 5;
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

}


