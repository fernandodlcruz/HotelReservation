package com.hotelreservation.entitiestests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hotelreservation.model.Customer;

class CustomerTests {

	Customer customer; 
	
	@BeforeEach
	public void setUp() {
		customer = new Customer();
	}
	
	@Test
	void TestCustomerId() {
		customer.setId(1);
		assertEquals(customer.getId(), 1);
	}
	
	@Test
	void TestCustomerFirstName() {
		customer.setFirstName("Sam");
		assertEquals(customer.getFirstName(), "Sam");
	}
	
	@Test
	void TestCustomerLastName() {
		customer.setLastName("Navarro");
		assertEquals(customer.getLastName(), "Navarro");
	}
	
	@Test
	void TestCustomerEmail() {
		customer.setEmail("testemail@email.com");
		assertEquals(customer.getEmail(), "testemail@email.com");
	}
	
	@Test
	void TestCustomerPhone() {
		customer.setPhone("7785439087");
		assertEquals(customer.getPhone(), "7785439087");
	}
	
	@Test
	void TestCustomerVendorID() {
		customer.setVendorID(123);
		assertEquals(customer.getVendorID(), 123);
	}

}
