package com.hotelreservation.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import com.hotelreservation.model.Booking;
import com.hotelreservation.repository.BookingDAO;
import com.hotelreservation.repository.CustomerDAO;

public class BookingService {
	private BookingDAO dao = new BookingDAO();
	
	public boolean makeReservation(Booking booking) {
		boolean result = dao.Insert(booking); 
		
		notifyHotel(booking, "booked");
		return result;
	}
	
	public List<Booking> getBookings(int customerID){
		return dao.GetByCustomer(customerID);
	}
	
	public boolean cancelReservation(int bookingID) {
		return dao.Delete(bookingID);
	}
	
	public boolean updateReservation(Booking booking) {
		boolean result = dao.Update(booking); 
		notifyHotel(booking, "modified");
		return result;
	}
	
	public boolean notifyHotel(Booking booking, String action) {
		BufferedWriter writer = null;
		CustomerDAO custDAO = new CustomerDAO();
		
        try {
        	booking.setCustomer(custDAO.GetById(booking.getCustomer().getId()));
        	
            //create a temporary file
            File logFile = new File("Room " + booking.getRoom().getRoomNumber() + " " + action + ".txt");

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("Customer " + booking.getCustomer().getFirstName() + " " +
            		booking.getCustomer().getLastName() +
            		" " + action + " the room " + booking.getRoom().getRoomNumber() +
            		" between " + booking.getStartDate() + " and " +
            		booking.getEndDate());
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
        
        return false;
	}
}
