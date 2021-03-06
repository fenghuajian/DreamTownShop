package dao.imp;

import domain.Customer;
import dao.ICustomerDao;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements ICustomerDao {

	@Override
	//判断用户是否存在
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
	//修改用户信息
	public void updateCustomer(Customer customer) {
		String sql="update customer set username=?,defaultname=?,password=?,phone=?,defaultphone=?,defaultaddr=?,mailbox=? where customerid=?";
		String sql1="update users set username=?,password=? where usersid=?";
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
			//System.out.println("已经修改用户信息！");


			pstmt=	pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, customer.getUsername());
			pstmt.setString(3, customer.getCustomerId());
			pstmt.setString(2, customer.getPassword());
			pstmt.executeUpdate();
			System.out.println("已经修改用户信息！");

		} catch (SQLException e) {
			e.printStackTrace();

		}
		finally {

			DBConnection.closeConn(conn);
		}



	}

	@Override
	public void addShop(String customerid, String shopid, String shopname) {
		String sql="insert into shop values (?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, customerid);
			pstmt.setString(2, shopid);
			pstmt.setString(3, shopname);

			pstmt.executeUpdate();
			System.out.println("已经添加商店："+shopname+"!");

		} catch (SQLException e) {
			e.printStackTrace();

		}
		finally {

			DBConnection.closeConn(conn);
		}



	}

	@Override
	public void updaterole(String userid, String roleid) {
		System.out.println("userid:"+userid);
		System.out.println("roleid:"+roleid);
		String sql="update users set rolesid=? where usersid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, roleid);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
			System.out.println(userid+"已经成为商家!");

		} catch (SQLException e) {
			e.printStackTrace();

		}
		finally {

			DBConnection.closeConn(conn);
		}



	}

	@Override
	public void saveUser(String userid, String username, String password) {
		String sql="insert into users(usersid,username,password) values(?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
			System.out.println(username+"已经成为users!");

		} catch (SQLException e) {
			e.printStackTrace();

		}
		finally {

			DBConnection.closeConn(conn);
		}
	}

}
