package com.digit.crsApp.service;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.crsApp.CRSApp;
import com.digit.crsApp.beans.Professor;
import com.digit.crsApp.beans.Student;

public class ProfessorServices {
	static int sid;
	static int marks;
	static String grade;
	static String check;
	private static PreparedStatement pstmt;
	private static ResultSet resultset;
	private static Statement stmt;
	static String user_name;
	static String password;
	
	public static void login() {
		Scanner sc=new Scanner(System.in);
		int temp=0;
		System.out.println("please Login");
		
		try {
			
			 while(true) {
				 String sql = "select * from prequest";
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
					grade_marks();
					break;
					
				}
				
	
			}
			if(temp==3) {
				System.out.println("User name not found");
				CRSApp.manage();
				break;
				
			}
			else {
				System.out.println("Re-enter your login details");
			}
			
			
			
			 }

		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void grade_marks(){
		
		try {

			// Professor p = new Professor(1, "mudu",5);int pid, String pname, int exp
			Scanner sc = new Scanner(System.in);
			System.out.println("add student marks");
			System.out.println("Enter student id");
			sid = sc.nextInt();
			System.out.println("Enter student marks");
			marks = sc.nextInt();
			System.out.println("students grade");
			grade=getGrade();
			System.out.println(grade);
			
				ProfessorServices ps = new ProfessorServices(sid, marks,grade);
				String sql = "insert into marks values(?,?,?)";
				pstmt = CRSApp.con.prepareStatement(sql);
				pstmt.setInt(1, ps.getSid());
				pstmt.setInt(2, ps.getMarks());
				pstmt.setString(3, ps.getGrade());
				

				int x = pstmt.executeUpdate();
				if (x > 0) {
					String sql1="select Sname from student where sid=?";
					pstmt = CRSApp.con.prepareStatement(sql1);
					pstmt.setInt(1, ps.getSid());
					resultset=pstmt.executeQuery();
					while(resultset.next()==true) {
						System.out.println( resultset.getString("sname")+"marks Added------------ :");
					}

				}
				
			
			CRSApp.sleep(3000);
			System.out.println("\n\t\tstudent marks Added Successfully...");
			System.out.println("\n****************************************\n");
			System.out.println("Do you want to grade more student : y/n");
			check=sc.next();
			if(check.equals("y")) {
				grade_marks();
			}
			else if(check.equals("n")){
				System.out.println("Do you want to go the management : y/n");
				check=sc.next();
				if(check.equals("y")) {
					CRSApp.manage();
				}
				else if(check.equals("n")){
					System.out.println("\ngo to menu");
					AdminServices.menu();
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ProfessorServices(int sid, int marks,String grade) {
		super();
		this.sid = sid;
		this.marks = marks;
		this.grade=grade;
	}
	/**
	 * @return the grade
	 */
	public static String getGrade() {
		if(marks>=90) {
			return "O";
		}
		else if(marks>=70 && marks<90) {
			return "E";
		}
		else if(marks>=50 && marks<70) {
			 return "A";
		}
		return "F";
			
		
		
	}
	/**
	 * @param grade the grade to set
	 */
	public static void setGrade(String grade) {
		
	}
	/**
	 * @return the sid
	 */
	public int getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}
	/**
	 * @return the marks
	 */
	public int getMarks() {
		return marks;
	}
	/**
	 * @param marks the marks to set
	 */
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	
}
