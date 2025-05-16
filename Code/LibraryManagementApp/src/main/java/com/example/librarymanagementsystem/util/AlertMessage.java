package com.example.librarymanagementsystem.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertMessage {

    public static void success(String title, String message){
        Alert successMessage = new Alert(Alert.AlertType.NONE,title, ButtonType.OK);
        successMessage.setTitle(title);
        successMessage.setContentText(message);
//        successMessage.setGraphic();
        successMessage.showAndWait();

    }

    public static void error(String title, String message){
        Alert errorMessage = new Alert(Alert.AlertType.ERROR);
        errorMessage.setTitle(title);
        errorMessage.setContentText(message);
        errorMessage.showAndWait();
    }
}
