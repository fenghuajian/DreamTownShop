package dao.imp;

import domain.Roles;
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

    @Override
    public int deleteRole(String roleid) {
        String sql="delete roles  where rolesid=?";
        String sql1="delete rolespermission  where rolesid=?";
        Connection conn=null;
        PreparedStatement pstmt=null;
        conn= DBConnection.getConn();
        int flag=0;
        if(roleid !="")
        {
            try {

                pstmt=conn.prepareStatement(sql1);
                pstmt.setString(1, roleid);
                pstmt.executeUpdate();

                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1, roleid);
                pstmt.executeUpdate();

                System.out.println(roleid+"已经删除");

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

    @Override
    public int updateRoleName(String cname, String uname) {
        String sql="update roles set rolename=? where rolename=?";
        Connection conn=null;
        PreparedStatement pstmt=null;
        conn= DBConnection.getConn();
        int flag=0;
        if(uname !="" && cname!="")
        {
            try {
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1, uname);
                pstmt.setString(2, cname);

                pstmt.executeUpdate();
                System.out.println(cname+"已经改成"+uname);

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