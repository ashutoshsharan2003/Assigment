package com.capgemini.Ashutosh;


//import java.sql.Connection;


import java.sql.SQLException;
import java.util.Scanner;
public class InternationalSchool {
	
	public static void main(String[] args) throws ClassNotFoundException,SQLException {
		
		
		DataBaseOperations object = new DataBaseOperations();

		Scanner sc = new Scanner(System.in); // new - dynamic memory allocator.
		

		
		while(true) {
		
			System.out.println("Enter 1 for insert a Record");
			System.out.println("Enter 2 for delete a Record");
			System.out.println("Enter 3 for Modification the Record");
			System.out.println("Enter 4 for exiting the menu");
			
			int reply = sc.nextInt();
			
			switch(reply) {
			case 1:
				object.insert_student();
				
				break;
			case 2:
				object.delete_student();
				break;
			case 3:
				object.modify_fees();
				break;
			case 4:
				object.display();
				break;
			case 5:
				break;
			case 6:
				System.out.println("You have been successfully exited");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Input");
					
			}
		}
		
		
		
		
	}

}
