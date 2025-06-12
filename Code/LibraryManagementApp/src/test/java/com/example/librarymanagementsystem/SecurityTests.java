package com.example.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SecurityTests {
    @Test
    public void testSQLInjectionPrevention() {
        String url = "jdbc:mysql://localhost:3306/lms?useSSL=false";
        String user = "root";
        String password = "passwordis123";
        String maliciousInput = "' OR '1'='1";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Simulate prepared statement to prevent injection
            String query = "SELECT * FROM student WHERE student_id = ?";
            try (var stmt = conn.prepareStatement(query)) {
                stmt.setString(1, maliciousInput);
                var rs = stmt.executeQuery();
                assertFalse(rs.next(), "SQL injection succeeded unexpectedly");
            }
        } catch (SQLException e) {
            assertFalse(true, "Database error: " + e.getMessage());
        }
    }
}