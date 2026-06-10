package com.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public Bus getBus(int busId){
        String query = "select * from buses where bus_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareCall(query);
        ) {
            ps.setInt(1, busId);
            ResultSet rs = ps.executeQuery();

            Bus bus = new Bus();
            while(rs.next()){

                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusName(rs.getString("bus_name"));
                bus.setSource(rs.getString("source"));
                bus.setDestination(rs.getString("destination"));
                bus.setTotalSeats(rs.getInt("total_seats"));
                bus.setAvailableSeats(rs.getInt("available_seats"));
                bus.setPrice(rs.getDouble("price"));
                bus.setBusType(rs.getString("bus_type"));
            }
            return bus;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<Bus> getAllBuses(){
        String query = "select * from buses";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareCall(query);
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
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void updateBusDetails(int busId, String column, Object value) {
        String query = "UPDATE buses SET " + column + " = ? WHERE bus_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareCall(query);
        ) {
            ps.setObject(1, value);
            ps.setInt(2, busId);

            int rows = ps.executeUpdate();

            if(rows > 0){
                System.out.println("Update Successfull");
            }else{
                System.out.println("Bus Not Found!");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
}
