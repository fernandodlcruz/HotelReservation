package com.hotelreservation.service;

import com.hotelreservation.model.Customer;
import com.hotelreservation.repository.CustomerDAO;

public class CustomerService {
	private CustomerDAO dao = new CustomerDAO();

	public Customer getCustomer(String email, int vendorID) {
		return dao.GetByEmailVendor(email, vendorID);
	}

	public Customer createCustomer(Customer customer) {
		return dao.CreateCustomer(customer);
	}
}
