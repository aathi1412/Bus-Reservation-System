package com.bus.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.bus.model.Booking;
import com.bus.model.Bus;
import com.bus.util.PrintInFormat;
import com.bus.dao.BookingDAO;
import com.bus.dao.BusDAO;
import com.bus.exception.BusNotFoundException;

public class BookingService {

    private static BusService busService = new BusService();
    private static Booking booking = new Booking();
    private static BookingDAO bookingDAO = new BookingDAO();
    private static BusDAO busDAO = new BusDAO();

    private static PrintInFormat print = new PrintInFormat();

    public void bookTicket(Scanner sc, int userId) {
        while (true) {
            busService.searchBus(sc);
            System.out.print("choose Bus Id to Book Ticket : ");
            int busId = Integer.parseInt(sc.nextLine());
            System.out.print("How many Seats to Book       : ");
            int seats = Integer.parseInt(sc.nextLine());

            if(seats <= 0){
                System.out.println("Unable to Book" + seats + "Seats" + ". Try to Book Atleast 1 Seat.");
                return;
            }

            System.out.println("Available Buses");
            try {
                boolean flag = bookingDAO.bookTicket(userId, busId, seats);
                Bus bus = busDAO.getBus(busId);

                System.out.println();
                if(flag){
                    System.out.println("Booking Successful !!!");
                    System.out.println("===========================");
                    System.out.println("      Booking Details      ");
                    System.out.println("===========================");
                    System.out.println("Bus Name    : " + bus.getBusName());
                    System.out.println("Source      : " + bus.getSource());
                    System.out.println("Destination : " + bus.getDestination());
                    System.out.println("Seats       : " + seats);
                    System.out.println("Price       : " + seats * bus.getPrice());
                    System.out.println("Status      : " + "CONFIRMED");
                }  
            } catch (BusNotFoundException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
            System.out.print("Y to Book Ticket again / N to Back Menu: ");
            if(!sc.nextLine().equalsIgnoreCase("Y")){
                return;
            }
            System.out.println();
        }
    }

    public void myBookings(int userId) {
        ArrayList<Booking> bookings = bookingDAO.myBookings(userId);
        if(bookings == null){
            System.out.println("No Bookings Available");
            return;
        }
        print.printBookings(bookings);
    }

    public void cancelBooking(int userId, Scanner sc) {
        myBookings(userId);
        System.out.print("Choose Booking ID to Cancel Ticket: ");
        int bookingId = Integer.parseInt(sc.nextLine());
        boolean result = bookingDAO.cancelBooking(bookingId, userId);

        if(result){
            System.out.println("Successfully Ticket Cancelled");
        }else{
            System.out.println("Cancelling Failed !");
        }
    }

    public void viewAllBookings() {
        ArrayList<Booking> booking = bookingDAO.viewAllBookings();
        print.printBookings(booking);
    }
    
}
