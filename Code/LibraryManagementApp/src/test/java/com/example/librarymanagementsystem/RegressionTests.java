package com.example.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;

import javafx.scene.control.Label;

public class RegressionTests {
    @Test
    public void testLoginPostUpdate(FxRobot robot) {
        robot.clickOn("#usernameTextField").write("stu001");
        robot.clickOn("#enterPasswordField").write("stuPass123");
        robot.clickOn("#loginButton");
        Assertions.assertThat(robot.lookup("#loginMessageLabel").queryAs(Label.class))
                .hasText("Login successful!");
    }
}