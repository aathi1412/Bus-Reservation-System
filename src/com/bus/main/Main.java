package com.bus.main;
import java.util.Scanner;

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

                System.out.println("working.........");
            }
            else{
                on = false;
            }
        }

        sc.close();
    }
}
