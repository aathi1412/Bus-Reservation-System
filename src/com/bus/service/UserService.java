package com.bus.service;

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
}
