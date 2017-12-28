package com.packt.webstore.domain.repository;

import java.util.List;

import com.packt.webstore.domain.Customer;

public interface ICustomerRepository {

	public Integer addCustomer(Customer customer);

	public List<Customer> getCustomersList();
}
