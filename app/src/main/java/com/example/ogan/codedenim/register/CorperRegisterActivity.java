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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ogan.codedenim.NetworkConnectivity;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.ServiceGenerator;
import com.example.ogan.codedenim.login.LoginActivity;
import com.example.ogan.codedenim.user.Corper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class CorperRegisterActivity extends AppCompatActivity {


    // UI references.
    private View mProgressView;
    private View mLoginFormView;
    private TextInputEditText mCallUpNumber;
    private TextInputEditText mCorperFirstName;
    private TextInputEditText mCorperLastName;
    private Spinner mCorperGender;
    private TextInputEditText mDateOfBirth;
    private TextInputEditText mCorperNumber;
    private Spinner mNyscState;
    private TextInputEditText mCorperInstitution;
    private TextInputEditText mCorperDiscipline;
    private TextInputEditText mCorperEmail;
    private TextInputEditText mCorperPassword;
    private TextInputEditText mCorperConfirmPassword;

    String corperGender;
    String nyscState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corper_reg);

        // Set up the login form.
        mCorperEmail = findViewById(R.id.corper_email);


        mCallUpNumber = findViewById(R.id.call_up_number);
        mCorperFirstName = findViewById(R.id.first_name);
        mCorperLastName = findViewById(R.id.last_name);
        mCorperGender = findViewById(R.id.spinner_gender);
        mDateOfBirth = findViewById(R.id.date_of_birth);
        mCorperNumber = findViewById(R.id.mobile_number);
        mNyscState = findViewById(R.id.nysc_state);
        mCorperInstitution = findViewById(R.id.corper_institution);
        mCorperDiscipline = findViewById(R.id.corper_discipline);
        mCorperPassword = findViewById(R.id.corper_password);
        mCorperConfirmPassword = findViewById(R.id.corper_confirm_password);

        Button registerButton = findViewById(R.id.corper_register_button);
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NetworkConnectivity.INSTANCE.checkNetworkConnecttion(getApplicationContext())){
                    attemptRegister();
                } else {
                    NetworkConnectivity.INSTANCE.showNoInternetMessage(CorperRegisterActivity.this);
                }
            }
        });

        mLoginFormView = findViewById(R.id.corper_register_form);
        mProgressView = findViewById(R.id.login_progress);
        setupSpinner();
    }


    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        ArrayAdapter stateSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_state_options, android.R.layout.simple_spinner_item);


        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        stateSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mCorperGender.setAdapter(genderSpinnerAdapter);

        mNyscState.setAdapter(stateSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mCorperGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    corperGender = selection;
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                corperGender = "Select Gender";
            }
        });


        mNyscState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    nyscState = selection;
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                nyscState = "Select NYSC State";
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
        mCorperEmail.setError(null);
        mCorperPassword.setError(null);
        mCallUpNumber.setError(null);
        mCorperFirstName.setError(null);
        mCorperLastName.setError(null);
        mDateOfBirth.setError(null);
        mCorperNumber.setError(null);
        mCorperInstitution.setError(null);
        mCorperDiscipline.setError(null);
        mCorperConfirmPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = mCorperEmail.getText().toString().trim();
        String password = mCorperPassword.getText().toString().trim();
        String callUpNumber = mCallUpNumber.getText().toString().trim();
        String corperFirstName = mCorperFirstName.getText().toString().trim();
        String corperLastName = mCorperLastName.getText().toString().trim();
        String dateOfBirth = mDateOfBirth.getText().toString().trim();
        String corperNumber = mCorperNumber.getText().toString().trim();
        String corperInstitution = mCorperInstitution.getText().toString().trim();
        String corperDiscipline = mCorperDiscipline.getText().toString().trim();
        String corperConfirmPassword = mCorperConfirmPassword.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mCorperPassword.setError(getString(R.string.error_field_required));
            focusView = mCorperPassword;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mCorperPassword.setError(getString(R.string.error_invalid_password));
            focusView = mCorperPassword;
            cancel = true;
        }


        // Check for a empty confirm password or if is the same with password
        if (TextUtils.isEmpty(corperConfirmPassword) || !corperConfirmPassword.equals(password)) {
            mCorperConfirmPassword.setError(getString(R.string.error_incorrect_password));
            focusView = mCorperConfirmPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mCorperEmail.setError(getString(R.string.error_field_required));
            focusView = mCorperEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mCorperEmail.setError(getString(R.string.error_invalid_email));
            focusView = mCorperEmail;
            cancel = true;
        }

        // Check for empty call up number
        if (TextUtils.isEmpty(callUpNumber)) {
            mCallUpNumber.setError(getString(R.string.error_field_required));
            focusView = mCallUpNumber;
            cancel = true;
        } else if (!isCallUpNoValid(callUpNumber)) {
            mCallUpNumber.setError("Call up number is invalid");
            focusView = mCallUpNumber;
            cancel = true;
        }

        // Check for empty firstname
        if (TextUtils.isEmpty(corperFirstName)) {
            mCorperFirstName.setError(getString(R.string.error_field_required));
            focusView = mCorperFirstName;
            cancel = true;
        }

        // Check for empty lastname
        if (TextUtils.isEmpty(corperLastName)) {
            mCorperLastName.setError(getString(R.string.error_field_required));
            focusView = mCorperLastName;
            cancel = true;
        }


        //check for empty date of birth
        if (TextUtils.isEmpty(dateOfBirth)) {
            mDateOfBirth.setError(getString(R.string.error_field_required));
            focusView = mDateOfBirth;
            cancel = true;
        }

        //check for empty number
        if (TextUtils.isEmpty(corperNumber)) {
            mCorperNumber.setError(getString(R.string.error_field_required));
            focusView = mCorperNumber;
            cancel = true;
        }

        //check for valid state not selected
        if (nyscState.equals("Select State")) {
            Toast.makeText(getApplicationContext(), "Pick a state", Toast.LENGTH_SHORT).show();
            focusView = mNyscState;
            cancel = true;
        }


        //check for empty corper institution
        if (TextUtils.isEmpty(corperInstitution)) {
            mCorperInstitution.setError(getString(R.string.error_field_required));
            focusView = mCorperInstitution;
            cancel = true;
        }

        //check for empty discipline
        if (TextUtils.isEmpty(corperDiscipline)) {
            mCorperDiscipline.setError(getString(R.string.error_field_required));
            focusView = mCorperDiscipline;
            cancel = true;
        }

        if (corperGender.equals("Select Gender")) {
            Toast.makeText(getApplicationContext(), "Pick a gender", Toast.LENGTH_SHORT).show();
            focusView = mCorperGender;
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

            Call<ResponseBody> register = ServiceGenerator.INSTANCE.getApiMethods().registerCorper(new Corper(callUpNumber,
                    corperFirstName, corperLastName, corperGender, dateOfBirth, corperNumber,
                    nyscState, corperInstitution, corperDiscipline, email, password, corperConfirmPassword));

            register.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @Nullable Response<ResponseBody> response) {

                    showProgress(false);
                    if (response != null) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    showProgress(false);
                }
            });
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isCallUpNoValid(String callUpNo) {

        return callUpNo.length() > 5;
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

