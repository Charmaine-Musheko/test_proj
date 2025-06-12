package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.database.Database;
import com.example.librarymanagementsystem.util.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label loginMessageLabel;

    private Database db = new Database();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void loginButtonOnAction(ActionEvent event) {
        if (usernameTextField.getText().isEmpty() || enterPasswordField.getText().isEmpty()) {
            loginMessageLabel.setText("Please enter username and password");
            return;
        }
        if (db.validateLogin(usernameTextField.getText(), enterPasswordField.getText())) {
            loginMessageLabel.setText("Login successful!");
            SceneController.switchScene(event, "/com/example/librarymanagementsystem/views/dashboard.fxml");
        } else {
            loginMessageLabel.setText("Invalid username or password");
        }
    }

    @FXML
    public void registerButtonOnAction(ActionEvent event) {
        SceneController.switchScene(event, "/com/example/librarymanagementsystem/views/register.fxml");
    }
}