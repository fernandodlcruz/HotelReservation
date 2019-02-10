package com.hotelreservation.model;

public class Room {
	private int roomNumber;
	private int roomCapacity;
	private double price;
	private String description;

	public Room() {		
	}
	
	public Room(int roomNumber, int roomCapacity, double price, String description) {
		this.roomNumber = roomNumber;
		this.roomCapacity = roomCapacity;
		this.price = price;
		this.description = description;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int rmNmbr) {
		this.roomNumber = rmNmbr;
	}
	public int getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(int rmCpct) {
		this.roomCapacity = rmCpct;
	}
	public double getPrice() {
		return price;
	}

	public void setPrice(int prc) {
		this.price = prc;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String dscrptn) {
		this.description = dscrptn;
	}
	
	
}

