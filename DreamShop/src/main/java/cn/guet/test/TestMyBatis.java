package cn.guet.test;


import cn.guet.dao.IRoleDao;
import cn.guet.dao.IUserDao;
import cn.guet.domain.Roles;
import cn.guet.domain.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;

//import org.junit.Test;

public class TestMyBatis {

    /**
     * 测试查询
     * @throws Exception
     */
    @Test
    public void findAll_user() throws Exception {
        // 加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqlSession对象
        SqlSession session = factory.openSession();
        // 获取到代理对象
        IUserDao dao = session.getMapper(IUserDao.class);
        // 查询所有数据
        List<Users> list = dao.findAll();
        for(Users account : list){
            System.out.println(account);
        }
        // 关闭资源
        session.close();
        in.close();
    }

    @Test
    public void run2() throws Exception {
        // 加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqlSession对象
        SqlSession session = factory.openSession();
        // 获取到代理对象
        IRoleDao dao = session.getMapper(IRoleDao.class);
        // 查询所有数据
        List<Roles> list = dao.findAll();
        for(Roles account : list){
            System.out.println(account);
        }

        /*
        * Roles{rolesId='8656f2d56d3a49e49481cbf975a01e81', roleName='普通用户', permissions=null}
            Roles{rolesId='969323d6ff9a4acf8aabc8d367474d14', roleName='管理员', permissions=null}
                Roles{rolesId='d26a3b7228d14a518df62e30f9fb2df1', roleName='卖家', permissions=null}

        * */
        // 关闭资源
        session.close();
        in.close();
    }
    /**
     * 测试更新
     * @throws Exception
     */
    @Test
    public void run3() throws Exception {
        Users account = new Users();
        //account.setId(10);
        account.setUsersid("1");
        account.setPassword("1234");
        account.setUsername("test1");
        account.setRolesid("8656f2d56d3a49e49481cbf975a01e81");

        // 加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqlSession对象
        SqlSession session = factory.openSession();
        // 获取到代理对象
       IUserDao dao = session.getMapper(IUserDao.class);

        // 保存
        dao.saveUser(account);

        // 提交事务
        session.commit();

        // 关闭资源
        session.close();
        in.close();
    }


}
