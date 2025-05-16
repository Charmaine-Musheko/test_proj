package com.example.librarymanagementsystem.controllers.admin;

import com.example.librarymanagementsystem.SceneController;
import com.example.librarymanagementsystem.model.Admin;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.dao.AdminDao.updateAdmin;
import static com.example.librarymanagementsystem.dao.AuthorDao.updateAuthor;
import static com.example.librarymanagementsystem.util.AlertMessage.error;
import static com.example.librarymanagementsystem.util.AlertMessage.success;

// This class acts as the controller class for the administrator's personal account page. In this class you will find a method for updating the admin's information
public class Account {

    private String currentUserID;

    public void setCurrentUserID(String currentUserID) {
        this.currentUserID = currentUserID;
    }

    private Admin ad;

    @FXML
    private Button AccountBtn;

    @FXML
    private AnchorPane AccountPage;

    @FXML
    private Button LecturerBtn;

    @FXML
    private Button LibrarianBtn;

    @FXML
    private Button LogoutBtn;

    @FXML
    private Button MemberBtn;

    @FXML
    private Button StudentBtn;

    @FXML
    private Button UpdateBtn;

    @FXML
    private TextField txt_CellphoneNumber;

    @FXML
    private TextField txt_Email;

    @FXML
    private TextField txt_FirstName;

    @FXML
    private TextField txt_ID;

    @FXML
    private TextField txt_LastName;

    @FXML
    private TextField txt_MiddleName;

    @FXML
    private TextField txt_Password;


    ObservableList<Admin> listAd;

    SceneController sceneController = new SceneController();
    // The following chain of action events are all methods used to change the page to the respective pages available
    // The first takes us to the librarian page
    @FXML
    void LibrarianPage(ActionEvent event) {
        sceneController.changePane(event, "admin/viewLibrarianPage.fxml", AccountPage);
    }

    // The second takes us to the admin's account page
    @FXML
    void AccountPage(ActionEvent event) {
        sceneController.changePane(event, "admin/Account.fxml", AccountPage);
    }

    // The third takes us to the student page
    @FXML
    void StudentPage(ActionEvent event) {
        sceneController.changePane(event, "admin/StudentPage.fxml", AccountPage);
    }

    // The fourth takes us to the lecturer page
    @FXML
    void LecturerPage(ActionEvent event) {
        sceneController.changePane(event, "admin/LecturerPage.fxml", AccountPage);
    }

    //The last takes us to the member page
    @FXML
    void MemberPage(ActionEvent event) {
        sceneController.changePane(event, "admin/MemberPage.fxml", AccountPage);
    }



    public void loadData(Admin admin){
        this.ad = admin;
        txt_ID.appendText(String.valueOf(this.ad.getUserID()));
        txt_FirstName.appendText(String.valueOf(this.ad.getFirstName()));
        txt_MiddleName.appendText(this.ad.getMiddleName());
        txt_LastName.appendText(String.valueOf(this.ad.getLastName()));
        txt_Email.appendText(String.valueOf(this.ad.getEmail()));
        txt_CellphoneNumber.appendText(String.valueOf(this.ad.getCellphoneNum()));
        txt_Password.appendText(String.valueOf(this.ad.getPassword()));
    }

    @FXML
    void updateAdminTable(ActionEvent event) {
        this.ad.setUserID(txt_ID.getText());
        this.ad.setFirstName(txt_FirstName.getText());
        this.ad.setMiddleName(txt_MiddleName.getText());
        this.ad.setLastName(txt_LastName.getText());
        this.ad.setEmail(txt_Email.getText());
        this.ad.setCellphoneNum(txt_CellphoneNumber.getText());
        this.ad.setPassword(txt_Password.getText());

        int rows = updateAdmin(this.ad);

        if (rows > 0){
            success("Success","Admin Data updated successfully");
            LibrarianPage(event);
        } else{
            error("Error", "Admin not updated. Check value(s) entered");
        }

    }


    public void initialize(URL url, ResourceBundle rb){

    }
}
