package Niubilityjin.JDBC.test;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;

public class MainDemo1 {

	public static void main(String[] args) {
		Connection conn=null;
		try {
			conn=DBUtils.getConnection();
			String sql="select*from emp";
			Statement sta=conn.createStatement();
			ResultSet rs=sta.executeQuery(sql);
			while(rs.next()){
				int id=rs.getInt(1);
				String str=rs.getString(2);
				System.out.println(id+"---"+str);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}

	}

}
