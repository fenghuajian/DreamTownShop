package dao.imp;


import domain.Collection;
import domain.Product;
import domain.carinfo;
import dao.IProductDao;
import util.DBConnection;
import util.PageModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements IProductDao {

    private Class<Product> persistentClass;

    public ProductDaoImpl() {
        conn = DBConnection.getConn();
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        persistentClass = (Class<Product>) type.getActualTypeArguments()[0];
    }
    @Override
    public void addCar(String productId, String customerId) {
        // TODO Auto-generated method stub
        int flag=0;

        String sql1="select num from cart where productid=? and customerid=?";
        String sql="INSERT INTO cart VALUES(?,?,?)";
        String sql2="update cart set num=num+1 where productid=? and customerid=?";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DBConnection.getConn();
            pstmt=conn.prepareStatement(sql1);
            pstmt.setString(1,productId);
            pstmt.setString(2,customerId);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
              //  num=rs.getInt(num)+1;
                System.out.println("已经加入过购物车！");
                pstmt=conn.prepareStatement(sql2);
                pstmt.setString(1,productId);
                pstmt.setString(2,customerId);

                pstmt.executeUpdate();
            }
            else
            {

                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,productId);
                pstmt.setString(2,customerId);
                pstmt.setInt(3,1);
                pstmt.executeUpdate();
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {

            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<carinfo> getOrder(String customerId) {

        String sql="select p.*，c.num from product p,cart c where p.productid=c.productid and c.customerid=?";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        conn=DBConnection.getConn();
        ArrayList<carinfo> carinfos=new ArrayList<carinfo>();
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, customerId);//方法链
            rs=pstmt.executeQuery();
            while(rs.next()){
                Product product=new Product();
                carinfo carinfo=new carinfo();
                carinfo.setDescInfo(rs.getString("DESCINFO"));
                carinfo.setName(rs.getString("name"));
                carinfo.setPicURL(rs.getString("picurl"));
                carinfo.setPrice(rs.getFloat("price"));
                carinfo.setProductId(rs.getString("productid"));
                carinfo.setNum(rs.getInt("num"));


               /* product.setProductId(rs.getString("PRODUCTID"));
                product.setName(rs.getString("NAME"));
                product.setPrice(rs.getFloat("PRICE"));
                product.setDescInfo(rs.getString("DESCINFO"));
                product.setPicURL(rs.getString("PICURL"));*/
               carinfos.add(carinfo);
               // products.add(product);
            }
            return carinfos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnection.closeConn(conn);
        return null;
    }

    /**
     * 模糊查询商品显示
     * @param currentPage
     * @param name
     * @return
     */
    @Override
    public PageModel<Product> getProduct1(int currentPage, String name) {
        //获取总条数
        int totalRows =0;
        String sql="  SELECT count(*) FROM (SELECT p.*,c.name cname FROM \n" +
                "  product p left join category c on p.categoryid=c.categoryid\n" +
                "   ) t WHERE  isremai='false' AND  (name like \n" +
                "   '%"+name+"%' or shopname like '%"+name+"%' or cname like '%"+name+"%')";

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
             totalRows = rs.getInt(1);// 获取第一列
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int rowsPerPage = 3;
        int endRow = currentPage * rowsPerPage;
        int startRow = (currentPage - 1) * rowsPerPage + 1;

        int totalPage = 0;
        if (totalRows % rowsPerPage == 0) {
            totalPage = totalRows / rowsPerPage;
        } else {
            totalPage = totalRows / rowsPerPage + 1;
        }

    //获取所有数据
        String sql1="SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT p.*,c.name cname FROM \n" +
                "   product p left join category c on p.categoryid=c.categoryid\n" +
                "    ) t WHERE rownum<=? and isremai='false' AND  (name like \n" +
                "    '%"+name+"%' or shopname like '%"+name+"%' or cname like '%"+name+"%')\n" +
                "       ) WHERE rn>=?";

        List<Product> objList = new ArrayList<Product>();
        try {
            pstmt = conn.prepareStatement(sql1);
            pstmt.setInt(1, endRow);
            // pstmt.setString(2, name);
            pstmt.setInt(2, startRow);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {// 判断游标是否能够向下移动
                Product t = persistentClass.newInstance();
                List<Method> list = this.matchPojoMethods(t, "set");
                Iterator<Method> iter = list.iterator();
                while (iter.hasNext()) {
                    Method method = iter.next();
                    String type = method.getParameterTypes()[0].getName();
                    if (type.indexOf("String") != -1) {
                        method.invoke(t, rs.getString(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("int") != -1) {
                        method.invoke(t, rs.getInt(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("float") != -1) {
                        method.invoke(t, rs.getFloat(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("Float") != -1) {
                        method.invoke(t, rs.getFloat(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("Date")!=-1){
                        method.invoke(t, rs.getDate(method.getName().substring(3).toLowerCase()));
                    }
                }
                objList.add(t);
            }
            PageModel<Product> pm=new PageModel<Product>();
            pm.setList(objList);//数据
            pm.setTotalPage(totalPage);
            return (PageModel<Product>) pm;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // rs.getString(列名)
        // rs.getInt(列名)
        /*
         * 1、动态创建对象 2、动态调用方法
         */
        return null;
    }

    /**
     * 拿到热卖商品
     * @param currentPage
     * @param name
     * @return
     */
    @Override
    public PageModel<Product> getProduct2(int currentPage, String name) {
        //获取总条数
        int totalRows =0;
        String sql="  SELECT count(*) FROM (SELECT p.*,c.name cname FROM \n" +
                "  product p left join category c on p.categoryid=c.categoryid\n" +
                "   ) t WHERE  isremai='true' AND  (name like \n" +
                "   '%"+name+"%' or shopname like '%"+name+"%' or cname like '%"+name+"%')";
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            totalRows = rs.getInt(1);// 获取第一列
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int rowsPerPage = 3;
        //只拿两个数据
        int endRow = 2;
        int startRow = 0;

        int totalPage = 0;
        if (totalRows % rowsPerPage == 0) {
            totalPage = totalRows / rowsPerPage;
        } else {
            totalPage = totalRows / rowsPerPage + 1;
        }

        //获取所有数据
        String sql1="SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT p.*,c.name cname FROM \n" +
                "   product p left join category c on p.categoryid=c.categoryid\n" +
                "    ) t WHERE rownum<=? and isremai='true' AND  (name like \n" +
                "    '%"+name+"%' or shopname like '%"+name+"%' or cname like '%"+name+"%')\n" +
                "       ) WHERE rn>=?";

        List<Product> objList = new ArrayList<Product>();
        try {
            pstmt = conn.prepareStatement(sql1);
            pstmt.setInt(1, endRow);
            // pstmt.setString(2, name);
            pstmt.setInt(2, startRow);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {// 判断游标是否能够向下移动
                Product t = persistentClass.newInstance();
                List<Method> list = this.matchPojoMethods(t, "set");
                Iterator<Method> iter = list.iterator();
                while (iter.hasNext()) {
                    Method method = iter.next();
                    String type = method.getParameterTypes()[0].getName();
                    if (type.indexOf("String") != -1) {
                        method.invoke(t, rs.getString(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("int") != -1) {
                        method.invoke(t, rs.getInt(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("float") != -1) {
                        method.invoke(t, rs.getFloat(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("Float") != -1) {
                        method.invoke(t, rs.getFloat(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("Date")!=-1){
                        method.invoke(t, rs.getDate(method.getName().substring(3).toLowerCase()));
                    }
                }
                objList.add(t);
            }
            PageModel<Product> pm=new PageModel<Product>();
            pm.setList(objList);//数据
            pm.setTotalPage(totalPage);
            return (PageModel<Product>) pm;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加入收藏
     * @param productId
     * @param customerId
     * @return
     */
    @Override
    public int addCollection(String productId, String customerId) {

        int flag=1;

        String sql1="select * from collection where productid=? and customerid=?";
        String sql="INSERT INTO collection(productid,customerid) VALUES(?,?)";
        //String sql2="update cart set num=num+1 where productid=? and customerid=?";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            //Class.forName("oracle.jdbc.OracleDriver");
            conn=DBConnection.getConn();
            pstmt=conn.prepareStatement(sql1);
            pstmt.setString(1,productId);
            pstmt.setString(2,customerId);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                flag=0;
            }
            else
            {
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,productId);
                pstmt.setString(2,customerId);
                pstmt.executeUpdate();
            }

       } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {

            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
       return  flag;
    }

    @Override
    public List<Product> getProductRemai(String name) {


        //获取所有数据
        String sql1="SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT p.*,c.name cname FROM \n" +
                "   product p left join category c on p.categoryid=c.categoryid\n" +
                "    ) t WHERE rownum<=2 and isremai='true' AND  (name like \n" +
                "    '%"+name+"%' or shopname like '%"+name+"%' or cname like '%"+name+"%')\n" +
                "       ) WHERE rn>=0";

        List<Product> objList = new ArrayList<Product>();
        try {
            pstmt = conn.prepareStatement(sql1);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {// 判断游标是否能够向下移动
                Product t = persistentClass.newInstance();
                List<Method> list = this.matchPojoMethods(t, "set");
                Iterator<Method> iter = list.iterator();
                while (iter.hasNext()) {
                    Method method = iter.next();
                    String type = method.getParameterTypes()[0].getName();
                    if (type.indexOf("String") != -1) {
                        method.invoke(t, rs.getString(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("int") != -1) {
                        method.invoke(t, rs.getInt(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("float") != -1) {
                        method.invoke(t, rs.getFloat(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("Float") != -1) {
                        method.invoke(t, rs.getFloat(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("Date")!=-1){
                        method.invoke(t, rs.getDate(method.getName().substring(3).toLowerCase()));
                    }
                }
                objList.add(t);
            }

            return objList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageModel<Product> viewCollection(int currentPage, String customerid) {
        //获取总条数
        int totalRows =0;
        String sql="  SELECT count(*) FROM (select * from product\n" +
                "  where productid in(select productid from collection\n" +
                "  where customerid=?))";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customerid);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            totalRows = rs.getInt(1);// 获取第一列
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String tableName = persistentClass.getSimpleName().toLowerCase();
        int rowsPerPage = 3;
        int endRow = currentPage * rowsPerPage;
        int startRow = (currentPage - 1) * rowsPerPage + 1;

        int totalPage = 0;

        if (totalRows % rowsPerPage == 0) {
            totalPage = totalRows / rowsPerPage;
        } else {
            totalPage = totalRows / rowsPerPage + 1;
        }

        String sql1 = "  SELECT * FROM (SELECT rownum rn,t.* FROM ( select * from product\n" +
                "    where productid in(select productid from collection\n" +
                "    \twhere customerid=?)) t WHERE rownum<=? ) WHERE rn>=?";

        List<Product> objList = new ArrayList<Product>();
        try {
            pstmt = conn.prepareStatement(sql1);
            pstmt.setInt(2, endRow);
            pstmt.setString(1, customerid);
            pstmt.setInt(3, startRow);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {// 判断游标是否能够向下移动
               Product t = new Product();
               t.setProductId(rs.getString("productid"));
               t.setPicURL(rs.getString("picurl"));
               t.setDescInfo(rs.getString("descinfo"));
               t.setShopname(rs.getString("shopname"));
               t.setName(rs.getString("name"));
               t.setPrice(rs.getFloat("price"));
                objList.add(t);
            }
            PageModel<Product> pm=new PageModel<Product>();
            pm.setList(objList);//数据
            pm.setTotalPage(totalPage);
            return (PageModel<Product>) pm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCollection(String productId, String customerId) {

        String sql="delete from collection where productid=? and customerid=?";
        //String sql2="update cart set num=num+1 where productid=? and customerid=?";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            //Class.forName("oracle.jdbc.OracleDriver");
            conn=DBConnection.getConn();
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,productId);
                pstmt.setString(2,customerId);
                pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {

            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public PageModel<Product> getProduct(int currentPage, String categoryId) {
        String tableName = persistentClass.getSimpleName().toLowerCase();

        int rowsPerPage = 6;

        int endRow = currentPage * rowsPerPage;
        int startRow = (currentPage - 1) * rowsPerPage + 1;
        int totalRows = getTotalRows();
        int totalPage = 0;
        if (totalRows % rowsPerPage == 0) {
            totalPage = totalRows / rowsPerPage;
        } else {
            totalPage = totalRows / rowsPerPage + 1;
        }

        String sql = "SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM " + tableName
                + ") t WHERE rownum<=? AND  categoryId=?) WHERE rn>=?";

        List<Product> objList = new ArrayList<Product>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, endRow);
            pstmt.setString(2, categoryId);
            pstmt.setInt(3, startRow);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {// 判断游标是否能够向下移动
                Product t = persistentClass.newInstance();
                List<Method> list = this.matchPojoMethods(t, "set");
                Iterator<Method> iter = list.iterator();
                while (iter.hasNext()) {
                    Method method = iter.next();
                    String type = method.getParameterTypes()[0].getName();
                    if (type.indexOf("String") != -1) {
                        method.invoke(t, rs.getString(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("int") != -1) {
                        method.invoke(t, rs.getInt(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("float") != -1) {
                        method.invoke(t, rs.getFloat(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("Float") != -1) {
                        method.invoke(t, rs.getFloat(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("Date")!=-1){
                        method.invoke(t, rs.getDate(method.getName().substring(3).toLowerCase()));
                    }
                }
                objList.add(t);
            }
            PageModel<Product> pm=new PageModel<Product>();
            pm.setList(objList);//数据
            pm.setTotalPage(totalPage);
            return (PageModel<Product>) pm;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // rs.getString(列名)
        // rs.getInt(列名)
        /*
         * 1、动态创建对象 2、动态调用方法
         */
        return null;
    }

    /**
     * 可能还喜欢
     * @param productid
     * @return
     */
    @Override
    public List<Product> getOther(String productid) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="SELECT categoryid FROM product WHERE productid=?";
        conn=DBConnection.getConn();
        List<Product> prolist=new ArrayList<Product>();

        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,productid);
            rs=pstmt.executeQuery();
            String categoryid=null;
            if(rs.next()){
                categoryid=rs.getString("categoryid");//拿到roleId
            }
            System.out.println(categoryid);
            sql="SELECT * FROM product WHERE categoryid=?";

            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, categoryid);//方法链
            rs=pstmt.executeQuery();
            while(rs.next()){

                Product t = persistentClass.newInstance();
                List<Method> list = this.matchPojoMethods(t, "set");
                Iterator<Method> iter = list.iterator();
                while (iter.hasNext()) {
                    Method method = iter.next();
                    String type = method.getParameterTypes()[0].getName();
                    if (type.indexOf("String") != -1) {
                        method.invoke(t, rs.getString(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("int") != -1) {
                        method.invoke(t, rs.getInt(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("Float") != -1) {
                        method.invoke(t, rs.getFloat(method.getName().substring(3).toLowerCase()));
                    } else if (type.indexOf("Date") != -1) {
                        method.invoke(t, rs.getDate(method.getName().substring(3).toLowerCase()));
                    }
                }
                prolist.add(t);

            }
            return prolist;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        DBConnection.closeConn(conn);
        return null;
    }



    public List<Method> matchPojoMethods(Product entity, String methodName) {
        // 获得当前Pojo所有方法对象
        Method[] methods = entity.getClass().getDeclaredMethods();

        // List容器存放所有带get字符串的Method对象
        List<Method> list = new ArrayList<Method>();

        // 过滤当前Pojo类所有带get字符串的Method对象,存入List容器
        for (int index = 0; index < methods.length; index++) {
            if (methods[index].getName().indexOf(methodName) != -1) {
                list.add(methods[index]);
            }
        }
        return list;
    }
}