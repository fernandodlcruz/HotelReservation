package com.hotelreservation.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FilterRoom extends Filter {

	public FilterRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilterRoom(int roomNumber, 
			Date checkInDate, 
			Date checkOutDate,
			int capacity) {
		super(roomNumber, checkInDate, checkOutDate, capacity);
		// TODO Auto-generated constructor stub
	}

}
