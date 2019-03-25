package com.hotelreservation.entitiestests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hotelreservation.model.Booking;
import com.hotelreservation.model.Room;

class RoomTests {
	
	Room room;

	@BeforeEach
	void setUp() throws Exception {
		room = new Room();
	}
	
	@Test
	void TestRoomNum() {
		room.setRoomNumber(102);
		assertEquals(room.getRoomNumber(), 102);
	}
	
	@Test
	void TestRoomCapacity() {
		room.setRoomCapacity(2);
		assertEquals(room.getRoomCapacity(), 2);
	}
	
	@Test
	void TestRoomPrice() {
		room.setPrice(99.99);
		assertEquals(room.getPrice(), 99.99);
	}
	
	@Test
	void TestRoomDescription() {
		room.setDescription("regular room with nothing special");
		assertEquals(room.getDescription(), "regular room with nothing special");
	}
}
