package com.hotelreservation.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.JSONP;

import com.hotelreservation.model.Customer;
import com.hotelreservation.service.CustomerService;

@Path("customers")
public class CustomerResource {
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllCustomers() {
		return new ArrayList<>(CustomerService.getAllCustomers().values());
	}*/
	
	@GET
	@Path("/sign-in")
	@Produces({"application/x-javascript"})
	@JSONP(callback="jsonp",queryParam="callback")
	public Customer getCustomer(@QueryParam("callback") String callback,
			@PathParam("email") String email,
			@PathParam("vendorId") int vendorID) {
		CustomerService custService = new CustomerService();
		
		return custService.getCustomer(email, vendorID);
	}
	
	@GET
	@Path("/sign-up")
	@Produces({"application/x-javascript"})
	@JSONP(callback="jsonp",queryParam="callback")
	public Customer createCustomer(@QueryParam("callback") String callback,
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("email") String email,
			@QueryParam("phone") String phone,
			@QueryParam("vendorID") int vendorID) {
		CustomerService custService = new CustomerService();
		Customer cust = new Customer(0, firstName, lastName, email, phone, vendorID);
		
		return custService.createCustomer(cust);
	}
}
