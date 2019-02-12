package com.hotelreservation.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hotelreservation.model.FilterRoom;
import com.hotelreservation.model.Room;
import com.hotelreservation.service.SearchRoomService;

@Path("listrooms")
public class SearchRoomResource {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String ListRooms(FilterRoom filter) {
		SearchRoomService searchRoom = new SearchRoomService();
		
		List<Room> listRoom = searchRoom.ListResult(filter);
		
		ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	    String arrayToJson = "";
	    try {
			arrayToJson = objectMapper.writeValueAsString(listRoom);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrayToJson;
	}
}
