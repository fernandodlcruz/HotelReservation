package com.hotelreservation.service;

import java.util.List;
import com.hotelreservation.model.Booking;
import com.hotelreservation.repository.BookingDAO;

public class BookingService {
	private BookingDAO dao = new BookingDAO();
	
	public boolean makeReservation(Booking booking) {
		return dao.Insert(booking);
	}
	
	//Should this be implemented in CustomerService instead?
	public List<Booking> getBookings(int customerID){
		return dao.GetByCustomer(customerID);
	}
}
