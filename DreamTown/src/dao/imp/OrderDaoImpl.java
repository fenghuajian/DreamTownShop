package dao.imp;

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

}
