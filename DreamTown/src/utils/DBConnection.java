package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获得连接 关闭连接
 * 
 * @author liwei
 *
 */
public class DBConnection {

	public static ThreadLocal<Connection> map = new ThreadLocal<Connection>();// 其实就是一个HashMap，但是这个HashMap和本地线程绑定了

	public static Connection getConn() {
		Connection conn = map.get();
		if (conn != null) {
			return conn;
		} else {
			Properties prop = new Properties();
			InputStream in;
			try {
				in = Class.forName("utils.DBConnection").getResourceAsStream("/druid.properties");
				prop.load(in);

				String url = prop.getProperty("url");
				Class.forName(prop.getProperty("driverClassName"));// 加载驱动
				conn = DriverManager.getConnection(url, prop.getProperty("username"), prop.getProperty("password"));// 全新创建一个conn
				map.set(conn);
				return conn;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void closeConn() {
		try {
			Connection conn = map.get();
			if (conn != null) {
				map.remove();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}