package cn.guet.service.impl;


import cn.guet.dao.ICustomerDao;
import cn.guet.domain.Customer;
import cn.guet.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
@Transactional()
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	ICustomerDao customerDao;

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	@Override
	public int isExist(String account) {
		return customerDao.isExist(account);
	}
	@Override
	public Customer getById(String customerId) {
		return customerDao.getById(customerId);
	}
	@Override
	public String verify(String customerName, String password) {
		return customerDao.verify(customerName, password);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}

	@Override
	public void addShop(String customerid, String shopid, String shopname) {
		customerDao.addShop(customerid,shopid,shopname);
	}

	@Override
	public void updaterole(String userid, String roleid) {
		customerDao.updaterole(userid,roleid);
	}

	@Override
	public void saveUser(String userid, String username, String password) {
		customerDao.saveUser(userid,username,password);
	}
}