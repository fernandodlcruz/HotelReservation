package com.hotelreservation.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	 private int id;
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String phone;
	 private int vendorID;
	 
	 public Customer() {
		 
	 }
	 
	 public Customer(int id, String firstName, String lastName, String email, String phone, int vendorID) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.phone = phone;
			this.vendorID = vendorID;
	 }
	
	 public int getVendorID() {
		return vendorID;
	}
	
	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}
	
	public int getId() {
	  return id;
	 }
	
	 public void setId(int id) {
	  this.id = id;
	 }
	
	 public String getFirstName() {
	  return firstName;
	 }
	
	 public void setFirstName(String firstName) {
	  this.firstName = firstName;
	 }
	
	 public String getLastName() {
	  return lastName;
	 }
	
	 public void setLastName(String lastName) {
	  this.lastName = lastName;
	 }
	 public void setEmail(String em) {
		  this.email = em;
		 }
	 public String getEmail() {
		  return email;
		 }
	 public void setPhone(String tel)
	 {
		 this.phone=tel;
	 }
	 public String getPhone()
	 {
		 return phone;
	 }
}
