package com.SportsShopApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
public static Connection getDbConnection() throws ClassNotFoundException, SQLException	{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		    
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
		  
		return con; 
		
		}
}