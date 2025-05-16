package com.example.librarymanagementsystem.dao;

import com.example.librarymanagementsystem.model.Admin;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.librarymanagementsystem.util.DBHelper.connection;

public class AdminDao extends UserDao{

    public static ObservableList<Admin> getAllAdmin() {
        ObservableList<Admin> adminObservableList = FXCollections.observableArrayList();
        try(Connection conn = connection()){
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select admin_id ,first_name, middle_name, last_name, email, cellphone, password from admin");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Admin admin = new Admin();

                admin.setUserID(rs.getString("admin_id"));
                admin.setFirstName(rs.getString("first_name"));
                admin.setMiddleName(rs.getString("middle_name"));
                admin.setLastName(rs.getString("last_name"));
                admin.setEmail(rs.getString("email"));
                admin.setCellphoneNum(rs.getString("cellphone"));
                admin.setPassword(rs.getString("password"));

                adminObservableList.add(admin);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return adminObservableList;
    }
    public static int updateAdmin(Admin admin){
        try (Connection conn = connection()){
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement("update admin set first_name = ?, middle_name = ?, last_name = ?, email = ?, cellphone = ?, password = ? where admin_id = ?");

            preparedStatement.setString(1, admin.getUserID());
            preparedStatement.setString(2, admin.getFirstName());
            preparedStatement.setString(3, admin.getLastName());
            preparedStatement.setString(4, admin.getMiddleName());
            preparedStatement.setString(5, admin.getEmail());
            preparedStatement.setString(6, admin.getCellphoneNum());
            preparedStatement.setString(7, admin.getPassword());


            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


    public static boolean userExists(User user){
        ResultSet resultSet = null;
        try (Connection conn = connection()){
            assert conn != null;
//            TODO: Add the stored procedure that will be used to add users
            String sql = "select * from admin where admin_id = ? and password = ?";
            PreparedStatement preparedStatement = conn.prepareCall(sql);
//
            preparedStatement.setString(1, user.getUserID());
            preparedStatement.setString(2, user.getPassword());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

