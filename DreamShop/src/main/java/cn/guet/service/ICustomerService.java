package cn.guet.service;


import cn.guet.domain.Customer;

public interface ICustomerService {
	Customer getById(String customerId);
	void save(Customer customer);
	int isExist(String account);
	String verify(String customerName, String password);

	void updateCustomer(Customer customer);

    void addShop(String customerid, String shopid, String shopname);

	void updaterole(String userid, String roleid);

	void saveUser(String userid, String username, String password);
}