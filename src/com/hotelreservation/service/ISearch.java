package com.hotelreservation.service;

import java.util.List;
import com.hotelreservation.model.Filter;

// Interface that is used to make searches for rooms and reservations
public interface ISearch<E> {
	List<E> ListResult(Filter filter);
}
