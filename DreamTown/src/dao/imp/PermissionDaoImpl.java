package dao.imp;

import domain.Permission;
import dao.IPermissionDao;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements IPermissionDao {

	@Override
	public List<Permission> getPermissionByRoleId(String roleId) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT p.* FROM permission p,rolespermission rp WHERE rp.permissionid=p.permissionid AND rp.rolesid=?";
		conn=DBConnection.getConn();
		List<Permission> permissions=new ArrayList<Permission>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, roleId);//������
			rs=pstmt.executeQuery();
			while(rs.next()){
				/**
				 * ÿѭ��һ�ξ���һ��Ȩ�ޣ�����Ҫnewһ��Ȩ�޶��󣬲���һһ���Ȩ�޵����Խ��и�ֵ����
				 */
				Permission permission=new Permission();
				permission.setPermissionid(rs.getString("PERMISSIONID"));
				permission.setPid(rs.getString("PID"));
				permission.setName(rs.getString("NAME"));
				permission.setUrl(rs.getString("URL"));
				permission.setIcon(rs.getString("ICON"));
				permission.setIsParent(rs.getString("ISPARENT"));
				permission.setTarget(rs.getString("TARGET"));
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
	public void savePermission(Permission p) {
		String sql="insert into permission(permissionid,pid,name,iconskin,url,isparent,target) values(?,?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, p.getPermissionid());
			pstmt.setString(2, p.getPid());
			pstmt.setString(3, p.getName());
			pstmt.setString(4, p.getIconSkin());
			pstmt.setString(5, p.getUrl());
			pstmt.setString(6, p.getIsParent());
			pstmt.setString(7, "center");

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConn(conn);
	}

	@Override
	public void updatePermission(Permission p) {
		String sql="update permission set pid=?,name=?,iconskin=?,url=?,isparent=? where permissionid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn=DBConnection.getConn();
		try {
			pstmt=conn.prepareStatement(sql);

			pstmt.setString(1, p.getPid());
			pstmt.setString(2, p.getName());
			pstmt.setString(3, p.getIconSkin());
			pstmt.setString(4, p.getUrl());
			pstmt.setString(5, p.getIsParent());
			pstmt.setString(6, p.getPermissionid());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConn(conn);
	}
}