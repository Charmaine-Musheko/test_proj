package com.example.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegrationTests {
    @Test
    public void testDatabaseInsertAndQuery() {
        String url = "jdbc:mysql://localhost:3306/lms?useSSL=false";
        String user = "root";
        String password = "passwordis123";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String insertQuery = "INSERT INTO student (student_id, first_name, last_name, email, home_address, telephone, password, repeat_password, dateof_birth) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (var stmt = conn.prepareStatement(insertQuery)) {
                stmt.setString(1, "stu002");
                stmt.setString(2, "Jane");
                stmt.setString(3, "Smith");
                stmt.setString(4, "jane@example.com");
                stmt.setString(5, "456 Oak St");
                stmt.setString(6, "0823456789");
                stmt.setString(7, "pass456");
                stmt.setString(8, "pass456");
                stmt.setDate(9, java.sql.Date.valueOf("1995-05-15"));
                stmt.executeUpdate();
            }

            String selectQuery = "SELECT * FROM student WHERE student_id = 'stu002'";
            try (var stmt = conn.prepareStatement(selectQuery);
                 ResultSet rs = stmt.executeQuery()) {
                assertTrue(rs.next(), "Query returned no results");
                assertTrue(rs.getString("first_name").equals("Jane"), "Incorrect data inserted");
            }
        } catch (SQLException e) {
            assertTrue(false, "Database error: " + e.getMessage());
        }
    }
}