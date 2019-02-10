package com.hotelreservation.model;

import java.util.Date;

public class Filter {
	protected int roomNumber;	
	protected Date checkInDate;
	protected Date checkOutDate;
	protected int roomCapacity;
	
	public Filter() {		
	}
	
	public Filter(int roomNumber, Date checkInDate, Date checkOutDate, int capacity) {
		this.roomNumber = roomNumber;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		roomCapacity = capacity;
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
	public int getRoomCapacity() {
		return roomCapacity;
	}
	public void setRoomCapacity(int value) {
		this.roomCapacity = value;
	}
}
