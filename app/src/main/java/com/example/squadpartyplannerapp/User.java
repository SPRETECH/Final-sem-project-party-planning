package com.example.squadpartyplannerapp;

public class User {
    public String FirstName,LastName,EmailID,PhoneNo,Password,ProfileURL;

    public User() {
    }

    public User(String firstName, String lastName, String emailID, String phoneNo, String password, String profileURL) {
        FirstName = firstName;
        LastName = lastName;
        EmailID = emailID;
        PhoneNo = phoneNo;
        Password = password;
        ProfileURL = profileURL;
    }
}
