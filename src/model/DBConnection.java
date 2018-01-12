package model;

import java.sql.*;
import javax.swing.*;

public class DBConnection {
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	
	//For local machine
	//static final String DB_URL = "jdbc:mysql://localhost:3306/ShopSystem?useSSL=true";
	
	//For amazon rds database, success! Yes:)
	static final String DB_URL = "jdbc:mysql://sms.ccsde8goiljn.ap-southeast-2.rds.amazonaws.com:3306/shopsystem?";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "51705691";
	
	public static  Connection getconn()
	{
		Connection conn = null;
		
		try
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			//JOptionPane.showMessageDialog(null, "Connection Successful!");
		}	
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Connection SQL Exception!");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Class Not Found!");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void queryClose(PreparedStatement pstmt, ResultSet rs, Connection conn)
	{
		try
		{
			if (rs != null )
			{
				rs.close();
			}
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		
		try
		{
			if (pstmt != null)
			{
				pstmt.close();
			}
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
				
		try
		{
			if (conn != null)
			{
				conn.close();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void addClose(PreparedStatement pstmt, Connection conn)
	{
		try
		{
			if (pstmt != null)
			{
				pstmt.close();
			}
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		try
		{
			if (conn != null)
			{
				conn.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
