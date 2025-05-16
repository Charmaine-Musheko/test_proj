package com.example.librarymanagementsystem.dao;

import com.example.librarymanagementsystem.errorHandling.InvalidBookException;
import com.example.librarymanagementsystem.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

import static com.example.librarymanagementsystem.util.AlertMessage.error;
import static com.example.librarymanagementsystem.util.DBHelper.connection;

public class BookDao {
    public static int addBook(Book newBook)  {
        try (Connection conn = connection()){
            assert conn != null;
            CallableStatement callableStatement = conn.prepareCall("{call add_new_book(?,?,?,?,?,?,?)}");

            callableStatement.setString(1, newBook.getTitle());
            callableStatement.setInt(2, newBook.getYearPublished());
            callableStatement.setInt(3, newBook.getEdition());
            callableStatement.setString(4, newBook.getAuthors().get(0).getName());
            callableStatement.setString(5, newBook.getPublisher().getPublisher());
            callableStatement.setString(6, newBook.getType());
            callableStatement.setString(7, newBook.getGenre());

            return callableStatement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public static int updateBook(Book book)  {
        try (Connection conn = connection()){
            assert conn != null;
            CallableStatement callableStatement = conn.prepareCall("{call update_book(?,?,?,?)}");

            callableStatement.setString(1, book.getIsbn());
            callableStatement.setString(2, book.getTitle());
            callableStatement.setInt(3, book.getEdition());
            callableStatement.setInt(4, book.getYearPublished());

            return callableStatement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public static String deleteBook(Book book)  {
        String message = null;
        try (Connection conn = connection()){
            assert conn != null;
            CallableStatement callableStatement = conn.prepareCall("{call delete_book(?,?)}");
            callableStatement.setString(1, book.getIsbn());
            callableStatement.registerOutParameter(2, Types.VARCHAR);

            callableStatement.executeUpdate();

            message = callableStatement.getString(2);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return message;
    }

    public static ObservableList<Book> getAllBooksLibrarian()  {
        ObservableList<Book> bookObservableList = FXCollections.observableArrayList();
        try (Connection conn = connection()){
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement("select * from lib_view_all_books");
            ResultSet allBooks = preparedStatement.executeQuery();

            while (allBooks.next()){
                Author author = new Author();
                author.setName(allBooks.getString("author_name"));
                Publisher publisher = new Publisher();
                publisher.setPublisher(allBooks.getString("publisher_name"));
                ArrayList<Author> authors = new ArrayList<>();
                authors.add(author);
                Book book = new Book();
                book.setIsbn(allBooks.getString("isbn"));
                book.setTitle(allBooks.getString("book_title"));
                book.setAuthors(authors);
                book.setEdition(allBooks.getInt("edition"));
                book.setPublisher(publisher);
                book.setYearPublished(allBooks.getInt("year_published"));
                book.setStatus(allBooks.getString("status_name"));

                bookObservableList.add(book);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (InvalidBookException e){
            error("Invalid Book Details", e.getMessage());
        }
        return bookObservableList;
    }

    public static ObservableList<Book> getAvailableBooks(){
        ObservableList<Book> availableBooksList = FXCollections.observableArrayList();
        try (Connection conn = connection()){
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement("{select * from view_available_books}");
            ResultSet availableBooks = preparedStatement.executeQuery();

            while (availableBooks.next()){
                Author author = new Author();
                author.setName(availableBooks.getString("author_name"));
                Publisher pub = new Publisher();
                pub.setPublisher(availableBooks.getString("publisher"));
                ArrayList<Author> authors = new ArrayList<>();
                authors.add(author);
                Book book = new Book();

                book.setIsbn(availableBooks.getString("isbn"));
                book.setTitle(availableBooks.getString("book_title"));
                book.setAuthors(authors);
                book.setEdition(availableBooks.getInt("edition"));
                book.setPublisher(pub);
                book.setYearPublished(availableBooks.getInt("year_published"));

                availableBooksList.add(book);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (InvalidBookException e){
            error("Invalid Book Details", e.getMessage());
        }
        return availableBooksList;
    }
}
