package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Products.Product;
import com.packt.webstore.domain.repository.IProductRepository;

@Primary
@Repository
public class ProductRepositoryHibernate implements IProductRepository {

	private static final SessionFactory sessionFactory = HibernateSessionFactory.buildSessionFactory();

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void sessionShutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	@Override
	public List<Product> getAllProducts() {
		Session session = getSessionFactory().openSession();
		List<Product> products = new ArrayList<Product>();
		try {
			session.getTransaction().begin();
			products = session.createQuery("FROM Product where withdrawn <> 'Y'").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return products;
	}

	@Override
	public Product getProductById(String productId) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		Product product = new Product();
		try {
			tx = session.beginTransaction();
			product = (Product) session.get(Product.class, productId);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return product;
	}

	@Override
	public void addProduct(Product newProduct) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(newProduct);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void withdrawProduct(int deletedProductId) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Product product = (Product) session.get(Product.class, String.valueOf(deletedProductId));
			product.setWithdrawn("Y");
			session.save(product);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void editProduct(Product editedProduct) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(editedProduct);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		Session session = getSessionFactory().openSession();
		List<Product> products = new ArrayList<Product>();
		String selectByCategory = "select e from products e where e.category = :category";
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			products = session.createQuery(selectByCategory, Product.class).setParameter("category", category)
					.getResultList();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return products;
	}

	@Override
	public List<Product> getProductsByParameter(String parameter, String value) {
		Session session = getSessionFactory().openSession();
		List<Product> products = new ArrayList<Product>();
		String selectByCategory = "select e from products e where e.:parameter = :value";
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			products = session.createQuery(selectByCategory, Product.class).setParameter("parameter", parameter)
					.setParameter("value", value).getResultList();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return products;
	}

}
