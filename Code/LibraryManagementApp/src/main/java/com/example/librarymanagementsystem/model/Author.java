package com.example.librarymanagementsystem.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Author {
    private IntegerProperty id;
    private StringProperty name;
    private ObservableList<Book> booksWritten;

    public Author(StringProperty name, ObservableList<Book> booksWritten) {
        this.name = name;
        this.booksWritten = booksWritten;
    }

    public Author() {}


    public String getName() {
        return nameProperty().get();
    }

    public StringProperty nameProperty() {
        if (name == null) {
            name = new SimpleStringProperty(this, "Author name");
        }
        return name;
    }

    public void setName(String name) {
        this.nameProperty().set(name);
    }

    public ObservableList<Book> getBooksWritten() {
        return booksWritten;
    }

    public int getId() {
        return idProperty().get();
    }

    public IntegerProperty idProperty() {
        if (id == null) {
            id = new SimpleIntegerProperty(this, "Author ID");
        }
        return id;
    }

    public void setId(int id) {
        this.idProperty().set(id);
    }

    public void setBooksWritten(ObservableList<Book> booksWritten) {
        this.booksWritten = booksWritten;
    }
}
