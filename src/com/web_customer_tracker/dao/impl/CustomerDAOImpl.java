package com.web_customer_tracker.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.web_customer_tracker.dao.CustomerDAO;
import com.web_customer_tracker.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();

		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

		List<Customer> customers = query.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("delete from Customer where id = :customerId ");

		query.setParameter("customerId", id);

		query.executeUpdate();
	}

	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();

		Customer customer = session.get(Customer.class, id);

		return customer;
	}

	@Override
	public List<Customer> searchCustomer(String searchName) {
		Session session = sessionFactory.getCurrentSession();

		Query query = null;

		if (searchName != null && searchName.trim().length() > 0) {
			query = session.createQuery("from Customer where lower(firstName) like :name or lower(lastName) like :name",
					Customer.class);
			query.setParameter("name", "%" + searchName.toLowerCase() + "%");
		} else {
			query = session.createQuery("from Customer", Customer.class);
		}

		List<Customer> customers = query.getResultList();

		return customers;
	}
}
