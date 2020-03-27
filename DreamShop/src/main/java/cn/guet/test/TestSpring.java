package cn.guet.test;

import cn.guet.service.IUserService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

//import org.junit.Test;

public class TestSpring {

    @Test
    public void run1(){
        // 加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 获取对象
        IUserService as = (IUserService) ac.getBean("userService");
        // 调用方法
        as.findAll();
    }

}
