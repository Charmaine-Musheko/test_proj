package com.example.librarymanagementsystem.controllers.admin;

import com.example.librarymanagementsystem.SceneController;
import com.example.librarymanagementsystem.model.Lecturer;
import com.example.librarymanagementsystem.model.Librarian;
import com.example.librarymanagementsystem.util.DBHelper;
import com.mysql.cj.conf.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.dao.LecturerDao.getAllLecturers;
import static com.example.librarymanagementsystem.util.DBHelper.connection;
import static com.example.librarymanagementsystem.util.TableViewUtil.getRow;

// Class that controls the Lecturer Page
public class LecturerPageController {


    private String currentUserID;

    public void setCurrentUserID(String currentUserID) {
        this.currentUserID = currentUserID;
    }

    @FXML
    private Button AccountBtn;

    @FXML
    private Button AddBtn;

    @FXML
    private Button ChangePasswordBtn;

    @FXML
    private Button DeleteBtn;

    @FXML
    private Button LibrarianBtn;

    @FXML
    private TableColumn<Lecturer, String> Lec_CN;

    @FXML
    private TableColumn<Lecturer, String> Lec_E;

    @FXML
    private TableColumn<Lecturer, String> Lec_FN;

    @FXML
    private TableColumn<Lecturer, String> Lec_ID;

    @FXML
    private TableColumn<Lecturer, String> Lec_LN;

    @FXML
    private TableColumn<Lecturer, String> Lec_MN;

    @FXML
    private TableColumn<Lecturer, String> Lec_P;

    @FXML
    private AnchorPane LecturerPage;


    @FXML
    private Button LecturerBtn;

    @FXML
    private TableView<Lecturer> LecturerTable;

    @FXML
    private Button LogoutBtn;

    @FXML
    private Button MemberBtn;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button StudentBtn;

    @FXML
    private Button UpdateBtn;

    @FXML
    private TextField txt_ID;

    @FXML
    private TextField txt_cellphoneNumber;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_firstName;

    @FXML
    private TextField txt_lastName;

    @FXML
    private TextField txt_middleName;

    @FXML
    private TextField txt_password;

    ObservableList<Lecturer> listLec;

    SceneController sceneController = new SceneController();

    int index = -1;

    Connection conn = null;
    ResultSet rs =null;
    CallableStatement pst = null;

    // The following chain of action events are all methods used to change the page to the respective pages available
    // The first takes us to the librarian page
    @FXML
    void LibrarianPage(ActionEvent event) {
        sceneController.changePane(event, "admin/LibrarianPage.fxml", LecturerPage);
    }

    // The second takes us to the admin's account page
    @FXML
    void AccountPage(ActionEvent event) {
        sceneController.changePane(event, "admin/Account.fxml", LecturerPage);
    }

    // The third takes us to the student page
    @FXML
    void StudentPage(ActionEvent event) {
        sceneController.changePane(event, "admin/StudentPage.fxml", LecturerPage);
    }

    // The fourth takes us to the lecturer page
    @FXML
    void LecturerPage(ActionEvent event) {
        sceneController.changePane(event, "admin/LecturerPage.fxml", LecturerPage);
    }

    //The last takes us to the member page
    @FXML
    void MemberPage(ActionEvent event) {
        sceneController.changePane(event, "admin/MemberPage.fxml", LecturerPage);
    }

    public void searchUser(){

    }

    public void getSelected(){
        index = LecturerTable.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        txt_firstName.setText(Lec_FN.getCellData(index).toString());
        txt_middleName.setText(Lec_MN.getCellData(index).toString());;
        txt_lastName.setText(Lec_LN.getCellData(index).toString());;
        txt_email.setText(Lec_E.getCellData(index).toString());;
        txt_cellphoneNumber.setText(Lec_CN.getCellData(index).toString());;
        txt_password.setText(Lec_P.getCellData(index).toString());;
    }

    public void edit(){
            try{
                conn = DBHelper.connection();
                String value1 = txt_firstName.getText();
                String value2 = txt_middleName.getText();
                String value3 = txt_lastName.getText();
                String value4 = txt_email.getText();
                String value5 = txt_cellphoneNumber.getText();
                String value6 = txt_password.getText();

                String sql = "update lecturer set first_name '"+value1+"''"+value2+"''"+value3+"''"+value4+"''"+value5+"''"+value6;
                pst = conn.prepareCall(sql);
                pst.execute();
                System.out.println("Update Successful");
                updateTable();
            }
            catch(Exception e){System.out.println("Update Failed!!");}
    }

    // The function used to add a new lecturer into the database and onto the website.
    public void addUser() {
        conn = DBHelper.connection();
            String sql = "call add_lecturer(?,?,?,?,?,?)";
            try {
                pst = conn.prepareCall(sql);
                pst.setString(1, txt_firstName.getText());
                pst.setString(2, txt_middleName.getText());
                pst.setString(3, txt_lastName.getText());
                pst.setString(4, txt_email.getText());
                pst.setString(5, txt_cellphoneNumber.getText());
                pst.setString(6, txt_password.getText());
                pst.execute();
                System.out.println("User added successfully");
                updateTable();
            } catch (Exception e) {
                System.out.println("User not added");
            }
        }

        // This method is used to update the user's data
        public void updateTable(){
            Lec_ID.setCellValueFactory(cellData -> cellData.getValue().userIDProperty() );
            Lec_FN.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty() );;
            Lec_MN.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty() );;
            Lec_LN.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty() );;
            Lec_E.setCellValueFactory(cellData -> cellData.getValue().emailProperty() );;
            Lec_CN.setCellValueFactory(cellData -> cellData.getValue().cellphoneNumProperty() );;
            Lec_P.setCellValueFactory(cellData -> cellData.getValue().passwordProperty() );;

            listLec = getAllLecturers();
            LecturerTable.setItems(listLec);
     }

    // Delete user method is used to delete the data on a particular user
    public void deleteUser(){
        Lecturer lecturer = getRow(LecturerTable);
        conn = DBHelper.connection();
        String sql = "call delete_librarian(?,?)";
        try (Connection conn = connection()){
            assert conn != null;
            pst = conn.prepareCall(sql);
            pst.setString(1, lecturer.getUserID());
            pst.registerOutParameter(2, Types.VARCHAR);
            pst.executeUpdate();
            System.out.println("User Deleted Successfully");
            updateTable();
        }catch (Exception e){
            System.out.println("User Not Deleted!!");
        }
    }

    public void initialize(URL url, ResourceBundle rb){
        updateTable();
    }
}


