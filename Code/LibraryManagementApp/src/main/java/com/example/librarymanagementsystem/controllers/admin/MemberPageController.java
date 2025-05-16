package com.example.librarymanagementsystem.controllers.admin;

import com.example.librarymanagementsystem.SceneController;
import com.example.librarymanagementsystem.model.Member;
import com.example.librarymanagementsystem.model.Student;
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

import static com.example.librarymanagementsystem.dao.MemberDao.getAllMembers;
import static com.example.librarymanagementsystem.util.DBHelper.connection;
import static com.example.librarymanagementsystem.util.TableViewUtil.getRow;

// Class that controls the Member Page
public class MemberPageController {

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
    private Button LecturerBtn;

    @FXML
    private Button LogoutBtn;

    @FXML
    private TableColumn<Member, String> Mem_CN;

    @FXML
    private TableColumn<Member, String> Mem_E;

    @FXML
    private TableColumn<Member, String> Mem_FN;

    @FXML
    private TableColumn<Member, String> Mem_ID;

    @FXML
    private TableColumn<Member, String> Mem_LN;

    @FXML
    private TableColumn<Member, String> Mem_MN;

    @FXML
    private TableColumn<Member, String> Mem_P;


    @FXML
    private AnchorPane MemberPage;

    @FXML
    private Button MemberBtn;

    @FXML
    private TableView<Member> MemberTable;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button StudentBtn;

    @FXML
    private Button UpdateBtn;

    @FXML
    private TextField txt_ID;

    @FXML
    private TextField txt_cellphoneNum;

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

    ObservableList<Member> listMem;

    SceneController sceneController = new SceneController();

    int index = -1;

    Connection conn = null;
    ResultSet rs =null;
    CallableStatement pst = null;

    // The following chain of action events are all methods used to change the page to the respective pages available
    // The first takes us to the librarian page
    @FXML
    void LibrarianPage(ActionEvent event) {
        sceneController.changePane(event, "admin/LibrarianPage.fxml", MemberPage);
    }

    // The second takes us to the admin's account page
    @FXML
    void AccountPage(ActionEvent event) {
        sceneController.changePane(event, "admin/Account.fxml", MemberPage);
    }

    // The third takes us to the student page
    @FXML
    void StudentPage(ActionEvent event) {
        sceneController.changePane(event, "admin/StudentPage.fxml", MemberPage);
    }

    // The fourth takes us to the lecturer page
    @FXML
    void LecturerPage(ActionEvent event) {
        sceneController.changePane(event, "admin/LecturerPage.fxml", MemberPage);
    }

    //The last takes us to the member page

    @FXML
    void MemberPage(ActionEvent event) {
        sceneController.changePane(event, "admin/MemberPage.fxml", MemberPage);
    }

    // The function used to add a new member into the database and onto the website.
    public void addUser() {
        String sql = "call add_member(?,?,?,?,?,?)";
        try (Connection conn = connection()){
            assert conn != null;
            pst = conn.prepareCall(sql);
            pst.setString(1, txt_firstName.getText());
            pst.setString(2, txt_middleName.getText());
            pst.setString(3, txt_lastName.getText());
            pst.setString(4, txt_email.getText());
            pst.setString(5, txt_cellphoneNum.getText());
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
            conn = connection();
            String value1 = txt_firstName.getText();
            String value2 = txt_middleName.getText();
            String value3 = txt_lastName.getText();
            String value4 = txt_email.getText();
            String value5 = txt_cellphoneNum.getText();
            String value6 = txt_password.getText();

            String sql = "update member set first_name ''"+value1+"''"+value2+"''"+value3+"''"+value4+"''"+value5+"''"+value6;
            pst = conn.prepareCall(sql);
            pst.execute();
            System.out.println("Update Successful");
            updateTable();
        }
        catch(Exception e){System.out.println("Update Failed!!");}
    }

    // This method is used to update the user's data
    public void updateTable(){
        Mem_ID.setCellValueFactory(cellData -> cellData.getValue().userIDProperty() );
        Mem_FN.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty() );;
        Mem_MN.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty() );;
        Mem_LN.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty() );;
        Mem_E.setCellValueFactory(cellData -> cellData.getValue().emailProperty() );;
        Mem_CN.setCellValueFactory(cellData -> cellData.getValue().cellphoneNumProperty() );;
        Mem_P.setCellValueFactory(cellData -> cellData.getValue().passwordProperty() );;

        listMem = getAllMembers();
        MemberTable.setItems(listMem);
    }

    // Delete user method is used to delete the data on a particular user
    public void deleteUser(){
        Member member = getRow(MemberTable);
        conn = DBHelper.connection();
        String sql = "call delete_member(?,?)";
        try (Connection conn = connection()){
            assert conn != null;
            pst = conn.prepareCall(sql);
            pst.setString(1, member.getUserID());
            pst.registerOutParameter(2, Types.VARCHAR);
            pst.executeUpdate();
            String message = pst.getString(2);
            System.out.println("User Deleted Successfully");
            updateTable();
        }catch (Exception e){
            System.out.println("User Not Deleted!!");
        }
    }

    public void initialize(URL url, ResourceBundle rb){
        Mem_ID.setCellValueFactory(cellData -> cellData.getValue().userIDProperty());
        Mem_FN.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        Mem_MN.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty());
        Mem_LN.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        Mem_E.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        Mem_CN.setCellValueFactory(cellData -> cellData.getValue().cellphoneNumProperty());
        Mem_P.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());

        updateTable();
    }
}

