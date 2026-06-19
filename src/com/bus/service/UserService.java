package com.bus.service;

import java.sql.SQLException;

import com.bus.dao.UserDAO;
import com.bus.exception.AuthenticationException;
import com.bus.model.User;

public class UserService {
    
    private static UserDAO userDAO = new UserDAO();

    public void register(User user){
        if(userDAO.registerUser(user)){
            System.out.println("Registration Successfull");
        }else{
            System.out.println("Registration Failed");
        }
    }

    public User login(String email, String password) throws AuthenticationException{
        return userDAO.validateUser(email, password);
    }

    public boolean checkExistingEmail(String email) throws SQLException{
        try {
            return userDAO.checkExistingEmail(email);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
