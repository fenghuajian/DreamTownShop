package cn.guet.service.impl;


import cn.guet.dao.IOrderDao;
import cn.guet.domain.Orderinfo;
import cn.guet.domain.Orders;
import cn.guet.service.IOrderService;
import cn.guet.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements IOrderService {
	@Autowired
	IOrderDao orderDao;

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

		//orderDao.update1(orders);
	}

	@Override
	public void delete(String productId) {
		orderDao.deleteOrder(productId);
	}

	@Override
	public void updataCar(Orderinfo orderinfo) {
		//orderDao.updateCar(orderinfo);
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
