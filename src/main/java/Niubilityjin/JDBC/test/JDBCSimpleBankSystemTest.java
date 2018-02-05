package Niubilityjin.JDBC.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class JDBCSimpleBankSystemTest {

	public static void main(String[] args) {
		//(借款人,收款人,借款金额)
		BankPay("laojin","laowang",1000);

	}
	public static void BankPay(String from,String to,double money) {
Connection conn=null;
		
		String sql="update account set money=money+? where name =?";
		try {
			
			conn=DBUtils.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ps.setDouble(1, -money);
			ps.setString(2, from);
			int n=ps.executeUpdate();
			if(n!=1){
				throw new Exception("扣款错误");
			}
			
			ps.setDouble(1, money);
			ps.setString(2, to);
			int m=ps.executeUpdate();
			if(m!=1){
				throw new Exception("收款错误");
			}
			
			ps.close();
			//账户非0检查
			String dql="select money from account where name=?";
			ps=conn.prepareStatement(dql);
			ps.setString(1, from);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				double mo=rs.getDouble(1);
				if(mo<0){
					throw new Exception("余额不足");
				}else{
					conn.commit();
				}
			}
			
			
			System.out.println("收款返回值"+m+"#####"+"借款返回值"+n);
			
		} catch (Exception e) {
			e.printStackTrace();
			DBUtils.rollback(conn);
		}finally{
			DBUtils.closeConnection(conn);
		}

	}

}
