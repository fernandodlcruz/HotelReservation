package com.hotelreservation.repository;

import java.util.List;

public interface IRepository<E> {
	boolean Insert(E entity);	 
    boolean Update(E entity);
    boolean Delete(int id);
    E GetById(int id);
    List<E> GetAll();
}
