package dao;

import java.sql.Connection;

public class Dao {
	public Connection con = DBConnection.getConnect();
}
