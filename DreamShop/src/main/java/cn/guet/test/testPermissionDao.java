package cn.guet.test;

import cn.guet.dao.IPermissionDao;
import cn.guet.dao.IRoleDao;
import cn.guet.domain.Permission;
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

public class testPermissionDao {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IPermissionDao accountDao;

    @Before
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IPermissionDao.class);
    }

    @After
    public  void destroy()throws  Exception{
        session.commit();
        session.close();
        in.close();
    }
    @Test
    public  void find(){
        List<Permission> permissions=accountDao.getPermissionByRoleId("1234");
        System.out.println(permissions);

    }
    @Test
    public  void page(){
      /* int num= accountDao.getPermissionNum();
        System.out.println(num);*/
        List<Permission> list=accountDao.findPage(0,5);
        System.out.println(list);
        PageModel<Permission> pm=new PageModel<Permission>();
        pm.setList(list);
        pm.setTotalPage(6);
        pm.setCurrentPage(1);
        System.out.println(pm);

    }
    @Test
    public  void save(){
        Permission p=new Permission();
        p.setPermissionid("123");
        p.setName("测试1");
        accountDao.savePermission(p);
    }
    @Test
    public  void update(){
        Permission p=new Permission();
        p.setPermissionid("123");
        p.setName("测试2");
        accountDao.updatePermission(p);
    }
    @Test
    public  void delete(){
        accountDao.deletePermission("123");
    }

}
