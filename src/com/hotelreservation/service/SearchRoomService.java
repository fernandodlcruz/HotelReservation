package com.hotelreservation.service;

import java.util.Iterator;
import java.util.List;

import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Filter;
import com.hotelreservation.model.Room;
import com.hotelreservation.repository.RoomDAO;

public class SearchRoomService implements ISearch<Room> {
	private RoomDAO dao = new RoomDAO();
	
	@Override
	public List<Room> ListResult(Filter filter) {
		Room room;
		
		// Get all rooms that attends the capacity filter
		List<Room> listRooms = dao.GetAllByFilter(filter);
		
		// Filter all rooms that attends the period
		Iterator<Room> itr = listRooms.iterator();
		
        while(itr.hasNext()) {
        	room = itr.next();
        	
        	for(Booking booking : room.getListBooking()) {
	            if(CompareDateRangeService.checkForDateClash(filter.getCheckInDate(), filter.getCheckOutDate(), booking.getStartDate(), booking.getEndDate())) {
	                itr.remove();
	                break;
	            }
        	}
        }
		
		// Return filtered list
		return listRooms;
	}
	
	public Room GetById(int roomId) {
		// Return filtered list
		return dao.GetById(roomId);
	}
}
