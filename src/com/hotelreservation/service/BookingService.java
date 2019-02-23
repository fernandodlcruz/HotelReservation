package com.hotelreservation.service;

import java.sql.Date;
import java.util.List;
import com.hotelreservation.model.Booking;
import com.hotelreservation.repository.BookingDAO;

public class BookingService {
	private BookingDAO dao = new BookingDAO();
	
	public boolean makeReservation(Booking booking) {
		return dao.Insert(booking);
	}
	
	public List<Booking> getBookings(int customerID){
		return dao.GetByCustomer(customerID);
	}
	
	public boolean cancelReservation(int bookingID) {
		return dao.Delete(bookingID);
	}
	
	public boolean updateReservation(int bookingID, int roomNumber, Date startDate, Date endDate) {
		return dao.Update(bookingID, roomNumber, startDate, endDate);
	}
}
