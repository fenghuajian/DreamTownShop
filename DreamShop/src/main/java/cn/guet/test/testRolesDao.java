package cn.guet.test;

import cn.guet.dao.IOrderDao;
import cn.guet.dao.IRoleDao;
import cn.guet.domain.Roles;
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

public class testRolesDao {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IRoleDao accountDao;

    @Before
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IRoleDao.class);
    }

    @After
    public  void destroy()throws  Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public  void save(){
        Roles roles=new Roles();
        roles.setRolesId("1234");
        roles.setRoleName("测试角色");
        accountDao.saveRole(roles);

    }

    @Test
    public  void grant(){
        String permissionIds[]={"19c33eb3909e450a91476731739df5c0","8ad9d3c6a434417bbd45150be217c739"};
        for(int i=0;i<permissionIds.length;i++){
            accountDao.SaveGrant("1234",permissionIds[i]);
        }
    }
    @Test
    public void delete(){
        accountDao.deleteRolesPermission("1234");
    }
    @Test
    public void PAGE(){
        List<Roles> list=accountDao.findPage(0,5);
        System.out.println(list);
        PageModel<Roles> pm=new PageModel<Roles>();
        pm.setList(list);
        pm.setTotalPage(6);
        pm.setCurrentPage(1);
        System.out.println(pm);
    }

}
