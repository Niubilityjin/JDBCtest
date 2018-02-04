package Niubilityjin.JDBC.test;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;

public class MainDemo1 {

	public static void main(String[] args) {
		Thread t1=new DemoThread(3000);
		t1.start();
		Thread t2=new DemoThread(3000);
		t2.start();
		Thread t3=new DemoThread(3000);
		t3.start();
		Thread t4=new DemoThread(3000);
		t4.start();
		Thread t5=new DemoThread(3000);
		t5.start();
			
		}
	}

/**
 * 线程类
 */
	class DemoThread extends Thread{
	//睡眠时间
	int wait;
	public DemoThread(int wait){
		this.wait=wait;
	}
	@Override
	public void run() {
		Connection conn=DBUtils.getConnection();
		try {
			String sql="select*from dept";
			Statement sta=conn.createStatement();
			ResultSet rs=sta.executeQuery(sql);
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String pwd=rs.getString(3);
				System.out.println(id+"---"+name+"---"+pwd);
				}
			Thread.sleep(wait);
			System.out.println(wait+"已结束");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		
			DBUtils.closeConnection(conn);
		}
		
	}

}