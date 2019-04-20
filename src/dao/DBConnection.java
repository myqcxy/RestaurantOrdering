package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
	public static Connection getConnect() {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=RestaurantOrdering";
		String userName = "sa"; // 默认用户名
		String userPwd = "123456";
		try {
			Class.forName(driverName);
			return DriverManager.getConnection(dbURL, userName, userPwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void colse(Connection conn) {
		
			try {
				if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
