package com.example.librarymanagementsystem.controllers.members;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

public class HelloController {

    private String currentUser;

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    @FXML
    private TextField emailfield;

    @FXML
    private TextField firstnamefield;

    @FXML
    private TextArea homeaddressfield;

    @FXML
    private TextField lastnamefield;

    @FXML
    private TextField passwordfield;

    @FXML
    private TextField repeatpasswordfield;

    @FXML
    private Button switchtologin;

    @FXML
    private Button submitButton;

    @FXML
    private TextField telephonefield;

    @FXML
    private Button registerbutton;

    @FXML
    private Button books;

    @FXML
    private Button clearance;

    @FXML
    private Button close;

    @FXML
    private Button export;

    @FXML
    private Button home;

    @FXML
    private Button settings;

    @FXML
    private Button students;

    @FXML
    void switchtoaccount(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccountPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Account");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void switchtobooks(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BookPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Books");
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void switchtologin(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 800);
        stage.setScene(scene);
        stage.show();
    }




    @FXML
    public void logoutbutton(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 800);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void login1(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void register(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 800);
        stage.setScene(scene);
        stage.show();

    Window owner = submitButton.getScene().getWindow();
        System.out.println(firstnamefield.getText());
        System.out.println(lastnamefield.getText());
        System.out.println(emailfield.getText());
        System.out.println(homeaddressfield.getText());
        System.out.println(telephonefield.getText());
        System.out.println(passwordfield.getText());
        System.out.println(repeatpasswordfield.getText());


        if (firstnamefield.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your first name");
        return;
    }

        if (lastnamefield.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your last name");
        return;
    }

        if (emailfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email address");
            return;
        }
        if (homeaddressfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your Home Address");
            return;
        }
        if (telephonefield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your Telephone Number");
            return;
        }

        if (passwordfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your password");
            return;
        }
        if (repeatpasswordfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Confirm your Password");
            return;
        }

    String first_name = firstnamefield.getText();
    String last_name = lastnamefield.getText();
    String email =  emailfield.getText();
    String home_address = homeaddressfield.getText();
    String telephone = telephonefield.getText();
    String password =passwordfield.getText();



        database  database  = new database ();
        database .insertRecord(first_name, last_name, email, home_address, telephone, password);

    showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
            "Welcome " + firstnamefield.getText());

}
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}