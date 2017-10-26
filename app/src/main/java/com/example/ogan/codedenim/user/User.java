package com.example.ogan.codedenim.user;

/**
 * Created by belema on 10/25/17.
 */

public class User {
    private String FirstName;
    private String LastName;
    private String Gender;
    private String DateOfBirth;
    private String MobileNumber;
    private String Email;
    private String password;
    private String confirmPassword;

    public User(String firstName, String lastName, String gender, String dateOfBirth,
                String mobileNumber, String email, String password, String confirmPassword) {
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        DateOfBirth = dateOfBirth;
        MobileNumber = mobileNumber;
        Email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
