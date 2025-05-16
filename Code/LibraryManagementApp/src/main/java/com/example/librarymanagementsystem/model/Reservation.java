package com.example.librarymanagementsystem.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Date;

public class Reservation{
    IntegerProperty reservationID;
    Book reservedBook;
    User student;
    User member;
    User lecture;
    ObjectProperty<Date> dateReserved;


    public Reservation(){}

    public int getReservationID() {
        return reservationIDProperty().get();
    }

    public IntegerProperty reservationIDProperty() {
        if (reservationID == null) {
            reservationID = new SimpleIntegerProperty(this, "Reservation ID");
        }
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationIDProperty().set(reservationID);
    }

    public Book getReservedBook() {
        return reservedBook;
    }

    public void setReservedBook(Book reservedBook) {
        this.reservedBook = reservedBook;
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

    public Date getDateReserved() {
        return dateReservedProperty().get();
    }

    public ObjectProperty<Date> dateReservedProperty() {
        if (dateReserved == null) {
            dateReserved = new SimpleObjectProperty<>(this, "dateReserved");
        }
        return dateReserved;
    }

    public void setDateReserved(Date dateReserved) {
        this.dateReservedProperty().set(dateReserved);
    }

//    @Override
//    public String toString() {
//        return "Reservation{" +
//                "reservationID=" + reservationID.toString() +
//                ", reservedBook=" + reservedBook.getIsbn() +
//                ", student=" + student.getUserID() +
//                ", member=" + member.getUserID() +
//                ", lecture=" + lecture.getUserID() +
//                ", dateReserved=" + dateReserved.toString() +
//                '}';
//    }
}
