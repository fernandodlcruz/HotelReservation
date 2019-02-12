package com.hotelreservation.service;

import com.hotelreservation.model.Booking;
import com.hotelreservation.repository.BookingDAO;

public class BookingService {
	private BookingDAO dao = new BookingDAO();
	
	public boolean makeReservation(Booking booking) {
		return dao.Insert(booking);
	}
}
