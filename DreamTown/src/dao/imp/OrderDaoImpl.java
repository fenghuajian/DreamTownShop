package dao.imp;

import bean.Orderinfo;
import bean.Orders;
import bean.Product;
import dao.IOrderDao;
import util.DBConnection;
import util.PageModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl<Orderinfo> implements IOrderDao {

	@Override
	public void deleteOrder(String productId) {
		String sql="delete  from cart where productid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConn(conn);
	}

	@Override
	public void saveOrder(Orderinfo orderinfo) {
		String sql="insert into orderinfo values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, orderinfo.getOrderid());
			pstmt.setString(2, orderinfo.getCustomerid());
			pstmt.setString(3, orderinfo.getProductid());
			pstmt.setString(4, orderinfo.getStatus());
			pstmt.setString(5, orderinfo.getExpress());
			pstmt.setTimestamp(6, orderinfo.getOrderdate());
			pstmt.setString(7, orderinfo.getPname());

			pstmt.setString(8, orderinfo.getPic());
			pstmt.setString(9, orderinfo.getPinfo());
			pstmt.setInt(10, orderinfo.getNum());
			pstmt.setString(11, orderinfo.getBname());
			pstmt.setString(12, orderinfo.getBphone());
			pstmt.setString(13, orderinfo.getBaddr());
			pstmt.setFloat(14, orderinfo.getPrice());
			pstmt.executeUpdate();
			System.out.println("已经把商品"+orderinfo.getPname()+"加入订单");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConn(conn);
	}

	@Override
	public void updateCar(Orderinfo orderinfo) {

		String sql="select num from cart where productid=? and customerid=?";
		String sql1="update cart set num=? where productid=? and customerid=? ";
		String sql2="delete from cart where productid=? and customerid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		conn=DBConnection.getConn();
		int num1=0;int num2=0;
		int num=0;
		num=orderinfo.getNum();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,orderinfo.getProductid());
			pstmt.setString(2,orderinfo.getCustomerid());
			rs=pstmt.executeQuery();

			if(rs.next()) {
				num1=rs.getInt("num");
				num2=num1-num;
				if(num2>0)
				{
					//修改购物车表格
					pstmt=conn.prepareStatement(sql1);
					pstmt.setInt(1,num2);
					pstmt.setString(2,orderinfo.getProductid());
					pstmt.setString(3,orderinfo.getCustomerid());
					pstmt.executeUpdate();

					System.out.println("已经在购物车修改商品："+orderinfo.getPname());
				}
				else{
					//删除相应信息
					pstmt=conn.prepareStatement(sql2);
					pstmt.setString(1,orderinfo.getProductid());
					pstmt.setString(2,orderinfo.getCustomerid());
					pstmt.executeUpdate();
					System.out.println("已经在购物车删除商品："+orderinfo.getPname());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		finally {
			DBConnection.closeConn(conn);
		}

	}

	@Override
	public PageModel<Orderinfo> listOrderinfo(int currentPage, String shopid) {
		System.out.println("dao:"+shopid);

		int rowsPerPage = 3;

		int endRow = currentPage * rowsPerPage;
		int startRow = (currentPage - 1) * rowsPerPage + 1;
		int totalRows = getTotalRows();
		int totalPage = 0;
		if (totalRows % rowsPerPage == 0) {
			totalPage = totalRows / rowsPerPage;
		} else {
			totalPage = totalRows / rowsPerPage + 1;
		}

		String sql = "SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM orderinfo "
				+ ") t WHERE rownum<=? "
				+") WHERE rn>=?";

		String sql1 = "SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM orderinfo " +
				" ) t WHERE rownum<=? and t.productid in (select productid from product " +
				"            where shopid=?)" +
				") WHERE rn>=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		conn=DBConnection.getConn();
		/*System.out.println(endRow);
		System.out.println(startRow);*/
		List<Orderinfo> objList = new ArrayList<Orderinfo>();

		try {

			if(shopid ==""||shopid==null)
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, endRow);
				// pstmt.setString(2, name);
				pstmt.setInt(2, startRow);

				rs = pstmt.executeQuery();
				while (rs.next()) {// 判断游标是否能够向下移动

					Orderinfo t = persistentClass.newInstance();
					t.setPrice(rs.getFloat("price"));
					t.setNum(rs.getInt("num"));
					t.setOrderdate(rs.getTimestamp("orderdate"));

					t.setCustomerid(rs.getString("customerid"));
					t.setBphone(rs.getString("nphone"));
					t.setBname(rs.getString("bname"));
					t.setBaddr(rs.getString("baddr"));
					t.setPinfo(rs.getString("pinfo"));
					t.setPname(rs.getString("pname"));
					t.setStatus(rs.getString("status"));
					t.setExpress(rs.getString("express"));
					t.setPic(rs.getString("pic"));
					t.setProductid(rs.getString("productid"));
					t.setOrderid(rs.getString("orderid"));

					objList.add(t);
				}
			}
			else{
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, endRow);
				// pstmt.setString(2, name);
				pstmt.setString(2, shopid);
				pstmt.setInt(3, startRow);
				rs = pstmt.executeQuery();
				while (rs.next()) {// 判断游标是否能够向下移动

					Orderinfo t = persistentClass.newInstance();
					t.setPrice(rs.getFloat("price"));
					t.setNum(rs.getInt("num"));
					t.setOrderdate(rs.getTimestamp("orderdate"));

					t.setCustomerid(rs.getString("customerid"));
					t.setBphone(rs.getString("nphone"));
					t.setBname(rs.getString("bname"));
					t.setBaddr(rs.getString("baddr"));
					t.setPinfo(rs.getString("pinfo"));
					t.setPname(rs.getString("pname"));
					t.setStatus(rs.getString("status"));
					t.setExpress(rs.getString("express"));
					t.setPic(rs.getString("pic"));
					t.setProductid(rs.getString("productid"));
					t.setOrderid(rs.getString("orderid"));
					objList.add(t);
				}
			}

			PageModel<Orderinfo> pm=new PageModel<Orderinfo>();
			pm.setList(objList);//数据
			pm.setTotalPage(totalPage);
			return (PageModel<Orderinfo>) pm;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} /*catch (InvocationTargetException e) {
			e.printStackTrace();
		}*/
		// rs.getString(列名)
		// rs.getInt(列名)
		/*
		 * 1、动态创建对象 2、动态调用方法
		 */
		return null;
	}

	@Override
	public void update1(Orders orders) {

	}

	@Override
	public void updateOrderinfo(String orderid) {
		System.out.println(orderid);
		String sql="update orderinfo set status='已发货' where orderid=? ";
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);

			pstmt.setString(1, orderid);

			pstmt.executeUpdate();
			System.out.println("订单"+orderid+"已发货");
		} catch (SQLException e) {
			e.printStackTrace();
		}
			DBConnection.closeConn(conn);

	}

	@Override
	public void deleteOrderinfo(String orderid) {
		System.out.println(orderid);
		String sql="update orderinfo set status='已发货，不在显示' where orderid=? ";
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);

			pstmt.setString(1, orderid);

			pstmt.executeUpdate();
			System.out.println("订单"+orderid+"已删除");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConn(conn);

	}

}
