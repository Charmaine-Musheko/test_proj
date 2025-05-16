package com.example.librarymanagementsystem.dao;

import com.example.librarymanagementsystem.model.Lecturer;
import com.example.librarymanagementsystem.model.Librarian;
import com.example.librarymanagementsystem.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static com.example.librarymanagementsystem.util.DBHelper.connection;

public class LibrarianDao extends UserDao{
    public static ObservableList<Librarian> getAllLibrarians() {
        ObservableList<Librarian> lecturerObservableList = FXCollections.observableArrayList();
        try (Connection conn = connection()){
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select librarian_id ,first_name, middle_name, last_name, email, cellphone, password from librarian");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Librarian librarian = new Librarian();

                librarian.setUserID(rs.getString("librarian_id"));
                librarian.setFirstName(rs.getString("first_name"));
                librarian.setMiddleName(rs.getString("middle_name"));
                librarian.setLastName(rs.getString("last_name"));
                librarian.setEmail(rs.getString("email"));
                librarian.setCellphoneNum(rs.getString("cellphone"));
                librarian.setPassword(rs.getString("password"));

                lecturerObservableList.add(librarian);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return lecturerObservableList;
    }


    public static boolean userExists(User user){
        ResultSet resultSet = null;
        try (Connection conn = connection()){
            assert conn != null;
//            TODO: Add the stored procedure that will be used to add users
            String sql = "select * from librarian where librarian_id = ? and password = ?";
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
