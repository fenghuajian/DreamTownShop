package test;

import org.junit.Test;
import util.getCode;

public class testPhone {
    @Test
    public  void phone(){
       // SendPhoneCode.sendCode("18877335730","123456");
    }
    @Test
    public  void get(){
        String coede=getCode.vcode();
        System.out.println("验证码:"+coede);
    }
}
