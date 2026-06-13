package com.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bus.exception.BusHasBookingException;
import com.bus.exception.BusNotFoundException;
import com.bus.model.Bus;
import com.bus.util.DBConnection;


public class BusDAO {
    
    public boolean addBusToDB(Bus bus){
        String query = "INSERT INTO buses(bus_name, source, destination, total_seats, available_seats, price, bus_type) VALUES(?,?,?,?,?,?,?);";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
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

    public Bus getBus(int busId) throws BusNotFoundException{
        String query = "select * from buses where bus_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {
            ps.setInt(1, busId);
            ResultSet rs = ps.executeQuery();

            Bus bus = new Bus();
            if(rs.next()){
                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusName(rs.getString("bus_name"));
                bus.setSource(rs.getString("source"));
                bus.setDestination(rs.getString("destination"));
                bus.setTotalSeats(rs.getInt("total_seats"));
                bus.setAvailableSeats(rs.getInt("available_seats"));
                bus.setPrice(rs.getDouble("price"));
                bus.setBusType(rs.getString("bus_type"));
            }else{
                throw new BusNotFoundException("Bus Not Found Exception!");
            }
            return bus;
        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching bus", e);
        }
    }

    public ArrayList<Bus> getBuses(String source, String destination) throws BusNotFoundException{
        String query = "SELECT * FROM buses WHERE source = ? AND destination = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {
            ps.setString(1, source);
            ps.setString(2, destination);

            ResultSet rs = ps.executeQuery();

            ArrayList<Bus> buses = new ArrayList<>();

            if(!rs.next()){
                throw new BusNotFoundException("No Bus Found!");
            }

            while(rs.next()){
                Bus bus = new Bus();
                
                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusName(rs.getString("bus_name"));
                bus.setSource(rs.getString("source"));
                bus.setDestination(rs.getString("destination"));
                bus.setTotalSeats(rs.getInt("total_seats"));
                bus.setAvailableSeats(rs.getInt("available_seats"));
                bus.setPrice(rs.getDouble("price"));
                bus.setBusType(rs.getString("bus_type"));

                buses.add(bus);
            }
            return buses;
        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching bus", e);
        }
    }

    public ArrayList<Bus> getAllBuses(){
        String query = "SELECT * FROM buses";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {
            ResultSet rs = ps.executeQuery();

            ArrayList<Bus> buses = new ArrayList<>();

            while(rs.next()){
                Bus bus = new Bus();
                
                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusName(rs.getString("bus_name"));
                bus.setSource(rs.getString("source"));
                bus.setDestination(rs.getString("destination"));
                bus.setTotalSeats(rs.getInt("total_seats"));
                bus.setAvailableSeats(rs.getInt("available_seats"));
                bus.setPrice(rs.getDouble("price"));
                bus.setBusType(rs.getString("bus_type"));

                buses.add(bus);
            }

            return buses;
        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching bus", e);
        }

    }

    public void updateBusDetails(int busId, String column, Object value) {
        String query = "UPDATE buses SET " + column + " = ? WHERE bus_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {
            ps.setObject(1, value);
            ps.setInt(2, busId);

            int rows = ps.executeUpdate();

            if(rows > 0){
                System.out.println("Update Successful");
            }else{
                System.out.println("Failed to Update Bus Details!");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public void deleteBus(int busId) throws BusNotFoundException, BusHasBookingException {
        String checkBookingquery = "SELECT 1 FROM bookings WHERE bus_id = ? LIMIT 1";
        String deleteQuery = "DELETE FROM buses WHERE bus_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            
        ) {
            // check booking availablity....
            try (
                PreparedStatement checkBookingPs = con.prepareStatement(checkBookingquery);
            ) {
                checkBookingPs.setInt(1, busId);
                try (
                    ResultSet rs = checkBookingPs.executeQuery();
                ) { 
                    if(rs.next()){
                        throw new BusHasBookingException(
                            "      Cannot delete bus. Existing bookings found.");
                    }
                }
            } 
            // delete bus...
            try (
                PreparedStatement deleteBusPs = con.prepareStatement(deleteQuery);
            ) {
                deleteBusPs.setInt(1, busId);
                if(deleteBusPs.executeUpdate() != 1){
                    throw new BusNotFoundException(
                        "Bus Not Found Exception !");
                }
            }
        } catch (SQLException e) {
             throw new RuntimeException(
                "Database error while deleting bus", e);
        }
    }

    
}
