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

        while (true) {
            System.out.println("===== BUS RESERVATION SYSTEM =====");
            System.out.println();
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println();

            System.out.print("Enter Choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    registerUser(sc);
                    break;

                case 2:
                    login(sc);
                    break;

                case 3:
                    System.out.println("Thank you (Arigatho)");
                    return;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
        
    }

    private static void registerUser(Scanner sc){

        System.out.println("------ New User Registration ------");
        System.out.println();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Phone Number (+91): ");
        String phone = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        String role = "USER";
        System.out.println();
        

        User user = new User(name, email, phone, password, role);
        userService.register(user);

    }

    private static void login(Scanner sc) {
        while (true) {
            System.out.println("-------- Login --------");
            System.out.println();

            System.out.print("Enter Email:");
            String email = sc.nextLine();
            System.out.print("Enter Password:");
            String password = sc.nextLine();
            System.out.println();


            currentUser = userService.login(email, password);

            if(currentUser == null){
                System.out.println("Invalid Credintials");
                System.out.print("Y to Login Again / N to back to Main Menu: ");
                String yesOrNo = sc.nextLine();
                if(yesOrNo.equalsIgnoreCase("Y")){
                    continue;
                }else {
                    return;
                }
            }

            if(currentUser.getRole().equals("ADMIN")){
                System.out.println("Login Successful - ADMIN");
                System.out.println();
                adminMenu(sc);
            } else{
                System.out.println("Login Successful - USER");
                System.out.println();
                userMenu(sc);
            }
        }
    }

    private static void adminMenu(Scanner sc){
        while(true){
            System.out.println("1. Add Bus");
            System.out.println("2. View Buses");
            System.out.println("3. Update Bus");
            System.out.println("4. Delete Bus");
            System.out.println("5. View Bookings");
            System.out.println("6. Logout");
            System.out.println();

            System.out.print("Enter Choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            System.out.println();

            switch (choice) {
                case 1:
                    busService.addBus(sc);
                    break;

                case 2:
                    busService.viewAllBuses();
                    break;

                case 3:
                    busService.updateBus(sc);
                    break;

                case 4:
                    busService.deleteBus();
                    break;

                case 5:
                    bookingService.viewAllBookings();
                    break;

                case 6:
                    System.out.println("Logout Successfully");
                    currentUser = null;
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void userMenu(Scanner sc){
        while (true) {
            System.out.println("1. Search Bus");
            System.out.println("2. View All Buses");
            System.out.println("3. Book Ticket");
            System.out.println("4. My Bookings");
            System.out.println("5. Cancel Booking");
            System.out.println("6. Logout");
            System.out.println();

            System.out.print("Enter Choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            System.out.println();

            switch (choice) {
                case 1:
                    busService.searchBus();
                    break;

                case 2:
                    busService.viewAllBuses();
                    break;

                case 3:
                    bookingService.bookTicket();
                    break;

                case 4:
                    bookingService.myBookings();
                    break;

                case 5:
                    bookingService.cancekBooking();
                    break;

                case 6:
                    System.out.println("Logout Successfully");
                    currentUser = null;
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
