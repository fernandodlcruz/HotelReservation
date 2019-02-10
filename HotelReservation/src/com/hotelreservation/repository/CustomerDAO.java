package com.hotelreservation.repository;

import java.util.List;
import com.hotelreservation.model.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO implements IRepository<Customer> {

	@Override
	public boolean Insert(Customer entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Update(Customer entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer GetById(int id) {
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * FROM Customer WHERE id= " + id;
		
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstName(rs.getString("name"));
                return customer;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
	}

	@Override
	public List<Customer> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
