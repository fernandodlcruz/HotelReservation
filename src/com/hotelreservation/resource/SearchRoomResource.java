package com.hotelreservation.resource;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hotelreservation.model.Filter;
import com.hotelreservation.model.Room;
import com.hotelreservation.service.SearchRoomService;

@Path("listrooms")
public class SearchRoomResource {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> ListRooms(Filter filter) {
		SearchRoomService searchRoom = new SearchRoomService();
		
		List<Room> listRoom = searchRoom.ListResult(filter);
		
		return listRoom;
	}
}
