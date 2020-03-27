package dao;

import domain.Product;
import domain.carinfo;
import util.PageModel;

import java.util.List;

public interface IProductDao extends IBaseDao<Product> {
    List<carinfo> getOrder(String customerId);
    void addCar(String productId, String customerId);
    PageModel<Product> getProduct(int currentPage, String categoryId);
    List<Product> getOther(String productid);

    PageModel<Product> getProduct1(int currentPage, String name);

    PageModel<Product> getProduct2(int currentPage, String name);
}
