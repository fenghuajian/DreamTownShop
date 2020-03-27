package cn.guet.test;

import cn.guet.dao.ICustomerDao;
import cn.guet.dao.IUserDao;
import cn.guet.domain.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class testCustomerDao {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private ICustomerDao accountDao;

    @Before
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(ICustomerDao.class);
    }

    @After
    public  void destroy()throws  Exception{
        session.commit();
        session.close();
        in.close();
    }
    /**
     * 测试查询
     */
    @Test
    public void find(){
        //1、根据id查询
       /* Customer customer=accountDao.getById("41AE0");
        System.out.println(customer);*/
       //2、根据姓名查询用户是否存在
        /*int c=accountDao.isExist("fhj1");
        System.out.println(c);*/
        //3、验证用户并返回用户id
        /*String id=accountDao.verify("fhj","fhj12345");
        System.out.println(id);*/
    }

    /**
     * 测试添加
     */
    @Test
    public void insert(){
        //1、添加商店信息
       // accountDao.addShop("1","1","1");
        //2、保存用户信息
        Customer customer=new Customer();
        customer.setCustomerid("5555");
        accountDao.save(customer);
        //3、保存User信息
        //accountDao.saveUser("11","1","1");
    }
    /**
     * 测试删除
     */
    @Test
    public void delete(){
        accountDao.delete("5555");
    }
    /**
     * 测试修改
     */
    @Test
    public  void uodate(){
        /*Customer customer=new Customer();
        customer.setCustomerid("5555");
        customer.setDefaultaddr("5555");
        accountDao.updateCustomer(customer);*/
        /**
         * 测试修改角色
         */
       // accountDao.updaterole("1234","d26a3b7228d14a518df62e30f9fb2df1");
    }

}
