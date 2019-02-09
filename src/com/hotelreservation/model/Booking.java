package com.hotelreservation.model;
import  java.util.*;

public class Booking {
	private Room room;
	private Customer customer;
	private int vendor;
	private Date startDate;
	private Date endDate;
	
	public Booking(Room room, Customer customer, int vendor, Date startDate, Date endDate) {
		
		this.room = room;
		this.customer = customer;
		this.vendor = vendor;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getVendor() {
		return vendor;
	}

	public void setVendor(int vendor) {
		this.vendor = vendor;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	

	
	
	
	

}
