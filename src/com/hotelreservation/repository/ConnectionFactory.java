package com.hotelreservation.repository;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://eunocontrole.com:3306/eunoc863_hotelreservation";
    public static final String USER = "eunoc863_hruser";
    public static final String PASS = "tla@3275";

    public static Connection getConnection()
    {
		try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
	}
}