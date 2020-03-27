package cn.guet.test;

import cn.guet.dao.IProductDao;
import cn.guet.domain.Orderinfo;
import cn.guet.domain.Permission;
import cn.guet.domain.Product;
import cn.guet.domain.carinfo;
import cn.guet.util.PageModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class testProductDao {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IProductDao accountDao;

    @Before
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IProductDao.class);
    }

    @After
    public  void destroy()throws  Exception{
        session.commit();
        session.close();
        in.close();
    }
    //测试商家分页
    @Test
    public void find(){
        String name="店";
      // name="'%"+name+"%'";
        System.out.println(name);
        //int num=accountDao.getNumByName(name);
       int num=accountDao.getNum();
        System.out.println(num);
       // List<Product> products=accountDao.getAllByName("店");
        List<Product> products=accountDao.pageByName(0,5,"店");
      //  System.out.println(products);
        PageModel<Product> pm=new PageModel<Product>();
        pm.setList(products);
        pm.setTotalPage(6);
        pm.setCurrentPage(1);
        System.out.println(pm);
    }
    //测试管理员商品分页
    @Test
    public void find1(){

        int num=accountDao.getNum();
        System.out.println(num);
        //List<Product> list=accountDao.getAll();
        List<Product> list=accountDao.findPage(0,5);
      //  System.out.println(list);
        PageModel<Product> pm=new PageModel<Product>();
        pm.setList(list);
        pm.setTotalPage(6);
        pm.setCurrentPage(1);
        System.out.println(pm);

    }

    //测试其他查询
    @Test
    public void find2(){

        /*List<carinfo> list=accountDao.getOrder("3EEF0");
        System.out.println(list);*/
        //List<Product> list=accountDao.getOther("7fa23e04421b44a9bcb2ba88e57c0e9b");
        Product p=accountDao.SelectProductById("4ba74529a75042c28ed7cfd577d778f7");
        System.out.println(p);
        //System.out.println(list);
    }
    @Test
    public void find3(){
        int num=accountDao.getCartNum("1111","1111");
        System.out.println(num);
    }

    @Test
    public void save(){
        Product p1=new Product();
        p1.setProductId("111");
        p1.setPrice(1234f);
        accountDao.save(p1);
    }
    @Test
    public void update(){
        Product p1=new Product();
        p1.setProductId("111");
        p1.setPrice(12345f);
        accountDao.update(p1);
    }
    @Test
    public void update2(){
        accountDao.updateCarNum("1111","1111");
    }



}
