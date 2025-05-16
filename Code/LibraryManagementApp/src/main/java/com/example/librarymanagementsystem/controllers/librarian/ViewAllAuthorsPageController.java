package com.example.librarymanagementsystem.controllers.librarian;

import com.example.librarymanagementsystem.SceneController;
import com.example.librarymanagementsystem.model.Author;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.dao.AuthorDao.getAuthors;
import static com.example.librarymanagementsystem.util.TableViewUtil.getRow;

public class ViewAllAuthorsPageController  implements Initializable {
    @FXML
    private TableColumn<Author, Integer> columnAuthorID;

    @FXML
    private TableColumn<Author, String> columnFullName;

    @FXML
    private Button btnGoToUpdateAuthor;

    @FXML
    private TableView<Author> tableViewAuthors;

    @FXML
    private AnchorPane mainPanel;

    SceneController sceneController = new SceneController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnAuthorID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        columnFullName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        populateTable();
    }
    public void populateTable(){
        tableViewAuthors.setItems(getAuthors());
    }

    @FXML
    void goToUpdateAuthor(ActionEvent event) {
        Author author = getRow(tableViewAuthors);
        try {
            File file = new File("src/main/resources/com/example/librarymanagementsystem/views/librarian/updateAuthorPage.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root  = loader.load();
            UpdateAuthorPageController updateAuthorPageController = loader.getController();
//        load details onto controller
            updateAuthorPageController.loadData(author);
            sceneController.changePane(event, root ,mainPanel);
        } catch (Exception e){
            e.printStackTrace();
        }


    }

}

