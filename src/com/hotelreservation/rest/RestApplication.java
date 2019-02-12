package com.hotelreservation.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import com.hotelreservation.resource.*;

public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(CustomerResource.class);
		s.add(SearchRoomResource.class);
		s.add(BookingResource.class);
		return s;
	}
}