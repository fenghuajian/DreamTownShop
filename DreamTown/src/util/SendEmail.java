package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    public static void recmail(String mail,String code) {
        String to=mail;
        String from="2274105767@qq.com";
        String host="smtp.qq.com";

        Properties properties=System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");

        Session session=Session.getDefaultInstance(properties,new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("2274105767", "mhoewzhcfurhdjcf");
            }
        });

        MimeMessage message=new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("邮箱验证码");
            message.setText("您的验证码为："+code);
            Transport.send(message);
            System.out.println("Sent message successfully");
        }  catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
