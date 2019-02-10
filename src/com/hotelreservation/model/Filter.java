package com.hotelreservation.model;

import java.util.Date;

public class Filter {
	protected int roomNumber;	
	protected Date checkInDate;
	protected Date checkOutDate;
	
	public Filter() {		
	}
	
	public Filter(int roomNumber, Date checkInDate, Date checkOutDate) {
		this.roomNumber = roomNumber;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
}
