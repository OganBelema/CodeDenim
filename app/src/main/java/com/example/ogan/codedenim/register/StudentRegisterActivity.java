package com.example.ogan.codedenim.register;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ogan.codedenim.MyUtilClass;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.ServiceGenerator;
import com.example.ogan.codedenim.login.LoginActivity;
import com.example.ogan.codedenim.user.Student;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class StudentRegisterActivity extends AppCompatActivity {

    // UI references.
    private View mProgressView;
    private Spinner genderSpinner;
    private TextInputEditText mMatricNumber;
    private TextInputEditText mFirstName;
    private TextInputEditText mLastName;
    private EditText mDay;
    private EditText mMonth;
    private EditText mYear;
    private TextInputEditText mPhoneNumber;
    private TextInputEditText mInstitution;
    private TextInputEditText mDiscipline;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private TextInputEditText mConfirmPassword;
    private Button registerButton;

    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        genderSpinner = findViewById(R.id.spinner_gender_student);
        mMatricNumber = findViewById(R.id.matric_number_et);
        mFirstName = findViewById(R.id.first_name_student);
        mLastName = findViewById(R.id.last_name_student);
        mDay = findViewById(R.id.day_et);
        mMonth = findViewById(R.id.month_et);
        mYear = findViewById(R.id.year_et);
        mInstitution = findViewById(R.id.student_institution);
        mPhoneNumber = findViewById(R.id.mobile_number_student);
        mDiscipline = findViewById(R.id.student_discipline);
        mEmail = findViewById(R.id.student_email);
        mPassword = findViewById(R.id.student_password);
        mConfirmPassword = findViewById(R.id.student_confirm_password);

        mProgressView = findViewById(R.id.student_login_progress);

        registerButton = findViewById(R.id.student_register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyUtilClass.INSTANCE.checkNetworkConnection(getApplicationContext())){
                    attemptRegister();
                } else {
                    MyUtilClass.INSTANCE.showNoInternetMessage(StudentRegisterActivity.this);
                }
            }
        });

        TextView loginPage = findViewById(R.id.sign_in_tv);
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentRegisterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
        mMatricNumber.setError(null);
        mFirstName.setError(null);
        mLastName.setError(null);
        mDay.setError(null);
        mMonth.setError(null);
        mYear.setError(null);
        mPhoneNumber.setError(null);
        mInstitution.setError(null);
        mDiscipline.setError(null);
        mConfirmPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String matricNumber = mMatricNumber.getText().toString().trim();
        String firstname = mFirstName.getText().toString().trim();
        String lastName = mLastName.getText().toString().trim();
        String day = mDay.getText().toString().trim();
        String month = mMonth.getText().toString().trim();
        String year = mYear.getText().toString().trim();
        String phoneNumber = mPhoneNumber.getText().toString().trim();
        String institution = mInstitution.getText().toString().trim();
        String discipline = mDiscipline.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();
        String dateOfBirth = month + "/" + day + "/" + year;

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

        // Check for empty call up number
        if (TextUtils.isEmpty(matricNumber)) {
            mMatricNumber.setError(getString(R.string.error_field_required));
            focusView = mMatricNumber;
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
        if (TextUtils.isEmpty(day)) {
            mDay.setError(getString(R.string.error_field_required));
            focusView = mDay;
            cancel = true;
        } else if (Integer.valueOf(day) > 31 || Integer.valueOf(day) < 1){
            mDay.setError("Must be within 1 to 31");
            focusView = mDay;
            cancel = true;
        }

        if (TextUtils.isEmpty(month)) {
            mMonth.setError(getString(R.string.error_field_required));
            focusView = mMonth;
            cancel = true;
        } else  if (Integer.valueOf(month) > 12 || Integer.valueOf(month) < 1){
            mMonth.setError("Must be within 1 to 12");
            focusView = mMonth;
            cancel = true;
        }

        if (TextUtils.isEmpty(year)) {
            mYear.setError(getString(R.string.error_field_required));
            focusView = mYear;
            cancel = true;
        }

        //check for empty number
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumber.setError(getString(R.string.error_field_required));
            focusView = mPhoneNumber;
            cancel = true;
        }


        //check for empty corper institution
        if (TextUtils.isEmpty(institution)) {
            mInstitution.setError(getString(R.string.error_field_required));
            focusView = mInstitution;
            cancel = true;
        }

        //check for empty discipline
        if (TextUtils.isEmpty(discipline)) {
            mDiscipline.setError(getString(R.string.error_field_required));
            focusView = mDiscipline;
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
            showProgress();
            register(new Student(firstname, lastName, gender, dateOfBirth,
                    phoneNumber, email, password, confirmPassword, institution, discipline, matricNumber));
        }
    }

    private void register(Student student){
        ServiceGenerator.INSTANCE.getApiMethods().registerStudent(student)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @Nullable Response<ResponseBody> response) {

                        hideProgress();
                        if (response != null) {
                            System.out.println(response.message());
                            System.out.println(response.code());
                            System.out.println(response.errorBody());

                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(StudentRegisterActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            } else MyUtilClass.INSTANCE.showErrorMessage(StudentRegisterActivity.this, response, "ModelState");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @Nullable Throwable t) {

                        hideProgress();
                        if (t != null){
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }


    private boolean isPasswordValid(String password) {

        return password.length() > 5;
    }


    private void showProgress() {
       mProgressView.setVisibility(View.VISIBLE);
       registerButton.setEnabled(false);
    }

    private void hideProgress(){
        mProgressView.setVisibility(View.GONE);
        registerButton.setEnabled(true);
    }
}

