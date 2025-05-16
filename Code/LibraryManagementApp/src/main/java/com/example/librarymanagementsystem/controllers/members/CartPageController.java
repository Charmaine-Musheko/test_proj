package com.example.librarymanagementsystem.controllers.members;

import com.example.librarymanagementsystem.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static com.example.librarymanagementsystem.Main.logout;

public class CartPageController {
    private String currentUser;

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    @FXML
    private Button accountbutton;

    @FXML
    private Button booksbutton;

    @FXML
    private Button cartbutton;

    @FXML
    private Button homebutton;

    @FXML
    private Button logoutbutton;


    SceneController sceneController = new SceneController();

    @FXML
    void switchtohome(ActionEvent event) throws Exception{
        sceneController.switchTo(event, "members/dashboard.fxml");
    }

    @FXML
    void logoutbutton(ActionEvent event) throws Exception{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        logout(stage);
    }

    @FXML
    void switchtoaccount(ActionEvent event) throws Exception{
        sceneController.switchTo(event, "members/AccountPage.fxml");
    }

    @FXML
    void switchtobooks(ActionEvent event) throws Exception{
        sceneController.switchTo(event, "members/BookPage.fxml");
    }

    @FXML
    void switchtocart(ActionEvent event) throws Exception{
        sceneController.switchTo(event, "members/CartPage.fxml");
    }

}

