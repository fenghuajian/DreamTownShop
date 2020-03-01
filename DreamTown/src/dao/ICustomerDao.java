package dao;

import bean.Customer;

public interface ICustomerDao extends IBaseDao<Customer>{
	int isExist(String account);
	String verify(String customerName, String password);

	void updateCustomer(Customer customer);

    void addShop(String customerid, String shopid, String shopname);

    void updaterole(String userid, String roleid);

	void saveUser(String userid, String username, String password);
}