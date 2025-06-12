package com.example.librarymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class SceneController {

   public void switchTo(ActionEvent event, String page)  {
    try {
        URL url = getClass().getResource("/com/example/librarymanagementsystem/views/" + page);
        System.out.println("Loading FXML from: " + url);  // helpful for debugging

        if (url == null) {
            throw new RuntimeException("FXML file not found: " + page);
        }

        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (Exception e){
        e.printStackTrace();
        System.out.println("Error while switching scene: " + e.getMessage());
    }
}


    public void changePane(ActionEvent event, String page, AnchorPane panel){
        try {
            Node node;
            node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/" + page)));
            panel.getChildren().setAll(node);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changePane(ActionEvent event, Node node, AnchorPane panel){
        try {
            panel.getChildren().setAll(node);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
