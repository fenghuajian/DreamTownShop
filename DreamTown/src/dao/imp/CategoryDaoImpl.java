package dao.imp;

import domain.Category;
import dao.ICategoryDao;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryDaoImpl extends BaseDaoImpl<Category> implements ICategoryDao {


    @Override
    public void saveCategory(Category category) {

    }

    @Override
    public int updateCategory(String cname, String uname) {

        String sql="update category set name=? where name=?";
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