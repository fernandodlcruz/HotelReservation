package com.hotelreservation.repository;

import java.util.ArrayList;
import java.util.List;

import com.hotelreservation.model.Room;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomDAO implements IRepository<Room> {
	private Connection conn;
	
	RoomDAO() {
		conn = ConnectionFactory.getConnection();
	}

	@Override
	public boolean Insert(Room entity) {
		String query = "INSERT INTO Room(RoomNumber, Capacity, Price, Description) " + 
						"VALUES(" + entity.getRoomNumber() + 
						", " + entity.getRoomCapacity() +
						", " + entity.getPrice() +
						", '" + entity.getDescription() + "')";
		
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
		return false;
	}

	@Override
	public boolean Update(Room entity) {
		String query = "UPDATE Room SET " +
				", Capacity = " + entity.getRoomCapacity() +
				", Price = " + entity.getPrice() +
				", Description = '" + entity.getDescription() + "'" +
				" WHERE RoomNumber = " + entity.getRoomNumber();

		try {
		    Statement stmt = conn.createStatement();
		    stmt.executeUpdate(query);
		    
		    return true;
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean Delete(int id) {
		String query = "DELETE FROM Room " +
				"WHERE RoomNumber = " + id;

		try {
		    Statement stmt = conn.createStatement();
		    stmt.executeUpdate(query);
		    
		    return true;
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}

		return false;
	}

	@Override
	public Room GetById(int id) {
		Room room = null; 

		String query = "SELECT * FROM Room WHERE RoomNumber = " + id;
		
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
                room = new Room(rs.getInt("RoomNumber"), rs.getInt("RoomCapacity"), rs.getDouble("Price"), rs.getString("Description"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
		return room;
	}

	@Override
	public List<Room> GetAll() {
		List<Room> listRoom = new ArrayList<Room>(); 
		
		String query = "SELECT * FROM Room";
		
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                listRoom.add(new Room(rs.getInt("RoomNumber"), rs.getInt("RoomCapacity"), rs.getDouble("Price"), rs.getString("Description")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
		return listRoom;
	}

}
