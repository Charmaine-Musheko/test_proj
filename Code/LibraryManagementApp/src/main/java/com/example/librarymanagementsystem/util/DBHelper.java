package com.example.librarymanagementsystem.util;

import com.example.librarymanagementsystem.model.Admin;
import com.example.librarymanagementsystem.model.Lecturer;
import com.example.librarymanagementsystem.model.Member;
import com.example.librarymanagementsystem.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class DBHelper {
    public static Connection connection(){
        Connection conn;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String url = "jdbc:mysql://localhost:3306/lms";
            final String user = "libman";
            final String password = "passwordis123";

            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
