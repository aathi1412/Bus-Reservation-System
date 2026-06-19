package com.bus.service;

import java.sql.SQLException;
import java.util.Scanner;

import com.bus.dao.UserDAO;
import com.bus.exception.AuthenticationException;
import com.bus.model.User;

public class UserService {
    
    private static UserDAO userDAO = new UserDAO();

    public void registerUser(Scanner sc){
        System.out.println();
        System.out.println("------ New User Registration ------");
        System.out.println();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Phone Number (+91): ");
        String phone = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.println();

        try {
            boolean isValid = userDAO.checkExistingEmail(email);
            if(!isValid){
                System.out.println(".................Email Already Registered!");
                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        String role = "USER";
        System.out.println();

        System.out.print("Confirm to Register [y/n]: ");
        if(!sc.nextLine().equalsIgnoreCase("y")){
            return;
        }

        User user = new User(name, email, phone, password, role);
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
