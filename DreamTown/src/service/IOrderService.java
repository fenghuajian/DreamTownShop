package service;

import util.PageModel;

import bean.Orders;

public interface IOrderService {
	void saveOrder(Orders order);

	PageModel<Orders> getAllProduct(int parseInt);

	void update(Orders order);
	
	void delete(String productId);

}
