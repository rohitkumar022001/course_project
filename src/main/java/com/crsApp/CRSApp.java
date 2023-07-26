package com.crsApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import com.digit.crsApp.beans.Users;
import com.digit.crsApp.service.AdminServices;

public class CRSApp {
	public static Connection con;
	public static void sleep(int val) {
		try {
			System.out.println("Please Wait....\n");
			Thread.sleep(val);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception{
		System.out.println("Select the Type of User:");
		System.out.println("1. Admin\n"
				+ "2. Professor\n"
				+ "3. Student\n");
		System.out.println("Enter number jo aapko karna hai");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
		String url = "jdbc:mysql://localhost:3306/courses";

		String user = "root";
		String pwd  = "Welcome@123";
		// Step:2
		con = DriverManager.getConnection(url, user, pwd);
		
		switch (n) {
		case 1: {
			while(true) {
			boolean b = Users.login();
			sleep(3000);
			if(b==true) {
				System.out.println("Login Successful...\n");
				System.out.println("------------------------------------");
				AdminServices adsrv = new AdminServices();
				adsrv.menu();
				break;
			}
			else {
				System.err.println("Invalid......\n");
				System.out.println("Enter again");
				
			}
			}
		}
		default:
		}
	}
}
