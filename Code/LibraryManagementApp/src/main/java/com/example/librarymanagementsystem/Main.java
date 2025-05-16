package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.controllers.librarian.AddBookPageController;
import com.example.librarymanagementsystem.controllers.librarian.LibMainPageController;
import com.example.librarymanagementsystem.util.SubmenuHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage){
        try {
//            URL url = getClass().getResource("views/librarian/libMainPage.fxml");
            URL url = getClass().getResource("views/login.fxml");
            assert url != null;
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("University Library");
            stage.show();
            stage.centerOnScreen();
            stage.setOnCloseRequest(event -> {
                event.consume();
                exit(stage);
            });

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void exit(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to log out");
        alert.setContentText("Are you sure you want to log out?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You succesfully logged out");
            stage.close();
        }
    }

    public static void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to log out");
        alert.setContentText("Are you sure you want to log out?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You succesfully logged out");
            SceneController sceneController = new SceneController();
            try {
//            URL url = getClass().getResource("views/librarian/libMainPage.fxml");
                URL url = Main.class.getResource("views/login.fxml");
                assert url != null;
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}