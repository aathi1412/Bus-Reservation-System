package com.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bus.model.User;
import com.bus.util.DBConnection;

public class UserDAO {

    

    public boolean registerUser(User user){
        String query = "INSERT INTO users(name, email, phone, password, role) VALUES(?,?,?,?,?);";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {            
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());

            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User validateUser(String email, String password){
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                User user = new User();
                
            }
            
        } catch (SQLException e) {
            return null;
        }
    }
}
