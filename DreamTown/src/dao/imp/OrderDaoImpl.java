package dao.imp;

import bean.Orderinfo;
import bean.Orders;
import dao.IOrderDao;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl extends BaseDaoImpl<Orders> implements IOrderDao {

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
		DBConnection.closeConn(conn);
	}

}
