package com.abcbank.service;

import com.abcbank.dto.CustomerDto;
import com.abcbank.model.Customer;

public interface CustomerService {

	
	public Object updateById(CustomerDto customerDto);

	public Object getCustomer(int customer_id);

	public Object getAllCustomer();

	public Object validateUser(Customer customer);

	public Object savecustomer(Customer customer);

}
