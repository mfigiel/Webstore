package com.packt.webstore.service;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.packt.webstore.domain.Customer;

import java.util.List;

@WebService
public interface ICustomerService {

	@WebMethod
	public Integer addCustomer(Customer customer);
	@WebMethod
	public List<Customer> getCustomersList();
}
