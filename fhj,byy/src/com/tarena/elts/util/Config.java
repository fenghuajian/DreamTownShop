package com.tarena.elts.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;
/**
 * 使用JAVA API读取.properties文件
 */
public class Config {

	/**
	 * JAVA提供的用于读取.properties文件的API
	 */
	private Properties table = new Properties();
	
	/**
	 * 在这个构造方法中，我们对给定的文件进行解析
	 */
	public Config(String fileName){
		try{
		InputStream is=this.getClass().getResourceAsStream("/com/tarena/elts/ui/"+fileName);
			table.load(is);
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据key获取对应的int类型的value方法
	 */
	public int getInt(String key){
		/**
		 * 例如：读取的properties文件是client.properties
		 * 那么当在构造方法加载此文件后(调用load()方法)，可以
		 * 通过Properties的getProperty()方法获取下面这项
		 * 的值
		 * 文件中有一项是QuestionNumber=20
		 * 读取方式：String value=table.getProperty("QuestionNumber")
		 * 再将这个字符串转换为int类型并返回
		 */
		return Integer.parseInt(table.getProperty(key));
	}
	
	/**
	 * 根据key获取对应的String类型的value方法
	 */
	public String getString(String key){
		return table.getProperty(key);

	}
	
	/**
	 * 根据key获取对应的Double类型的value方法
	 */
	public double getDouble(String key){
		return Double.parseDouble(table.getProperty(key));
	}
}