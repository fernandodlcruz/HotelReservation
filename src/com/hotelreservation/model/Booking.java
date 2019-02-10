package com.hotelreservation.model;
import  java.util.*;

public class Booking {
	private Room room;
	private Customer customer;
	private Date startDate;
	private Date endDate;
	
	public Booking(Room room, Customer customer, int vendor, Date startDate, Date endDate) {
		
		this.room = room;
		this.customer = customer;
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
