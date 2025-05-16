package com.example.librarymanagementsystem;

import java.sql.*;
import java.time.LocalDate;

public class Database {


    // Replace below database url, username and password with your actual database credentials
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/lms?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Mypassword1234";
    private static final String INSERT_QUERY = "INSERT INTO student (first_name, last_name, email, home_address, telephone, password, repeat_password, dateof_birth ) VALUES (?, ?, ?, ?, ?, ? ,?, ?)";

    public void insertRecord(String first_name, String last_name, String email, String   home_address, String telephone, String password, String repeat_password, LocalDate dateof_birth) {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, home_address);
            preparedStatement.setString(5, telephone);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, repeat_password);
            preparedStatement.setDate(8, Date.valueOf(dateof_birth));


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }



}

