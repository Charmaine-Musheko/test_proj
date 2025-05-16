package com.example.librarymanagementsystem.controllers.admin;

import com.example.librarymanagementsystem.SceneController;
import com.example.librarymanagementsystem.dao.StudentDao;
import com.example.librarymanagementsystem.model.Member;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.util.DBHelper;
import com.mysql.cj.conf.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.util.DBHelper.connection;
import static com.example.librarymanagementsystem.util.TableViewUtil.getRow;

// Class that controls the Student Page
public class StudentPageController {

    private String currentUserID;

    public void setCurrentUserID(String currentUserID) {
        this.currentUserID = currentUserID;
    }

    @FXML
    private Button AccountBtn;

    @FXML
    private Button AccountsBtn;

    @FXML
    private Button AddBtn;

    @FXML
    private Button AddUBtn;

    @FXML
    private Button DelUBtn;

    @FXML
    private Button DeleteBtn;

    @FXML
    private Button LibrarianBtn;

    @FXML
    private Button LogoutBtn;

    @FXML
    private TextField SearchBar;

    @FXML
    private TableColumn<Student, String> Stud_CN;

    @FXML
    private TableColumn<Student, String> Stud_E;

    @FXML
    private TableColumn<Student, String> Stud_FN;

    @FXML
    private TableColumn<Student, String> Stud_ID;

    @FXML
    private TableColumn<Student, String> Stud_LN;

    @FXML
    private TableColumn<Student, String> Stud_MN;

    @FXML
    private TableColumn<Student, String> Stud_P;

    @FXML
    private AnchorPane StudentPage;

    @FXML
    private TableView<Student> StudentTable;

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


    ObservableList<Student> listStud;

    SceneController sceneController = new SceneController();


    Connection conn = null;
    CallableStatement pst = null;

    // The following chain of action events are all methods used to change the page to the respective pages available
    // The first takes us to the librarian page
    @FXML
    void LibrarianPage(ActionEvent event) {
        sceneController.changePane(event, "admin/LibrarianPage.fxml", StudentPage);
    }

    // The second takes us to the admin's account page
    @FXML
    void AccountPage(ActionEvent event) {
        sceneController.changePane(event, "admin/Account.fxml", StudentPage);
    }

    // The third takes us to the student page
    @FXML
    void StudentPage(ActionEvent event) {
        sceneController.changePane(event, "admin/StudentPage.fxml", StudentPage);
    }

    // The fourth takes us to the lecturer page
    @FXML
    void LecturerPage(ActionEvent event) {
        sceneController.changePane(event, "admin/LecturerPage.fxml", StudentPage);
    }

    //The last takes us to the member page
    @FXML
    void MemberPage(ActionEvent event) {
        sceneController.changePane(event, "admin/MemberPage.fxml", StudentPage);
    }

    // The function used to add a new student into the database and onto the website.
    public void addUser() {
        conn = DBHelper.connection();
        String sql = "call add_student(?,?,?,?,?,?)";
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

    public void edit(){
        try{
            conn = DBHelper.connection();
            String value1 = txt_firstName.getText();
            String value2 = txt_middleName.getText();
            String value3 = txt_lastName.getText();
            String value4 = txt_email.getText();
            String value5 = txt_cellphoneNumber.getText();
            String value6 = txt_password.getText();

            String sql = "update student set first_name "+value1+"''"+value2+"''"+value3+"''"+value4+"''"+value5+"''"+value6;
            pst = conn.prepareCall(sql);
            pst.execute();System.out.println("Update Successful");
            updateTable();
        }
        catch(Exception e){System.out.println("Update Failed!!");}
    }

    // This method is used to update the user's data
    public void updateTable(){
        Stud_ID.setCellValueFactory(cellData -> cellData.getValue().userIDProperty() );
        Stud_FN.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty() );;
        Stud_MN.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty() );;
        Stud_LN.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty() );;
        Stud_E.setCellValueFactory(cellData -> cellData.getValue().emailProperty() );;
        Stud_CN.setCellValueFactory(cellData -> cellData.getValue().cellphoneNumProperty() );;
        Stud_P.setCellValueFactory(cellData -> cellData.getValue().passwordProperty() );;

        listStud = StudentDao.getAllStudents();
        StudentTable.setItems(listStud);
    }

    // Delete user method is used to delete the data on a particular user
    public void deleteUser(){
        Student student = getRow(StudentTable);
        conn = DBHelper.connection();
        String sql = "call delete_student(?,?)";
        try (Connection conn = connection()){
            assert conn != null;
            pst = conn.prepareCall(sql);
            pst.setString(1, student.getUserID());
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
