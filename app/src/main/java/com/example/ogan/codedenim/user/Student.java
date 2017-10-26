package com.example.ogan.codedenim.user;

/**
 * Created by belema on 10/26/17.
 */

public class Student extends User {

    private String Institution;
    private String Discpline;
    private String MatNumber;

    public Student(String firstName, String lastName, String gender, String dateOfBirth, String mobileNumber, String email, String password, String confirmPassword, String institution, String discpline, String matNumber) {
        super(firstName, lastName, gender, dateOfBirth, mobileNumber, email, password, confirmPassword);
        Institution = institution;
        Discpline = discpline;
        MatNumber = matNumber;
    }
}
