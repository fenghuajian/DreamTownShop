package service;

import domain.Product;
import domain.carinfo;
import util.PageModel;

import java.util.List;

public interface IProductService {

	PageModel<Product> getAllProduct(int currentPage);
	int deleteProduct(String productId);
	void save(Product product);
	void update(Product product);
	List<carinfo> getOrder(String customerId);

	Product SelectProductById(String productid);
	PageModel<Product> getProduct(int currentPage, String categoryId);
	void addCar(String productId, String customerId);
	List<Product> getOther(String productid);

    PageModel<Product> getProduct1(int currentPage, String name);

    PageModel<Product> getProduct2(int parseInt, String name);

    int addCollection(String productId, String customerId);

	List<Product> getProductRemai(String name);

    PageModel<Product> viewCollection(int parseInt, String customerid);

	void deleteCollection(String productId, String customerId);
}