package dao.imp;

import bean.Customer;
import dao.ICustomerDao;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements ICustomerDao {

	@Override
	public int isExist(String account) {
		String sql="select * from customer where username=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		DBConnection.closeConn(conn);
		return 0;
	}

	@Override
	public String verify(String customerName, String password) {
		String sql="select * from customer where username=? and password=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, customerName);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("customerId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		DBConnection.closeConn(conn);
		return null;
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		String sql="update customer set username=?,defaultname=?,password=?," +
				"phone=?,defaultphone=?,defaultaddr=?,mailbox=? where customerid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;

		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, customer.getUsername());
			pstmt.setString(2, customer.getDefaultName());
			pstmt.setString(3, customer.getPassword());
			pstmt.setString(4, customer.getPhone());
			pstmt.setString(5, customer.getDefaultPhone());
			pstmt.setString(6, customer.getDefaultAddr());
			pstmt.setString(7, customer.getMailBox());
			pstmt.setString(8, customer.getCustomerId());
			pstmt.executeUpdate();
			System.out.println("已经修改用户信息！");

		} catch (SQLException e) {
			e.printStackTrace();

		}
		finally {

			DBConnection.closeConn(conn);
		}



	}

}
