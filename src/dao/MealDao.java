package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Meal;

public class MealDao {
	Connection con = DBConnection.getConnect();
	
	public List<Meal> getAllMeals(){
		//����һ��List<Meal>����
				List<Meal> meals = new ArrayList<Meal>();
				//sql���
				String sql = "select * from Meal";
				//ʹ��PreparedStatement��SQL���ִ��
				PreparedStatement ps;
				try {
					ps = con.prepareStatement(sql);
					//�����ȡ�õ��Ľ��
					try (ResultSet rs = ps.executeQuery();) {
						while(rs.next()){//�������houseû�л�ȡ

							Meal m = new Meal();
						
							m.setMid(rs.getInt("mid"));
							m.setMname(rs.getString("mname"));
							m.setPhoto(rs.getString("photo"));
							m.setPrice(rs.getFloat("price"));
							m.setPhoto("images/"+m.getPhoto());
							meals.add(m);
						}
					}
					ps.close();//�ر�ps
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return meals;
	}
	
	public boolean addMeal(Meal meal){
		String sql="insert into Meal(mname,price,photo) values(?,?,?)";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setString(1,meal.getMname());
			      pstmt.setFloat(2,meal.getPrice());
			      pstmt.setString(3,meal.getPhoto());
			      
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
		//����һ��List<Meal>����
		Meal m = new Meal();
		//sql���
		String sql = "select * from Meal where mid=?";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mid);
			//�����ȡ�õ��Ľ��
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()){//�������houseû�л�ȡ
				
					m.setMid(rs.getInt("mid"));
					m.setMname(rs.getString("mname"));
					m.setPhoto(rs.getString("photo"));
					m.setPrice(rs.getFloat("price"));
					m.setPhoto("images/"+m.getPhoto());
				
				}
			}
			ps.close();//�ر�ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return m;

	}

	public boolean updateMeal(Meal meal) {
		String sql="update Meal set mname=?,price=?,photo=? where mid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setString(1,meal.getMname());
			      pstmt.setFloat(2,meal.getPrice());
			      pstmt.setString(3,meal.getPhoto());
			      pstmt.setInt(4, meal.getMid());
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
}
