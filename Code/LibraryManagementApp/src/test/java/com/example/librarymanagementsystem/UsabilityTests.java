package com.example.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

import javafx.scene.text.Text;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsabilityTests {
    @Test
    public void testNavigationSpeed(FxRobot robot) {
        long start = System.currentTimeMillis();
        robot.clickOn("#registerButton");
        long end = System.currentTimeMillis();
        assertTrue((end - start) < 2000, "Navigation took more than 2 seconds");
    }

    @Test
    public void testUIResponsiveness(FxRobot robot) {
        long start = System.currentTimeMillis();
        robot.clickOn("#usernameTextField").write("stu001");
        long end = System.currentTimeMillis();
        assertTrue((end - start) < 1000, "Text input took more than 1 second");
    }


    @Test
    public void testErrorMessageClarity(FxRobot robot) {
        robot.clickOn("#usernameTextField").write("invalidUser");
        robot.clickOn("#enterPasswordField").write("wrongPass");
        robot.clickOn("#loginButton");
        Text errorMessage = robot.lookup("#loginMessageLabel").queryText();
        assertTrue(errorMessage.getText().contains("Invalid username or password"), "Error message is not clear");
    }
}