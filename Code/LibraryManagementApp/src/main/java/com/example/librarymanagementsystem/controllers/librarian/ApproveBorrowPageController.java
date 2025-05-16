package com.example.librarymanagementsystem.controllers.librarian;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Reservation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.dao.ReservationDao.*;

public class ApproveBorrowPageController implements Initializable {

    @FXML
    private Button btnApproveReservation;

    @FXML
    private Button btnDenyReservation;

    @FXML
    private TableColumn<Reservation, Date> columnDateReserved;

    @FXML
    private TableColumn<Reservation, String> columnISBN;

    @FXML
    private TableColumn<Reservation, String> columnLecturerID;

    @FXML
    private TableColumn<Reservation, String> columnMemberID;

    @FXML
    private TableColumn<Reservation, Integer> columnReserverationID;

    @FXML
    private TableColumn<Reservation, String> columnStudentID;

    @FXML
    private TableColumn<Reservation, String> columnTitle;

    @FXML
    private TableView<Reservation> tableReservedBooks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnReserverationID.setCellValueFactory(cellData -> cellData.getValue().reservationIDProperty().asObject());
        columnISBN.setCellValueFactory(cellData -> cellData.getValue().getReservedBook().isbnProperty());
        columnTitle.setCellValueFactory(cellData -> cellData.getValue().getReservedBook().titleProperty());
        columnStudentID.setCellValueFactory(cellData -> cellData.getValue().getStudent().userIDProperty());
        columnMemberID.setCellValueFactory(cellData -> cellData.getValue().getMember().userIDProperty());
        columnLecturerID.setCellValueFactory(cellData -> cellData.getValue().getLecture().userIDProperty());
        columnDateReserved.setCellValueFactory(cellData -> cellData.getValue().dateReservedProperty());

        populateTable();
//        handleRowSelect();

    }

    private void populateTable() {
        ObservableList<Reservation> reservations = getReservedBooks();
        tableReservedBooks.setItems(reservations);
    }

    @FXML
    void approveReservation(ActionEvent event) {
        ObservableList<Reservation> selectedItemsReservation = tableReservedBooks.getSelectionModel().getSelectedItems();
        Reservation reservation = selectedItemsReservation.get(0);
        System.out.println(reservation);
        approveBookReservation(reservation);
        populateTable();

    }

    @FXML
    void denyReservation(ActionEvent event) {
        ObservableList<Reservation> selectedItemsReservation = tableReservedBooks.getSelectionModel().getSelectedItems();
        Reservation selectedReservation = selectedItemsReservation.get(0);
        System.out.println(selectedReservation);
        denyBookReservation(selectedReservation);
        populateTable();
    }

}
