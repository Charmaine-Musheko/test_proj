package com.example.librarymanagementsystem.util;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class TableViewUtil {

    public static <T> T getRow(TableView<T> tableView){
        return tableView.getSelectionModel().getSelectedItem();
    }

    public static <T> void populateTable(ObservableList<T> observableList, TableView<T> tableView){
        tableView.setItems(observableList);
    }

    public static <T> void refreshTable(TableView<T> tableView, ObservableList<T> observableList){
        tableView.getItems().clear();
        tableView.setItems(observableList);
    }
}
