package com.example.librarymanagementsystem.dao;

import com.example.librarymanagementsystem.model.Lecturer;
import com.example.librarymanagementsystem.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static com.example.librarymanagementsystem.util.DBHelper.connection;

public class LecturerDao extends UserDao{
    public static ObservableList<Lecturer> getAllLecturers() {
        ObservableList<Lecturer> lecturerObservableList = FXCollections.observableArrayList();
        try (Connection conn = connection()){
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select lecturer_id ,first_name, middle_name, last_name, email, cellphone, password from lecturer");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Lecturer lecturer = new Lecturer();

                lecturer.setUserID(rs.getString("lecturer_id"));
                lecturer.setFirstName(rs.getString("first_name"));
                lecturer.setMiddleName(rs.getString("middle_name"));
                lecturer.setLastName(rs.getString("last_name"));
                lecturer.setEmail(rs.getString("email"));
                lecturer.setCellphoneNum(rs.getString("cellphone"));
                lecturer.setPassword(rs.getString("password"));

                lecturerObservableList.add(lecturer);
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
            String sql = "select * from lecturer where lecturer_id = ? and password = ?";
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
