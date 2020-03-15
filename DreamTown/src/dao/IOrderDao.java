package dao;

import bean.Orderinfo;
import bean.Orders;
import util.PageModel;

public interface IOrderDao extends IBaseDao<Orderinfo> {
	void deleteOrder(String productId);
	void saveOrder(Orderinfo orderinfo);

	void updateCar(Orderinfo orderinfo);

    PageModel<Orderinfo> listOrderinfo(int parseInt, String shopid);

	void update1(Orders orders);

    void updateOrderinfo(String s, String orderid);

	void deleteOrderinfo(String orderid, String status);

    PageModel<Orderinfo> getOderinfo(int parseInt, String customerid);

	void cancelOrder(String orderid);
}
