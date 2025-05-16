package com.example.librarymanagementsystem.dao;

import com.example.librarymanagementsystem.errorHandling.InvalidBookException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Reservation;
import com.example.librarymanagementsystem.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static com.example.librarymanagementsystem.util.AlertMessage.error;
import static com.example.librarymanagementsystem.util.DBHelper.connection;

public class ReservationDao {
    public static void approveBookReservation(Reservation reservation){
        try(Connection conn = connection()){
            assert conn != null;
            CallableStatement callableStatement = conn.prepareCall("{call approve_reservation(?,?,?,?,?)}");

            callableStatement.setInt(1, reservation.getReservationID());
            callableStatement.setString(2, reservation.getReservedBook().getIsbn());
            callableStatement.setString(3, reservation.getStudent().getUserID());
            callableStatement.setString(4, reservation.getMember().getUserID());
            callableStatement.setString(5, reservation.getLecture().getUserID());

            callableStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void denyBookReservation(Reservation reservation){
        try(Connection conn = connection()){
            assert conn != null;
            CallableStatement callableStatement = conn.prepareCall("{call deny_reservation(?,?)}");

            callableStatement.setInt(1, reservation.getReservationID());
            callableStatement.setString(2, reservation.getReservedBook().getIsbn());

            callableStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ObservableList<Reservation> getReservedBooks(){
        ResultSet reservedBooksResultSet = null;
        ObservableList<Reservation> reservedBooksList = FXCollections.observableArrayList();
        try (Connection conn = connection()){
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement("select reservation_id, b.isbn, b.book_title,student_id, member_id, lecturer_id, date_reserved from reserved_books\n" +
                    "inner join book b on b.isbn = reserved_books.isbn;");
            reservedBooksResultSet = preparedStatement.executeQuery();

            while (reservedBooksResultSet.next()){
                Reservation reservation = new Reservation();
                User student = new User();
                student.setUserID(reservedBooksResultSet.getString("student_id"));
                User member = new User();
                member.setUserID(reservedBooksResultSet.getString("member_id"));
                User lecturer = new User();
                lecturer.setUserID(reservedBooksResultSet.getString("lecturer_id"));
                Book book = new Book();


                reservation.setReservationID(reservedBooksResultSet.getInt("reservation_id"));
                book.setIsbn(reservedBooksResultSet.getString("isbn"));
                book.setTitle(reservedBooksResultSet.getString("book_title"));
                reservation.setStudent(student);
                reservation.setLecture(lecturer);
                reservation.setMember(member);
                reservation.setDateReserved(reservedBooksResultSet.getDate("date_reserved"));
                reservation.setReservedBook(book);

                reservedBooksList.add(reservation);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (InvalidBookException e){
            error("Invalid Book Details", e.getMessage());
        }
        return reservedBooksList;
    }
}
