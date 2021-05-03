package com.web_customer_tracker.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web_customer_tracker.dao.CustomerDAO;
import com.web_customer_tracker.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();

		Query<Customer> query = session.createQuery("from Customer", Customer.class);

		List<Customer> customers = query.getResultList();

		return customers;
	}

}
