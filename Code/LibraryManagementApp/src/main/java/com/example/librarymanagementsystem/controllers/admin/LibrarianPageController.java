package com.example.librarymanagementsystem.controllers.admin;

import com.example.librarymanagementsystem.SceneController;
import com.example.librarymanagementsystem.model.Lecturer;
import com.example.librarymanagementsystem.model.Librarian;
import com.example.librarymanagementsystem.model.Member;
import com.example.librarymanagementsystem.util.DBHelper;
import com.mysql.cj.conf.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.librarymanagementsystem.dao.LecturerDao.getAllLecturers;
import static com.example.librarymanagementsystem.dao.LibrarianDao.getAllLibrarians;
import static com.example.librarymanagementsystem.util.DBHelper.connection;
import static com.example.librarymanagementsystem.util.TableViewUtil.getRow;

// Class that controls the Librarian Page
public class LibrarianPageController {

    private String currentUserID;

    public void setCurrentUserID(String currentUserID) {
        this.currentUserID = currentUserID;
    }
    @FXML
    private Button AddBtn;

    @FXML
    private Button DeleteBtn;

    @FXML
    private Button UpdateBtn;

    @FXML
    private Button AccountBtn;

    @FXML
    private Button LecturerBtn;

    @FXML
    private TableColumn<Librarian, String> Lib_CN;

    @FXML
    private TableColumn<Librarian, String> Lib_E;

    @FXML
    private TableColumn<Librarian, String> Lib_FN;

    @FXML
    private TableColumn<Librarian, String> Lib_ID;

    @FXML
    private TableColumn<Librarian, String> Lib_LN;

    @FXML
    private TableColumn<Librarian, String> Lib_MN;

    @FXML
    private TableColumn<Librarian, String> Lib_P;

    @FXML
    private Button LibrarianBtn;

    @FXML
    private AnchorPane LibrarianPage;

    @FXML
    private TableView<Librarian> LibrarianTable;

    @FXML
    private Button LogoutBtn;

    @FXML
    private Button MemberBtn;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button StudentBtn;

    @FXML
    private TextField txt_CellphoneNumber;

    @FXML
    private TextField txt_Email;

    @FXML
    private TextField txt_FirstName;

    @FXML
    private TextField txt_LastName;

    @FXML
    private TextField txt_MiddleName;

    @FXML
    private TextField txt_Password;

    @FXML
    private TextField txt_id;

    ObservableList<Librarian> listLib;

    SceneController sceneController = new SceneController();

    int index = -1;

    Connection conn = null;
    ResultSet rs =null;
    CallableStatement pst = null;

    // The following chain of action events are all methods used to change the page to the respective pages available
    // The first takes us to the librarian page
    @FXML
    void LibrarianPage(ActionEvent event) {
        sceneController.changePane(event, "admin/LibrarianPage.fxml", LibrarianPage);
    }

    // The second takes us to the admin's account page
    @FXML
    void AccountPage(ActionEvent event) {
        sceneController.changePane(event, "admin/Account.fxml", LibrarianPage);
    }

    // The third takes us to the student page
    @FXML
    void StudentPage(ActionEvent event) {
        sceneController.changePane(event, "admin/StudentPage.fxml", LibrarianPage);
    }

    // The fourth takes us to the lecturer page
    @FXML
    void LecturerPage(ActionEvent event) {
        sceneController.changePane(event, "admin/LecturerPage.fxml", LibrarianPage);
    }

    //The last takes us to the member page
    @FXML
    void MemberPage(ActionEvent event) {
        sceneController.changePane(event, "admin/MemberPage.fxml", LibrarianPage);
    }

    public void searchUser(){

    }

    // The function used to add a new librarian into the database and onto the website.
    public void getSelected(){
        index = LibrarianTable.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        txt_FirstName.setText(Lib_FN.getCellData(index).toString());
        txt_MiddleName.setText(Lib_MN.getCellData(index).toString());;
        txt_LastName.setText(Lib_LN.getCellData(index).toString());;
        txt_Email.setText(Lib_E.getCellData(index).toString());;
        txt_CellphoneNumber.setText(Lib_CN.getCellData(index).toString());;
        txt_Password.setText(Lib_P.getCellData(index).toString());;
    }

    public void edit(){
        try{
            conn = DBHelper.connection();
            String value1 = txt_FirstName.getText();
            String value2 = txt_MiddleName.getText();
            String value3 = txt_LastName.getText();
            String value4 = txt_Email.getText();
            String value5 = txt_CellphoneNumber.getText();
            String value6 = txt_Password.getText();

            String sql = "update librarian set first_name '"+value1+"''"+value2+"''"+value3+"''"+value4+"''"+value5+"''"+value6;
            pst = conn.prepareCall(sql);
            pst.execute();
            System.out.println("Update Successful");
            updateTable();
        }
        catch(Exception e){System.out.println("Update Failed!!");}
    }

    public void addUser() {
        conn = DBHelper.connection();
        String sql = "call add_librarian(?,?,?,?,?,?)";
        try {
            pst = conn.prepareCall(sql);
            pst.setString(1, txt_FirstName.getText());
            pst.setString(2, txt_MiddleName.getText());
            pst.setString(3, txt_LastName.getText());
            pst.setString(4, txt_Email.getText());
            pst.setString(5, txt_CellphoneNumber.getText());
            pst.setString(6, txt_Password.getText());
            pst.execute();
            System.out.println("User added successfully");
            updateTable();
        } catch (Exception e) {
            System.out.println("User not added");
        }
    }

    // This method is used to update the user's data
    public void updateTable(){
        Lib_ID.setCellValueFactory(cellData -> cellData.getValue().userIDProperty() );
        Lib_FN.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty() );;
        Lib_MN.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty() );;
        Lib_LN.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty() );;
        Lib_E.setCellValueFactory(cellData -> cellData.getValue().emailProperty() );;
        Lib_CN.setCellValueFactory(cellData -> cellData.getValue().cellphoneNumProperty() );;
        Lib_P.setCellValueFactory(cellData -> cellData.getValue().passwordProperty() );;

        listLib = getAllLibrarians();
        LibrarianTable.setItems(listLib);
    }

    // Delete user method is used to delete the data on a particular user
    public void deleteUser(){
        Librarian librarian = getRow(LibrarianTable);
        conn = DBHelper.connection();
        String sql = "call delete_librarian(?,?)";
        try (Connection conn = connection()){
            assert conn != null;
            pst = conn.prepareCall(sql);
            pst.setString(1, librarian.getUserID());
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




