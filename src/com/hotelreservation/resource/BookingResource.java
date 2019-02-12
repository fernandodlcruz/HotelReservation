package com.hotelreservation.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hotelreservation.model.Booking;
import com.hotelreservation.service.BookingService;

@Path("booking")
public class BookingResource {
	@POST
	@Path("/make-reservation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String ListRooms(Booking booking) {
		BookingService bookService = new BookingService();
		
		if (bookService.makeReservation(booking)) {
			return "{\"status\": \"ok\"}";
		} else {
			return "{\"status\": \"nok\", \"message\": \"Reservation failed to complete\"}";
		}
	}
}
