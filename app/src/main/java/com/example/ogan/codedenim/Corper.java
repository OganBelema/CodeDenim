package com.example.ogan.codedenim;

/**
 * Created by ogan on 8/27/17.
 */

public class Corper {
    private String CallUpNumber;
    private String FirstName;
    private String LastName;
    private String Gender;
    private String DateOfBirth;
    private String MobileNumber;
    private String NyscState;
    private String Institution;
    private String Discipline;
    private String Email;
    private String password;
    private String confirmPassword;

    public Corper(String mCallUpNumber, String mCorperFirstName, String mCorperLastName, String mCorperGender, String mDateOfBirth,
           String mCorperNumber, String mNyscState, String mCorperInstitution, String mCorperDiscipline,
           String mCorperEmail, String mCorperPassword, String mCorperConfirmPassword){
        this.CallUpNumber = mCallUpNumber;
        this.FirstName = mCorperFirstName;
        this.LastName = mCorperLastName;
        this.Gender = mCorperGender;
        this.MobileNumber = mCorperNumber;
        this.DateOfBirth = mDateOfBirth;
        this.NyscState = mNyscState;
        this.Institution = mCorperInstitution;
        this.Discipline = mCorperDiscipline;
        this.Email = mCorperEmail;
        this.password = mCorperPassword;
        this.confirmPassword = mCorperConfirmPassword;
    }
}
