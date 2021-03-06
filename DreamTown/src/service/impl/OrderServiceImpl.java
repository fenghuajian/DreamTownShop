package service.impl;

import domain.Orderinfo;
import util.PageModel;

import domain.Orders;
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
		/*return orderDao.selectAll(parseInt);*/
		return null;
	}

	@Override
	public void update(Orders orders)

	{

		orderDao.update1(orders);
	}

	@Override
	public void delete(String productId) {
		orderDao.deleteOrder(productId);
	}

	@Override
	public void updataCar(Orderinfo orderinfo) {
		orderDao.updateCar(orderinfo);
	}

	@Override
	public PageModel<Orderinfo> listOrderinfo(int parseInt,String shopid) {
		return orderDao.listOrderinfo(parseInt,shopid);
	}

	@Override
	public void updateOrderinfo(String orderid, String status) {
		orderDao.updateOrderinfo(orderid,status);
	}

	@Override
	public void deleteOrderinfo(String orderid, String status) {
		orderDao.deleteOrderinfo(orderid,status);
	}

	@Override
	public PageModel<Orderinfo> getOderinfo(int parseInt, String customerid) {
		return orderDao.getOderinfo(parseInt,customerid);
	}

	@Override
	public void cancelOrder(String orderid) {
		orderDao.cancelOrder(orderid);
	}
}
