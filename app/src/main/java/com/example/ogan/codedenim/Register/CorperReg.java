package com.example.ogan.codedenim.Register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
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

import com.example.ogan.codedenim.Corper;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.ServiceGenerator;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class CorperReg extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

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

    CorperRegisterApi corperRegisterApi;

    static final String url = "https://codedenim.azurewebsites.net/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corper_reg);
        // Set up the login form.
        mCorperEmail = (TextInputEditText) findViewById(R.id.corper_email);


        mCallUpNumber = (TextInputEditText) findViewById(R.id.call_up_number);
        mCorperFirstName = (TextInputEditText) findViewById(R.id.first_name);
        mCorperLastName = (TextInputEditText) findViewById(R.id.last_name);
        mCorperGender = (Spinner) findViewById(R.id.spinner_gender);
        mDateOfBirth = (TextInputEditText) findViewById(R.id.date_of_birth);
        mCorperNumber = (TextInputEditText) findViewById(R.id.mobile_number);
        mNyscState = (Spinner) findViewById(R.id.nysc_state);
        mCorperInstitution = (TextInputEditText) findViewById(R.id.corper_institution);
        mCorperDiscipline = (TextInputEditText) findViewById(R.id.corper_discipline);
        mCorperPassword = (TextInputEditText) findViewById(R.id.corper_password);
        mCorperConfirmPassword = (TextInputEditText) findViewById(R.id.corper_confirm_password);

        Button mEmailSignInButton = (Button) findViewById(R.id.corper_register_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.corper_register_form);
        mProgressView = findViewById(R.id.corper_register_progress);
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
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

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
        String corperFirstName= mCorperFirstName.getText().toString().trim();
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
        } else if(!isPasswordValid(password)){
            mCorperPassword.setError(getString(R.string.error_invalid_password));
            focusView = mCorperPassword;
            cancel = true;
        }


        // Check for a empty confirm password or if is the same with password
        if (TextUtils.isEmpty(corperConfirmPassword) || !corperConfirmPassword.equals(password)) {
            mCorperPassword.setError(getString(R.string.error_incorrect_password));
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
            mAuthTask = new UserLoginTask(callUpNumber, corperFirstName, corperLastName,
                    corperGender, dateOfBirth, corperNumber,
                    nyscState, corperInstitution, corperDiscipline,
                    email, password, corperConfirmPassword);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
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
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final String mCallUpNumber;
        private final String mCorperFirstName;
        private final String mCorperLastName;
        private final String mCorperGender;
        private final String mDateOfBirth;
        private final String mCorperNumber;
        private final String mNyscState;
        private final String mCorperInstitution;
        private final String mCorperDiscipline;
        private final String mCorperConfirmPassword;

        UserLoginTask(String mCallUpNumber, String mCorperFirstName, String mCorperLastName,
                      String mCorperGender, String mDateOfBirth, String mCorperNumber,
                      String mNyscState, String mCorperInstitution, String mCorperDiscipline,
                      String email, String password, String mCorperConfirmPassword) {
            mEmail = email;
            mPassword = password;
            this.mCallUpNumber = mCallUpNumber;
            this.mCorperFirstName = mCorperFirstName;
            this.mCorperLastName = mCorperLastName;
            this.mCorperGender = mCorperGender;
            this.mDateOfBirth = mDateOfBirth;
            this.mCorperNumber = mCorperNumber;
            this.mNyscState = mNyscState;
            this.mCorperInstitution = mCorperInstitution;
            this.mCorperDiscipline = mCorperDiscipline;
            this.mCorperConfirmPassword = mCorperConfirmPassword;

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.


            corperRegisterApi = ServiceGenerator.createService(CorperRegisterApi.class);

            Call<ResponseBody> register = corperRegisterApi.registerCorper(new Corper(mCallUpNumber,
                    mCorperFirstName,mCorperLastName,mCorperGender,mDateOfBirth,mCorperNumber,
                    mNyscState,mCorperInstitution,mCorperDiscipline,mEmail,mPassword,mCorperConfirmPassword));

            try{
                Response<ResponseBody> response = register.execute();
                if (response.isSuccessful()) {

                    try {
                        // get String from response
                        String stringResponse = response.body().string();
                        System.out.println(response.message());
                        System.out.println("headers: " + response.headers());
                        System.out.println("raw: " + response.raw());
                        System.out.println(stringResponse);
                        //finish();
                        return true;
                        // Do whatever you want with the String
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {

                    System.out.println(response.message());
                    System.out.println("headers: " + response.headers());
                    System.out.println("raw: " + response.raw());
                    Toast.makeText(getApplicationContext(), "Registration Failed due to " + response.errorBody(), Toast.LENGTH_SHORT).show();

                }
            } catch (IOException e){
                e.printStackTrace();
            }


            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Error registering", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

