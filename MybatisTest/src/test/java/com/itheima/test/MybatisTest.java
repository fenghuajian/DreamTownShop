package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.Myuser;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * mybatis的入门案例
 */
public class MybatisTest {

    /**
     * 入门案例
     * @param args
     */
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }
    @Test
    public void testFindByCondition(){
        Myuser u = new Myuser();
       // u.setUsername("fhj");
        u.setSex("m");
//        u.setUserSex("女");

        //5.执行查询所有方法
        List< Myuser> users = userDao.findUserByCondition(u);
        for( Myuser user : users){
            System.out.println(user);
        }

    }

    @Test
    public void testFindAll() {

        List<Myuser> users = userDao.findAll();
        for(Myuser user : users){
            System.out.println(user);
        }

    }
    /**
     * 测试foreach标签的使用
     */
    @Test
    public void testFindInIds(){
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        //list.add(3);
        //list.add(46);
        vo.setIds(list);
        //5.执行查询所有方法
        List<Myuser> users = userDao.findUserInIds(vo);
        for(Myuser user : users){
            System.out.println(user);
        }

    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave(){
        Myuser user = new Myuser();
        user.setAddress("广州");
        user.setId(3);
        user.setSex("w");
        user.setUsername("qqqx");


        System.out.println("保存操作之前："+user);
        //5.执行保存方法
        userDao.saveUser(user);

        System.out.println("保存操作之后："+user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate(){
        Myuser user = new Myuser();
        user.setAddress("广州");
        user.setId(3);
        user.setSex("w");
        user.setUsername("qqx");

        //5.执行保存方法
        userDao.updateUser(user);
    }
    /**
     * 测试删除操作
     */
    @Test
    public void testDelete(){
        //5.执行删除方法
        userDao.deleteUser(3);
    }
    /*根据id查询*/
    @Test
    public void testFindOne(){
        //5.执行查询一个方法
        Myuser  user = userDao.findById(3);
        System.out.println(user);
    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName(){
        //5.执行查询一个方法
      //  List<Myuser> users = userDao.findByName("%q%");
      List<Myuser> users = userDao.findByName("q");
        for(Myuser user : users){
            System.out.println(user);
        }
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal(){
        //5.执行查询一个方法
        int count = userDao.findTotal();
        System.out.println(count);
    }
    /**
     * 测试使用QueryVo作为查询条件
     */
    /*@Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        Myuser user = new Myuser();
        user.setUsername("%q%");
        vo.setUser(user);
        //5.执行查询一个方法
        List<Myuser> users = userDao.findUserByVo(vo);
        for(Myuser u : users){
            System.out.println(u);
        }
    }*/

}
