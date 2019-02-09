package com.hotelreservation.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Room;

public class BookingDAO implements IRepository<Booking>{

	@Override
	public boolean Insert(Booking entity) {
		Customer customer = entity.getCustomer();
		Room room = entity.getRoom();
		
		Connection connection = ConnectionFactory.getConnection();
		String query = "INSERT INTO ROOM_BOOKING VALUES (" + 
				room.getRoomNumber() + ", " +
				customer.getId() + ", " +
				customer.getVendorID() + ", " +
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
				"StartDate = " + entity.getStartDate() + ", " +
				"EndDate = " + entity.getEndDate() +
				"WHERE RoomNumber = " + room.getRoomNumber() + 
					" AND CustomerID = " + customer.getId() +
					" AND VendorID = " + customer.getVendorID();
		
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

	@Override
	public Booking GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
