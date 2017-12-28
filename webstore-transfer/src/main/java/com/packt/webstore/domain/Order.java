package com.packt.webstore.domain;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.packt.webstore.domain.Products.OrderedProduct;
import com.packt.webstore.domain.Products.Product;

public class Order {
	
	private Integer id;
	private List<Product> orderedProducts;
	private Customer customer;
	private SimpleDateFormat orderDate;
	private Set<OrderedProduct> products = new HashSet<OrderedProduct>(0);
	
	public Order(Customer customer) {
		this.customer = customer;
	}
	public Order() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Product> getOrderedProducts() {
		return orderedProducts;
	}
	public void setOrderedProducts(List<Product> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public SimpleDateFormat getOrderDate() {
		return orderDate;
	}
	private void setOrderDate(SimpleDateFormat sdf) {
		this.orderDate = sdf;
	}
	public Set<OrderedProduct> getProducts() {
		return products;
	}
	public void setProducts(Set<OrderedProduct> products) {
		this.products = products;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
