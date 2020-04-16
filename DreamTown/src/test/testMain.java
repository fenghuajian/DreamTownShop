package test;

import org.junit.Test;
import util.SendEmail;

import javax.mail.Address;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//
public class testMain {
   int i= (int) ((Math.random()*9+1)*100000);
  String content=String.valueOf(i);

    @Test
    public void send1(){/**/
        SendEmail.recmail("2274105767@qq.com","123456");
    }
 @Test
public void send(){/**/
     try {
           boolean flag=false;
           //TODO Auto-generated method stub
           Properties props = new Properties();
           // 开启debug调试（会提示发邮件的过程）
            props.setProperty("mail.debug", "true");
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
            // 设置邮件服务器主机名
            props.setProperty("mail.host", "smtp.qq.com");
            // 发送邮件协议名称
            props.setProperty("mail.transport.protocol", "smtp");

            // 设置环境信息
            Session session = Session.getInstance(props);

            // 创建邮件对象
         MimeMessage msg = new MimeMessage(session);
         try {
             msg.setSubject("商城验证码");
         } catch (javax.mail.MessagingException e) {
             e.printStackTrace();
         }
         // 设置邮件内容
            String mail1="2274105767@qq.com";

            msg.setText(content);
            // 设置发件人
            msg.setFrom(new InternetAddress("2274105767@qq.com"));

            Transport transport = session.getTransport();

            //jbmhvmdlrymqeahf
            // 连接邮件服务器
            transport.connect("2274105767", "mhoewzhcfurhdjcf");
            // 发送邮件:mail1是接收者邮箱
            transport.sendMessage(msg, new Address[] { new InternetAddress(mail1) });
            flag=true;
            // 关闭连接
            transport.close();

        } catch (AddressException e) {
         e.printStackTrace();
     } catch (NoSuchProviderException e) {
         e.printStackTrace();
     } catch (javax.mail.MessagingException e) {
         e.printStackTrace();
     }

 }

    @Test


public  void random(){

        int a=2,b=3,c=4,s1,s2,s3,x1,x2,x3,x4,x5,x6,n=0;
        s1=25*a+75*b+50*c;
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
//
}
