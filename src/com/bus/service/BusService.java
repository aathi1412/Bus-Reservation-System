package com.bus.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.bus.dao.BusDAO;
import com.bus.exception.BusHasBookingException;
import com.bus.exception.BusNotFoundException;
import com.bus.model.Bus;
import com.bus.util.PrintInFormat;
import com.bus.util.ReadInputUtil;

public class BusService {

    private static BusDAO busDAO = new BusDAO();
    private static PrintInFormat printInFormat = new PrintInFormat();

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
        System.out.println();

        Bus bus = new Bus(busName, source, destination, seats, seats, price, busType);
        boolean message = busDAO.addBusToDB(bus);

        if(message){
            System.out.println("Bus Added Successfully !!!");
        }else{
            System.out.println("Failed to Add Bus !!!");
        }
        System.out.println();
    }

    public void viewAllBuses() {
        try {
            ArrayList<Bus> buses = busDAO.getAllBuses();
            printInFormat.printBuses(buses);
        } catch (BusNotFoundException e) {
            System.out.println("No Buses Found");
            System.out.println();
        }
    }

    public void updateBus(Scanner sc) {
        System.out.println();
        System.out.println("Available Buses to Update");
        System.out.println();
        viewAllBuses();
        System.out.print("Enter Bus ID to select bus: ");
        int busId = Integer.parseInt(sc.nextLine());
        try {
            while (true) {
                Bus bus = busDAO.getBus(busId);
                System.out.println();
                System.out.println("What do you want to Update ?");
                System.out.println();
                System.out.println("1. Bus Name [ " + bus.getBusName() + " ]");
                System.out.println("2. Source [" + bus.getSource() + "]");
                System.out.println("3. Destination [" + bus.getDestination() + "]");
                System.out.println("4. Total Seats [" + bus.getTotalSeats() + "]");
                System.out.println("5. Bus ticket Fare [" + bus.getPrice() + "]");
                System.out.println("6. Bus Type [" + bus.getBusType() + "]");
                System.out.println();
                System.out.print("Choose option to Update field: ");
                int choice = Integer.parseInt(sc.nextLine());
                System.out.println();

                String column = null;
                Object value = null;

            
                switch (choice) {
                    case 1:
                        System.out.print("Enter New Bus Name: ");
                        column = "bus_name";
                        value = sc.nextLine();
                        break;
                    
                    case 2:
                        System.out.print("Enter New source: ");
                        column = "source";
                        value = sc.nextLine();
                        break;
                    
                    case 3:
                        System.out.print("Enter New Destination: ");
                        column = "destination";
                        value = sc.nextLine();
                        break;
                    
                    case 4:
                        System.out.print("Enter Total seats: ");
                        column = "total_seats";
                        value = Integer.parseInt(sc.nextLine());
                        break;
                    
                    case 5:
                        System.out.print("Enter New Ticket fare: ");
                        column = "price";
                        value = Double.parseDouble(sc.nextLine());
                        break;
                    
                    case 6:
                        System.out.print("Enter Bus Type: ");
                        column = "bus_type";
                        value = sc.nextLine();
                        break;
                    
                    default:
                        System.out.println("Invalid Choice. Try Again!!!");
                        continue;
                }
                busDAO.updateBusDetails(busId, column, value);

                System.out.print("Y to update other details / N for Back to Admin Menu: ");
                if(!sc.nextLine().equalsIgnoreCase("Y")){
                    System.out.println();
                    return;
                }
            }
        } catch (BusNotFoundException e) {
            System.out.println(e.getMessage());
        }

        
           
    }

    public void deleteBus(Scanner sc) {
        System.out.println("Available Buses to Delete");
        System.out.println();
        viewAllBuses();

        int busId = ReadInputUtil.readInt("Enter Bus ID to select bus: ", sc);

        System.out.print("Are you Sure to delete? [y/n]: ");
        if(!sc.nextLine().equalsIgnoreCase("y")){
            System.out.println();
            return;
        }
        
        try {
            Bus bus = busDAO.getBus(busId);
            busDAO.deleteBus(bus.getBusId());
            System.out.println("Bus successfully Deleted !");
        } catch (BusNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(BusHasBookingException e){
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    public boolean searchBus(Scanner sc) {
        System.out.print("Enter Source: ");
        String source = sc.nextLine();
        System.out.print("Enter Destination: ");
        String destination = sc.nextLine();
        System.out.println();

        try {
            ArrayList<Bus> buses = busDAO.getBuses(source, destination);
            printInFormat.printBuses(buses);
        } catch (BusNotFoundException e) {
            System.out.println("No Buses Found from " + source + " to " + destination);
            System.out.println();
            return false;
        }
        System.out.println();
        return true;
    }
    
}
