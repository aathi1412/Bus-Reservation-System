package com.bus.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.bus.dao.BusDAO;
import com.bus.model.Bus;

public class BusService {

    private BusDAO busDAO = new BusDAO();

    public void addBus(Scanner sc) {

        System.out.println("---------Add a New Bus----------");

        System.out.print("Enter Bus Name: ");
        String busName = sc.nextLine();
        System.out.print("Enter Source Departure Place of Bus: ");
        String source = sc.nextLine();
        System.out.print("Enter the Destination of Bus: ");
        String destination = sc.nextLine();
        System.out.print("Enter Total Seats in Bus: ");
        int seats = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Bus ticket Fare: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Enter Bus Type ( AC / NON AC ): ");
        String busType = sc.nextLine();

        Bus bus = new Bus(busName, source, destination, seats, seats, price, busType);
        boolean message = busDAO.addBusToDB(bus);

        if(message){
            System.out.println("Bus Added Successfully");
        }else{
            System.out.println("Failed to Add Bus!!!");
        }

    }

    public void viewAllBuses() {
        System.out.printf(
            "%-5s %-20s %-15s %-15s %-10s %-10s%n",
            "ID", "Bus Name", "Source", "Destination", "Seats", "Price"
        );
        ArrayList<Bus> buses = busDAO.getAllBuses();
        for (Bus bus : buses) {
            System.out.printf(
                "%-5s %-20s %-15s %-15s %-10s %-10s%n",
                bus.getBusId(),
                bus.getBusName(),
                bus.getSource(),
                bus.getDestination(), 
                bus.getTotalSeats(),
                bus.getAvailableSeats(),
                bus.getPrice(),
                bus.getBusType()
            );
        }
        System.out.println();
    }

    public void updateBus(Scanner sc) {
        System.out.println("Available Buses to Update");
        viewAllBuses();
        System.out.print("Enter Bus ID to select bus");
        int busId = Integer.parseInt(sc.nextLine());
        Bus bus = busDAO.getBus(busId);
        
        while (true) {
            System.out.println();
            System.out.println("What do you want to Update");
            System.out.println();
            System.out.print("1. Bus Name [ " + bus.getBusName() + " ]");
            System.out.print("2. Source [" + bus.getSource() + "]");
            System.out.print("3. Destination [" + bus.getDestination() + "]");
            System.out.print("4. Total Seats [" + bus.getTotalSeats() + "]");
            System.out.print("5. Bus ticket Fare [" + bus.getPrice() + "]");
            System.out.print("6. Bus Type [" + bus.getBusType() + "]");
            System.out.println();
            System.out.print("Choose option to Update field");
            int choice = Integer.parseInt(sc.nextLine());
            System.out.println();

            String column = null;
            Object value = null;

        
            switch (choice) {
                case 1:
                    System.out.print("Enter New Bus Name");
                    column = "bus_name";
                    value = sc.nextLine();
                    break;
                
                case 2:
                    System.out.print("Enter New source");
                    column = "source";
                    value = sc.nextLine();
                    break;
                
                case 3:
                    System.out.print("Enter New Destination");
                    column = "destination";
                    value = sc.nextLine();
                    break;
                
                case 4:
                    System.out.print("Enter Total seats");
                    column = "total_seats";
                    value = Integer.parseInt(sc.nextLine());
                    break;
                
                case 5:
                    System.out.print("Enter New Ticket fare");
                    column = "price";
                    value = Double.parseDouble(sc.nextLine());
                    break;
                
                case 6:
                    System.out.print("Enter Bus Type");
                    column = "bus_type";
                    value = sc.nextLine();
                    break;
                
                default:
                    System.out.println("Invalid Choice");
                    break;
            }

            busDAO.updateBusDetails(busId, column, value);

            System.out.print("Y to update other field / N for Back to Admin Menu: ");
            String yN = sc.nextLine();
            if(yN.equalsIgnoreCase("N")){
                return;
            }
        }

    }

    public void deleteBus() {
        System.out.println("Available Buses to Delete");
        viewAllBuses();
    }

    public void searchBus() {
        
    }
    
}
