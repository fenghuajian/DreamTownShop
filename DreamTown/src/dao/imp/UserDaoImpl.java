package dao.imp;

import domain.Permission;
import domain.Roles;
import domain.Shop;
import domain.Users;
import dao.IRoleDao;
import dao.IUserDao;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

public class UserDaoImpl extends BaseDaoImpl<Users> implements IUserDao {

	IRoleDao roleDao=null;
	public UserDaoImpl(){
		roleDao=new RoleDaoImpl();
	}
	@Override
	public Users login(String username, String password) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM users WHERE username=? AND password=?";
		conn=DBConnection.getConn();
		Users user=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();
			if(rs.next()){
				user=new Users();
				user.setUsersId(rs.getString("USERSID"));
				user.setUsername(username);
				String roleId=rs.getString("ROLESID");//拿到roleId
				Roles role=roleDao.getById(roleId);
				user.setRole(role);//用户和角色关联
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConn(conn);
		return user;
	}
	@Override
	public Set<Permission> getPermission(String username){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT rolesid FROM users WHERE username=?";
		conn=DBConnection.getConn();
		Set<Permission> permissions=new TreeSet<Permission>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,username);
			rs=pstmt.executeQuery();
			String roleId=null;
			if(rs.next()){
				roleId=rs.getString("ROLESID");//拿到roleId
			}
			sql="SELECT p.* FROM permission p,rolespermission rp WHERE rp.permissionid=p.permissionid AND rp.rolesid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, roleId);//方法链
			rs=pstmt.executeQuery();
			while(rs.next()){
				/**
				 * 每循环一次就是一个权限，就需要new一个权限对象，并且一一针对权限的属性进行赋值操作
				 */
				Permission permission=new Permission();
				permission.setPermissionid(rs.getString("PERMISSIONID"));
				permission.setPid(rs.getString("PID"));
				permission.setName(rs.getString("NAME"));
				permission.setUrl(rs.getString("URL"));
				permission.setIcon(rs.getString("ICON"));
				permission.setIsParent(rs.getString("ISPARENT"));
				permission.setTarget(rs.getString("TARGET"));
				permission.setIconSkin(rs.getString("ICONSKIN"));
				permissions.add(permission);
			}
			return permissions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConn(conn);
		return null;
	}
	@Override
	public void saveGrant(String userId, String roleId) {
		try {
			String sql="UPDATE users SET rolesid=? WHERE usersid=?";
			Connection conn=null;
			PreparedStatement pstmt=null;
			conn=DBConnection.getConn();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,roleId);
			pstmt.setString(2, userId);
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		DBConnection.closeConn(conn);
	}

	@Override
	public Shop getShop(String userId) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM shop WHERE customerid=?";
		conn=DBConnection.getConn();

		Shop shop=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userId);

			rs=pstmt.executeQuery();
			if(rs.next()){
				shop=new Shop();
				shop.setCustomerid(userId);
				shop.setShopid(rs.getString("shopid"));
				shop.setShopname(rs.getString("shopname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConn(conn);
		return shop;
	}

	/**
	 * 拿到rolesid
	 * @param userid
	 * @return
	 */
	@Override
	public String getRoleId(String userid) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String roleid=null;
		String sql="select rolesid from users where usersid=?";
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userid);

			rs=pstmt.executeQuery();
			if(rs.next()){
				roleid=rs.getString("rolesid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConn(conn);
		return roleid;

	}

	@Override
	public void saveCustomer(Users user) {
		try {
			String sql="insert into customer(customerid,username,password) values(?,?,?)";
			String sql1="insert into users(usersid,username,password) values(?,?,?)";
			Connection conn=null;
			PreparedStatement pstmt=null;
			conn=DBConnection.getConn();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,user.getUsersId());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.executeUpdate();

			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1,user.getUsersId());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());

			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		DBConnection.closeConn(conn);
	}

	@Override
	public int deleteCustomer(String userId) {
		int flag=0;
		if(userId !="")
		{
			try {
				String sql="delete from customer where customerid=?";
				String sql1="delete from users where usersid=?";
				Connection conn=null;
				PreparedStatement pstmt=null;
				conn=DBConnection.getConn();
				conn.setAutoCommit(false);
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,userId);
				pstmt.executeUpdate();

				pstmt=conn.prepareStatement(sql1);
				pstmt.setString(1,userId);
				pstmt.executeUpdate();
				conn.commit();
				flag=1;
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			DBConnection.closeConn(conn);
		}

		return flag;
	}

	@Override
	public int SimpleUpdateUser(String uid, String uname, String pwd) {
		String sql="update users set username=?,password=? where usersid=?";
		String sql1="update customer set username=?,password=? where customerid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn= DBConnection.getConn();
		int flag=0;
		if(uid !="")
		{
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, uname);
				pstmt.setString(2, pwd);
				pstmt.setString(3, uid);
				pstmt.executeUpdate();

				pstmt=conn.prepareStatement(sql1);
				pstmt.setString(1, uname);
				pstmt.setString(2, pwd);
				pstmt.setString(3, uid);
				pstmt.executeUpdate();
				System.out.println(uid+"已经改成"+uname);

			} catch (SQLException e) {
				e.printStackTrace();

			}
			finally {

				DBConnection.closeConn(conn);
			}
			flag=1;
		}


		return flag;
	}

}