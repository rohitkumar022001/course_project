package com.digit.crsApp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;


import com.crsApp.CRSApp;

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
	private static Statement stmt2;
	private static ResultSet resultset1;
	private static ResultSet resultsetn;
	
	
	public static void login() {
		Scanner sc=new Scanner(System.in);
		int temp=0;
		System.out.println("please Login");
		
		try {
			boolean flag=true;
			
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
					CRSApp.sleep(3000);
					System.out.println("Login successful..........");
					flag = grade_marks();
					break;
					
				}
				
	
			}
			
			if(!flag) {
				CRSApp.manage();
				break;
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
	public static boolean grade_marks(){
		
		try {

            // creating id-->course

            HashMap<Integer,String> map = new HashMap<>();

            // id -> professor
            HashMap<Integer,String> map2 = new HashMap<>();

            

            String sql1 = "select cid,cname from course";

            String sqlpr = "select c_id,pname from professor";

            

            stmt = CRSApp.con.createStatement();

            stmt2 = CRSApp.con.createStatement();

            

            resultset = stmt.executeQuery(sql1);

 

            resultset1 = stmt2.executeQuery(sqlpr);

            

            

            while (resultset1.next() == true) {

                int cid = resultset1.getInt("c_id");

                String pname = resultset1.getString("pname");

                map2.put(cid, pname);

            }

            

            while (resultset.next() == true) {

                int cid = resultset.getInt("cid");

                String cname = resultset.getString("cname");

                map.put(cid, cname);

 

            }

            

            

// finished

            

            Scanner sc = new Scanner(System.in);

            System.out.println("add student marks");

 

            System.out.println("Enter student id");

 

            sid = sc.nextInt();

  // start
            
            String sql4 = "select sname from student where sid=? ";
            
            pstmt = CRSApp.con.prepareStatement(sql4);
            pstmt.setInt(1, sid);
            
            resultsetn = pstmt.executeQuery();
            if(!resultsetn.next()) {
            	System.out.println("Id - "+sid+" is not Present in The DATABASE . ");
            	return false;
            }
            
            //end

 

            System.out.println("Enter student marks");

 

            marks = sc.nextInt();

System.out.println("student grade");

            grade = getGrade();

            System.out.println(grade);

 

            ProfessorServices ps = new ProfessorServices(sid, marks, grade);

 

            String sql2 = "insert into marks values(?,?,?)";

 

            pstmt = CRSApp.con.prepareStatement(sql2);

 

            pstmt.setInt(1, ps.getSid());

 

            pstmt.setInt(2, ps.getMarks());

            pstmt.setString(3, ps.getGrade());

 

            int x = pstmt.executeUpdate();

 

            if (x > 0) {

 

                String sql3 = "select Sname,cid from student where sid=?";

 

                pstmt = CRSApp.con.prepareStatement(sql3);

 

                pstmt.setInt(1, ps.getSid());

 

                resultset = pstmt.executeQuery();

 

                while (resultset.next() == true) {

                    System.out.println("Professor "+map2.get(resultset.getInt("cid"))+" is Grading.....\n");
                    CRSApp.sleep(3000);

                    System.out.println(resultset.getString("sname") + " marks Added For Course "+map.get(resultset.getInt("cid"))+"------------ :");

 

                }

 

            }

 

            System.out.println("\n\t\tstudent marks Added Successfully...");

 

            System.out.println("\n**********************\n");

 

            System.out.println("Do you want to grade more student : y/n");

 

            check = sc.next();

 

            if (check.equals("y")) {

 

                grade_marks();

 

            }

 

            else if (check.equals("n")) {

 

                System.out.println("Do you want to go the management : y/n");

 

                check = sc.next();

 

                if (check.equals("y")) {

 

                    CRSApp.manage();

 

                }

 

                else if (check.equals("n")) {

                    System.out.println("Look into the menu");

 

                    AdminServices.menu();

 

                }

 

            }

 

        } catch (Exception e) {

 

            e.printStackTrace();

 

        }
		return true;
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
