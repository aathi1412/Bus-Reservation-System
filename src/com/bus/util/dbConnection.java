package com.bus.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection{
    
    private static String url;
    private static String user;
    private static String pass;

    static {
        try {
            Properties props = new Properties();

            InputStream is = DBConnection.class.getResourceAsStream("db.properties");
            
            if (is == null) {
                throw new RuntimeException("db.properties not found");
            }
            props.load(is);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            pass = props.getProperty("db.password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }
    
}
