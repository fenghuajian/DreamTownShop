package dao.imp;

import bean.Orderinfo;
import bean.Orders;
import dao.IOrderDao;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConn(conn);
	}

}
