package com.example.librarymanagementsystem.controllers.librarian;

import com.example.librarymanagementsystem.Main;
import com.example.librarymanagementsystem.SceneController;
import com.example.librarymanagementsystem.util.SubmenuHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class LibMainPageController implements Initializable {


    SceneController sceneController = new SceneController();
    SubmenuHelper submenuHelper = new SubmenuHelper();

    @FXML
    private Button btnOpenViewBooks;

    @FXML
    public VBox bookSubMenu;

    @FXML
    private AnchorPane panelMain;

    @FXML
    private Button btnGoHome;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnOpenAccount;

    @FXML
    private Button btnOpenAddBook;

    @FXML
    private Button btnOpenApproveBorrow;

    @FXML
    private Button btnOpenApproveReturns;

    @FXML
    private Button btnOpenDeleteAuthor;

    @FXML
    private Button btnOpenViewAuthor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookSubMenu.setVisible(false);
        bookSubMenu.setManaged(false);
    }

    @FXML
    void goHome(ActionEvent event) {
        panelMain.getChildren().clear();
    }
    @FXML
    void openAccount(ActionEvent event) {

    }

//    Open pages that deal with Books
    @FXML
    void openBookSubMenu(ActionEvent event) {
        submenuHelper.manageMenu(bookSubMenu);
    }
    @FXML
    void openAddBook(ActionEvent event) {
        sceneController.changePane(event, "librarian/addBookPage.fxml", panelMain);
    }
    @FXML
    void openViewBooks(ActionEvent event) {
        sceneController.changePane(event, "librarian/viewAllBooksPage.fxml", panelMain);
    }

    @FXML
    void openApproveBorrow(ActionEvent event) {
        sceneController.changePane(event, "librarian/approveBorrowPage.fxml", panelMain);
    }

    @FXML
    void openApproveReturns(ActionEvent event) {
        sceneController.changePane(event, "librarian/approveReturnsPage.fxml", panelMain);
    }

//    Open the view author page

    @FXML
    void openViewAuthor(ActionEvent event) {
        sceneController.changePane(event, "librarian/viewAllAuthorsPage.fxml", panelMain);
    }

//    logout of the application
    @FXML
    void logout(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Main.logout(stage);
    }
}
