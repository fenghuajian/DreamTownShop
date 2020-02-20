package dao;

import bean.Orderinfo;
import bean.Orders;

public interface IOrderDao extends IBaseDao<Orders> {
	void deleteOrder(String productId);
	void saveOrder(Orderinfo orderinfo);

	void updateCar(Orderinfo orderinfo);
}
