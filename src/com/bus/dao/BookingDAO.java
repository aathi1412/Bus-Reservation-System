package com.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.bus.exception.BusNotFoundException;
import com.bus.model.Booking;
import com.bus.model.Bus;
import com.bus.util.DBConnection;
import com.bus.util.FormatDateTime;

public class BookingDAO {
    
    private static BusDAO busDAO = new BusDAO();
    private static FormatDateTime formatDateTime = new FormatDateTime();

    public boolean bookTicket(int userId, int busId, int seats) throws BusNotFoundException{
        try {
           Bus bus = busDAO.getBus(busId);

            String updateSeat = "UPDATE buses SET available_seats = available_seats - ? WHERE bus_id = ? AND available_seats >= ?;";
            String bookTicket = "INSERT INTO bookings(user_id, bus_id, seats_booked, price, status) VALUES(?,?,?,?,?)";

            
                try (
                    Connection con = DBConnection.getConnection();
                ){
                    con.setAutoCommit(false);
                    
                    try{
                        
                        try (
                            PreparedStatement ps1 = con.prepareStatement(updateSeat);
                        ) {
                            ps1.setInt(1, seats);
                            ps1.setInt(2, busId);
                            ps1.setInt(3, seats);

                            if(ps1.executeUpdate() != 1){
                                con.rollback();
                                return false;
                            }
                        } 

                        try (
                            PreparedStatement ps2 = con.prepareStatement(bookTicket);
                        ) {
                            ps2.setInt(1, userId);
                            ps2.setInt(2, busId);
                            ps2.setInt(3, seats);  
                            ps2.setDouble(4, seats * bus.getPrice());
                            ps2.setString(5, "CONFIRMED");

                            if(ps2.executeUpdate() != 1){
                                con.rollback();
                                return false;
                            }
                        }
                        con.commit();
                        return true;
                    } catch (SQLException e) {
                        con.rollback();
                        throw e;
                    }    
                } 
            
        } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        
    }

	public ArrayList<Booking> myBookings(int userId) {
        String query = "SELECT booking_id, seats_booked, booking_date, price, status FROM bookings WHERE user_id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {
            ps.setInt(1, userId);
            
            ResultSet rs = ps.executeQuery();
            ArrayList<Booking> bookings = new ArrayList<>();

            while(rs.next()){
                Booking booking = new Booking();

                booking.setBookingId(rs.getInt("booking_id"));
                booking.setSeatsBooked(rs.getInt("seats_booked"));
                booking.setBookingDate(formatDateTime.formatDate(rs.getTimestamp("booking_date")));
                booking.setPrice(rs.getDouble("price"));
                booking.setStatus(rs.getString("status"));

                bookings.add(booking);
            }
            return bookings;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

    public ArrayList<Booking> viewAllBookings(){
        String query = "SELECT booking_id, seats_booked, booking_date, price, status FROM bookings";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
        ) {
            ResultSet rs = ps.executeQuery();
            ArrayList<Booking> bookings = new ArrayList<>();

            while(rs.next()){
                Booking booking = new Booking();

                booking.setBookingId(rs.getInt("booking_id"));
                booking.setSeatsBooked(rs.getInt("seats_booked"));
                booking.setBookingDate(formatDateTime.formatDate(rs.getTimestamp("booking_date")));
                booking.setPrice(rs.getDouble("price"));
                booking.setStatus(rs.getString("status"));

                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) {
           e.printStackTrace();
           return null;
        }
    }

    public boolean cancelBooking(int bookingId, int userId){
        String selectQuery = "SELECT seats_booked, bus_id FROM bookings WHERE booking_id = ? AND user_id = ? AND status = 'CONFIRMED' FOR UPDATE";
        String updateStatusQuery = "UPDATE bookings SET status = 'CANCELLED' WHERE booking_id = ?;";
        String updateSeatQuery = "UPDATE buses SET available_seats = available_seats + ? WHERE bus_id = ?;";

        try (
            Connection con = DBConnection.getConnection();
        ){
            con.setAutoCommit(false);
            try {
                int busId;
                int seatsBooked;
                // lock and validate seats.......
                try (
                    PreparedStatement selectPs = con.prepareStatement(selectQuery);
                ) {
                    selectPs.setInt(1, bookingId);
                    selectPs.setInt(2, userId);
                    
                    try (
                        ResultSet rs = selectPs.executeQuery();
                    ) {
                        if (!rs.next()) {
                            return false;
                        }

                        busId = rs.getInt("bus_id");
                        seatsBooked = rs.getInt("seats_booked");
                    } 
                }
                //  update status
                try (
                    PreparedStatement updateStatusPs = con.prepareStatement(updateStatusQuery);
                ) {
                    updateStatusPs.setInt(1, bookingId);

                    if(updateStatusPs.executeUpdate() != 1){
                        con.rollback();
                        return false;
                    }
                }
                //  Cancel Booking
                try (
                    PreparedStatement updateSeatPs = con.prepareStatement(updateSeatQuery);
                ) {

                    updateSeatPs.setInt(1, seatsBooked);
                    updateSeatPs.setInt(2, busId);

                    if(updateSeatPs.executeUpdate() != 1){
                        con.rollback();
                        return false;
                    }
                }

                con.commit();
                return true;

            }catch (SQLException e) {
                con.rollback();
                throw e;
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
