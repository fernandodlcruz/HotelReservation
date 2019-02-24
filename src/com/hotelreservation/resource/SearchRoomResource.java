package com.hotelreservation.resource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.JSONP;

import com.hotelreservation.model.Customer;
import com.hotelreservation.model.FilterRoom;
import com.hotelreservation.model.Room;
import com.hotelreservation.service.SearchRoomService;

@Path("listrooms")
public class SearchRoomResource {
	@GET
	@Produces({"application/x-javascript"})
	@JSONP(callback="jsonp",queryParam="callback")
	public List<Room> ListRooms(@QueryParam("callback") String callback,
			@QueryParam("roomNumber") int roomNumber,
            @QueryParam("checkInDate") String checkInDate,
            @QueryParam("checkOutDate") String checkOutDate,
            @QueryParam("roomCapacity") int roomCapacity) {
		SearchRoomService searchRoom = new SearchRoomService();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		
		FilterRoom filter = null;
        try {
            filter = new FilterRoom(roomNumber, df.parse(checkInDate), df.parse(checkOutDate), roomCapacity);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		List<Room> listRoom = searchRoom.ListResult(filter);
		
		return listRoom;
	}
	
	@GET
	@Path("/get-room")
	@Produces({"application/x-javascript"})
	@JSONP(callback="jsonp",queryParam="callback")
	public Room GetRoomById(@QueryParam("callback") String callback, @QueryParam("roomId") int roomId) {
		SearchRoomService searchRoom = new SearchRoomService();
		
		return searchRoom.GetById(roomId);
	}
}
