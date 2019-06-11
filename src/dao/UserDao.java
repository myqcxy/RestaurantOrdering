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
		String sql="insert into [User](uid,upass,phone,email) values(?,?,?,?)";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setString(1,user.getUid());
			      pstmt.setString(2,user.getUpass());
			      pstmt.setString(3,user.getPhone());
			      pstmt.setString(4, user.getEmail());
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
				user.setIntegral(rs.getInt("integral"));
				user.setVip(rs.getInt("vip"));
				String email = rs.getString("email");
				if(email!=null)
				user.setEmail(email.trim());
				else user.setEmail("");
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

	public boolean modifyPassWord(User user) {
		String sql="update  [User] set upass=?,sid=''  where uid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      
			      pstmt.setString(1,user.getUpass());
			      pstmt.setString(2,user.getUid().trim());
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}

	public boolean modifyPhone(User user) {
		String sql="update  [User] set phone=? where uid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setString(2,user.getUid());
			      pstmt.setString(1,user.getPhone());
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}

	public User hasSid(String sid) {
		User u=null;
		String sql = "select * from [User] where sid=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, sid);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				String uid=rs.getString("uid");
				u=this.getUser(uid);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public boolean setSid(String sid, String uid) {
		String sql="update  [User] set sid=? where uid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      
			      pstmt.setString(1,sid);
			      pstmt.setString(2,uid);
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}

	public boolean emailIsRegisted(String email) {
		boolean isExist = true;
		String sql = "select * from [User] where email=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}

	public boolean modifyEmail(User user) {
		String sql="update  [User] set email=?  where uid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      
			      pstmt.setString(1,user.getEmail());
			      pstmt.setString(2,user.getUid().trim());
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}

	public int getIntegral(String uid, float price) {
		String sql = "select * from [User] where uid=?";
		int integral=0;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, uid);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				integral=rs.getInt("integral");
			}
			if(integral>(int)(price-1)*10){
				integral=(int)(price-1)*10;
			}
			integral-=integral%10;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return integral;
	}

	public boolean delIntegral(String uid, int integral) {
		String sql="update  [User] set integral=integral-?  where uid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      
			      pstmt.setInt(1,integral);
			      pstmt.setString(2,uid);
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}

	public boolean drawBack(String uid, int integral, float price, int payState) {
		if(payState==2) return true;
		System.out.println(integral+"sssssssssss");
		String sql="update  [User] set integral=integral+?-?, balance=balance+? where uid=?";
		
			
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      
			      pstmt.setInt(1,integral);
			      pstmt.setInt(2, (int)price);
			      pstmt.setFloat(3, price);
			      pstmt.setString(4,uid);
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}
}
