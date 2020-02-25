package service;

import bean.Customer;

public interface ICustomerService {
	Customer getById(String customerId);
	void save(Customer customer);
	int isExist(String account);
	String verify(String customerName, String password);

	void updateCustomer(Customer customer);
}