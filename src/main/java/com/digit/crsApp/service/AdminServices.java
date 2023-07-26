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

	private PreparedStatement pstmt;
	public int n;
	Scanner sc = new Scanner(System.in);
	private ResultSet resultset;
	private Statement stmt;

	public void menu() {
		// TODO Auto-generated method stub
		System.out.println("\nSelect Option:");
		System.out.println("1. Add course\n" + "2. Add Student\n" + "3. Add Professor\n" + "4. Remove Course\n"
				+ "5. Remove Professor\n" + "6. Remove Student\n" + "7. View All Students\n" + "8. View All Courses\n"
				+ "9. View All Professors\n" + "10. View All Users\n" + "0. Exit\n");
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
		case 0: {
			System.exit(0);
		}
		default: {
			System.err.println("enter a valid input");
			break;
		}
		}
	}

	private void viewUser() {
		// TODO Auto-generated method stub
		CRSApp.sleep(3000);
		try {
			String sql = "select * from users";
			stmt = CRSApp.con.createStatement();
			 resultset = stmt.executeQuery(sql);
			 System.out.println("User Details below........... ");
			while (resultset.next() == true) {

				System.out.println(resultset.getInt(1));

				System.out.println(resultset.getString(2));

				System.out.println("--------");

			}

		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu();
	}

	public void viewProfessor() {
		// TODO Auto-generated method stub
		CRSApp.sleep(3000);
		try {
			String sql = "select * from professor";
			stmt = CRSApp.con.createStatement();
			 resultset = stmt.executeQuery(sql);
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

	public void viewCourse() {
		// TODO Auto-generated method stub
		CRSApp.sleep(3000);
		try {
			String sql = "select * from course";
			stmt = CRSApp.con.createStatement();
			 resultset = stmt.executeQuery(sql);
			 System.out.println("course Details below........... ");
			while (resultset.next() == true) {

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

	public void viewStudent() {
		// TODO Auto-generated method stub
		CRSApp.sleep(3000);
		try {
			String sql = "select * from student";
			stmt = CRSApp.con.createStatement();
			 resultset = stmt.executeQuery(sql);
			 System.out.println("student Details below........... ");
			while (resultset.next() == true) {

				System.out.println(resultset.getInt("sid"));

				System.out.println(resultset.getString(2));

				System.out.println(resultset.getString(3));

				System.out.println(resultset.getString(4));

				System.out.println(resultset.getString(5));

				System.out.println("--------");

			}

		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu();

	}

	private void removeStudent() {
		// TODO Auto-generated method stub
		try {
			String sql = "delete from student where sid=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("How many student do you want to remove");
			n = sc.nextInt();
			for (int i = 0; i < n; i++) {

				System.out.println("enter student id to be removed");
				pstmt.setInt(1, sc.nextInt());
				int x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println("Student removing");
				}
				else {
					System.out.println("student not removed");
					menu();
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (n == 0) {
			System.out.println((n) + " student removed");
			menu();
		} else {
			CRSApp.sleep(3000);
			System.out.println("\n\t\tStudent removed Successfully...");
			System.out.println("\n****************************************\n");
			menu();
		}

	}

	public void removeProfessor() {
		// TODO Auto-generated method stub
		try {
			String sql = "delete from professor where pid=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("How many professor do you want to remove");
			n = sc.nextInt();
			for (int i = 0; i < n; i++) {

				System.out.println("enter professor id to be removed");
				pstmt.setInt(1, sc.nextInt());
				int x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println("professor removing");
				}
				else {
					System.out.println("professor not removed");
					menu();
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (n == 0) {
			System.out.println((n) + " professor removed");
			menu();
		} else {
			CRSApp.sleep(3000);
			System.out.println("\n\t\tProfesor removed Successfully...");
			System.out.println("\n****************************************\n");
			menu();
		}

	}

	public void removeCourse() {
		// TODO Auto-generated method stub
		try {
			String sql = "delete from course where cid=?";
			pstmt = CRSApp.con.prepareStatement(sql);
			System.out.println("How many course do you want to delete");
			n = sc.nextInt();
			for (int i = 0; i < n; i++) {

				System.out.println("enter  course id to be removed");
				pstmt.setInt(1, sc.nextInt());
				int x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println( "course removing");
				} else {
					System.out.println("course not removed");
					menu();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (n == 0) {
			System.out.println((n) + " course removed");
			menu();
		} else {
			CRSApp.sleep(3000);
			System.out.println("\n\t\tCourse removed Successfully...");
			System.out.println("\n****************************************\n");
			menu();
		}

	}

	public void addProfessor() {
		// TODO Auto-generated method stub
		try {

			// Professor p = new Professor(1, "mudu",5);int pid, String pname, int exp
			int id, exp;
			String pn;
			System.out.println("add professor");
			System.out.println("\nHow many professor do you want to add ?");
			n = sc.nextInt();
			for (int j = 0; j < n; j++) {
				System.out.println("professor " + (j + 1));
				System.out.println("Enter professor id");
				id = sc.nextInt();
				System.out.println("Enter professor name");
				pn = sc.next();
				System.out.println("Enter professor experience");
				exp = sc.nextInt();

				Professor p = new Professor(id, pn, exp);
				String sql = "insert into professor values(?,?,?)";
				pstmt = CRSApp.con.prepareStatement(sql);
				pstmt.setInt(1, p.getPid());
				pstmt.setString(2, p.getPname());
				pstmt.setInt(3, p.getExp());

				int x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println(p.getPname() + " Professor Added------------ :");

				}
				
			}
			CRSApp.sleep(3000);
			System.out.println("\n\t\tProfessor Added Successfully...");
			System.out.println("\n****************************************\n");
			menu();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addCourse() {
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
					System.out.println(c.getCname() + " Course Added---------  :");

				}
			}
			CRSApp.sleep(3000);
			System.out.println("\n\t\tCourse Added Successfully...");
			System.out.println("\n****************************************\n");
			menu();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addStudent() {
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
				System.out.println("Enter student id ");
				id = sc.nextInt();
				System.out.println("Enter student name");
				name = sc.next();
				System.out.println("Enter student email");
				email = sc.next();
				System.out.println("Enter student username");
				username = sc.next();
				System.out.println("Enter student password");
				pswd = sc.next();

				Student s = new Student(id, name, email, username, pswd);
				String sql = "insert into student values(?,?,?,?,?)";
				pstmt = CRSApp.con.prepareStatement(sql);
				pstmt.setInt(1, s.getSid());
				pstmt.setString(2, s.getSname());
				pstmt.setString(3, s.getEmail());
				pstmt.setString(4, s.getUser_name());
				pstmt.setString(5, s.getPassword());

				int x = pstmt.executeUpdate();
				CRSApp.sleep(3000);
				if (x > 0) {
					System.out.println(s.getSname() + " Student Added------------ :");

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
