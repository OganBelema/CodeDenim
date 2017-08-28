package com.example.ogan.codedenim.Register;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ogan.codedenim.Corper;
import com.example.ogan.codedenim.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CorperRegister extends AppCompatActivity {

    private TextInputEditText mCallUpNumber;
    private TextInputEditText mCorperFirstName;
    private TextInputEditText mCorperLastName;
    private TextInputEditText mCorperGender;
    private TextInputEditText mDateOfBirth;
    private TextInputEditText mCorperNumber;
    private TextInputEditText mNyscState;
    private TextInputEditText mCorperInstitution;
    private TextInputEditText mCorperDiscipline;
    private TextInputEditText mCorperEmail;
    private TextInputEditText mCorperPassword;
    private TextInputEditText mCorperConfirmPassword;
    private Button mRegisterButton;

    CorperRegisterApi corperRegisterApi;

    static final String url = "https://codedenim.azurewebsites.net/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corper_register);

        mCallUpNumber = (TextInputEditText) findViewById(R.id.call_up_number);
        mCorperFirstName = (TextInputEditText) findViewById(R.id.first_name);
        mCorperLastName = (TextInputEditText) findViewById(R.id.last_name);
        mCorperGender = (TextInputEditText) findViewById(R.id.gender);
        mDateOfBirth = (TextInputEditText) findViewById(R.id.date_of_birth);
        mCorperNumber = (TextInputEditText) findViewById(R.id.mobile_number);
        mNyscState = (TextInputEditText) findViewById(R.id.nysc_state);
        mCorperInstitution = (TextInputEditText) findViewById(R.id.corper_institution);
        mCorperDiscipline = (TextInputEditText) findViewById(R.id.corper_discipline);
        mCorperEmail = (TextInputEditText) findViewById(R.id.corper_email);
        mCorperPassword = (TextInputEditText) findViewById(R.id.corper_password);
        mCorperConfirmPassword = (TextInputEditText) findViewById(R.id.corper_confirm_password);

        mRegisterButton = (Button) findViewById(R.id.corper_register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked");
                Retrofit retrofit = new Retrofit.Builder().baseUrl(url).
                        addConverterFactory(GsonConverterFactory.create()).build();

                corperRegisterApi = retrofit.create(CorperRegisterApi.class);

                Call<ResponseBody> register = corperRegisterApi.registerCorper(new Corper(mCallUpNumber.getText().toString().trim(),
                        mCorperFirstName.getText().toString().trim(),
                        mCorperLastName.getText().toString().trim(),
                        mCorperGender.getText().toString().trim(),
                        mDateOfBirth.getText().toString().trim(),
                        mCorperNumber.getText().toString().trim(),
                        mNyscState.getText().toString().trim(),
                        mCorperInstitution.getText().toString().trim(),
                        mCorperDiscipline.getText().toString().trim(),
                        mCorperEmail.getText().toString().trim(),
                        mCorperPassword.getText().toString().trim(),
                        mCorperConfirmPassword.getText().toString().trim()));

                register.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        System.out.println(response.message());
                        System.out.println(response.errorBody());
                        System.out.println(response.code());
                        System.out.println(response.headers());
                        System.out.println(response.raw());

                        if (response.isSuccessful()) {
                            try {
                                // get String from response
                                String stringResponse = response.body().string();
                                System.out.println(response.message());
                                System.out.println("headers: " + response.headers());
                                System.out.println("raw: " + response.raw());
                                System.out.println(stringResponse);
                                finish();
                                // Do whatever you want with the String
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        System.out.println("error" + t.getMessage());

                    }
                });
            }
        });

    }
}
