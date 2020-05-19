package cn.guet.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Connection getConn() {
		Properties prop = new Properties();
		InputStream in;
		try {
			in = Class.forName("cn.guet.util.DBConnection").getResourceAsStream("/druid.properties");
			prop.load(in);

			String url = prop.getProperty("url");
			Class.forName(prop.getProperty("driverClassName"));// ��������
			Connection conn = DriverManager.getConnection(url, prop.getProperty("username"), prop.getProperty("password"));
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void closeConn(Connection conn)
	{
		try {
			if(conn!=null) 	conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}