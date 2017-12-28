package com.packt.webstore.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.repository.IOrderRepository;

@Component
@WebService(endpointInterface="com.packt.webstore.service.IOrderService", serviceName="orderService")
public class OrderServiceImpl {

	@Autowired
	private IOrderRepository orderRepository;

	
	public List<Order> getOrdersList() {	
		return orderRepository.getOrdersList();
	}


	public Order getOrderById(String orderId) {
		return orderRepository.getOrderById(orderId);
	}


	public void addOrder(Order newOrder) {
		orderRepository.addOrder(newOrder);
	}
	
}
