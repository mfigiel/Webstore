package com.packt.webstore.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.packt.webstore.domain.Customer;

import com.packt.webstore.domain.repository.ICustomerRepository;

@Component
@WebService(endpointInterface="com.packt.webstore.service.ICustomerService", serviceName="customerService")
public class CustomerServiceImpl {

	@Autowired
	private ICustomerRepository customerRepository;

	public Integer addCustomer(Customer customer) {
		return customerRepository.addCustomer(customer);
	}

	public List<Customer> getCustomersList() {
		return customerRepository.getCustomersList();
	}
}
