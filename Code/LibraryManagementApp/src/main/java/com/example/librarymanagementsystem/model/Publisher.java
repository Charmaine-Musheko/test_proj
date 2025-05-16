package com.example.librarymanagementsystem.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Publisher {
    private StringProperty publisher;
    private ObservableList<Book> booksPublished;

    public Publisher(StringProperty publisher, ObservableList<Book> booksPublished) {
        this.publisher = publisher;
        this.booksPublished = booksPublished;
    }

    public Publisher() {}

    public String getPublisher() {
        return publisherProperty().get();
    }

    public StringProperty publisherProperty() {
        if (publisher == null) {
            publisher = new SimpleStringProperty(this, "Publisher");
        }
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisherProperty().set(publisher);
    }

    public ObservableList<Book> getBooksPublished() {
        return booksPublished;
    }

    public void setBooksPublished(ObservableList<Book> booksPublished) {
        this.booksPublished = booksPublished;
    }
}
