package test;

import bean.User;
import dao.userDao;
import org.junit.Test;


public class test {

    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("fhj");
        loginuser.setPassword("fhj1234");


        userDao dao = new userDao();
        User user = dao.login(loginuser);

        System.out.println(user);
    }
}


