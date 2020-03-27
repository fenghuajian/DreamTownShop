package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.Myuser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;
    @Before     /*测试类运行前执行*/
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }
    @After      /*测试类运行后执行*/
    public  void destroy()throws  Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test   /*根据id查询*/
    public void testFindOne(){
        Myuser  user = userDao.findById(2);
        System.out.println(user);
        Myuser  user1 = userDao.findById(2);
        System.out.println(user1);
        System.out.println(user==user1);
       // System.out.println(user.getAccounts());
    }
    @Test   /*根据名字查询*/
    public  void testFindByName(){
//        List<User> users = userDao.findUserByName("%mybatis%");
        List< Myuser > users = userDao.findUserByName("zlb");
        for( Myuser  user : users){
            System.out.println(user);
            System.out.println(user.getAccounts());
        }

    }
    @Test
    public  void testFindByRid() {
//        List<User> users = userDao.findUserByName("%mybatis%");
        List<Myuser> users = userDao.findByRid(1);
        for (Myuser user : users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
            System.out.println(user.getRoles());
        }

    }
    @Test
    public void test(){
        Myuser myuser=userDao.findBynw(2,"zlb");
        System.out.println(myuser);
        System.out.println(myuser.getRoles());
    }


   /* @Test       *//*查询总记录*//*
    public  void testFindTotal(){
        int total = userDao.findTotalUser();
        System.out.println(total);
    }*/
  /*  @Test       *//*保存用户*//*
    public void testSave(){
        Myuser user = new  Myuser ();
        user.setUsername("fhj");
        user.setAddress("北京市昌平区");
        user.setSex("m");
        userDao.saveUser(user);
    }
    @Test   *//*更新用户*//*
    public void testUpdate(){
        Myuser  user = new  Myuser ();
        user.setId(21);
        user.setUsername("qqq");
        user.setAddress("北京市海淀区");
        user.setSex("男");
        userDao.updateUser(user);
    }
    @Test   *//*删除用户*//*
    public void testDelete(){
        userDao.deleteUser(21);
    }
*/
}
