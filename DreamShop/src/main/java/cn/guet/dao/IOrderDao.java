package cn.guet.dao;


import cn.guet.domain.Orderinfo;
import cn.guet.domain.Orders;
import cn.guet.util.PageModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao  {

	/*
	* 根据商品id删除购物车订单
	* */
		@Delete("elete  from cart where productid=#{productId}")
	void deleteOrder(String productId);

	/**
	 * 保存订单信息
	 *
	 * @param orderinfo
	 */
	@Insert("insert into orderinfo(orderid,status,express,orderdate,productid,pname,price,pic,num,pinfo,customerid,bname,nphone,baddr) " +
			" values('${orderid}','${status}','${express}','${orderdate}','${productid}','${pname}','${price}'," +
			" '${pic}','${num}','${pinfo}','${customerid}','${bname}','${bphone}','${baddr}')")

	void saveOrder(Orderinfo orderinfo);

	/**
	 *  private  String orderid;
	 *
	 * 	 根据productid 和customerid更新购物车信息
	 * 	 分两步，1、先获取orderinfo数量num，再获取cart的数量num1，
	 * 	 		另num2=num1-num
	 * 	 	2、如果num2>0,修改cart的num
	 * 	 		否则，删除对应的cart中数据
	 * @param
	 */
	@Update("update cart set num=#{num} where productid=#{productid} and customerid=#{customerid}")
	void updateCarNum(@Param("num") int num,@Param("productid") String productid,@Param("customerid") String customerid);

	@Select("select num from cart where productid=#{productid} and customerid=#{customerid}")
	int findCarNum(@Param("productid") String productid,@Param("customerid") String customerid);
	@Delete("delete from cart where productid=#{productid} and customerid=#{customerid}")
	void deleteCart(String productid,String customerid);

	/**
	 * 分页：1、获得所有行数
	 * 2、获取所有信息放进list
	 * 3、分为商家和管理员
	 * 4、获取当前页
	 *
	 */
	@Select("SELECT COUNT(*) FROM ORDERINFO")
	int findAllNum();
	@Select("select count(*) from orderinfo where shopid=#{shopid}")
	int finnumByshopid(String shopid);
	@Select("SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM orderinfo \n" +
			"\t\t\t\t) t WHERE t.status in('已支付,尚未发货','已收货','已发货','用户已收货,并删除订单') and rownum<='${endRow}'\n" +
			"      ) WHERE rn>='${startRow}'")
	List<Orderinfo> findAll(@Param("startRow") int startRow,@Param("endRow") int endRow);
	@Select(" SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM orderinfo \n" +
			"\t\t\t\t ) t WHERE t.status in('已支付,尚未发货','已发货','用户已收货,并删除订单','已收货') and rownum<='${endRow}' and t.productid in (select productid from product \n" +
			"\t\t\t\t            where shopid='${shopid}')\n" +
			"\t\t\t\t) WHERE rn>='${startRow}'")
	List<Orderinfo> findByShopid(@Param("startRow") int startRow,@Param("endRow") int endRow,@Param("shopid") String shopid);

    PageModel<Orderinfo> listOrderinfo(int parseInt, String shopid);

	/**
	 * 修改订单状态
	 * @param s
	 * @param orderid
	 */
	@Update("update orderinfo set status=#{status} where orderid=#{orderid}")
    void updateOrderinfo(@Param("status") String s, @Param("orderid") String orderid);

	void deleteOrderinfo(String orderid, String status);

	/**
	 * 分页，获取用户的订单信息
	 * 1、先获取订单数量
	 * 2、获取自个订单并放入list
	 *
	 * @return
	 */
	@Select("select count(*) from orderinfo where customerid=#{customerid}")
	int findNunByCustomerid(String customerid);
	@Select("        SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM orderinfo \n" +
			"  ) t WHERE customerid='${customerid}' and status in('已支付,尚未发货','已发货','商家已发货,并删除订单','已收货')and rownum<='${endRow}'\n" +
			"     ) WHERE rn>='${startRow}'")
	List<Orderinfo> findBycustomerid(@Param("startRow") int startRow,@Param("endRow") int endRow,@Param("customerid") String customerid);
    PageModel<Orderinfo> getOderinfo(int parseInt, String customerid);

	/**
	 * 取消订单
	 * @param orderid
	 */
	@Delete("delete orderinfo where orderid=#{orderid}")
	void cancelOrder(String orderid);


}
