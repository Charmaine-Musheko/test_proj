package com.example.librarymanagementsystem.dao;

import com.example.librarymanagementsystem.errorHandling.InvalidBookException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BookReturn;
import com.example.librarymanagementsystem.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static com.example.librarymanagementsystem.util.AlertMessage.error;
import static com.example.librarymanagementsystem.util.DBHelper.connection;

public class BookReturnDao {
    public static ObservableList<BookReturn> getReturnedBooks(){
        ObservableList<BookReturn> bookReturnsList = FXCollections.observableArrayList();
        try (Connection conn = connection()){
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement("select return_id, b.isbn, book_title, student_id, member_id, lecturer_id, date_returned from returned_books\n" +
                    "inner join book b on returned_books.isbn = b.isbn;");

            ResultSet resultSetReturned = preparedStatement.executeQuery();

            while (resultSetReturned.next()){
                BookReturn bookReturn = new BookReturn();

                User student = new User();
                student.setUserID(resultSetReturned.getString("student_id"));

                User member = new User();
                member.setUserID(resultSetReturned.getString("member_id"));

                User lecturer = new User();
                lecturer.setUserID(resultSetReturned.getString("lecturer_id"));

                Book book = new Book();
                book.setIsbn(resultSetReturned.getString("isbn"));
                book.setTitle(resultSetReturned.getString("book_title"));

                bookReturn.setReturnID(resultSetReturned.getInt("return_id"));
                bookReturn.setReturnedBook(book);
                bookReturn.setStudent(student);
                bookReturn.setMember(member);
                bookReturn.setLecture(lecturer);
                bookReturn.setDateReturned(resultSetReturned.getDate("date_returned"));

                bookReturnsList.add(bookReturn);
            }

        }catch (SQLException e){
            e.printStackTrace();
        } catch (InvalidBookException e){
            error("Invalid Book Details", e.getMessage());
        }
        return bookReturnsList;
    }

    public static void approveBookReturn(BookReturn returnedBook){
        try (Connection conn = connection()){
            assert conn != null;
            CallableStatement callableStatement = conn.prepareCall("{call approve_return(?,?)}");

            callableStatement.setInt(1, returnedBook.getReturnID());
            callableStatement.setString(2, returnedBook.getReturnedBook().getIsbn());

            callableStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void denyBookReturn(BookReturn returnedBook){
        try (Connection conn = connection()){
            assert conn != null;
            CallableStatement callableStatement = conn.prepareCall("{call deny_return(?,?)}");

            callableStatement.setInt(1, returnedBook.getReturnID());
            callableStatement.setString(2, returnedBook.getReturnedBook().getIsbn());

            callableStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
