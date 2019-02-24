package com.hotelreservation.resource;


import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.server.JSONP;
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
	
	@POST
	@Path("/cancel-reservation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String cancelReservation(int bookingID) {
		BookingService bookService = new BookingService();
		
		if (bookService.cancelReservation(bookingID)) {
			return "{\"status\": \"deleted\"}";
		} else {
			return "{\"status\": \"no_deletion\", \"message\": \"Reservation does not exist\"}";
		}
	}
	
	@POST
	@Path("/update-reservation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateReservation(int bookingID, int roomNumber, String startDate, String endDate) {
		BookingService bookService = new BookingService();
		
		Date sd;
		Date nd;
		try {
			sd = new SimpleDateFormat("yyyy-mm-dd").parse(startDate);
			nd = new SimpleDateFormat("yyyy-mm-dd").parse(endDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
			return "{\"status\": \"no_update\", \"message\": \"Wrong date format. expected yyyy-mm-dd.\"}";
		}
		
		//Update is not touching the Customer name or first name so they can be null at this step.
		Booking booking = new Booking(bookingID, null, null, sd, nd); 
		
		if (bookService.updateReservation(booking)) {
			return "{\"status\": \"updated\"}";
		} else {
			return "{\"status\": \"no_update\", \"message\": \"Reservation does not exist\"}";
		}
	}
	
	@GET
	@Produces({"application/x-javascript"})
	@JSONP(callback="jsonp",queryParam="customerID")
	public List<Booking> getRoomBooking(@QueryParam("customerID") int customerID) {
		BookingService bs = new BookingService();
		List<Booking> listBookings = null;
		
        listBookings = bs.getBookings(customerID);
		
		return listBookings;
	}
}
