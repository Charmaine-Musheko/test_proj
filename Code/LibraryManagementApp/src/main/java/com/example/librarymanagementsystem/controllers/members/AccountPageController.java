package com.example.librarymanagementsystem.controllers.members;

import com.example.librarymanagementsystem.SceneController;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import static com.example.librarymanagementsystem.Main.logout;

public class AccountPageController {

    @FXML
    private Button accountbutton;

    @FXML
    private Button booksbutton;

    @FXML
    private Button cartbutton;

    @FXML
    private TableColumn<?, ?> emailcol;

    @FXML
    private TableColumn<?, ?> firstnamecol;

    @FXML
    private TableColumn<?, ?> homeaddresscol;

    @FXML
    private Button homebutton;

    @FXML
    private TableColumn<?, ?> lastnamecol;

    @FXML
    private TableColumn<?, ?> telephonecol;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_firstName;

    @FXML
    private TextArea txt_homeAddress;

    @FXML
    private TextField txt_lastName;

    @FXML
    private TextField txt_telephone;

    @FXML
    private Button updateAccBtn;



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


    @FXML
    public void updatedetails(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/members/AccountPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("registration");
        stage.setScene(scene);
        stage.show();



        String first_name = txt_firstName.getText();
        String last_name = txt_lastName.getText();
        String email = txt_email.getText();
        String home_address = txt_homeAddress.getText();
        String telephone = txt_telephone.getText();

        AccountDao accDao = new AccountDao();
        accDao.insertRecord( first_name, last_name, email, home_address, telephone);


    }
    private String currentUser;

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
