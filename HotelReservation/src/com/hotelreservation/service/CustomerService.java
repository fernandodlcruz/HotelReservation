package com.hotelreservation.service;

import java.util.HashMap;
import java.util.Map;
import com.hotelreservation.model.Customer;
import com.hotelreservation.repository.CustomerDAO;

public class CustomerService {
	private static Map<Integer, Customer> customers = new HashMap<>();
	private static int id = 1;
	private static CustomerDAO dao = new CustomerDAO();

	static {
		 initializeCustomer();
	}

	private static void initializeCustomer() {
		Customer cust = new Customer();

		cust.setFirstName("Chamu");
		cust.setLastName("Majety");
		cust.setId(id);
		id++;
	
		Customer cust2 = new Customer();
		cust2.setFirstName("Siva");
		cust2.setLastName("Ponnam");
		cust2.setId(id);
		id++;

		customers.put(cust.getId(), cust);
		customers.put(cust2.getId(), cust2);
	}

	public static Map<Integer, Customer> getAllCustomers() {
		return customers;
	}

	public static Customer getCustomer(int id) {
		Customer cust = dao.GetById(id); // customers.get(id);
		return cust;
	}

	public static Customer createCustomer(Customer customer) {
		Customer cust = new Customer();
		
		cust.setFirstName(customer.getFirstName());
		cust.setLastName(customer.getLastName());
		cust.setId(id);
		id++;
		return cust;
	}
}
