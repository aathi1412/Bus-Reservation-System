package com.bus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.bus.model.User;

public class UserDAO {

    private Connection con;

    public UserDAO() {
        try {
            String url = "jdbc:mysql://localhost:3306/your_database";
            String user = "username";
            String pass = "password";
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerUser(User user){
        
    }

    public User validateUser(String email, String password){
        return null ;
    }
}
