package com.web_customer_tracker.dao;

import java.util.List;

import com.web_customer_tracker.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer); 

}
