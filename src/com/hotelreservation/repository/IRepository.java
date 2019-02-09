package com.hotelreservation.repository;

import java.util.List;

public interface IRepository<E> {
	boolean Insert(E entity);	 
    boolean Update(E entity);
    boolean Delete(E entity);
    E GetById(E entity);
    List<E> GetAll();
}
