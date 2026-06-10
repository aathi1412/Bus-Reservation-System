package com.bus.util;

import java.util.ArrayList;

import com.bus.model.Bus;

public class PrintInFormat {
    
    public void printBuses(ArrayList<Bus> buses){
        System.out.printf(
            "%-10s %-16s %-15s %-15s %-14s %-17s %-10s %-13s%n",
            "Bus ID", "Bus Name", "Source", "Destination", "Total Seats", "Available Seats","Price", "Bus Type"
        );
        
        for (Bus bus : buses) {
            System.out.printf(
                "%-10s %-16s %-15s %-15s %-14s %-17s %-10s %-13s%n",
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
}
