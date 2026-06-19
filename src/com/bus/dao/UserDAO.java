package com.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bus.exception.AuthenticationException;
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
            ps.setString(2, user.getEmail().toLowerCase());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());

            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User validateUser(String email, String password) throws AuthenticationException{
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {
            ps.setString(1, email.toLowerCase());
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }else{
                throw new AuthenticationException("Invalid Credintials!"); 
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while validating user", e);
        }
    }

    public boolean checkExistingEmail(String email) throws SQLException{
        String query = "SELECT email FROM users WHERE email = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new SQLException("Database Error " + e.getMessage());
        }
    }
}
