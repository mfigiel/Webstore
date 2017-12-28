package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.repository.IOrderRepository;

@Repository
public class OrderRepository implements IOrderRepository {

	private static final SessionFactory sessionFactory = HibernateSessionFactory.buildSessionFactory();

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void sessionShutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	@Override
	public void addOrder(Order order) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		Integer orderID = null;
		try {
			tx = session.beginTransaction();
			orderID = (Integer) session.save(order);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public List<Order> getOrdersList() {
		Session session = getSessionFactory().openSession();
		List<Order> orderList = new ArrayList<Order>();
		try {
			session.getTransaction().begin();
			String sql = "Select c from " + Order.class.getName() + " c " + " order by c.name";
			TypedQuery<Order> query = session.createQuery(sql);
			orderList = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return orderList;
	}

	@Override
	public Order getOrderById(String orderId) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		Order order = new Order();
		try {
			tx = session.beginTransaction();
			order = (Order) session.get(Order.class, orderId);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return order;
	}

}
