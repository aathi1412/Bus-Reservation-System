package com.bus.service;

import java.util.Scanner;

import com.bus.model.Bus;
import com.bus.dao.BookingDAO;
import com.bus.dao.BusDAO;

public class BookingService {

    private static BusService busService = new BusService();
    private static BookingDAO bookingDAO = new BookingDAO();
    private static BusDAO busDAO = new BusDAO();

    public void bookTicket(Scanner sc, int userId) {
        while (true) {
            busService.searchBus(sc);
            System.out.print("choose Bus Id to Book Ticket: ");
            int busId = Integer.parseInt(sc.nextLine());
            System.out.print("How many Seats to Book: ");
            int seats = Integer.parseInt(sc.nextLine());

            System.out.println("Available Buses");
            boolean flag = bookingDAO.bookTicket(userId, busId, seats);
            Bus bus = busDAO.getBus(busId);

            System.out.println();

            if(flag){
                System.out.println("Booking Successful !!!");

                System.out.println("=================");
                System.out.println("    Ticket   ");
                System.out.println("=================");
                System.out.println("Bus Name: " + bus.getBusName());
                System.out.println("Source: " + bus.getSource());
                System.out.println("Destination: " + bus.getDestination());
                System.out.println("Seats: " + seats);
            }   

            System.out.println("Y to Book Ticket again / N to Back Menu");
            String YN = sc.nextLine();
            if(YN.equalsIgnoreCase("Y")){
                continue;
            }else{
                return;
            }
        }
    }

    public void myBookings() {
        
    }

    public void cancekBooking() {
        
    }

    public void viewAllBookings() {
        
    }
    
}
