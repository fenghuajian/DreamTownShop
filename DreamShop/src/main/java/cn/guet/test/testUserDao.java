package cn.guet.test;

import cn.guet.dao.IUserDao;
import cn.guet.domain.Permission;
import cn.guet.domain.Shop;
import cn.guet.domain.Users;
import cn.guet.util.PageModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class testUserDao {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao accountDao;

    @Before
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IUserDao.class);
    }

    @After
    public  void destroy()throws  Exception{
        session.commit();
        session.close();
        in.close();
    }
    /*
    * 测试登录
    * */
    @Test
    public  void  login(){
      Users accounts = accountDao.login("fhj","fhj1234");
        System.out.println(accounts);
    }
    /*测试授权
    * d26a3b7228d14a518df62e30f9fb2df1
    * */
    @Test
    public  void  grant(){
         accountDao.saveGrant("1","d26a3b7228d14a518df62e30f9fb2df1");
        //System.out.println(accounts);
    }
        /*
        * 测试拿到权限
        *   Set<Permission> getPermission
        * */

    @Test
    public  void getPermission() {
        Set<Permission> permission = accountDao.getPermission("fhj");

        for (Permission str : permission) {

            System.out.println(str);

        }
    }

    /**
     * 测试分页
     */
    @Test
    public  void findPage(){
        List<Users> list=accountDao.findPage(4,6);
        for(Users account : list){
            System.out.println(account);
        }
        PageModel<Users> pm=new PageModel<Users>();
        pm.setCurrentPage(2);
        pm.setTotalPage(2);
        pm.setList(list);
        System.out.println(pm);
    }

    /**
     * 测试删除，拿到shop已经拿到表行数
     */
    @Test
    public void delete(){
        //accountDao.deleteUser("1");
       /* Shop s=accountDao.getshop("9AE95");
        System.out.println(s);*/
       int count=accountDao.getCount();
        System.out.println(count);
    }

}
