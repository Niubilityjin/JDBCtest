package Niubilityjin.JDBC.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AutoKeys {

	public static void main(String[] args) {
		Connection conn=null;
		String sql1="insert into keywords values(null,?)";
		try {
			conn=DBUtils.getConnection();
			conn.setAutoCommit(false);
			//自动生成序列号的列名
			//一张表中可以存在多个自增长的列
			//所以这里使用数组保存
			String[] cols={"id"};
			//获取ps对象
			//第一个参数,生成执行计划sql
			//第二个参数,执行时返回得到自增长值的列名
			PreparedStatement ps=conn.prepareStatement(sql1, cols);
			ps.setString(1, "雾霾");
			//ps一执行,自增的id值
			//就返回到ps对象中保存
			ps.executeUpdate();
			//获取自增长的值
			ResultSet rs=ps.getGeneratedKeys();
			int id=0;
			while(rs.next()){
				id=rs.getInt(1);
				System.out.println(id);
			}
			ps.close();
			String sql2="insert into post values(null,?,?)";
			ps=conn.prepareStatement(sql2);
			ps.setString(1, "今天天气很冷,晚上又雾霾");
			ps.setInt(2, id);
			int n=ps.executeUpdate();
			if(n!=1){
				throw new Exception("post插入错误");
			}
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			DBUtils.rollback(conn);
		}finally{
			DBUtils.closeConnection(conn);
		}

	}

	}


