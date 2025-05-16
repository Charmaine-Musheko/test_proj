package com.example.librarymanagementsystem.controllers.librarian;

import com.example.librarymanagementsystem.SceneController;
import com.example.librarymanagementsystem.dao.BookDao;
import com.example.librarymanagementsystem.model.Book;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.dao.BookDao.deleteBook;
import static com.example.librarymanagementsystem.dao.BookDao.getAllBooksLibrarian;
import static com.example.librarymanagementsystem.util.AlertMessage.success;
import static com.example.librarymanagementsystem.util.TableViewUtil.getRow;

public class ViewAllBooksPageController implements Initializable {

    @FXML
    private TableColumn<Book, String> columnAuthor;

    @FXML
    private TableColumn<Book, Integer> columnEdition;

    @FXML
    private TableColumn<Book, String> columnISBN;

    @FXML
    private TableColumn<Book, String> columnPublisher;

    @FXML
    private TableColumn<Book, String> columnStatus;

    @FXML
    private TableColumn<Book, String> columnTitle;

    @FXML
    private TableColumn<Book, Integer> columnYearPublished;

    @FXML
    private TableView<Book> tabelAllBooks;

    @FXML
    private Button btnDeleteBook;

    @FXML
    private Button btnGoToUpdateBook;

    @FXML
    private AnchorPane mainPanel;

    SceneController sceneController = new SceneController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnISBN.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        columnTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        columnAuthor.setCellValueFactory(cellData -> cellData.getValue().getAuthors().get(0).nameProperty());
        columnEdition.setCellValueFactory(cellData -> cellData.getValue().editionProperty().asObject());
        columnPublisher.setCellValueFactory(cellData -> cellData.getValue().getPublisher().publisherProperty());
        columnYearPublished.setCellValueFactory(cellData -> cellData.getValue().yearPublishedProperty().asObject());
        columnStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        populateTable();

    }

    private void populateTable() {
        ObservableList<Book> books = getAllBooksLibrarian();
        tabelAllBooks.setItems(books);
    }

    @FXML
    void deleteSelectedBook(ActionEvent event) {
        Book book = getRow(tabelAllBooks);
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setContentText(String.format(
                    """
                        Are you sure you want to delete:
                        id - %s
                        title - %s
                        author - %s
                        """, book.getIsbn(), book.getTitle(), book.getAuthors().get(0).getName()));
        if (confirmation.showAndWait().get() == ButtonType.OK) {
            String message = deleteBook(book);
            success("Success", message);
            refreshTable();
        }

    }

    @FXML
    void goToUpdateBook(ActionEvent event) {
        // Get selected row
        Book book = getRow(tabelAllBooks);
        // Get next page controller
        try {
            File file = new File("src/main/resources/com/example/librarymanagementsystem/views/librarian/updateBookPage.fxml");
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            Parent root  = loader.load();
            UpdateBookPageController updateBookPageController = loader.getController();
//        load details onto controller
            updateBookPageController.loadDetails(book);
            sceneController.changePane(event, root ,mainPanel);
        } catch (Exception e){
            e.printStackTrace();
        }
//        change panel
    }

    public void refreshTable(){
        tabelAllBooks.getItems().clear();
        populateTable();
    }

}
