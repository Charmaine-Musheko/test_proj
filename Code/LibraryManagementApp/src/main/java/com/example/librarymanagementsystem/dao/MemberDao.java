package com.example.librarymanagementsystem.dao;

import com.example.librarymanagementsystem.model.Member;
import com.example.librarymanagementsystem.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.librarymanagementsystem.util.DBHelper.connection;

public class MemberDao extends UserDao{
    public static ObservableList<Member> getAllMembers() {
        ObservableList<Member> memberObservableList = FXCollections.observableArrayList();
        try (Connection conn = connection()){
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select member_id ,first_name, middle_name, last_name, email, cellphone, password from member");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Member member = new Member();

                member.setUserID(rs.getString("member_id"));
                member.setFirstName(rs.getString("first_name"));
                member.setMiddleName(rs.getString("middle_name"));
                member.setLastName(rs.getString("last_name"));
                member.setEmail(rs.getString("email"));
                member.setCellphoneNum(rs.getString("cellphone"));
                member.setPassword(rs.getString("password"));

                memberObservableList.add(member);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return memberObservableList;
    }


    public static boolean userExists(User user){
        ResultSet resultSet = null;
        try (Connection conn = connection()){
            assert conn != null;
//            TODO: Add the stored procedure that will be used to add users
            String sql = "select * from member where member_id = ? and password = ?";
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
