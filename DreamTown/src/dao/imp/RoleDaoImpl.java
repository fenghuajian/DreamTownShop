package dao.imp;

import bean.Roles;
import dao.IRoleDao;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleDaoImpl extends BaseDaoImpl<Roles> implements IRoleDao {

    @Override
    public void saveGrant(String roleId, String[] permissionIds) {
        try {
            Connection conn=null;
            PreparedStatement pstmt=null;
            String sql="DELETE FROM rolespermission WHERE rolesid=?";
            conn= DBConnection.getConn();
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, roleId);
            pstmt.executeUpdate();

            sql="INSERT INTO rolespermission VALUES(?,?)";
            pstmt=conn.prepareStatement(sql);
            for(int i=0;i<permissionIds.length;i++){
                pstmt.setString(1, roleId);
                pstmt.setString(2, permissionIds[i]);
                pstmt.executeUpdate();
            }

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
}