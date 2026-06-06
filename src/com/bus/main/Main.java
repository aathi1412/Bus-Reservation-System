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
            System.out.println("3. Search Buses");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. View Bookings");
            System.out.println("7. Exit");

            int userInputOption = sc.nextInt();
            
            if(userInputOption == 1){
                registerUser(sc);
                System.out.println("Registered.........");
            }
            else{
                on = false;
            }
        }

        sc.close();
    }

    public static void registerUser(Scanner sc){

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
}
