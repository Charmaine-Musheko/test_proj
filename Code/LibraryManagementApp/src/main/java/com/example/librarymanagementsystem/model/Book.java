package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.errorHandling.InvalidBookException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Book {
    private StringProperty isbn;
    private StringProperty title;
    private ArrayList<Author> authors = new ArrayList<>();
    private IntegerProperty edition;
    private IntegerProperty yearPublished;
    private Publisher publisher;
    private StringProperty genre;
    private StringProperty type;
    private StringProperty status;

    public Book(StringProperty isbn, StringProperty title, ArrayList<Author> authors, IntegerProperty edition, IntegerProperty yearPublished, Publisher publisher, StringProperty genre, StringProperty type) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.edition = edition;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.genre = genre;
        this.type = type;
    }

    public Book(){}

    public Book(String isbn, String title, ArrayList<Author> authors, int edition, int yearPublished, Publisher publisher, String genre, String type){
        this.isbnProperty().set(isbn);
        this.titleProperty().set(title);
        this.authors.addAll(authors);
        this.editionProperty().set(edition);
        this.yearPublishedProperty().set(yearPublished);
        this.publisher  = publisher;
        this.genreProperty().set(genre);
        this.typeProperty().set(type);
    }

    public Book(String title, ArrayList<Author> authors, int edition, int yearPublished, Publisher publisher, String genre, String type){
        this.titleProperty().set(title);
        this.authors.addAll(authors);
        this.editionProperty().set(edition);
        this.yearPublishedProperty().set(yearPublished);
        this.publisher  = publisher;
        this.genreProperty().set(genre);
        this.typeProperty().set(type);
    }
    public Book(String isbn,String title, ArrayList<Author> authors, int edition, int yearPublished, Publisher publisher){
        this.isbnProperty().set(isbn);
        this.titleProperty().set(title);
        this.authors.addAll(authors);
        this.editionProperty().set(edition);
        this.yearPublishedProperty().set(yearPublished);
        this.publisher  = publisher;
    }
    public Book(StringProperty title, ArrayList<Author> authors, IntegerProperty edition, IntegerProperty yearPublished, Publisher publisher, StringProperty genre, StringProperty type) {
        this.title = title;
        assert false;
        this.authors = authors;
        this.edition = edition;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.genre = genre;
        this.type = type;
    }

    public String getStatus() {
        return statusProperty().get();
    }

    public StringProperty statusProperty() {
        if (status == null) {
            status = new SimpleStringProperty(this,"Status");
        }
        return status;
    }

    public void setStatus(String status) {
        this.statusProperty().set(status);
    }

    public String getIsbn() {
        return isbnProperty().get();
    }

    public StringProperty isbnProperty() {
        if (isbn == null) {
            isbn = new SimpleStringProperty(this, "ISBN");
        }
        return isbn;
    }

    public void setIsbn(String isbn) throws InvalidBookException {
        if (isbn.equals("") | isbn.equals(" ")){
            throw new InvalidBookException("ISBN cannot be empty");
        } else {
            this.isbnProperty().set(isbn);
        }

    }

    public String getTitle() {
        return titleProperty().get();
    }

    public StringProperty titleProperty() {
        if (title == null) {
            title = new SimpleStringProperty(this, "Title");
        }
        return title;
    }

    public void setTitle(String title) throws InvalidBookException{
        if (title.equals("") | title.equals(" ")){
            throw new InvalidBookException("Title cannot be empty");
        } else {
            this.titleProperty().set(title);
        }
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public int getEdition() {
        return editionProperty().get();
    }

    public IntegerProperty editionProperty() {
        if (edition == null) {
            edition = new SimpleIntegerProperty(this, "Year");
        }
        return edition;
    }

    public void setEdition(int edition) throws InvalidBookException{
        if (edition == 0) {
            throw new InvalidBookException("Edition cannot be zero");
        } else if (edition < 0){
            throw new InvalidBookException("Edition cannot be less than 0");
        } else {
            this.editionProperty().set(edition);
        }
    }

    public int getYearPublished() {
        return yearPublishedProperty().get();
    }

    public IntegerProperty yearPublishedProperty() {
        if (yearPublished == null) {
            yearPublished = new SimpleIntegerProperty(this, "Year");
        }
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) throws InvalidBookException{
        if (yearPublished > 2022){
            throw new InvalidBookException("Year published cannot be greater than the current year");
        } else if (yearPublished < 1901){
            throw new InvalidBookException("Year published cannot be before the 20th century");
        } else {
            this.yearPublishedProperty().set(yearPublished);
        }
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genreProperty().get();
    }

    public StringProperty genreProperty() {
        if (genre == null) {
            genre = new SimpleStringProperty(this, "Genre");
        }
        return genre;
    }

    public void setGenre(String genre) throws InvalidBookException{
        if (genre.equals("") | genre.equals(" ")){
            throw new InvalidBookException("Genre cannot be empty");
        } else {
            this.genreProperty().set(genre);
        }
    }

    public String getType() {
        return typeProperty().get();
    }

    public StringProperty typeProperty() {
        if (type == null) {
            type = new SimpleStringProperty(this, "Type");
        }
        return type;
    }

    public void setType(String type) throws InvalidBookException{
        if (type.equals("") | type.equals(" ")){
            throw new InvalidBookException("Book type cannot be empty");
        } else {
            this.typeProperty().set(type);
        }
    }
}
