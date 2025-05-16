package com.example.librarymanagementsystem.dao;

import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.librarymanagementsystem.util.DBHelper.connection;

public class StudentDao extends UserDao{

    public static ObservableList<Student> getAllStudents() {
        ObservableList<Student> studentsObservableList = FXCollections.observableArrayList();
        try (Connection conn = connection()){
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select student_id ,first_name, middle_name, last_name, email, cellphone, password from student");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Student student = new Student();

                student.setUserID(rs.getString("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setMiddleName(rs.getString("middle_name"));
                student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email"));
                student.setCellphoneNum(rs.getString("cellphone"));
                student.setPassword(rs.getString("password"));

                studentsObservableList.add(student);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return studentsObservableList;
    }


    public static boolean userExists(User user){
        ResultSet resultSet = null;
        try (Connection conn = connection()){
            assert conn != null;
//            TODO: Add the stored procedure that will be used to add users
            String sql = "select * from student where student_id = ? and password = ?";
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
