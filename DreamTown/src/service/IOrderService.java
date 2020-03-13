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

    PageModel<Orderinfo> listOrderinfo(int parseInt,String shopid);

    void updateOrderinfo(String orderid, String status);

	void deleteOrderinfo(String orderid);

    PageModel<Orderinfo> getOderinfo(int parseInt, String customerid);
}
