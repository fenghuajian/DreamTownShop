package service;

import bean.Orderinfo;
import util.PageModel;

import bean.Orders;

public interface IOrderService {
	void saveOrder(Orderinfo orderinfo);

	PageModel<Orders> getAllProduct(int parseInt);

	void update(Orders order);
	
	void delete(String productId);

	void updataCar(Orderinfo orderinfo);
}
