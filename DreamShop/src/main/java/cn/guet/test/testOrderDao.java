package cn.guet.test;

import cn.guet.dao.ICustomerDao;
import cn.guet.dao.IOrderDao;
import cn.guet.domain.Orderinfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class testOrderDao {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IOrderDao accountDao;

    @Before
    public  void init()throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IOrderDao.class);
    }

    @After
    public  void destroy()throws  Exception{
        session.commit();
        session.close();
        in.close();
    }
    /*
    * */
    @Test
    public void find(){
        List<Orderinfo> orderinfos=accountDao.findAll(0,5);
        System.out.println(orderinfos);
       /* System.out.println(".......");
        List<Orderinfo> orderinfo=accountDao.findBycustomerid(0,3,"3EEF0");
        System.out.println(orderinfo);
*/
       /* List<Orderinfo> O =accountDao.findByShopid(0,3,"1CA2A");
        System.out.println(O);*/

       // int num=accountDao.findCarNum("1111","1111");

        //int num=accountDao.findAllNum();
       /* int num=accountDao.findNunByCustomerid("3EEF0");
        System.out.println(num);*/


    }
    @Test
    public void insert()  {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date =null;
        try {
            date = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Orderinfo orderinfo=new Orderinfo();
            orderinfo.setCustomerid("111");
            orderinfo.setStatus("111");
            orderinfo.setOrderid("111");
            orderinfo.setProductid("111");
            orderinfo.setBaddr("111");
            orderinfo.setBphone("111");
            orderinfo.setBname("111");
            orderinfo.setExpress("111");
            orderinfo.setNum(1);
            orderinfo.setOrderdate(new Timestamp(date.getTime()));
            orderinfo.setPic("111");
            orderinfo.setPinfo("111");
            orderinfo.setPname("111");
            orderinfo.setPrice(1234f);

            accountDao.saveOrder(orderinfo);
    }
    @Test
    public void delete(){
   //
    }
    @Test
    public void update(){
       // accountDao.updateCarNum(2,"1111","1111");
        accountDao.updateOrderinfo("订单完成","2da5f00f2ad440d7a83902bc9e33ad2e");
    }


}
