package com.example.librarymanagementsystem.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class User {
    private StringProperty userID;
    private StringProperty firstName;
    private StringProperty middleName;
    private StringProperty lastName;
    private StringProperty email;
    private StringProperty password;
    private StringProperty cellphoneNum;

    public User(StringProperty firstName, StringProperty middleName, StringProperty lastName, StringProperty email, StringProperty password, StringProperty cellphoneNum) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cellphoneNum = cellphoneNum;
    }

    public User() {

    }

    public String getUserID() {
        return userIDProperty().get();
    }

    public StringProperty userIDProperty() {
        if (userID == null) {
            userID = new SimpleStringProperty(this, "UserID");
        }
        return userID;
    }

    public void setUserID(String userID) {
        this.userIDProperty().set(userID);
    }

//    First Name
    public String getFirstName() {
        return firstNameProperty().get();
    }

    public StringProperty firstNameProperty() {
        if (firstName == null) {
            firstName = new SimpleStringProperty(this, "First Name");
        }
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstNameProperty().set(firstName);
    }

    public String getMiddleName() {
        return middleNameProperty().get();
    }

    public StringProperty middleNameProperty() {
        if (middleName == null) {
            middleName = new SimpleStringProperty(this, "Middle Name");
        }
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleNameProperty().set(middleName);
    }

    public String getLastName() {
        return lastNameProperty().get();
    }

    public StringProperty lastNameProperty() {
        if (lastName == null) {
            lastName = new SimpleStringProperty(this, "Last Name");
        }
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastNameProperty().set(lastName);
    }

    public String getEmail() {
        return emailProperty().get();
    }

    public StringProperty emailProperty() {
        if (email == null) {
            email = new SimpleStringProperty(this, "Email Address");
        }
        return email;
    }

    public void setEmail(String email) {
        this.emailProperty().set(email);
    }

    public String getPassword() {
        return passwordProperty().get();
    }

    public StringProperty passwordProperty() {
        if (password == null) {
            password = new SimpleStringProperty(this, "Password");
        }
        return password;
    }

    public void setPassword(String password) {
        this.passwordProperty().set(password);
    }

    public String getCellphoneNum() {
        return cellphoneNumProperty().get();
    }

    public StringProperty cellphoneNumProperty() {
        if (cellphoneNum == null) {
            cellphoneNum = new SimpleStringProperty(this, "Cellphone Number");
        }
        return cellphoneNum;
    }

    public void setCellphoneNum(String cellphoneNum) {
        this.cellphoneNumProperty().set(cellphoneNum);
    }

    public User(String userID,String firstName, String middleName, String lastName, String email, String password, String cellphoneNum) {
        setUserID(userID);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setCellphoneNum(cellphoneNum);
    }

}
