package dao;

import bean.Customer;

public interface ICustomerDao extends IBaseDao<Customer>{
	int isExist(String account);
	String verify(String customerName, String password);
}