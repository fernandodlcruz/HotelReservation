package com.hotelreservation.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hotelreservation.model.Customer;
import com.hotelreservation.service.CustomerService;

@Path("customers")
public class CustomerResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllCustomers() {
		return new ArrayList<>(CustomerService.getAllCustomers().values());
	}
	
	@GET
	@Path("/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("customerId") int custId) {
		Customer cust = CustomerService.getCustomer(custId);
		return cust;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Customer createCustomer(Customer cust) {
		Customer customer = CustomerService.createCustomer(cust);
		return customer;
	}
}
