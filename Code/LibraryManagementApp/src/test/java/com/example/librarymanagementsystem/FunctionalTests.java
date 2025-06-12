package com.example.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

public class FunctionalTests {
    @Test
    public void testLoginValid(FxRobot robot) {
        robot.clickOn("#usernameTextField").write("stu001");
        robot.clickOn("#enterPasswordField").write("stuPass123");
        robot.clickOn("#loginButton");
        Assertions.assertThat(robot.lookup("#loginMessageLabel").queryAs(Label.class))
                .hasText("Login successful!");
    }

    @Test
    public void testLoginInvalid(FxRobot robot) {
        robot.clickOn("#usernameTextField").write("stu001");
        robot.clickOn("#enterPasswordField").write("wrongPass");
        robot.clickOn("#loginButton");
        Assertions.assertThat(robot.lookup("#loginMessageLabel").queryAs(Label.class))
                .hasText("Invalid username or password");
    }

    @Test
    public void testRegisterValid(FxRobot robot) {
        robot.clickOn("#registerButton");
        robot.clickOn("#firstNameField").write("John");
        robot.clickOn("#lastNameField").write("Doe");
        robot.clickOn("#emailField").write("john.doe@example.com");
        robot.clickOn("#homeAddressField").write("123 Main St");
        robot.clickOn("#telephoneField").write("0812345678");
        robot.clickOn("#passwordField").write("pass123");
        robot.clickOn("#repeatPasswordField").write("pass123");
        robot.clickOn("#dateOfBirthField").write("2000-01-01");
        robot.clickOn(".button"); // Submit button
        // Verify indirectly via message or state
        Assertions.assertThat(robot.lookup("#firstNameField").queryAs(TextField.class))
                .hasText(""); // Check fields cleared
    }

    @Test
    public void testRegisterInvalidPassword(FxRobot robot) {
        robot.clickOn("#registerButton");
        robot.clickOn("#firstNameField").write("John");
        robot.clickOn("#lastNameField").write("Doe");
        robot.clickOn("#passwordField").write("pass123");
        robot.clickOn("#repeatPasswordField").write("pass124");
        robot.clickOn(".button"); // Submit button
        // Verify error message (indirectly)
        Assertions.assertThat(robot.lookup("#firstNameField").queryAs(TextField.class))
                .hasText("John"); // Fields should not clear
    }

    @Test
    public void testAddBookValid(FxRobot robot) {
        robot.clickOn("#usernameTextField").write("stu001");
        robot.clickOn("#enterPasswordField").write("stuPass123");
        robot.clickOn("#loginButton");
        robot.clickOn("#manageBooksButton");
        robot.clickOn("#txtFieldBookTitle").write("New Book");
        robot.clickOn("#txtFieldAuthor").write("John Doe");
        robot.clickOn("#txtFieldGenre").write("Fiction");
        robot.clickOn("#txtFieldEdition").write("1");
        robot.clickOn("#txtFieldYearPublished").write("2020");
        robot.clickOn("#comboBoxPublisher").write("Publisher1").type(KeyCode.ENTER);
        robot.clickOn("#comboBoxBookType").write("Novel").type(KeyCode.ENTER);
        robot.clickOn("#btnAddNewBook");
        Assertions.assertThat(robot.lookup("#txtFieldBookTitle").queryAs(TextField.class))
                .hasText(""); // Verify fields cleared
    }
}