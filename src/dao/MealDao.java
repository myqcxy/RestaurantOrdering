package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Meal;

public class MealDao {
	Connection con = DBConnection.getConnect();
	
	public List<Meal> getAllMeals(){
		//创建一个List<Meal>对象
				List<Meal> meals = new ArrayList<Meal>();
				//sql语句
				String sql = "select * from Meal";
				//使用PreparedStatement将SQL语句执行
				PreparedStatement ps;
				try {
					ps = con.prepareStatement(sql);
					//逐个获取得到的结果
					try (ResultSet rs = ps.executeQuery();) {
						while(rs.next()){//如果还有house没有获取

							Meal m = new Meal();
						
							m.setMid(rs.getInt("mid"));
							m.setMname(rs.getString("mname"));
							m.setPhoto(rs.getString("photo"));
							m.setPrice(rs.getFloat("price"));
							m.setPhoto("images/"+m.getPhoto());
							m.setCategory(rs.getString("Category"));
							m.setSales(rs.getInt("sales"));
							meals.add(m);
						}
					}
					ps.close();//关闭ps
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return meals;
	}
	
	public boolean addMeal(Meal meal){
		String sql="insert into Meal(mname,price,photo,Category) values(?,?,?,?)";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setString(1,meal.getMname());
			      pstmt.setFloat(2,meal.getPrice());
			      pstmt.setString(3,meal.getPhoto());
			      pstmt.setString(4, meal.getCategory());
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	
	}

	public Meal getMealById(int mid) {
		//创建一个List<Meal>对象
		Meal m = new Meal();
		//sql语句
		String sql = "select * from Meal where mid=?";
		//使用PreparedStatement将SQL语句执行
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mid);
			//逐个获取得到的结果
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()){//如果还有house没有获取
				
					m.setMid(rs.getInt("mid"));
					m.setMname(rs.getString("mname"));
					m.setPhoto(rs.getString("photo"));
					m.setPrice(rs.getFloat("price"));
					m.setPhoto("images/"+m.getPhoto());
					m.setCategory(rs.getString("Category"));
					m.setSales(rs.getInt("sales"));
				}
			}
			ps.close();//关闭ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return m;

	}


	public boolean updateMeal(Meal meal) {
		String sql="update Meal set mname=?,price=?,photo=?,Category=? where mid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setString(1,meal.getMname());
			      pstmt.setFloat(2,meal.getPrice());
			      pstmt.setString(3,meal.getPhoto());
			      pstmt.setString(4, meal.getCategory());
			      pstmt.setInt(5, meal.getMid());
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		 return isSuc;
		
	}

	public boolean delMeal(int mid) {
		boolean isSuc=false;
	    String sql = "delete from meal where mid=?";
	    try (
	        PreparedStatement pstmt = con.prepareStatement(sql);) {
	      pstmt.setLong(1,mid);
	      int row=pstmt.executeUpdate();
	      isSuc=row>0;
	    } catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	    return isSuc;
	}

	public List<Meal> getByCategory(String c, String uid) {
		//创建一个List<Meal>对象
		List<Meal> meals = new ArrayList<Meal>();
		//sql语句
		String sql = "select * from Meal where Category=?";
		//使用PreparedStatement将SQL语句执行
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, c);
			//逐个获取得到的结果
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()){//如果还有house没有获取

					Meal m = new Meal();
					
					m.setMid(rs.getInt("mid"));
					m.setMname(rs.getString("mname"));
					m.setPhoto(rs.getString("photo"));
					m.setPrice(rs.getFloat("price"));
					m.setPhoto("images/"+m.getPhoto());
					m.setCategory(rs.getString("Category"));
					m.setSales(rs.getInt("sales"));
					m.setAddToCacheNumber(getAddToCacheNumber(m.getMid(),uid));
					meals.add(m);
				}
			}
			ps.close();//关闭ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return meals;
	}

	public int getAddToCacheNumber(int mid, String uid) {
		
		//sql语句
		String sql = "select * from cache where uid=?";
		//使用PreparedStatement将SQL语句执行
		PreparedStatement ps;
		String ans="";
		int re=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, uid);
			//逐个获取得到的结果
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()){//如果还有house没有获取
					ans=rs.getString("mid");
				}
			}
			ps.close();//关闭ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ans=ans.trim();
	//	ans=ans.substring(0, ans.length()-1);
		String []mids = ans.split(":");
		for(String m:mids){
			if(m.equalsIgnoreCase(mid+""))
				re++;
			
		}
		return re;
	}

	public List<Meal> getAllMeals(String mid, String uid) {
		//创建一个List<Meal>对象
				List<Meal> meals = new ArrayList<Meal>();
				//sql语句
				String sql = "select * from cache where uid=?";
				//使用PreparedStatement将SQL语句执行
				PreparedStatement ps;
				String ans="";
				try {
					ps = con.prepareStatement(sql);
					ps.setString(1, uid);
					//逐个获取得到的结果
					try (ResultSet rs = ps.executeQuery();) {
						while(rs.next()){//如果还有house没有获取
							ans=rs.getString("mid");
						
							
						}
					}
					ps.close();//关闭ps
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ans=ans.trim();
			//	ans=ans.substring(0, ans.length()-1);
				String []mids = ans.split(":");
				for(String m:mids){
					boolean was=false;
					for(Meal mm:meals){
						if(mm.getMid()==Integer.parseInt(m))
								was=true;
					}
					if(was) continue;
					int sale = ans.split(m).length-1;
					Meal meal = getMealById(Integer.parseInt(m));
					meal.setSales(sale);
					meals.add(meal);
				}
				return meals;
	}

	public float getPrice(int mid) {
		//sql语句
				String sql = "select * from Meal where mid=?";
				//使用PreparedStatement将SQL语句执行
				PreparedStatement ps;
				float ans=0;
				try {
					ps = con.prepareStatement(sql);
					ps.setInt(1, mid);
					//逐个获取得到的结果
					try (ResultSet rs = ps.executeQuery();) {
						while(rs.next()){//如果还有house没有获取
							ans=rs.getInt("price");
						}
					}
					ps.close();//关闭ps
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return ans;
	}

	public int getCacheMealNumber(String mid, String uid) {
		//创建一个List<Meal>对象
		List<Meal> meals = new ArrayList<Meal>();
		//sql语句
		String sql = "select * from cache where uid=?";
		//使用PreparedStatement将SQL语句执行
		PreparedStatement ps;
		String ans="";
		int sale=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, uid);
			//逐个获取得到的结果
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()){//如果还有house没有获取
					ans=rs.getString("mid");
				
					
				}
			}
			ps.close();//关闭ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ans=ans.trim();
	//	ans=ans.substring(0, ans.length()-1);
		String []mids = ans.split(":");
		for(String m:mids){
			boolean was=false;
			for(Meal mm:meals){
				if(mm.getMid()==Integer.parseInt(m))
						was=true;
			}
			if(was) continue;
			sale = ans.split(m).length-1;
			
			
		}
		return sale;
	}
}
