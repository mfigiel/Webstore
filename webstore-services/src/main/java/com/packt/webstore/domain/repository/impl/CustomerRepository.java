package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.ICustomerRepository;

@Repository
public class CustomerRepository implements ICustomerRepository {

	private static final SessionFactory sessionFactory = HibernateSessionFactory.buildSessionFactory();

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void sessionShutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	@Override
	public Integer addCustomer(Customer customer) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		Integer customerId = null;
		try {
			tx = session.beginTransaction();
			customerId = (Integer) session.save(customer);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		return customerId;
	}

	@Override
	public List<Customer> getCustomersList() {
		Session session = getSessionFactory().openSession();
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			session.getTransaction().begin();
			String sql = "Select c from " + Customer.class.getName() + " c " + " order by c.name";
			TypedQuery<Customer> query = session.createQuery(sql);
			customerList = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return customerList;
	}
}
