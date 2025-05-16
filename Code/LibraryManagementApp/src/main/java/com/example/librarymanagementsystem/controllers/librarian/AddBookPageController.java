package com.example.librarymanagementsystem.controllers.librarian;

import com.example.librarymanagementsystem.errorHandling.InvalidBookException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Publisher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.example.librarymanagementsystem.dao.BookDao.addBook;
import static com.example.librarymanagementsystem.util.AlertMessage.error;
import static com.example.librarymanagementsystem.util.AlertMessage.success;
import static com.example.librarymanagementsystem.util.DBHelper.connection;

public class AddBookPageController implements Initializable {


    @FXML
    private Button btnAddNewBook;

    @FXML
    private Button btnCancel;

    @FXML
    private ComboBox<String> comboBoxBookType;

    @FXML
    private ComboBox<String> comboBoxPublisher;

    @FXML
    private AnchorPane panelAddBook;

    @FXML
    private TextField txtFieldAuthor;

    @FXML
    private TextField txtFieldBookTitle;

    @FXML
    private TextField txtFieldEdtion;

    @FXML
    private TextField txtFieldGenre;

    @FXML
    private TextField txtFieldYearPublished;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxBookType.setEditable(true);
        comboBoxPublisher.setEditable(true);
        setPublisherData();
        setTypeData();
    }

    @FXML
    void addNewBook(ActionEvent event) {
        Publisher bookPublisher = new Publisher();
        bookPublisher.setPublisher(comboBoxPublisher.getValue());
        Author bookAuthor = new Author();
        bookAuthor.setName(txtFieldAuthor.getText());
        ArrayList<Author> authors = new ArrayList<>();
        authors.add(bookAuthor);

        Book newBook = new Book();
        try {

            newBook.setTitle(txtFieldBookTitle.getText());
            if (txtFieldAuthor.getText().equals("")){
                throw new InvalidBookException("A Book MUST have an Author");
            }
            newBook.setAuthors(authors);
            newBook.setGenre(txtFieldGenre.getText());
            if ((comboBoxPublisher.getSelectionModel().isEmpty() && comboBoxPublisher.getValue() == null) || comboBoxPublisher.getValue().isEmpty()){
                throw new InvalidBookException("Publisher cannot be empty");
            }
            newBook.setPublisher(bookPublisher);
            if ((comboBoxBookType.getSelectionModel().isEmpty() && comboBoxBookType.getValue() == null) || comboBoxPublisher.getValue().isEmpty()){
                throw new InvalidBookException("Book type cannot be empty");
            }
            newBook.setType(comboBoxBookType.getValue());
            if (txtFieldYearPublished.getText().equals("")){
                throw new InvalidBookException("Year published cannot be empty");
            } else if (txtFieldYearPublished.getText().matches("[0-9]")){
                throw new InvalidBookException("Year published must be a number");
            }
            newBook.setYearPublished(Integer.parseInt(txtFieldYearPublished.getText().trim()));
            if (txtFieldEdtion.getText().equals("")){
                throw new InvalidBookException("Edition cannot be empty");
            } else if (!txtFieldEdtion.getText().matches("[0-9]")){
                throw new InvalidBookException("Edition must be a number");
            }
            newBook.setEdition(Integer.parseInt(txtFieldEdtion.getText().trim()));


            int rows = addBook(newBook);
            if (rows > 0) {
                success("Success", "Book added successfully");
                txtFieldGenre.clear();
                txtFieldEdtion.clear();
                txtFieldYearPublished.clear();
                txtFieldBookTitle.clear();
                txtFieldAuthor.clear();
                comboBoxPublisher.setValue(null);
                comboBoxBookType.setValue(null);
            } else{
                error("Error", "Book not added");
            }
        } catch (InvalidBookException e){
            error("Invalid Book Details", e.getMessage());
        } catch (NumberFormatException ex){
            error("Invalid Input", ex.getMessage() + "Entered input must be a number");
        }
    }

    @FXML
    void closeForm(ActionEvent event) {

    }

    public void setPublisherData() {
        ResultSet resultSet;
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try(Connection conn = connection()){
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("select publisher_name from publisher");
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                observableList.add(resultSet.getString("publisher_name"));
            }
            comboBoxPublisher.getItems().addAll(observableList);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void setTypeData() {
        ResultSet resultSet;
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try(Connection conn = connection()){
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("select type_name from book_type");
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                observableList.add(resultSet.getString("type_name"));
            }
            comboBoxBookType.getItems().addAll(observableList);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
