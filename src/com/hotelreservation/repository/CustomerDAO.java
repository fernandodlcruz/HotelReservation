package com.hotelreservation.repository;

import java.util.List;
import com.hotelreservation.model.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO implements IRepository<Customer> {

	private Connection conn;
	
	public CustomerDAO() {
		conn = ConnectionFactory.getConnection();
	}
	
	@Override
	public boolean Insert(Customer entity) {
		Statement stmt = null;
		
		String query = "INSERT INTO CUSTOMER (FirstName, LastName, Email, Phone, VendorID) "
				+ "VALUES ('"
				+ entity.getFirstName() + "', '" 
				+ entity.getLastName() + "', '" 
				+ entity.getEmail() + "', " 
				+ entity.getPhone() + ", "
				+ entity.getVendorID() + ")";
		
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(stmt!=null)
                   stmt.close();
             } catch(SQLException se2) {
             }// nothing we can do
             try{
                if(conn!=null)
                   conn.close();
             } catch(SQLException se) {
                se.printStackTrace();
             }
         }
        
        return false;
	}

	@Override
	public boolean Update(Customer entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Delete(int id) {
		Connection connection = ConnectionFactory.getConnection();
		String query = "DELETE FROM CUSTOMER WHERE CustomerID = " + id;
		
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
	}

	@Override
	public Customer GetById(int id) {
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * FROM CUSTOMER WHERE CustomerID = " + id;
		
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
                Customer customer = new Customer(rs.getInt("CustomerID"),
                		rs.getString("FirstName"),
                		rs.getString("LastName"),
                		rs.getString("Email"),
                		rs.getString("Phone"),
                		rs.getInt("VendorID"));
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
