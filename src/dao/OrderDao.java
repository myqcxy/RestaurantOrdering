package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.Cache;
import model.Comment;
import model.Meal;
import model.Order;

public class OrderDao extends Dao{

	public boolean toPay(Order order) {
		String sql="insert into [Order](uid,price,mid,number,totle,phone,method,discount,payMethod,payState,note) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		boolean isSuc=false;
		
		 try (
			      PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setString(1,order.getUid());
			      pstmt.setFloat(2,order.getPrice());
			      pstmt.setString(3,order.getMid());
			   
			      pstmt.setInt(4, order.getNumber());
			      pstmt.setFloat(5, order.getTotle());
			      pstmt.setString(6, order.getPhone());
			      pstmt.setInt(7, order.getMethod());
			      pstmt.setFloat(8, order.getDiscount());
			      pstmt.setInt(9, order.getPayMethod());
			      pstmt.setInt(10, order.getPayState());
			      pstmt.setString(11, order.getNote());
			      new MealDao().updateSales(order.getMid());
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}


	public float getOrderPrice(String uid) {
		String sql = "select * from Cache where uid=?";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		float price=0;
		String ans="";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, uid);
			//�����ȡ�õ��Ľ��
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()){//�������houseû�л�ȡ
					ans=rs.getString("mid");
				}
			}
			ps.close();//�ر�ps
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ans=ans.trim();
		//	ans=ans.substring(0, ans.length()-1);
			String []mids = ans.split(":");
			for(String m:mids){
				float p = new MealDao().getPrice(Integer.parseInt(m));
				price+=p;
			}
		return price;
	}


	public List<Order> getOrders(String uid) {
		//����һ��List<Order>����
		List<Order> orders = new ArrayList<Order>();
		//sql���
		String sql = "select * from [Order] Where uid=? Order By date Desc";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, uid);
			//�����ȡ�õ��Ľ��
			try (ResultSet rs = ps.executeQuery();) {
		
				while(rs.next()){//�������houseû�л�ȡ
					Order o  = new Order();
					o.setOid(rs.getLong("oid"));
					o.setUid(rs.getString("uid"));
					o.setTid(rs.getInt("tid"));
					o.setPrice(rs.getFloat("price"));
					Timestamp timestamp = rs.getTimestamp("date");
					
					o.setDate(new java.util.Date(timestamp.getTime()));
					o.setState(rs.getInt("state"));
					o.setMid(rs.getString("mid"));
					o.setMidString(new MealDao().getMealName(o.getMid()));
					o.setTotle(rs.getFloat("totle"));
					o.setPhone(rs.getString("phone"));
					o.setMethod(rs.getInt("method"));
					o.setDiscount(rs.getFloat("discount"));
					o.setPayMethod(rs.getInt("payMethod"));
					o.setPayState(rs.getInt("payState"));
					o.setNote(rs.getString("note"));
					o.setNumber(rs.getInt("number"));
					o.setPrice(o.getTotle()-o.getDiscount());
					orders.add(o);
				
				}
			}
			ps.close();//�ر�ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(orders.isEmpty()) return null;
		return orders;
	}


	public boolean cashRegister(long oid) {
		String sql="update [Order] set payState=1 where oid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setLong(1,oid);
			      
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			      pstmt.close();
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		 return isSuc;
	}


	public List<Order> getOrdersById(long oid) {
		//����һ��List<Order>����
				List<Order> orders = new ArrayList<Order>();
				//sql���
				String sql = "select * from [Order] Where oid=? Order By date Desc";
				//ʹ��PreparedStatement��SQL���ִ��
				PreparedStatement ps;
				try {
					ps = con.prepareStatement(sql);
					ps.setLong(1, oid);
					//�����ȡ�õ��Ľ��
					try (ResultSet rs = ps.executeQuery();) {
				
						while(rs.next()){//�������houseû�л�ȡ
							Order o  = new Order();
							o.setOid(rs.getLong("oid"));
							o.setUid(rs.getString("uid"));
							o.setTid(rs.getInt("tid"));
							o.setPrice(rs.getFloat("price"));
							o.setDate(rs.getDate("date"));
							o.setState(rs.getInt("state"));
							o.setMid(rs.getString("mid"));
							o.setMidString(new MealDao().getMealName(o.getMid()));
							o.setTotle(rs.getFloat("totle"));
							o.setPhone(rs.getString("phone"));
							o.setMethod(rs.getInt("method"));
							o.setDiscount(rs.getFloat("discount"));
							o.setPayMethod(rs.getInt("payMethod"));
							o.setPayState(rs.getInt("payState"));
							o.setNote(rs.getString("note"));
							o.setNumber(rs.getInt("number"));
							orders.add(o);
						
						}
					}
					ps.close();//�ر�ps
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return orders;
	}


	public List<Order> getOrdersByPhone(String phone) {
		//����һ��List<Order>����
		List<Order> orders = new ArrayList<Order>();
		//sql���
		String sql = "select * from [Order] Where phone like ? Order By date Desc";
		phone = "%"+phone+"%";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			//�����ȡ�õ��Ľ��
			try (ResultSet rs = ps.executeQuery();) {
		
				while(rs.next()){//�������houseû�л�ȡ
					Order o  = new Order();
					o.setOid(rs.getLong("oid"));
					o.setUid(rs.getString("uid"));
					o.setTid(rs.getInt("tid"));
					o.setPrice(rs.getFloat("price"));
					o.setDate(rs.getDate("date"));
					o.setState(rs.getInt("state"));
					o.setMid(rs.getString("mid"));
					o.setMidString(new MealDao().getMealName(o.getMid()));
					o.setTotle(rs.getFloat("totle"));
					o.setPhone(rs.getString("phone"));
					o.setMethod(rs.getInt("method"));
					o.setDiscount(rs.getFloat("discount"));
					o.setPayMethod(rs.getInt("payMethod"));
					o.setPayState(rs.getInt("payState"));
					o.setNote(rs.getString("note"));
					o.setNumber(rs.getInt("number"));
					orders.add(o);
				
				}
			}
			ps.close();//�ر�ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}


	public int choiceTable(long oid, int number) {
		int tid = new TableDao().findTable(number);
		if(-1!=tid){
			String sql="update [Order] set tid=? where oid=?";
			 try (
				        PreparedStatement pstmt = con.prepareStatement(sql);) {
				      pstmt.setInt(1,tid);
				      pstmt.setLong(2, oid);
				      
				      int row=pstmt.executeUpdate();
				      pstmt.close();
				    } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
			return tid;
	}


	public List<Order> getUnservedOrders() {
		//����һ��List<Order>����
				List<Order> orders = new ArrayList<Order>();
				//sql���
				String sql = "select * from [Order] Where (state=0 or state=1) and tid is not null Order By date Desc";
				//ʹ��PreparedStatement��SQL���ִ��
				PreparedStatement ps;
				try {
					ps = con.prepareStatement(sql);
					//�����ȡ�õ��Ľ��
					try (ResultSet rs = ps.executeQuery();) {
				
						while(rs.next()){//�������houseû�л�ȡ
							Order o  = new Order();
							o.setOid(rs.getLong("oid"));
							o.setUid(rs.getString("uid"));
							o.setTid(rs.getInt("tid"));
							o.setPrice(rs.getFloat("price"));
							o.setDate(rs.getDate("date"));
							o.setState(rs.getInt("state"));
							o.setMid(rs.getString("mid"));
							o.setMidString(new MealDao().getMealName(o.getMid()));
							o.setTotle(rs.getFloat("totle"));
							o.setPhone(rs.getString("phone"));
							o.setMethod(rs.getInt("method"));
							o.setDiscount(rs.getFloat("discount"));
							o.setPayMethod(rs.getInt("payMethod"));
							o.setPayState(rs.getInt("payState"));
							o.setNote(rs.getString("note"));
							o.setNumber(rs.getInt("number"));
							orders.add(o);
						
						}
					}
					ps.close();//�ر�ps
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return orders;
	}


	public boolean changeOrderState(long oid, int state) {
		String sql="update [Order] set state=? where oid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			 		pstmt.setInt(1, state);
			      pstmt.setLong(2,oid);
			      
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			      pstmt.close();
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		 return isSuc;
	}


	public float getDiscount(String mid) {
		mid=mid.trim();
		String []mids = mid.split(":");
		float discount=0;
		for(String m:mids){
			if(m.length()>0){
				discount+=new MealDao().getDicountByMid(Integer.parseInt(m));
			}
		}
			
			
		return discount;
	}


	public boolean comment(Comment comment) {
		String sql="update Comment set grade=? where oid=?";
		boolean isSuc=false;
		
		 try (
			      PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setLong(2,comment.getOid());
			      pstmt.setInt(1,comment.getGrade());
			   
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}


	public boolean hasNewOrder() {
		String sql = "select * from [Order] where state=3";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(sql);
			//�����ȡ�õ��Ľ��
			try (ResultSet rs = ps.executeQuery();) {
				return rs.next();
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return false;
	}


	public List<Order> getNewOrders() {
		//����һ��List<Order>����
		List<Order> orders = new ArrayList<Order>();
		//sql���
		String sql = "select * from [Order] Where state=3 Order By date Desc";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			//�����ȡ�õ��Ľ��
			try (ResultSet rs = ps.executeQuery();) {
		
				while(rs.next()){//�������houseû�л�ȡ
					Order o  = new Order();
					o.setOid(rs.getLong("oid"));
					o.setUid(rs.getString("uid"));
					o.setTid(rs.getInt("tid"));
					o.setPrice(rs.getFloat("price"));
					o.setDate(rs.getDate("date"));
					o.setState(rs.getInt("state"));
					o.setMid(rs.getString("mid"));
					o.setMidString(new MealDao().getMealName(o.getMid()));
					o.setTotle(rs.getFloat("totle"));
					o.setPhone(rs.getString("phone"));
					o.setMethod(rs.getInt("method"));
					o.setDiscount(rs.getFloat("discount"));
					o.setPayMethod(rs.getInt("payMethod"));
					o.setPayState(rs.getInt("payState"));
					o.setNote(rs.getString("note"));
					o.setNumber(rs.getInt("number"));
					orders.add(o);
				
				}
			}
			ps.close();//�ر�ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}


	

	
	
}
