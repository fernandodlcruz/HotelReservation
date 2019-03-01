package com.hotelreservation.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Room;

public class BookingDAO implements IRepository<Booking>{
	@Override
	public boolean Insert(Booking entity) {
		Customer customer = entity.getCustomer();
		Room room = entity.getRoom();
		
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Connection connection = ConnectionFactory.getConnection();
		String query = "INSERT INTO ROOM_BOOKING (RoomNumber, CustomerID, StartDate, EndDate) VALUES (" + 
				room.getRoomNumber() + ", " +
				customer.getId() + ", '" +
				sdf.format(entity.getStartDate()) + "', '" +
				sdf.format(entity.getEndDate()) + "')";
		
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
	}
	
	//We should only ever be updating the room number, start date, or end date of a reservation.
	//if we ever needed to update the customer, might as well delete the reservation and create a
	//new one from scratch. If we don't need to update all fields available in the method header, we
	//can set the unchanging fields to be the same as they were before.
	/*
	public boolean Update(int bookingID, int roomNumber, Date startDate, Date endDate) {
		Connection connection = ConnectionFactory.getConnection();
		String query = "UPDATE ROOM_BOOKING SET " +
				"RoomNumber = " + roomNumber + ", " +
				"StartDate = " + startDate + ", " + //TODO: find out if I need something around the date value
				"EndDate = " + endDate +
				" WHERE BookingID = " + bookingID;
		
		try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		return false;
	}
	*/
	
	@Override
	public boolean Update(Booking entity) {
		
		Connection connection = ConnectionFactory.getConnection();
		String query = "UPDATE ROOM_BOOKING SET " +
				"RoomNumber = " + entity.getRoom().getRoomNumber() + ", " +
				"StartDate = " + entity.getStartDate() + ", " + //TODO: find out if I need something around the date value
				"EndDate = " + entity.getEndDate() +
				" WHERE BookingID = " + entity.getBookingID();
		
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
		Connection connection = ConnectionFactory.getConnection();
		String query = "DELETE FROM ROOM_BOOKING " +
				"WHERE BookingID = " + id;
		
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
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * FROM ROOM_BOOKING " +
				"WHERE BookingID = " + id;
		
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            RoomDAO rmDAO = new RoomDAO();
            CustomerDAO cuDAO = new CustomerDAO();
            
            if(rs.next())
            {
            	Room room = rmDAO.GetById(rs.getInt("RoomID"));
            	Customer customer = cuDAO.GetById(rs.getInt("CustomerID"));
            	
            	return new Booking(rs.getInt("BookingID"),
            			room,
            			customer,
            			rs.getDate("StartDate"),
            			rs.getDate("EndDate")); 
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();            
        }
        
        return null;
	}
	
	
	//This method is to be used when we want to pull-up a customer's reservations
	public List<Booking> GetByCustomer(int customerID) {
		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection connection = ConnectionFactory.getConnection();
		
		//Select all the bookings with the given customer id.
		String query = "SELECT * FROM ROOM_BOOKING " +
				"WHERE CustomerID = " + customerID;
		
		try {
			Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            RoomDAO rmDAO = new RoomDAO();
            CustomerDAO cuDAO = new CustomerDAO();
            
            while(rs.next()) {
            	Room room = rmDAO.GetById(rs.getInt("RoomID"));
            	Customer customer = cuDAO.GetById(rs.getInt("CustomerID"));
            	
            	bookings.add(new Booking(rs.getInt("BookingID"),
            			room,
            			customer,
            			rs.getDate("StartDate"),
            			rs.getDate("EndDate")));
            }

        	return bookings;
			
		} catch (SQLException ex) {
            ex.printStackTrace();            
        }

		return null;
	}
	
	//Return all Rooms in ROOM_BOOKING with the given RoomNumber.
	public List<Booking> GetByRoomNum(int roomNum){
		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * FROM ROOM_BOOKING " +
				"WHERE RoomNumber = " + roomNum;
		
		try {
			Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            RoomDAO rmDAO = new RoomDAO();
            CustomerDAO cuDAO = new CustomerDAO();
            
            while(rs.next()) {
            	Room room = rmDAO.GetById(rs.getInt("RoomNumber"));
            	Customer customer = cuDAO.GetById(rs.getInt("CustomerID"));
            	
            	bookings.add(new Booking(rs.getInt("BookingID"),
            			room,
            			customer,
            			rs.getDate("StartDate"),
            			rs.getDate("EndDate")));
            }

        	return bookings;
			
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
