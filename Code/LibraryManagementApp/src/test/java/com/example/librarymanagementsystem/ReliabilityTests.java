package com.example.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ReliabilityTests {
    @Test
    public void testRepeatedLogins(FxRobot robot) {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 10; i++) {
                robot.interrupt();
                robot.clickOn("#usernameTextField").write("stu001");
                robot.clickOn("#enterPasswordField").write("stuPass123");
                robot.clickOn("#loginButton");
                robot.clickOn("#manageBooksButton");
                robot.clickOn("#btnCancel"); // Simulate logout or back
            }
        }, "Repeated logins failed");
    }
}