package service.impl;

import bean.Product;
import bean.carinfo;
import dao.IProductDao;
import dao.imp.ProductDaoImpl;
import service.IProductService;
import util.PageModel;

import java.util.List;


public class ProductServiceImpl implements IProductService {

	private IProductDao productDao;
	public ProductServiceImpl(){
		productDao=new ProductDaoImpl();
	}
	@Override
	public PageModel<Product> getAllProduct(int currentPage) {
		return productDao.selectAll(currentPage);
	}
	@Override
	public int deleteProduct(String productId) {
		return productDao.delete(productId);
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

		return productDao.getById(productid);
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

		 productDao.addCar(productId,customerId);
	}
}