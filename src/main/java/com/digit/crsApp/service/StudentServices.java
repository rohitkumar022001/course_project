package com.digit.crsApp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.crsApp.CRSApp;

public class StudentServices {
	 static int sid;
	private static PreparedStatement pstmt;
    static String check;
	private static ResultSet resultset;
	static String user_name;
	static String password;
	private static Statement stmt;
	public static void login() {
		Scanner sc=new Scanner(System.in);
		int temp=0;
		System.out.println("please Login");
		
		try {
			 while(true) {
				 String sql = "select * from srequest";
					stmt = CRSApp.con.createStatement();
					 resultset = stmt.executeQuery(sql);
					 temp++;
					 System.out.println("\nEnter registerd user name");
						user_name=sc.next();
						System.out.println("Enter password");
						password=sc.next();
			while (resultset.next() == true) {
				
				if(user_name.equals(resultset.getString("user_name")) 
						&& password.equals(resultset.getString("password"))) {
					System.out.println("Login successful..........");
					show_marks();
					break;
					
				}
				
	
			}
			if(temp==3) {
				System.err.println("User name not found");
				CRSApp.manage();
				break;
				
			}
			else {
				System.err.println("Re-enter your login details");
			}
			
			
			
			 }

		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public StudentServices(int sid) {
		this.sid = sid;
		
	}
	/**
	 * @return the sid
	 */
	public static int getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	
	public static void show_marks() {
		Scanner sc=new Scanner(System.in);
		try {
		System.out.println("Enter student id");
		sid = sc.nextInt();
		StudentServices ps = new StudentServices(sid);
		String sql1="select Sname from student where sid=?";
		pstmt = CRSApp.con.prepareStatement(sql1);
		pstmt.setInt(1, ps.getSid());
		resultset=pstmt.executeQuery();
		while(resultset.next()==true) {
			System.out.println("Name of the student....");
			System.out.println( resultset.getString("sname"));
		}
		String sql2="select * from marks where sid=?";
		pstmt = CRSApp.con.prepareStatement(sql2);
		pstmt.setInt(1, ps.getSid());
		resultset=pstmt.executeQuery();
		
		
		while(resultset.next()==true) {
			System.out.println("Marks of the student....");
			System.out.println( resultset.getInt("marks"));
			System.out.println("Grade of the student");
			System.out.println( resultset.getString("grade"));
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		AdminServices.menu();
	}

}
