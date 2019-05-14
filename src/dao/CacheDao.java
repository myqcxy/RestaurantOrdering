package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cache;
import model.Meal;

public class CacheDao extends Dao{

	public boolean addToCache(Cache cache) {
		String sql="update Cache set mid=? where uid=?";
		String mid = getMid(cache.getUid());
		if(mid==null) mid="";
		mid=mid.trim();
		if(mid.startsWith("null")) mid=mid.replaceAll("null", "");
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setString(1,mid + cache.getMid()+":");
			      pstmt.setString(2,cache.getUid());
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}

	public String getMid(String uid) {
				String ans="";
				String sql = "select * from Cache where uid=?";
				//使用PreparedStatement将SQL语句执行
				PreparedStatement ps;
				try {
					ps = con.prepareStatement(sql);
					ps.setString(1, uid);
					//逐个获取得到的结果
					try (ResultSet rs = ps.executeQuery();) {
						while(rs.next()){//如果还有house没有获取
						
							ans = rs.getString("mid");
							
						
						}
					}
					ps.close();//关闭ps
				} catch (SQLException e) {
					e.printStackTrace();
					return ans;
				}
				
				return ans;
	}

	public boolean delFromCache(Cache cache) {
		String sql="update Cache set mid=? where uid=?";
		String mid = getMid(cache.getUid());
		mid=mid.trim();
		mid=mid.replaceFirst(cache.getMid()+":", "");
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setString(1,mid);
			      pstmt.setString(2,cache.getUid());
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}

	public Cache getCache(String uid) {
		Cache cache = new Cache();
		String sql = "select * from Cache where uid=?";
		//使用PreparedStatement将SQL语句执行
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, uid);
			//逐个获取得到的结果
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()){//如果还有house没有获取
				
					cache.setMid(rs.getString("mid"));
					cache.setUid(uid);
					cache.setCid(rs.getInt("cid"));
					cache.setMid(cache.getMid().trim());
				
				}
			}
			ps.close();//关闭ps
		} catch (SQLException e) {
			e.printStackTrace();
			return cache;
		}
		return cache;
	}
	 
}
