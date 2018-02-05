package Niubilityjin.JDBC.test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	//由于一个项目中这四个变量只被赋值一次
		//使用静态变量
		private static String driver;
		private static String url;
		private static String userName;
		private static String password;
		//两个策略参数
		private static int initialSize;
		private static int maxActive;
		//声明BasicDataSource对象,赋值为null
		private static BasicDataSource ds=null;
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
				initialSize=Integer.parseInt(cfg.getProperty("initialSize"));
				maxActive=Integer.parseInt(cfg.getProperty("maxActive"));
				//创建管理员对象
				ds=new BasicDataSource();
				ds.setDriverClassName(driver);
				ds.setUrl(url);
				ds.setUsername(userName);
				ds.setPassword(password);
				ds.setInitialSize(initialSize);
				ds.setMaxActive(maxActive);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static Connection getConnection(){
			try {
				Connection conn=ds.getConnection();
				return conn;
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			return null;
		}
		public static void closeConnection(Connection conn){
			if(conn!=null){
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
		}
		public static void rollback(Connection conn){
			if(conn!=null){
				try {
					conn.rollback();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
}
