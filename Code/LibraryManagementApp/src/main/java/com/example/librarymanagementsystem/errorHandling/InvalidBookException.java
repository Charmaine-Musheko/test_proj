package com.example.librarymanagementsystem.errorHandling;

public class InvalidBookException extends Exception {

    public InvalidBookException(String message) {
        super(message);
    }
}
