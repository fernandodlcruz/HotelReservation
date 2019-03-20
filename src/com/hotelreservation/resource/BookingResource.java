package com.hotelreservation.resource;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.glassfish.jersey.server.JSONP;
import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Room;
import com.hotelreservation.service.BookingService;

@Path("booking")
public class BookingResource {
	@GET
	@Path("/make-reservation")
	@Produces({"application/x-javascript"})
	@JSONP(callback="jsonp",queryParam="callback")
	public String MakeReservation(@QueryParam("callback") String callback,
			@QueryParam("roomNumber") int roomNumber,
			@QueryParam("customerId") int customerId,
			@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		
		BookingService bookService = new BookingService();
		Customer cust = new Customer();
		Room room = new Room();
		Booking booking = new Booking();
		
		try {
			cust.setId(customerId);
			room.setRoomNumber(roomNumber);
			booking.setStartDate(df.parse(startDate));
			booking.setEndDate(df.parse(endDate));
			booking.setCustomer(cust);
			booking.setRoom(room);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (bookService.makeReservation(booking)) {
			return "{\"status\": \"ok\", \"message\": \"Reservation complete successfuly!\"}";
		} else {
			return "{\"status\": \"nok\", \"message\": \"Reservation failed to complete.\"}";
		}
	}
	
	@GET
	@Path("/cancel-reservation")
	@Produces({"application/x-javascript"})
	@JSONP(callback="jsonp",queryParam="callback")
	public String cancelReservation(@QueryParam("callback") String callback,
			@QueryParam("bookingID") int bookingID) {
		BookingService bookService = new BookingService();
		
		if (bookService.cancelReservation(bookingID)) {
			return "{\"status\": \"deleted\"}";
		} else {
			return "{\"status\": \"no_deletion\", \"message\": \"Reservation does not exist\"}";
		}
	}
	
	@GET
	@Path("/update-reservation")
	@Produces({"application/x-javascript"})
	@JSONP(callback="jsonp",queryParam="callback")
	public String updateReservation(@QueryParam("callback") String callback,
			@QueryParam("bookingID") int bookingID, 
			@QueryParam("roomNumber") int roomNumber, 
			@QueryParam("startDate") String startDate, 
			@QueryParam("endDate") String endDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		BookingService bookService = new BookingService();
		Booking booking = null;
		
		try {
			//Update is not touching the Customer name or first name so they can be null at this step.
			booking = new Booking(bookingID, null, null, df.parse(startDate), df.parse(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
			return "{\"status\": \"nok\", \"message\": \"Wrong date format. expected yyyy-mm-dd.\"}";
		}		
		
		if (bookService.updateReservation(booking)) {
			return "{\"status\": \"ok\", \"message\": \"Reservation updated successfuly!\"}";
		} else {
			return "{\"status\": \"nok\", \"message\": \"Reservation failed to update.\"}";
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
