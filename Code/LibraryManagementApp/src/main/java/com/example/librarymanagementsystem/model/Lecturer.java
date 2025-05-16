package com.example.librarymanagementsystem.model;

import javafx.beans.property.StringProperty;

// Governs the user Lecturer
public class Lecturer extends User {
    public Lecturer(StringProperty firstName, StringProperty middleName, StringProperty lastName, StringProperty email, StringProperty password, StringProperty cellphoneNum) {
        super(firstName, middleName, lastName, email, password, cellphoneNum);
    }

    public Lecturer(String userID, String firstName, String middleName, String lastName, String email, String password, String cellphoneNum) {
        setUserID(userID);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setCellphoneNum(cellphoneNum);
    }

    public Lecturer() {

    }
}

