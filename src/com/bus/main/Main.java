package com.bus.main;
import java.util.Scanner;

import com.bus.model.User;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        boolean on = true;

        while (on) {
            System.out.println("===== BUS RESERVATION SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int userInputOption = sc.nextInt();
            
            if(userInputOption == 1){
                registerUser(sc);
                System.out.println("Registered.........");
            }else if (userInputOption == 2) {
                login(sc);
            }
            else{
                on = false;
            }
        }

        sc.close();
    }

    private static void registerUser(Scanner sc){

        System.out.println("New User Registration:");
        System.out.print("Enter Name: ");
        String name = sc.next();
        System.out.println("Enter Phone Number (+91): ");
        int phone = sc.nextInt();
        System.out.println("Enter email: ");
        String email = sc.next();
        System.out.println("Enter Password: ");
        String password = sc.next();
        String role = "USER";
        

        User user = new User(name, email, phone, password, role);
        System.out.println(user.getName());

    }

    private static void login(Scanner sc) {
        System.out.println("-------- Login --------");
        System.out.println("Enter Email:");
        String email = sc.next();
        System.out.println("Enter Password:");
        String password = sc.next();
        System.out.println("Enter role: ( USER / ADMIN )");
        String role = sc.next();
    }
}
