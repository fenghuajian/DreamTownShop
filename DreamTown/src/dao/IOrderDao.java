package dao;

import bean.Orders;

public interface IOrderDao extends IBaseDao<Orders> {
	void deleteOrder(String productId);
}
