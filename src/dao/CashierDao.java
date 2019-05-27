package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cashier;
import model.Waiter;

public class CashierDao extends Dao {
	
	public boolean checkLogin(Cashier cashier) {
		String sql = "select * from Cashier where cid=? and cpass=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cashier.getCid());
			ps.setString(2, cashier.getCpass());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public float recharge(String uid, float amount) {
		String sql="update [User] set balance = (select balance from [User] where uid=?)+? where uid=?";
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			 	  pstmt.setString(1, uid);
			      pstmt.setFloat(2,amount);
			      pstmt.setString(3,uid);
			      pstmt.executeUpdate();
			      pstmt.close();
			      
			    } catch (SQLException e) {
					e.printStackTrace();
				}
		
		
		return new UserDao().getBalance(uid);
	}
}
