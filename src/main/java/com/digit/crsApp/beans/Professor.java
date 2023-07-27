package com.digit.crsApp.beans;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.crsApp.CRSApp;

public class Professor {
	private static PreparedStatement pstmt;
	int pid;
	static String pname;
	int exp;
	static String  user_name;
	static String password;
	private static Object resultset;
	private static Statement stmt;
	public static void Register() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Kindly register yourself");
		System.out.println("\nEnter user name");
		user_name=sc.next();
		System.out.println("Set password");
		password=sc.next();
		String sql = "insert into users values(?,?)";
		try {
		pstmt = CRSApp.con.prepareStatement(sql);
		
			pstmt.setString(1, getUser_name());
			
		pstmt.setString(2, getPassword());

		int x = pstmt.executeUpdate();
		if (x > 0) {
			System.out.println("Professor Registered------------ :");

		}
		
		String sql1 = "insert into prequest values(?,?)";
		
			
		pstmt = CRSApp.con.prepareStatement(sql1);
		 
		
			pstmt.setString(1, getUser_name());
			
		pstmt.setString(2, getPassword());

		int x1 = pstmt.executeUpdate();
		if (x1 > 0) {
			System.out.println("Request login activated:");
			CRSApp.manage();

		}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public Professor(int pid, String pname, int exp) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.exp = exp;
	}
	/**
	 * @return the user_name
	 */
	public static String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public static void setUser_name(String user_name) {
		Professor.user_name = user_name;
	}
	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public static void setPassword(String password) {
		Professor.password = password;
	}
	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}
	/**
	 * @return the pname
	 */
	public static String getPname() {
		return pname;
	}
	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}
	/**
	 * @param exp the exp to set
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	
}
