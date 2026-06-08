package com.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bus.model.Bus;
import com.bus.util.DBConnection;

public class BusDAO {
    
    public boolean addBusToDB(Bus bus){
        String query = "INSERT INTO buses(bus_name, source, destination, total_seats, available_seats, price, bus_type) VALUES(?,?,?,?,?,?,?);";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareCall(query);
        ) {
            ps.setString(1, bus.getBusName());
            ps.setString(2, bus.getSource());
            ps.setString(3, bus.getDestination());
            ps.setInt(4, bus.getTotalSeats());
            ps.setInt(5, bus.getAvailableSeats());
            ps.setDouble(6, bus.getPrice());
            ps.setString(7, bus.getBusType());

            return  ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }
}
