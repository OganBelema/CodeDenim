package com.example.ogan.codedenim.user;

/**
 * Created by ogan on 8/27/17.
 */

public class Corper extends User{
    private String CallUpNumber;
    private String NyscState;
    private String Institution;
    private String Discipline;

    public Corper(String firstName, String lastName, String gender, String dateOfBirth,
                  String mobileNumber, String email, String password, String confirmPassword,
                  String callUpNumber, String nyscState, String institution, String discipline) {
        super(firstName, lastName, gender, dateOfBirth, mobileNumber, email, password, confirmPassword);
        CallUpNumber = callUpNumber;
        NyscState = nyscState;
        Institution = institution;
        Discipline = discipline;
    }

}
