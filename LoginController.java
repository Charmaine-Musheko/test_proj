package com.example.librarymanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.text.BreakIterator;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginController implements Initializable {
    @FXML
    private Button RegisterButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
//        File brandingFile = new File("@OIP-Copy.jpg");
//        Image brandingImage = new Image(brandingFile.toURI().toString());
        //java.awt.Image image = brandingImageView.getImage(brandingImage);
    }

    public void loginButtonOnAction(ActionEvent event) {

        if (!usernameTextField.getText().isEmpty() && !enterPasswordField.getText().isEmpty()){
            validateLogin();
        }else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void RegisterButtonOnAction(ActionEvent event){
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            URL url = getClass().getResource("src/main/resources/com/example/librarymanagementsystem/Register.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateLogin() {
    }

}