package cn.guet.service;

import cn.guet.domain.Orderinfo;
import cn.guet.domain.Orders;
import cn.guet.util.PageModel;

;

public interface IOrderService {
	void saveOrder(Orderinfo orderinfo);

	PageModel<Orders> getAllProduct(int parseInt);

	void update(Orders order);
	
	void delete(String productId);

	void updataCar(Orderinfo orderinfo);

    PageModel<Orderinfo> listOrderinfo(int parseInt, String shopid);

    void updateOrderinfo(String orderid, String status);

	void deleteOrderinfo(String orderid, String status);

    PageModel<Orderinfo> getOderinfo(int parseInt, String customerid);

    void cancelOrder(String orderid);
}
