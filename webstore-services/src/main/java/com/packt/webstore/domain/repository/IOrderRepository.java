package com.packt.webstore.domain.repository;

import java.util.List;

import com.packt.webstore.domain.Order;

public interface IOrderRepository {

	public void addOrder(Order order);

	public List<Order> getOrdersList();

	public Order getOrderById(String orderId);
}
