package com.crsApp;

import java.sql.Connection;
import com.digit.crsApp.service.ProfessorServices;
import java.sql.DriverManager;
import java.util.Scanner;

import com.digit.crsApp.beans.Professor;
import com.digit.crsApp.beans.Student;
import com.digit.crsApp.beans.Users;
import com.digit.crsApp.service.AdminServices;
import com.digit.crsApp.service.StudentServices;

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
	public static void admin() {
		int temp=0;
		System.out.println("Admin Login");
		while(true) {
			temp++;
			boolean b = Users.login();
			sleep(1000);
			if(b==true) {
				System.out.println("Login Successful...\n");
				System.out.println("------------------------------------");
				
				manage();
				break;
			}
			if(temp==3) {
				System.out.println("admin not found");
				break;
				
			}
			else {
				System.err.println("Invalid......\n");
				System.out.println("Enter again");
				
			}
			
			}
	}
	public static void manage(){
		System.out.println("Select the Type of User:");
		System.out.println("1. Professor service\n"
				+ "2. Student service\n"
				+ "3. Register Professor\n"
				+ "4. Register Student\n"
				+"5. Menu");
		System.out.println("Enter number jo aapko karna hai");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		
		case 1:{
			ProfessorServices.login();
			break;
		}
		case 2:{
			StudentServices.login();
			break;
		}
		case 3:{
			Professor.Register();
			break;
		}
		case 4:{
			Student.Register();
			break;
		}
		case 5:{
			AdminServices adsrv = new AdminServices();
			adsrv.menu();
			break;
		}
		}
	}
	public static void main(String[] args) throws Exception{

		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
		String url = "jdbc:mysql://localhost:3306/courses";

		String user = "root";
		String pwd  = "Welcome@123";
		// Step:2
		con = DriverManager.getConnection(url, user, pwd);
		admin();
		
		
	}
}
