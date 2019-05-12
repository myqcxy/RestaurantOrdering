package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.User;

public class UserDao {
	Connection con = DBConnection.getConnect();
	
	public boolean login(User user){
		String sql = "select * from [User] where uid=? and upass=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUid());
			ps.setString(2, user.getUpass());
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

	public float getBalance(String uid) {
		float balance=0;
		String sql = "select * from [User] where uid=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, uid);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			balance=rs.getFloat("balance");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

	public boolean uidIsExist(String uid) {
		boolean isExist = true;
		String sql = "select * from [User] where uid=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, uid);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				isExist=true;
			else isExist=false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}

	public boolean regist(User user) {
		String sql="insert into [User](uid,upass,phone) values(?,?,?)";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			 System.out.println(user.getUid()+user.getUpass()+user.getPhone()+"2222");
			      pstmt.setString(1,user.getUid());
			      pstmt.setString(2,user.getUpass());
			      pstmt.setString(3,user.getPhone());
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}

	public User getUser(String uid) {
		User user = new User();
		String sql = "select * from [User] where uid=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, uid);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				user.setUid(uid);
				user.setUpass(rs.getString("upass"));
				user.setBalance(rs.getFloat("balance"));
				user.setPhone(rs.getString("phone"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return user;
	}

	public boolean updateUser(User user) {
		String sql="update  [User] set upass=?,phone=? where uid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setString(3,user.getUid());
			      pstmt.setString(1,user.getUpass());
			      pstmt.setString(2,user.getPhone());
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}
}
