package com.example.librarymanagementsystem.model;

import javafx.beans.property.StringProperty;

// Governs the user Admin
public class Admin extends User {
    public Admin(StringProperty firstName, StringProperty middleName, StringProperty lastName, StringProperty email, StringProperty password, StringProperty cellphoneNum) {
        super(firstName, middleName, lastName, email, password, cellphoneNum);
    }

    public Admin(String userID, String firstName, String middleName, String lastName, String email, String password, String cellphoneNum) {
        setUserID(userID);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setCellphoneNum(cellphoneNum);
    }

    public Admin() {

    }
}
