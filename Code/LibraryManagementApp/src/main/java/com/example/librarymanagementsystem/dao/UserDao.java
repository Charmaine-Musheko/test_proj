package com.example.librarymanagementsystem.dao;

import com.example.librarymanagementsystem.model.User;
import javafx.collections.ObservableList;

import java.sql.*;

import static com.example.librarymanagementsystem.util.DBHelper.connection;

public abstract class UserDao{



    public static int updateUser(User user, int role){
        try (Connection conn = connection()){
            assert conn != null;
//            TODO: Add the stored procedure that will be used to update  users
            String sql = "";
            CallableStatement callableStatement = conn.prepareCall(sql);
//            TODO: Add whatever parameters to the stored procedures using the the user class e.g for updating
//             i suggest using the firstName, middleName, lastname, email and cellphone as parameters (password can be separate for this one)
//            the role parameter can be used to decide on what table you are updating
//            callableStatement.setString(1, user.getUserID);
            return callableStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static int deleteUser(User user, int role){
        try (Connection conn = connection()){
            assert conn != null;
//            TODO: Add the stored procedure that will be used to delete users
            String sql = "";
            CallableStatement callableStatement = conn.prepareCall(sql);
//            TODO: Add whatever parameters to the stored procedures using the the user class e.g
//            I suggest you use the user's id as a parameter
//            the role parameter can be used to decide on what table you are deleting from
//            callableStatement.setString(1, user.getUserID);
            return callableStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
