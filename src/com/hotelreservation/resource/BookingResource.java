package com.hotelreservation.resource;


import java.sql.Date;
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
	public String MakeReservation(Booking booking) {
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
	public String updateReservation(int bookingID, int roomNumber, Date startDate, Date endDate) { //Don't know if we can use Date type or if we will need to use String and then cast it to Date.
		BookingService bookService = new BookingService();
		
		if (bookService.updateReservation(bookingID, roomNumber, startDate, endDate)) {
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
		
		return listBookings; //If the customer has no bookings then this will return null. Is that OK?
	}
}
