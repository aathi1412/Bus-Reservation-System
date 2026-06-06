package com.bus.main;
import java.util.Scanner;

import com.bus.model.User;
import com.bus.service.BookingService;
import com.bus.service.BusService;
import com.bus.service.UserService;

public class Main {

    private static UserService userService = new UserService();
    private static BookingService bookingService = new BookingService();
    private static BusService busService = new BusService();

    private static User currentUser;
    public static void main(String[] args) {

        
        Scanner sc = new Scanner(System.in);
        System.out.println("===== BUS RESERVATION SYSTEM =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                registerUser(sc);
                break;

            case 2:
                login(sc);
                break;

            default:
                System.out.println("Thank you (Arigatho)");
                break;
        }

        sc.close();
    }

    private static void registerUser(Scanner sc){

        System.out.println("------ New User Registration ------");
        System.out.print("Enter Name: ");
        String name = sc.next();
        System.out.print("Enter Phone Number (+91): ");
        int phone = sc.nextInt();
        System.out.print("Enter email: ");
        String email = sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();
        String role = "USER";
        

        User user = new User(name, email, phone, password, role);
        userService.register(user);

    }

    private static void login(Scanner sc) {

        System.out.println("-------- Login --------");
        System.out.print("Enter Email:");
        String email = sc.next();
        System.out.print("Enter Password:");
        String password = sc.next();
        System.out.print("Enter role: ( USER / ADMIN )");
        String role = sc.next();

        currentUser = userService.login(email, password);
    }
}
