package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BD {
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String usuario = "JD026";
	private static String clave = "daniel123";
	private static String driver = "oracle.jdbc.OracleDriver";
	
	public static void Close(ResultSet rs) throws SQLException{
		rs.close();
	}
	
	public static void Close(PreparedStatement ps) throws SQLException{
		ps.close();
	}
	
	public static void Close(Connection cn) throws SQLException {
		cn.close();
	}
	public static Connection getConnection() throws SQLException{
		try {
			Connection cn = DriverManager.getConnection(url,usuario,clave);
			return cn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}
	
	
	
}
