package com.hotelreservation.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Room;

public class BookingDAO implements IRepository<Booking>{
	//TODO: the ROOM_BOOKING table schema changed, update queries to reflect this. 
	@Override
	public boolean Insert(Booking entity) {
		Customer customer = entity.getCustomer();
		Room room = entity.getRoom();
		
		Connection connection = ConnectionFactory.getConnection();
		// TODO: Could break, specify columns
		String query = "INSERT INTO ROOM_BOOKING VALUES (" + 
				room.getRoomNumber() + ", " +
				customer.getId() + ", " +
				entity.getStartDate() + ", " +
				entity.getEndDate() + ")";
		
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean Update(Booking entity) {
		Customer customer = entity.getCustomer();
		Room room = entity.getRoom();
		
		Connection connection = ConnectionFactory.getConnection();
		String query = "UPDATE ROOM_BOOKING SET " +
				"StartDate = " + entity.getStartDate() + ", " + //TODO: find out if I need something around the date value
				"EndDate = " + entity.getEndDate() +
				" WHERE RoomNumber = " + room.getRoomNumber() + 
					" AND CustomerID = " + customer.getId();
		
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean Delete(Booking entity) {
		Customer customer = entity.getCustomer();
		Room room = entity.getRoom();
		
		Connection connection = ConnectionFactory.getConnection();
		String query = "DELETE FROM ROOM_BOOKING " +
				"WHERE RoomNumber = " + room.getRoomNumber() + 
					" AND CustomerID = " + customer.getId();
		
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
	}

	@Override
	public Booking GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Booking GetById(Booking entity) {
		Customer customer = entity.getCustomer();
		Room room = entity.getRoom();
		
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * FROM ROOM_BOOKING " +
				"WHERE RoomNumber = " + room.getRoomNumber() + 
					" AND CustomerID = " + customer.getId();
		
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
            	//TODO: Implement the RoomDAO and CustomerDAO classes as they will be needed here. 
            	//return new Booking()
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();            
        }
        
        return null;
	}

	@Override
	public List<Booking> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
