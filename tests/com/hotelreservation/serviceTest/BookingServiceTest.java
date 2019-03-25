package com.hotelreservation.serviceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Room;
import com.hotelreservation.service.BookingService;

class BookingServiceTest {
	BookingService service;
	DateFormat df;
	
	@BeforeEach
	void setUp() throws Exception {
		service = new BookingService();
		df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	}

	@Test
	void testMakeReservation() {
		Customer cust = new Customer();
		Room room = new Room();
		Booking booking = new Booking();
		
		try {
			cust.setId(1);
			room.setRoomNumber(3);
			booking.setStartDate(df.parse("2019-04-19T00:00:00"));
			booking.setEndDate(df.parse("2019-04-22T00:00:00"));
			booking.setCustomer(cust);
			booking.setRoom(room);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue(service.makeReservation(booking));
	}

	@Test
	void testGetBookingsByCustomer() {
		List<Booking> listBookings = null;
		
        listBookings = service.getBookings(1);
        
        assertFalse(listBookings.isEmpty());
	}

	@Test
	void testUpdateReservation() {
		Booking booking = null;
		
		List<Booking> listBookings = null;
		
        listBookings = service.getBookings(1);
        
        Booking bookingToUpdate = listBookings.get(0);

		try {
			//Update is not touching the Customer name or first name so they can be null at this step.
			booking = new Booking(bookingToUpdate.getBookingID(), bookingToUpdate.getRoom(), bookingToUpdate.getCustomer(), df.parse("2019-04-23T00:00:00"), df.parse("2019-04-27T00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertTrue(service.updateReservation(booking));
	}
	
	@Test
	void testCancelReservation() {
		List<Booking> listBookings = null;
		
        listBookings = service.getBookings(1);
        
        int id = listBookings.get(0).getBookingID();
		assertTrue(service.cancelReservation(id));
	}
}
