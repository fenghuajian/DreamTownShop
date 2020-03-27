package cn.guet.test;

import org.junit.Test;

public class testPath {

    @Test
    public void testPath(){

        String realPath = System.getProperty("/upload");
        System.out.println(realPath);

    }
}
