package cn.guet.test;

import cn.guet.dao.ICategoryDao;
import cn.guet.dao.ICustomerDao;
import cn.guet.domain.Category;
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

public class testCategoryDao {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private ICategoryDao accountDao;

    @Before
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(ICategoryDao.class);
    }

    @After
    public  void destroy()throws  Exception{
        session.commit();
        session.close();
        in.close();
    }
    @Test
    public void save(){
        Category category=new Category();
        category.setCategoryid("1234");
        category.setName("测试");
        accountDao.saveCategory(category);
    }
    @Test
    public void page(){
        int num=accountDao.getNum();
        System.out.println(num);
        List<Category> list=accountDao.findPage(0,5);
        System.out.println(list);
        PageModel<Category> pm=new PageModel<Category>();
        pm.setList(list);
        pm.setTotalPage(6);
        pm.setCurrentPage(1);
        System.out.println(pm);
    }

    @Test
    public void delete(){
            accountDao.deleteCategory("1234");
    }


}
