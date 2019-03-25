package com.hotelreservation.entitiestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hotelreservation.model.FilterRoom;

class FilterRoomTests {

	FilterRoom ft;
	
	@BeforeEach
	void setUp() throws Exception {
		ft = new FilterRoom();
	}
	
	@Test
	void TestFilterRoomRoomNumber() {
		ft.setRoomNumber(102);
		
		assertEquals(ft.getRoomNumber(), 102);
	}
	
	@Test
	void TestFilterRoomRoomCapacity() {
		ft.setRoomCapacity(2);
		assertEquals(ft.getRoomCapacity(), 2);
	}
	
	@Test
	void TestBookingDates() {
		Date startDate = new Date(2019-03-27);
		Date endDate = new Date(2019-03-30);
		ft.setCheckInDate(startDate);
		ft.setCheckOutDate(endDate);
		
		assertNotNull(ft.getCheckInDate());
		assertNotNull(ft.getCheckOutDate());
		assertEquals(ft.getCheckInDate(), startDate);
		assertEquals(ft.getCheckOutDate(), endDate);
	}

}
