package com.example.librarymanagementsystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class CompatibilityTests {
    private Stage stage;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize JavaFX application
        stage = FxToolkit.registerPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/librarymanagementsystem/views/login.fxml"));
        Scene scene = new Scene(root);
        FxToolkit.setupScene(() -> scene);
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Clean up after each test
        FxToolkit.hideStage();
        FxToolkit.cleanupStages();
        FxToolkit.cleanupApplication(new Main());
    }

    @Test
    public void testOSCompatibility() {
        String os = System.getProperty("os.name").toLowerCase();
        assertTrue(os.contains("windows") || os.contains("linux") || os.contains("mac"),
                "Unsupported OS: " + os);
    }

    @Test
    public void testJavaFXRendering() {
        // Verify that a node from login.fxml (e.g., loginMessageLabel) is rendered
        Assertions.assertThat(stage.getScene().lookup("#loginMessageLabel")).isNotNull()
                .matches(node -> node instanceof Label, "Node should be a Label");
    }
}