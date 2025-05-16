package com.example.librarymanagementsystem.controllers;


import com.example.librarymanagementsystem.SceneController;
import com.example.librarymanagementsystem.dao.*;
import com.example.librarymanagementsystem.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import static com.example.librarymanagementsystem.util.AlertMessage.error;
import static com.example.librarymanagementsystem.util.AlertMessage.success;

public class LoginController {
    @FXML
    TextField txtFieldUsername;

    @FXML
    PasswordField txtFieldPassword;

    @FXML
    CheckBox checkBoxShowPass;

    @FXML
    Button btnLogin;

    @FXML
    Button btnSignUp;

    private Stage stage;
    private Scene scene;
    private Parent root;

    SceneController sceneController = new SceneController();

    public void login(ActionEvent event){
        User existingUser = new User();

        existingUser.setUserID(txtFieldUsername.getText());
        existingUser.setPassword(txtFieldPassword.getText());

        if (StudentDao.userExists(existingUser) || MemberDao.userExists(existingUser) || LecturerDao.userExists(existingUser)) {
            success("Success", "Welcome back " + existingUser.getUserID());
            txtFieldPassword.clear();
            txtFieldUsername.clear();
            sceneController.switchTo(event, "members/dashboard.fxml");
        } else if (LibrarianDao.userExists(existingUser)){
            success("Success", "Welcome back " + existingUser.getUserID());
            txtFieldPassword.clear();
            txtFieldUsername.clear();
            sceneController.switchTo(event, "librarian/libMainPage.fxml");
        }  else if (AdminDao.userExists(existingUser)){
            success("Success", "Welcome back " + existingUser.getUserID());
            txtFieldPassword.clear();
            txtFieldUsername.clear();
            sceneController.switchTo(event, "admin/Account.fxml");
        }
        else{
            error("Incorrect details entered", "Username or Password do not match please check that the information entered is correct");
        }
    }

    public void goToSignUp(ActionEvent event) {
        sceneController.switchTo(event, "register.fxml");
    }
}

