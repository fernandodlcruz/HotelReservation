package com.hotelreservation.entitiestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Customer;
import com.hotelreservation.model.Room;

class BookingTests {
	
	Booking booking;

	@BeforeEach
	void setUp() throws Exception {
		booking = new Booking();
	}

	@Test
	void TestBookingHasRoom() {
		Room room = new Room();
		booking.setRoom(room);
		
		assertNotNull(booking.getRoom());
	}
	
	@Test 
	void TestBookingHasCustomer(){
		Customer custsomer = new Customer();
		booking.setCustomer(custsomer);
		
		assertNotNull(booking.getCustomer());
	}
	
	@Test
	void TestBookingDates() {
		Date startDate = new Date(2019-03-27);
		Date endDate = new Date(2019-03-30);
		booking.setStartDate(startDate);
		booking.setEndDate(endDate);
		
		assertNotNull(booking.getStartDate());
		assertNotNull(booking.getEndDate());
		assertEquals(booking.getStartDate(), startDate);
		assertEquals(booking.getEndDate(), endDate);
	}

}
