package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Meal;
import model.Table;

public class TableDao {
	Connection con = DBConnection.getConnect();

	public boolean addTable(Table table) {
		String sql="insert into [Table](number,describe) values(?,?)";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
					 pstmt.setInt(1, table.getNumber());
					 pstmt.setString(2, table.getDescribe());
				      
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			    return isSuc;
	}

	public List<Table> getAllTables() {
		//����һ��List<Table>����
		List<Table> tables = new ArrayList<Table>();
		//sql���
		String sql = "select * from [Table] where used!=3";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			//�����ȡ�õ��Ľ��
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()){//�������houseû�л�ȡ

					Table t = new Table();
					t.setTid(rs.getInt("tid"));
					t.setNumber(rs.getInt("number"));
					t.setDescribe(rs.getString("describe"));
					t.setUsed(rs.getInt("used"));
					
					tables.add(t);
				}
			}
			ps.close();//�ر�ps
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tables;
	}

	public Table getTableById(int tid) {
		//����һ��List<Table>����
				Table t = new Table();
				//sql���
				String sql = "select * from [Table] where tid=?";
				//ʹ��PreparedStatement��SQL���ִ��
				PreparedStatement ps;
				try {
					ps = con.prepareStatement(sql);
					ps.setInt(1, tid);
					//�����ȡ�õ��Ľ��
					try (ResultSet rs = ps.executeQuery();) {
						while(rs.next()){//�������houseû�л�ȡ

						
							t.setTid(rs.getInt("tid"));
							t.setNumber(rs.getInt("number"));
							t.setUsed(rs.getInt("used"));
							t.setDescribe(rs.getString("describe"));
					
						}
					}
					ps.close();//�ر�ps
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return t;
	}

	public boolean updateTable(Table table) {
		String sql="update [Table] set number=?,describe=? where tid=?";
		boolean isSuc=false;
		 try (
			        PreparedStatement pstmt = con.prepareStatement(sql);) {
			      pstmt.setInt(1,table.getNumber());
			      pstmt.setString(2,table.getDescribe());
			     
				      
			      pstmt.setInt(3,table.getTid());
			      int row=pstmt.executeUpdate();
			      isSuc=row>0;
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		 return isSuc;

	}

	public int findTable(int number) {
		int tid=-1;
		//sql���
		String sql = "select * from [Table] where used=0 and number>=?";
		//ʹ��PreparedStatement��SQL���ִ��
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, number);
			try (ResultSet rs = ps.executeQuery();) {
				if(rs.next()){
					tid=rs.getInt("tid");

				}
			}
			ps.close();//�ر�ps
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tid;
	}

	public boolean delTable(int tid) {
		boolean isSuc=false;
	    String sql = "update [Table] set used=3 where tid=?";
	    try (
	        PreparedStatement pstmt = con.prepareStatement(sql);) {
	      pstmt.setInt(1,tid);
	      int row=pstmt.executeUpdate();
	      isSuc=row>0;
	    } catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	    return isSuc;
	}

	
}
