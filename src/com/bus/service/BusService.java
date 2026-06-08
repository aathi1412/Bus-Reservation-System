package com.bus.service;

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
        
    }

    public void updateBus() {
        
    }

    public void deleteBus() {
        
    }

    public void searchBus() {
        
    }
    
}
