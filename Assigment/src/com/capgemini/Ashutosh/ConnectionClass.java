package com.capgemini.Ashutosh;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
	
	public Connection getConnection() throws ClassNotFoundException,SQLException {
		
		
// Connect to Database.
		
		Connection con = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assigment","root","PHW#84#jeor");
		System.out.println("Connection Established");
		


		return con;
	}

}
