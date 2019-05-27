package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Discount;
import model.Meal;
import model.Order;

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
							m.setCategory(rs.getString("Category"));
							m.setSales(rs.getInt("sales"));
							m.setDiscount(this.getDicountByMid(m.getMid()));
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
					m.setCategory(rs.getString("Category"));
					m.setSales(rs.getInt("sales"));
					m.setDiscount(this.getDicountByMid(m.getMid()));
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
		//����һ��List<Meal>����
		List<Meal> meals = new ArrayList<Meal>();
		//sql���
		String sql = "select * from Meal where Category=?";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, c);
			//�����ȡ�õ��Ľ��
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()){//�������houseû�л�ȡ

					Meal m = new Meal();
					
					m.setMid(rs.getInt("mid"));
					m.setMname(rs.getString("mname"));
					m.setPhoto(rs.getString("photo"));
					m.setPrice(rs.getFloat("price"));
					m.setPhoto("images/"+m.getPhoto());
					m.setCategory(rs.getString("Category"));
					m.setSales(rs.getInt("sales"));
					m.setAddToCacheNumber(getAddToCacheNumber(m.getMid(),uid));
					m.setDiscount(this.getDicountByMid(m.getMid()));
					meals.add(m);
				}
			}
			ps.close();//�ر�ps
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return meals;
	}

	public int getAddToCacheNumber(int mid, String uid) {
		
		//sql���
		String sql = "select * from cache where uid=?";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		String ans="";
		int re=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, uid);
			//�����ȡ�õ��Ľ��
			try (ResultSet rs = ps.executeQuery();) {
				if(rs.next()){//�������houseû�л�ȡ
					ans=rs.getString("mid");
				}
			
			}
			ps.close();//�ر�ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ans==null) ans="";
		ans=ans.trim();
	//	ans=ans.substring(0, ans.length()-1);
		String []mids = ans.split(":");
		for(String m:mids){
			if(m.equalsIgnoreCase(mid+""))
				re++;
			
		}
		return re;
	}

	public List<Meal> getAllMeals(String mid, String uid, boolean repeat) {
		
		if(mid==null||mid.length()<1)
			return null;
		//����һ��List<Meal>����
				List<Meal> meals = new ArrayList<Meal>();
				//sql���
				String sql = "select * from cache where uid=?";
				//ʹ��PreparedStatement��SQL���ִ��
				PreparedStatement ps;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ans=ans.trim();
				String []mids = ans.split(":");
				
				for(String m:mids){
					boolean was=false;
					for(Meal mm:meals){
						if(mm.getMid()==Integer.parseInt(m))
								was=true;
					}
					if(was&&!repeat) continue;
					int sale = ans.split(m).length-1;
					if(m.length()<1) break;
					Meal meal = getMealById(Integer.parseInt(m));
					meal.setSales(sale);
					meal.setDiscount(this.getDicountByMid(meal.getMid()));
					if(meal.getMid()>1)
						meals.add(meal);
				}
				return meals;
	}

	public float getPrice(int mid) {
		//sql���
				String sql = "select * from Meal where mid=?";
				//ʹ��PreparedStatement��SQL���ִ��
				PreparedStatement ps;
				float ans=0;
				try {
					ps = con.prepareStatement(sql);
					ps.setInt(1, mid);
					//�����ȡ�õ��Ľ��
					try (ResultSet rs = ps.executeQuery();) {
						while(rs.next()){//�������houseû�л�ȡ
							ans=rs.getInt("price");
						}
					}
					ps.close();//�ر�ps
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return ans;
	}
	//��ȡ�û�Ϊuid�Ĳ�ƷΪmid�ڹ��ﳵ������
	public int getCacheMealNumber(String mid, String uid) {
		//����һ��List<Meal>����
		List<Meal> meals = new ArrayList<Meal>();
		//sql���
		String sql = "select * from cache where uid=?";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		String ans="";
		int sale=0;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ans=ans.trim();
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
	//��Ӷ���֮���meal������+1
	public void updateSales(String mid) {
		String []mids = mid.split(":");
		int n=0;
		for(String m:mids){
			n++;
			if(n==mids.length) break;
			int sale = getSales(Integer.parseInt(m))+1;
			setSale(Integer.parseInt(m),sale);
		}
	}

	private boolean setSale(int mid, int sale) {
		String sql="update Meal set sales=? where mid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setInt(1,sale);
			      pstmt.setInt(2,mid);
			     
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		 return isSuc;
		
	}

	public int getSales(int mid) {
			//sql���
				String sql = "select * from meal where mid=?";
				//ʹ��PreparedStatement��SQL���ִ��
				PreparedStatement ps;
				String ans="";
				int sale=0;
				try {
					ps = con.prepareStatement(sql);
					ps.setInt(1, mid);
					//�����ȡ�õ��Ľ��
					try (ResultSet rs = ps.executeQuery();) {
						while(rs.next()){//�������houseû�л�ȡ
							sale=rs.getInt("sales");
						
							
						}
					}
					ps.close();//�ر�ps
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				return sale;
	}

	public String getMealName(String mid) {
		String mealName="";
		String []mids = mid.split(":");
		Map<String,Integer> map = new HashMap<String, Integer>();
		int t=0;
		for(String m:mids){
			t++;
			if(t==mids.length) break;
			if(!"".equals(m)){
				Integer num = map.get(m);  
                if(num == null || num == 0){  
                    map.put(m, 1);  
                }else if(num > 0){  
                    map.put(m, num+1);  
                }  
			}
		}
	
		Iterator<String> iter = map.keySet().iterator();  
        while(iter.hasNext()){  
            String key = iter.next();  
            Integer num = map.get(key);  
         
            mealName += getMname(Integer.parseInt(key))+"*"+num+";";
        }  
		return mealName;
	}

	private String getMname(int mid) {
		//sql���
		String sql = "select * from Meal where mid=?";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		String ans="";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mid);
			//�����ȡ�õ��Ľ��
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()){//�������houseû�л�ȡ
					ans=rs.getString("mname");
				}
			}
			ps.close();//�ر�ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ans.trim();
	}

	public List<Meal> getHotMeals(int statisticMethod) {
		//����һ��List<Meal>����
		List<Meal> meals = new ArrayList<Meal>();
		//sql���
		
		String sql = "select * from meal Order By sales Desc";
		if(statisticMethod==1) sql="select * from meal Order By sales";
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
							m.setCategory(rs.getString("Category"));
							m.setSales(rs.getInt("sales"));
							m.setDiscount(this.getDicountByMid(m.getMid()));
							
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

	public List<Meal> initRecommend(String uid) {
		//����һ��List<Meal>����
		List<Meal> meals = new ArrayList<Meal>();
		//sql���
		String sql = "select top 3 * from meal Order By sales desc";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		String ans="";
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
					m.setCategory(rs.getString("Category"));
					m.setSales(rs.getInt("sales"));
					m.setAddToCacheNumber(this.getAddToCacheNumber(m.getMid(), uid));
					m.setDiscount(this.getDicountByMid(m.getMid()));
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

	public List<Meal> userSearch(String searchContent, String uid) {
		//����һ��List<Meal>����
		List<Meal> meals = new ArrayList<Meal>();
		//sql���
		String sql = "select * from meal where mname like '%"+searchContent+"%'";
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
					m.setCategory(rs.getString("Category"));
					m.setSales(rs.getInt("sales"));
					m.setAddToCacheNumber(this.getAddToCacheNumber(m.getMid(), uid));
					m.setDiscount(this.getDicountByMid(m.getMid()));
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
	//��ȡ��Ʒ�Ĵ�����Ϣ
	public Discount getDiscount(int mid) {
		Discount d=null;
		//sql���
		String sql = "select * from discount where mid=?";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mid);
			//�����ȡ�õ��Ľ��
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()){//�������houseû�л�ȡ
					d=new Discount();
					d.setDid(rs.getInt("did"));
					d.setDescribe(rs.getString("describe"));
					d.setDis(rs.getFloat("discount"));
					d.setMid(mid);
					d.setStarttime(rs.getDate("starttime"));
					d.setEndtime(rs.getDate("endtime"));
				}
			}
			ps.close();//�ر�ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
		return d;
	}

	public boolean addDiscount(Discount discount) {
		String sql="";
		if(isInDiscount(discount.getMid())){
			sql="update Discount set discount=?,describe=?,starttime=?,endtime=? where mid=?";
			boolean isSuc=false;
			 try (
				        PreparedStatement pstmt = con.prepareStatement(sql);) {
				      pstmt.setFloat(1,discount.getDis());
				      pstmt.setInt(5,discount.getMid());
				      pstmt.setString(2,discount.getDescribe());
				      pstmt.setDate(3,discount.getStarttime());
				      pstmt.setDate(4, discount.getEndtime());
				      int row=pstmt.executeUpdate();
				      isSuc=row>0;
				    } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
			 return isSuc;
		}else{
			sql="insert into Discount(discount,mid,describe,starttime,endtime) values(?,?,?,?,?)";
			boolean isSuc=false;
			 try (
				        PreparedStatement pstmt = con.prepareStatement(sql);) {
				      pstmt.setFloat(1,discount.getDis());
				      pstmt.setInt(2,discount.getMid());
				      pstmt.setString(3,discount.getDescribe());
				      pstmt.setDate(4,discount.getStarttime());
				      pstmt.setDate(5, discount.getEndtime());
				      int row=pstmt.executeUpdate();
				      isSuc=row>0;
				    } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				    return isSuc;
		}
		
	}

	private boolean isInDiscount(int mid) {
		String sql="select * from Discount where mid=?";
		boolean isin=false;
		 try (
			      PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setInt(1,mid);
			      ResultSet rs=pstmt.executeQuery();
			     return rs.next();
			      
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return false;
	}

	public float getDicountByMid(int mid) {
		String sql="select * from Discount where mid=? and getdate() > starttime and getdate()<endtime";
		float discount=0;
		 try (
			      PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setInt(1,mid);
			      ResultSet rs=pstmt.executeQuery();
			      if(rs.next()){
			    	  discount = rs.getFloat("discount");
			      }
			      
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return discount;
	}
	
	public List<Meal> getSpecialOffer(String uid) {
		//����һ��List<Meal>����
				List<Meal> meals = new ArrayList<Meal>();
				//sql���
				String sql = "select Discount.*,Meal.* from Discount,Meal  where Discount.mid=Meal.mid and getdate() between Discount.starttime and Discount.endtime ";
				//ʹ��PreparedStatement��SQL���ִ��
				PreparedStatement ps;
				try {
					ps = con.prepareStatement(sql);
				
					//�����ȡ�õ��Ľ��
					try (ResultSet rs = ps.executeQuery();) {
						while(rs.next()){//�������houseû�л�ȡ
							Meal m = getMealByResultSet(rs,uid);
							m.setDiscount(this.getDicountByMid(m.getMid()));
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

	private Meal getMealByResultSet(ResultSet rs,String uid) throws SQLException {
		Meal m = new Meal();
		m.setMid(rs.getInt("mid"));
		m.setMname(rs.getString("mname"));
		m.setPhoto(rs.getString("photo"));
		m.setPrice(rs.getFloat("price"));
		m.setPhoto("images/"+m.getPhoto());
		m.setCategory(rs.getString("Category"));
		m.setSales(rs.getInt("sales"));
		m.setAddToCacheNumber(this.getAddToCacheNumber(m.getMid(), uid));
		m.setDiscount(rs.getFloat("discount"));
		return m;
	}

	public List<Meal> getHotMeals(String uid) {
		//����һ��List<Meal>����
				List<Meal> meals = new ArrayList<Meal>();
				//sql���
				
				String sql = "select * from meal Order By sales Desc";
				
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
									m.setCategory(rs.getString("Category"));
									m.setSales(rs.getInt("sales"));
									m.setDiscount(this.getDicountByMid(m.getMid()));
									m.setAddToCacheNumber(this.getAddToCacheNumber(m.getMid(), uid));
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

	public List<Meal> getLowPriceMeals(String uid) {
		//����һ��List<Meal>����
		List<Meal> meals = new ArrayList<Meal>();
		//sql���
		
		String sql = "select * from meal Order By price";
		
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
							m.setCategory(rs.getString("Category"));
							m.setSales(rs.getInt("sales"));
							m.setDiscount(this.getDicountByMid(m.getMid()));
							m.setAddToCacheNumber(this.getAddToCacheNumber(m.getMid(), uid));
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

	public List<Order> getMealsByPraise(int statisticMethod) {
		//����һ��List<Order>����
				List<Order> orders = new ArrayList<Order>();
				//sql���
				String sql = "select * from [Order]  where grade>0 Order By grade Desc";
				if(statisticMethod==3){
					sql="select * from [Order]  where grade>0 Order By grade";
				}
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
							o.setIntegral(rs.getInt("integral"));
							o.setGrade(rs.getInt("grade"));
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

}
