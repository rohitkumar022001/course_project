package com.digit.crsApp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.crsApp.CRSApp;
import com.digit.crsApp.beans.Course;
import com.digit.crsApp.beans.Professor;
import com.digit.crsApp.beans.Student;

public class AdminServices {
	private static PreparedStatement pstmt;
	public static int n;
	
	private static ResultSet resultset;
	private static Statement stmt;
	private static PreparedStatement pstmt1;
	private static ResultSet resultSet1;
	private static Statement stmt1;

	public static void menu() {
		// TODO Auto-generated method stub
		System.out.println("\nSelect Option:");
		System.out.println("1. Add course\n" + "2. Add Student\n" + "3. Add Professor\n" + "4. Remove Course\n"
				+ "5. Remove Professor\n" + "6. Remove Student\n" + "7. View All Students\n" + "8. View All Courses\n"
				+ "9. View All Professors\n" + "10. View All Users\n" +"11. management\n"+ "0. Exit\n");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice");
		n = sc.nextInt();
		switch (n) {
		case 1: {
			addCourse();
			break;

		}
		case 2: {
			addStudent();
			break;
		}
		case 3: {
			addProfessor();
			break;
		}
		case 4: {
			removeCourse();
			break;
		}
		case 5: {
			removeProfessor();
			break;
		}
		case 6: {
			removeStudent();
			break;
		}
		case 7: {
			viewStudent();
			break;
		}
		case 8: {
			viewCourse();
			break;
		}
		case 9: {
			 viewProfessor();
			break;
		}
		case 10: {
			 viewUser();
			break;
		}
		case 11: {
			CRSApp.manage();
		}
		case 0: {
			System.exit(0);
		}
		
		default: {
			System.err.println("enter a valid input");
			break;
		}
		}
	}

	public static void viewUser() {
		// TODO Auto-generated method stub
		
		try {
			String sql = "select * from users";
			stmt = CRSApp.con.createStatement();
			 resultset = stmt.executeQuery(sql);
			 
			 stmt1 = CRSApp.con.createStatement();
			 resultSet1 =  stmt1.executeQuery(sql);
			 
			 if(!resultSet1.next()) {
				 menu();
				 return;
			 }
			 CRSApp.sleep(3000);
			 System.out.println("User Details below........... ");
			while (resultset.next() == true) {

				System.out.println(resultset.getString(1));

				System.out.println(resultset.getString(2));

				System.out.println("--------");

			}

		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu();
	}

	public static void viewProfessor() {
		// TODO Auto-generated method stub
		
		try {
			String sql = "select * from professor";
			stmt = CRSApp.con.createStatement();
			 resultset = stmt.executeQuery(sql);
			 
			 stmt1 = CRSApp.con.createStatement();
			 resultSet1 =  stmt1.executeQuery(sql);
			 
			 if(!resultSet1.next()) {
				 System.out.println("No Professor Registered . ");
				 menu();
				 return;
			 }
			 CRSApp.sleep(3000);
			 System.out.println("professor Details below........... ");
			while (resultset.next() == true) {

				System.out.println(resultset.getInt("pid"));

				System.out.println(resultset.getString(2));

				System.out.println(resultset.getString(3));


				System.out.println("--------");

			}

		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu();
		
	}

	public static void viewCourse() {
		// TODO Auto-generated method stub
		
		try {
			String sql = "select * from course";
			stmt = CRSApp.con.createStatement();
			stmt1 = CRSApp.con.createStatement();
			 resultSet1 =  stmt1.executeQuery(sql);
			 resultset = stmt.executeQuery(sql);
			
			 if(!resultSet1.next()) {
				 System.out.println("No Course Added . ");
				 menu();
				 return;
			 }
			 CRSApp.sleep(3000);
			 
			 System.out.println("course Details below........... ");
			 while(resultset.next() == true){

				System.out.println(resultset.getInt("cid"));

				System.out.println(resultset.getString(2));

				System.out.println(resultset.getString(3));

				System.out.println(resultset.getString(4));


				System.out.println("--------");

			}

		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu();

		
	}

	public static void viewStudent() {
		// TODO Auto-generated method stub
	
		try {
			String sql = "select * from student";
			stmt = CRSApp.con.createStatement();
			 resultset = stmt.executeQuery(sql);
			 
			 if(!resultset.next()) {
				 System.out.println("NO Student Registered . ");
				 menu();
				 return;
			 }
			 CRSApp.sleep(3000);
			 
			 System.out.println("student Details below........... ");
			while (resultset.next() == true) {

				System.out.println(resultset.getInt("sid"));

				System.out.println(resultset.getString(2));

				System.out.println(resultset.getString(3));

				System.out.println(resultset.getString(4));

	

				System.out.println("--------");

			}

		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu();

	}

	private static void removeStudent() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int i=0;
		try {
			String sql = "delete from student where sid=?";
			
			pstmt = CRSApp.con.prepareStatement(sql);
			String sqls = "select sname from student where sid=?";
			pstmt1 = CRSApp.con.prepareStatement(sqls);
			
			System.out.println("How many student do you want to remove ?");
			n = sc.nextInt();
			
			for (i = 0; i < n; i++) {

				
				System.out.println("Enter student id to be removed");
				
				int y = sc.nextInt();
				pstmt1.setInt(1,y);
				resultSet1 = pstmt1.executeQuery();
				
				String sname ="";
				
				if(resultSet1.next()) {
				sname = resultSet1.getString("sname");
				}
				else {
					break;
				}
				
				pstmt.setInt(1, y);
				int x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println("Removing Studd - "+sname);
				}
				

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (i < n-1 ) {
			System.out.println((n-i)+" Students are not Found  . ");
			menu();
		} else {
			CRSApp.sleep(3000);
			System.out.println("\n\t\tStudent removed Successfully...");
			System.out.println("\n****************************************\n");
			menu();
		}

	}

	public static void removeProfessor() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int i=0;
		try {
			String sql = "delete from professor where pid=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			String sqls = "select pname from professor where pid=?";
			pstmt1 = CRSApp.con.prepareStatement(sqls);
			System.out.println("How many professor do you want to remove ?");
			n = sc.nextInt();
			for (i = 0; i < n; i++) {

				System.out.println("enter professor id to be removed");
				int y = sc.nextInt();
				pstmt1.setInt(1,y);
				resultSet1 = pstmt1.executeQuery();
				
				String pname ="";
				
				if(resultSet1.next()) {
				pname = resultSet1.getString("pname");
				}
				else {
					break;
				}
				
				pstmt.setInt(1, y);
				
				int x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println("removing proff - "+pname);
				}
				

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (i< n-1) {
			System.out.println((n-i) + " professor are not found . ");
			menu();
		} else {
			CRSApp.sleep(3000);
			System.out.println("\n\t\tProfesor removed Successfully...");
			System.out.println("\n****************************************\n");
			menu();
		}

	}

	public static void removeCourse() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int i=0;
		try {
			String sql = "delete from course where cid=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			String sqls = "select cname from course where cid=?";
			pstmt1 = CRSApp.con.prepareStatement(sqls);
			System.out.println("How many course do you want to delete ?");
			n = sc.nextInt();
			for (i = 0; i < n; i++) {

				System.out.println("Enter  course id to be removed");
				
				int y = sc.nextInt();
				pstmt1.setInt(1,y);
				resultSet1 = pstmt1.executeQuery();
				
				String cname ="";
				
				if(resultSet1.next()) {
				cname = resultSet1.getString("cname");
				}
				else {
					break;
				}
				
				pstmt.setInt(1, y);
				
				int x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println( "removing course - "+cname);
				} 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (i<n-1) {
			System.out.println((n-i) + " course not found . ");
			menu();
		} else {
			CRSApp.sleep(3000);
			System.out.println("\n\t\tCourse removed Successfully...");
			System.out.println("\n****************************************\n");
			menu();
		}

	}

	public static void addProfessor() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		try {

			// Professor p = new Professor(1, "mudu",5);int pid, String pname, int exp
			int id, exp;
			String pn;
			System.out.println("add professor");
			System.out.println("\nHow many professor do you want to add ?");
			n = sc.nextInt();
			for (int j = 0; j < n; j++) {
				System.out.println("Registering professor No :  " + (j + 1));
				Professor.Register();
				
				System.out.println("Enter professor id");
				id = sc.nextInt();
				System.out.println("Enter the Course ID to which the Professor will Teach : ");
				int cid = sc.nextInt();
				System.out.println("Enter professor name");
				pn = sc.next();
				System.out.println("Enter professor experience");
				exp = sc.nextInt();

				Professor p = new Professor(id,cid ,pn, exp);
				String sql = "insert into professor values(?,?,?,?)";
				pstmt = CRSApp.con.prepareStatement(sql);
				pstmt.setInt(1, p.getPid());
				pstmt.setInt(2,cid);
				pstmt.setString(3, p.getPname());
				pstmt.setInt(4, p.getExp());

				int x = pstmt.executeUpdate();
				if (x > 0) {
					CRSApp.sleep(3000);
					System.out.println(p.getPname() + " Professor Added------------ :");

				}
				
			}
			
			System.out.println("\n\t\tProfessor Added Successfully...");
			System.out.println("\n****************************************\n");
			menu();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void addCourse() {
		try {

			// Course c = new Course(1, "java", 4000, 3);
			int id, fee, dur, x;
			String cn;
			Scanner sc = new Scanner(System.in);
			System.out.println("add course");
			System.out.println("\nHow many courses do you want to add ?");
			n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				System.out.println("Course " + (i + 1));
				System.out.println("\nEnter course id");
				id = sc.nextInt();
				System.out.println("Enter course name");
				cn = sc.next();
				System.out.println("Enter course fee");
				fee = sc.nextInt();
				System.out.println("Enter course duration");
				dur = sc.nextInt();
				Course c = new Course(id, cn, fee, dur);
				String sql = "insert into course values(?,?,?,?)";
				pstmt = CRSApp.con.prepareStatement(sql);
				pstmt.setInt(1, c.getCid());
				pstmt.setString(2, c.getCname());
				pstmt.setInt(3, c.getFees());
				pstmt.setInt(4, c.getDur_months());
				
				x = pstmt.executeUpdate();
				if (x > 0) {
					CRSApp.sleep(3000);
					System.out.println(c.getCname() + "\n Course Added---------  :");

				}
			}
			
			System.out.println("\n\t\tCourse Added Successfully...");
			System.out.println("\n****************************************\n");
			menu();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addStudent() {
		try {

			// Student s = new Student(1, "rohit", "rohit@gmail.com,
			// "rohit2001","rohit@2001");int sid, String sname, String email, String
			// user_name,String password
			int id;
			String name, email, username, pswd;
			Scanner sc = new Scanner(System.in);
			System.out.println("add student");
			System.out.println("\nHow many student do you want to register ?");
			n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				System.out.println("Student " + (i + 1));
				Student.Register();
				
				System.out.println("Enter student id ");
				id = sc.nextInt();
				System.out.println("Enter Course id the Studnet want to Enroll : ");
				int cid = sc.nextInt();
				System.out.println("Enter student name");
				name = sc.next();
				System.out.println("Enter student email");
				email = sc.next();
				

				Student s = new Student(id, cid,name, email);
				String sql = "insert into student values(?,?,?,?)";
				pstmt = CRSApp.con.prepareStatement(sql);
				pstmt.setInt(1, s.getSid());
				pstmt.setInt(2,cid);
				pstmt.setString(3, s.getSname());
				pstmt.setString(4, s.getEmail());
				

				int x = pstmt.executeUpdate();
				
				if (x > 0) {
					CRSApp.sleep(3000);
					System.out.println(s.getSname() + "  Added------------ :");

				}
			}
			System.out.println("\n\t\tStudent Added Successfully...");
			System.out.println("\n****************************************\n");
			menu();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
