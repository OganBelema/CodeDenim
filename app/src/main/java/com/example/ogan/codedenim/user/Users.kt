package com.example.ogan.codedenim.user

/**
 * Created by belema on 10/25/17.
 */

open class User(private val FirstName: String, private val LastName: String, private val Gender: String, private val DateOfBirth: String,
                private val MobileNumber: String, private val Email: String, private val Password: String, private val ConfirmPassword: String)

class Student(firstName: String, lastName: String, gender: String, dateOfBirth: String, mobileNumber: String, email: String, password: String, confirmPassword: String, private val Institution: String, private val Discpline: String, private val MatNumber: String) : User(firstName, lastName, gender, dateOfBirth, mobileNumber, email, password, confirmPassword)

class Corp(firstName: String, lastName: String, gender: String, dateOfBirth: String,
           mobileNumber: String, email: String, password: String, confirmPassword: String,
           private val CallUpNumber: String, private val NyscState: String, private val Institution: String, private val Discipline: String) : User(firstName, lastName, gender, dateOfBirth, mobileNumber, email, password, confirmPassword)