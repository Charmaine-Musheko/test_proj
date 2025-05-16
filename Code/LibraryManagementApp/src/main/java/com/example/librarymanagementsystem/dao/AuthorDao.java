package com.example.librarymanagementsystem.dao;

import com.example.librarymanagementsystem.model.Author;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.librarymanagementsystem.util.DBHelper.connection;

public class AuthorDao {
    public static ObservableList<Author> getAuthors(){
        ObservableList<Author> authorsObservableList = FXCollections.observableArrayList();
        try (Connection conn = connection()){
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement("select * from author");
            ResultSet authorsResultSet = preparedStatement.executeQuery();

            while (authorsResultSet.next()){
                Author author = new Author();

                author.setId(authorsResultSet.getInt("author_id"));
                author.setName(authorsResultSet.getString("author_name"));

                authorsObservableList.add(author);
            }
            return authorsObservableList;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static int updateAuthor(Author author){
        try (Connection conn = connection()){
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement("update author set author_name = ? where author_id = ?");

            preparedStatement.setString(1, author.getName());
            preparedStatement.setInt(2, author.getId());

            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
