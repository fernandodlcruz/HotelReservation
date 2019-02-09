package com.hotelreservation.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	 private int id;
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String telephone;
	 private int vendorID;
	
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
	 public void setTelephone(String tel)
	 {
		 this.telephone=tel;
	 }
	 public String getTelephone()
	 {
		 return telephone;
	 }
}
