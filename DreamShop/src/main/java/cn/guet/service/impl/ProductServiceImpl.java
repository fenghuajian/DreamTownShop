package cn.guet.service.impl;



import cn.guet.dao.IProductDao;
import cn.guet.domain.Product;
import cn.guet.domain.carinfo;
import cn.guet.service.IProductService;
import cn.guet.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductDao productDao;

	@Override
	public PageModel<Product> getAllProduct(int currentPage) {
		return productDao.getAllProduct(currentPage);
	}
	@Override
	public int deleteProduct(String productId) {
		return productDao.deleteProduct(productId);
	}



	@Override
	public void save(Product product) {
		productDao.save(product);
	}
	@Override
	public void update(Product product) {
		productDao.update(product);
	}
	@Override
	public List<carinfo> getOrder(String customerId) {
		return productDao.getOrder(customerId);
	}
	@Override
	public Product SelectProductById(String productid) {

		return productDao.SelectProductById();
	}
	@Override
	public PageModel<Product> getProduct(int currentPage, String categoryId) {
		// TODO Auto-generated method stub
		return productDao.getProduct(currentPage,categoryId);
	}
	@Override
	public List<Product> getOther(String productid) {
		// TODO Auto-generated method stub

		return productDao.getOther(productid);
	}

	@Override
	public PageModel<Product> getProduct1(int currentPage, String name) {
		return productDao.getProduct1(currentPage,name);
	}

	@Override
	public void addCar(String productId, String customerId) {
		// TODO Auto-generated method stub

		 productDao.addToCar(productId,customerId);
	}
}