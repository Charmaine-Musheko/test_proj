package com.example.librarymanagementsystem.controllers.librarian;

import com.example.librarymanagementsystem.model.BookReturn;
import com.example.librarymanagementsystem.model.Reservation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.dao.BookReturnDao.*;
import static com.example.librarymanagementsystem.dao.ReservationDao.getReservedBooks;

public class ApproveReturnsPageController implements Initializable {

    @FXML
    private Button btnApproveReturn;

    @FXML
    private Button btnDenyReturn;

    @FXML
    private TableColumn<BookReturn, Date> columnDateReturned;

    @FXML
    private TableColumn<BookReturn, String> columnISBN;

    @FXML
    private TableColumn<BookReturn, String> columnLecturer;

    @FXML
    private TableColumn<BookReturn, String> columnMember;

    @FXML
    private TableColumn<BookReturn, Integer> columnReturnID;

    @FXML
    private TableColumn<BookReturn, String> columnStudent;

    @FXML
    private TableColumn<BookReturn, String> columnTitle;

    @FXML
    private TableView<BookReturn> tableReturnedBooks;

    @FXML
    private AnchorPane panelMain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnReturnID.setCellValueFactory(cellData -> cellData.getValue().returnIDProperty().asObject());
        columnISBN.setCellValueFactory(cellData -> cellData.getValue().getReturnedBook().isbnProperty());
        columnTitle.setCellValueFactory(cellData -> cellData.getValue().getReturnedBook().titleProperty());
        columnStudent.setCellValueFactory(cellData -> cellData.getValue().getStudent().userIDProperty());
        columnMember.setCellValueFactory(cellData -> cellData.getValue().getMember().userIDProperty());
        columnLecturer.setCellValueFactory(cellData -> cellData.getValue().getLecture().userIDProperty());
        columnDateReturned.setCellValueFactory(cellData -> cellData.getValue().dateReturnedProperty());

        populateTable();
    }

    private void populateTable() {
        ObservableList<BookReturn> reservations = getReturnedBooks();
        tableReturnedBooks.setItems(reservations);
    }

    @FXML
    void approveReturn(ActionEvent event) {
        BookReturn selectedReturnedBook = tableReturnedBooks.getSelectionModel().getSelectedItem();
        approveBookReturn(selectedReturnedBook);

    }

    @FXML
    void denyReservation(ActionEvent event) {
        BookReturn selectedReturnedBook = tableReturnedBooks.getSelectionModel().getSelectedItem();
        denyBookReturn(selectedReturnedBook);
    }

}
