package com.hotelreservation.repository;

import java.util.ArrayList;
import java.util.List;

import com.hotelreservation.model.Filter;
import com.hotelreservation.model.Room;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomDAO implements IRepository<Room> {
	private Connection conn;
	
	public RoomDAO() {
		conn = ConnectionFactory.getConnection();
	}

//  The back-end should only be able to read from the ROOM table, not modify it.
/*	
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
*/
	@Override
	public Room GetById(int id) {
		Statement stmt = null;
		Room room = null; 

		String query = "SELECT * FROM ROOM WHERE RoomNumber = " + id;
		
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
                room = new Room(rs.getInt("RoomNumber"), rs.getInt("RoomCapacity"), rs.getDouble("Price"), rs.getString("Description"));
            }
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(stmt!=null)
                   stmt.close();
             } catch(SQLException se2) {
             }// nothing we can do
             try{
                if(conn!=null)
                   conn.close();
             } catch(SQLException se) {
                se.printStackTrace();
             }
         }
        
		return room;
	}

	@Override
	public List<Room> GetAll() {
		Statement stmt = null;
		List<Room> listRoom = new ArrayList<Room>(); 
		
		String query = "SELECT * FROM ROOM";
		
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                listRoom.add(new Room(rs.getInt("RoomNumber"), rs.getInt("Capacity"), rs.getDouble("Price"), rs.getString("Description")));
            }
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
               if(stmt!=null)
                  stmt.close();
            } catch(SQLException se2) {
            }// nothing we can do
            try{
               if(conn!=null)
                  conn.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }
        }

		return listRoom;
	}

	public List<Room> GetAllByFilter(Filter filter) {
		Statement stmt = null;
		List<Room> listRoom = new ArrayList<Room>();
		BookingDAO bookDao = new BookingDAO();
		
		String query = "SELECT * FROM ROOM WHERE Capacity = " + filter.getRoomCapacity();
		
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Room room;
            while(rs.next())
            {
            	room = new Room(rs.getInt("RoomNumber"), rs.getInt("Capacity"), rs.getDouble("Price"), rs.getString("Description"));
            	room.setListBooking(bookDao.GetByRoomNum(room.getRoomNumber()));
            	
                listRoom.add(room);
            }
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(stmt!=null)
                   stmt.close();
             } catch(SQLException se2) {
             }// nothing we can do
             try{
                if(conn!=null)
                   conn.close();
             } catch(SQLException se) {
                se.printStackTrace();
             }
         }
        
		return listRoom;
	}
	
	@Override
	public boolean Insert(Room entity) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean Update(Room entity) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
