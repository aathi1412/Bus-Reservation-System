package com.bus.service;

import java.util.Scanner;

import com.bus.dao.BookingDAO;

public class BookingService {

    private static BusService busService = new BusService();
    private static BookingDAO bookingDAO = new BookingDAO();

    public void bookTicket(Scanner sc, int userId) {
       busService.searchBus(sc);
       System.out.print("choose Bus Id to Book Ticket: ");
       int busId = Integer.parseInt(sc.nextLine());
       System.out.print("How many Seats to Book: ");
       int seats = Integer.parseInt(sc.nextLine());
       bookingDAO.bookTicket(userId, busId, seats);

       System.out.println("Booking Successful !!!");

    }

    public void myBookings() {
        
    }

    public void cancekBooking() {
        
    }

    public void viewAllBookings() {
        
    }
    
}
