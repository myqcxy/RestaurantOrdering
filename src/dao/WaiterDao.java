package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Waiter;

public class WaiterDao extends Dao {

	public boolean checkLogin(Waiter waiter) {
		String sql = "select * from Waiter where wid=? and wpass=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, waiter.getWid());
			ps.setString(2, waiter.getWpass());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
}
