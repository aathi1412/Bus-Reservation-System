package com.bus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    
    private static Connection con;
    private static final String url = "jdbc:mysql://localhost:3306/bus_reservation";
    private static final String user = "root";
    private static final String pass = "Zoro@2004";

    public static Connection getConnection(){
        try {
            con =  DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
    
}
