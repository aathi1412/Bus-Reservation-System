package com.bus.service;

import com.bus.dao.UserDAO;
import com.bus.model.User;

public class UserService {
    
    private static UserDAO userDAO = new UserDAO();
    
    public void register(User user){
        userDAO.saveUser(user);
    }
}
