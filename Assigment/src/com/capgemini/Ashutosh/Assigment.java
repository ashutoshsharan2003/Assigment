package com.capgemini.Ashutosh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Assigment {
	
	public static void main(String[] args) throws ClassNotFoundException,SQLException {
		
		
// Connect to Database.
		
		Connection con = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assigment","root","PHW#84#jeor");
		System.out.println("Connection Established");
		
		
		String executingQuery = "SELECT * FROM emp";
		
		Statement st = con.createStatement();
		
		ResultSet rt = st.executeQuery(executingQuery);
		
		while(rt.next()) {
			int empno = rt.getInt(1);
			String ename = rt.getString("ename");
			System.out.println("The empno is : " + empno + " And name is : "+ ename);
		}
		rt.close();
		st.close();
		con.close();
		
		
		
		

	
	}

}