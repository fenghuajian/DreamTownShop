package service.impl;

import bean.Orderinfo;
import util.PageModel;

import bean.Orders;
import dao.IOrderDao;
import dao.imp.OrderDaoImpl;
import service.IOrderService;

public class OrderServiceImpl implements IOrderService {
	
	IOrderDao orderDao;
	public OrderServiceImpl() {
		orderDao=new OrderDaoImpl();
	}

	@Override
	public void saveOrder(Orderinfo orderinfo) {
		orderDao.saveOrder(orderinfo);
	}

	@Override
	public PageModel<Orders> getAllProduct(int parseInt) {
		return orderDao.selectAll(parseInt);
	}

	@Override
	public void update(Orders order) {
		orderDao.update(order);
	}

	@Override
	public void delete(String productId) {
		orderDao.deleteOrder(productId);
	}

	@Override
	public void updataCar(Orderinfo orderinfo) {
		orderDao.updateCar(orderinfo);
	}
}
