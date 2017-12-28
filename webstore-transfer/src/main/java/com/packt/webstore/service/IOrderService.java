package com.packt.webstore.service;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.packt.webstore.domain.Order;

import java.util.List;

@WebService
public interface IOrderService {

	@WebMethod
	public void addOrder(Order order);
	@WebMethod
	public List<Order> getOrdersList();
	@WebMethod
	public Order getOrderById(String orderId);
}
