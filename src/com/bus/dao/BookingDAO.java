package com.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bus.model.Bus;
import com.bus.util.DBConnection;

public class BookingDAO {
    
    private static BusDAO busDAO = new BusDAO();

    public void bookTicket(int userId, int busId, int seats){
        Bus bus = busDAO.getBus(busId);
        int available_seats = bus.getAvailableSeats();

        if(available_seats < seats){
            System.out.println("Unable to Book Ticket" + available_seats + "Seats only Available");
            return;
        }

        String query1 = "UPDATE buses SET available_seats = available_seats - ? WHERE bus_id = ? AND available_seats >= ?;";
        String query2 = "INSERT INTO bookings(user_id, bus_id, seats_booked) VALUES(?,?,?)";

        Connection con = DBConnection.getConnection();;
        try (con){
            con.setAutoCommit(false);
             
            PreparedStatement ps1 = con.prepareStatement(query1);
            PreparedStatement ps2 = con.prepareStatement(query2);

            ps1.setInt(1, seats);
            ps1.setInt(2, busId);
            ps1.setInt(3, seats);

            ps2.setInt(1, userId);
            ps2.setInt(2, busId);
            ps2.setInt(3, seats);

            int r1 = ps1.executeUpdate();
            int r2 = ps2.executeUpdate();

            if(r1 > 0 && r2 > 0){
                con.commit();
            }else{
                con.rollback();
            }
            
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
