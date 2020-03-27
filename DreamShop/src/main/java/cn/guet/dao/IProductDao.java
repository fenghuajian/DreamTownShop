package cn.guet.dao;


import cn.guet.domain.Permission;
import cn.guet.domain.Product;
import cn.guet.domain.carinfo;
import cn.guet.util.PageModel;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface IProductDao  {
    /**
     * 分页，获取所有商品信息
     *获取数量
     * 获取分页信息
     *
     * @return
     */
    @Select("SELECT * FROM PRODUCT")
    List<Product> getAll();
    @Select("select count(*) from product")
    int getNum();
    @Select("SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM  product) t WHERE rownum<=#{endRow}) WHERE rn>=#{startRow}")
    List<Product> findPage(@Param("startRow") int startRow, @Param("endRow") int endRow);
    //PageModel<Product> getAllProduct(int currentPage);

    /**
     * 根据productid删除product
     * @param productId
     * @return
     */
    @Delete("DELETE FROM product where productid=#{productid}")
    int deleteProduct(String productId);

    /**
     * 保存product

     * @param product
     */
    @Insert("insert into product(productid,categoryid,name,price,onlinedate,descinfo,picurl,isjingxuan,isremai,isxiajia,shopid,shopname)" +
            " values('${productId}','${categoryId}','${name}','${price}','${onlineDate}','${descInfo}','${picURL}'," +
            "'${isJingXuan}','${isReMai}','${isXiaJia}','${shopid}','${shopname}')")
    void save(Product product);

    /**
     * 更改商品
     * @param product
     */
    @Update("update product set productid='${productId}',categoryid='${categoryId}',name='${name}'," +
            "price='${price}',onlineDate='${onlineDate}',descinfo='${descInfo}'," +
            "picurl='${picURL}',isjingxuan='${isJingXuan}',isremai='${isReMai}',isxiajia='${isXiaJia}'," +
            "shopid='${shopid}',shopname='${shopname}' where productid='${productId}'")
    void update(Product product);

    /**
     * 根据用户id获取购物车信息
     * @param customerId
     * @return
     */
    @Select("select p.*，c.num from product p,cart c where p.productid=c.productid and c.customerid=#{customerid}")
    List<carinfo> getOrder(String customerId);

    /**
     * 根据productid拿到商品
     * @param productid
     * @return
     */
    @Select("select * from product where productid=#{productid}")
    Product SelectProductById(String productid);

    //PageModel<Product> getProduct(int currentPage, String categoryId);

    /**
     * 将商品加入购物车
     * 1、先判断购物车是否有这商品
     * 2、没有则添加
     * 3、有则数量加1
     * @param productId
     * @param customerId
     */
    @Select("select num from cart where productid=#{productid} and customerid=#{customerid}")
    int getCartNum(@Param("productid") String productId, @Param("customerid") String customerId);
    @Insert("insert cart(productid,customerid,num) values(#{productid},#{customerid},1)")
    void addToCar(@Param("productid") String productId, @Param("customerid") String customerId);
    @Update("update cart set num=num+1 where productid=#{productid} and customerid=#{customerid}")
    void updateCarNum(@Param("productid") String productId, @Param("customerid") String customerId);

   // List<Product> getOther(String productid);

    /**
     * 根据搜索名称分页
     * 1、拿到所有数据
     * 2、拿到所有数
     * 3、拿到分页信息
     *
     * @param name
     * @return
     */

    @Select("  select p.*,c.name from product p left join category c\n" +
            "                on  p.categoryid=c.categoryid \n" +
            "                where p.name like '%${name}%' or shopname like '%${name}%' or c.name like '%${name}%'")
    List<Product> getAllByName(@Param("name") String name);

    @Select(" select count(*) from product p left join category c\n" +
            "                on  p.categoryid=c.categoryid \n" +
            "                where  p.name like '%${n}%' or  c.name like '%${n}%' or  p.shopname like '%${n}%'  ")
    int getNumByName(@Param("n") String n);

    @Select("     SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT p.*,c.name cname FROM \n" +
            "                 product p left join category c on p.categoryid=c.categoryid\n" +
            "                ) t WHERE rownum<=4 AND  (name like \n" +
            "                '%${name}%' or shopname like '%${name}%' or cname like '%${name}%')\n" +
            "                ) WHERE rn>=0")
    List<Product> pageByName(@Param("startRow") int startRow, @Param("endRow") int endRow,@Param("name") String name);
    Product SelectProductById();
    PageModel<Product> getProduct(int currentPage, String categoryId);
    List<Product> getOther(String productid);
    PageModel<Product> getProduct1(int currentPage, String name);
    PageModel<Product> getAllProduct(int currentPage);
   // PageModel<Product> getProduct1(int currentPage, String name);
}
