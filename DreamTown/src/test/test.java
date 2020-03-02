package test;

import bean.User;
import dao.userDao;
import org.junit.Test;


public class test {

    @Test
    public void testLogin(){
       /* User loginuser = new User();
        loginuser.setUsername("fhj");
        loginuser.setPassword("fhj1234");


        userDao dao = new userDao();
        User user = dao.login(loginuser);

        System.out.println(user);*/

        int a=2,b=3,c=4,s1,s2,s3,x1,x2,x3,x4,x5,x6,n=0;
                    s1=a+b+c;
                    for(x1=1;x1<=s1;x1=x1)
                        for(x2=1;x2<=s1;x2++)
                            for(x3=1;x3<=s1;x3++)
                                if(x1*a+x2*b+x3*c==s1){
                                    n++;
                                    System.out.println("x1:"+x1);
                                    System.out.println("x2:"+x2);
                                    System.out.println("x3:"+x3);
                                    System.out.println("次数:"+n+"...................");
                }
    }
}


