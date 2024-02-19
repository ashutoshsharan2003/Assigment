package com.capgemini.Ashutosh;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class DataBaseOperations {
	
	Scanner sc = new Scanner(System.in);
	Scanner scs = new Scanner(System.in);
	ConnectionClass connectionObject = new ConnectionClass();
	Connection con = null;
	
	public void insert_student() throws ClassNotFoundException, SQLException {
		
	
		
		System.out.print("Enter Student Rollno (4 digits) : ");
		int studentRollno = sc.nextInt();
		System.out.print("Enter Student Name :");
		String studentName = scs.nextLine();
		System.out.print("Enter Student Standard (I,II,III...X : ");
		String studentStandard = scs.nextLine();
		System.out.print("Enter Date of Birth of Student (YYYY-MM-DD) ");
		String DOB = scs.nextLine();
		System.out.print("Enter Student Fees");
		float studentFees = sc.nextFloat();
		
		if(con == null) {
			con = connectionObject.getConnection();
			
			
			String insertQuery = "INSERT INTO student_details VALUES(?,?,?,?,?)";
			
			
			
			PreparedStatement psmt = con.prepareStatement(insertQuery);
			psmt.setInt(1, studentRollno);
			psmt.setString(2, studentName.toUpperCase());
			psmt.setString(3, studentStandard);
			psmt.setString(4, DOB);
			psmt.setFloat(5, studentFees);
			
			int status = psmt.executeUpdate(); // select - > execute Query, (display) non select -> execute update. (deletion,updation,insertion)
			
			if(status > 0) {
				System.out.println("Record inserted successfully:"+ status);
			}else {
				System.out.println("Record Can't be inserted");
				
			}
		
			
			
		}
		
	}
	
	public void delete_student() throws ClassNotFoundException, SQLException {
		System.out.println("Enter Student Rollno :");
		int studentRollno = sc.nextInt();
		
		Connection con = connectionObject.getConnection();
		
		String searchingString = "select * from student_details where Rollno=? ";
		PreparedStatement psmt2 = con.prepareStatement(searchingString);
		psmt2.setInt(1,studentRollno);
		ResultSet rs = psmt2.executeQuery();
		
		
		
		if(rs.next()) {
			
			int sRollno = rs.getInt(1);
			String sName = rs.getString(2);
			String sStandard = rs.getString(3);
			String sDOB = rs.getString(4);
			float sFees = rs.getFloat(5);
			System.out.println("The Student Details are as follows : ");
			System.out.println("Student Rollno : "+sRollno);
			System.out.println("Student Name: "+sName);
			System.out.println("Student Standard: "+ sStandard);
			System.out.println("Student Date_of_Birth: "+sDOB);
			System.out.println("Student Batch: "+sFees);
			
			
			System.out.println("Do you want to delete this Record ");
			String option = scs.next();
			option = option.toUpperCase();
			
			
			if(option.equals("Y")) {
				
				String deleteQuery = "DELETE FROM student_details WHERE Rollno="+rs.getString(1);
				
				PreparedStatement psmt3 = con.prepareStatement(deleteQuery);
				if(InsertIntoStudentLog(sRollno,sName,sStandard)) {
					
					int status2 = psmt3.executeUpdate();
					if(status2 > 0) {
						System.out.println("Data is successfully deleted");
					}else {
						System.out.println("Data is not successfully deleted");
					}
					
				}
				
				
				
			}else {
				System.out.println("Data will not be deleted");
			}
			
		}else {
			System.out.println("NO Record Found");
		}
		
	}
	
	public void modify_fees() throws SQLException, ClassNotFoundException {
		con = connectionObject.getConnection();
		System.out.println("Enter Student Roolno ");
		int studentRollno = sc.nextInt();
		String searchQuery = "SELECT * FROM student_details WHERE Rollno=?";
		PreparedStatement psmt4 = con.prepareStatement(searchQuery); // checked exception.
		
		
		psmt4.setInt(1,studentRollno);
		
		ResultSet rs = psmt4.executeQuery();
		
		if(rs.next()) {
			System.out.println("Student rollno: "+rs.getString(1));
			System.out.println("Student Name: "+rs.getString(2));
			System.out.println("Student Standard: "+rs.getString(3));
			System.out.println("Student Date of Birth: "+rs.getString(4));
			System.out.println("Student Fees is : "+rs.getFloat(5));
			
			
			System.out.println("Enter Fees Info to Update");
			Float updatedFees = sc.nextFloat();
			
			
			String updateQuery = "UPDATE student_details set Fees=? WHERE Rollno=?";
			
			PreparedStatement psmt5 = con.prepareStatement(updateQuery);
			
			psmt5.setFloat(1, updatedFees);
			psmt5.setInt(2, studentRollno);
			
			int status = psmt5.executeUpdate();
			
			if(status>0) {
				System.out.println("Fees Updated successfully");
			}
			
		}else {
			System.out.println("No record found");
		}
	}
	public void display() throws ClassNotFoundException, SQLException {
		con = connectionObject.getConnection();
		
		String searchQuery = "SELECT * FROM student_details";
		PreparedStatement psmt4 = con.prepareStatement(searchQuery); // checked exception.
		
		
		
		ResultSet rs = psmt4.executeQuery();
		
		if(rs.next()) {
			System.out.println("Student Rollno: "+rs.getInt(1));
			System.out.println("Student Name: "+rs.getString(2));
			System.out.println("Student Standard: "+rs.getString(3));
			System.out.println("Student Date of Birth: "+rs.getString(4));
			System.out.println("Student Fees: "+rs.getFloat(5));
		}
		
	}
	
	private boolean InsertIntoStudentLog(int student_rollno, String student_name, String student_standard) throws ClassNotFoundException, SQLException {
		
		
		if(con == null) {
			con = connectionObject.getConnection();
		}
		
		String insertQuery = "INSERT INTO studentLog VALUES(?,?,?,?)";
		
		String student_leave = (LocalDate.now()).toString();
		
		
		
		PreparedStatement psmt7 = con.prepareStatement(insertQuery);
		psmt7.setInt(1, student_rollno);
		psmt7.setString(2, student_name);
		psmt7.setString(3, student_standard);
		psmt7.setString(4, student_leave);
		
		int status3 = psmt7.executeUpdate();
		
		if(status3>0) {
			System.out.println("Data is successfully Moved to Student Log Section");
			return true;
		}else {
			System.out.println("Data can't be moved to studentLog section");
			return false;
		}
		
	}

}