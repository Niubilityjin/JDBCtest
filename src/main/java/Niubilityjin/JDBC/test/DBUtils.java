package Niubilityjin.JDBC.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	//由于一个项目中这四个变量只被赋值一次
		//使用静态变量
		static String driver;
		static String url;
		static String userName;
		static String password;
		//在程序一运行就开始加载 使用静态块
		static{
			try {
				//1.创建properties
				Properties cfg=new Properties();
				//2.使用load方法加载流
				//2.1获取配置文件的流
				InputStream in=DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
				cfg.load(in);
				driver=cfg.getProperty("jdbc.driver");
				url=cfg.getProperty("jdbc.url");
				userName=cfg.getProperty("jdbc.userName");
				password=cfg.getProperty("jdbc.password");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static Connection getConnection(){
			try {
				Class.forName(driver);//注册驱动
				Connection conn=DriverManager.getConnection(url, userName, password);
				return conn;
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			return null;
		}
		public static void closeConnection(Connection conn){
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
		}
}
