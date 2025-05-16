package com.example.librarymanagementsystem.controllers.librarian;

import com.example.librarymanagementsystem.SceneController;
import com.example.librarymanagementsystem.model.Author;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.dao.AuthorDao.updateAuthor;
import static com.example.librarymanagementsystem.util.AlertMessage.error;
import static com.example.librarymanagementsystem.util.AlertMessage.success;

public class UpdateAuthorPageController implements Initializable {

    private Author authorToBeUpdated;

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnUpdateAuthor;

    @FXML
    private TextField txtBoxAuthorID;

    @FXML
    private TextField txtBoxAuthorName;

    @FXML
    private AnchorPane mainPanel;

    SceneController sceneController = new SceneController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void loadData(Author author){
        this.authorToBeUpdated = author;
        txtBoxAuthorID.appendText(String.valueOf(this.authorToBeUpdated.getId()));
        txtBoxAuthorName.appendText(this.authorToBeUpdated.getName());
    }

    @FXML
    void goToViewAllBooks(ActionEvent event) {
        sceneController.changePane(event, "librarian/viewAllAuthorsPage.fxml", mainPanel);
    }

    @FXML
    void updateAuthorName(ActionEvent event) {
        this.authorToBeUpdated.setId(Integer.parseInt(txtBoxAuthorID.getText()));
        this.authorToBeUpdated.setName(txtBoxAuthorName.getText());

        int rows = updateAuthor(this.authorToBeUpdated);

        if (rows > 0){
            success("Success","Author Name updated successfully");
            goToViewAllBooks(event);
        } else{
            error("Error", "Author not updated. Check value(s) entered");
        }

    }

}
