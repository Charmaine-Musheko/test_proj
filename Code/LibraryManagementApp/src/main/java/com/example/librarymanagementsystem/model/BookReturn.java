package com.example.librarymanagementsystem.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Date;

public class BookReturn {
    IntegerProperty returnID;
    Book returnedBook;
    User student;
    User member;
    User lecture;
    ObjectProperty<Date> dateReturned;

    public int getReturnID() {
        return returnIDProperty().get();
    }

    public IntegerProperty returnIDProperty() {
        if (returnID == null) {
            returnID = new SimpleIntegerProperty(this, "Return ID");
        }
        return returnID;
    }

    public void setReturnID(int returnID) {
        this.returnIDProperty().set(returnID);
    }

    public Book getReturnedBook() {
        return returnedBook;
    }

    public void setReturnedBook(Book returnedBook) {
        this.returnedBook = returnedBook;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public User getLecture() {
        return lecture;
    }

    public void setLecture(User lecture) {
        this.lecture = lecture;
    }

    public Date getDateReturned() {
        return dateReturnedProperty().get();
    }

    public ObjectProperty<Date> dateReturnedProperty() {
        if (dateReturned == null) {
            dateReturned = new SimpleObjectProperty<>(this, "Date returned");
        }
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturnedProperty().set(dateReturned);
    }
}
