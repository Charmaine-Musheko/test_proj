package com.example.librarymanagementsystem.database;

import java.sql.*;
import java.time.LocalDate;

public class Database {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/lms?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "passwordis123";
    private static final String INSERT_QUERY = "INSERT INTO student (first_name, last_name, email, home_address, telephone, password, repeat_password, dateof_birth) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String LOGIN_QUERY = "SELECT password FROM student WHERE student_id = ?";

    public void insertRecord(String firstName, String lastName, String email, String homeAddress, String telephone,
                             String password, String repeatPassword, LocalDate dateOfBirth) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, homeAddress);
            preparedStatement.setString(5, telephone);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, repeatPassword);
            preparedStatement.setDate(8, Date.valueOf(dateOfBirth));
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public boolean validateLogin(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString("password").equals(password); // Use hashing in production
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
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