package com.itheima.test;

import org.junit.Test;
import phone.SendPhoneCode;

public class phone1 {



    @Test
    public void send(){
        SendPhoneCode.sendCode("18877335730","你好啊，封华健");
   }
}
