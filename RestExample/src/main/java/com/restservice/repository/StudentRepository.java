package com.restservice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.restservice.pojo.Student;

public class StudentRepository {
	
	ArrayList<Student> students = new ArrayList<Student>();
	Connection con;
	
//	public StudentRepository()
//	{
//		Student s1 = new Student();
//		s1.setStudentId(108);
//		s1.setStudentName("Seetha");
//		Student s2 = new Student();
//		s2.setStudentId(182);
//		s2.setStudentName("Ram");
//		students.add(s1);
//		students.add(s2);
//	}
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		String url = "jdbc:mysql://localhost:3306/student";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "root", "root");
		return con;
	}
	public ArrayList<Student> getStudents() throws ClassNotFoundException, SQLException
	{
		String query = "select * from student";
		con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			Student s = new Student();
			s.setStudentId(rs.getInt("studentId"));
			s.setStudentName(rs.getString("studentName"));
			students.add(s);
		}
		
		return students;	
	}
	public Student getStudent(int id) throws ClassNotFoundException, SQLException
	{
		Student s1 = new Student();
		String query = "select * from student where studentid="+id;
		con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			s1.setStudentId(rs.getInt("studentId"));
			s1.setStudentName(rs.getString("studentName"));
		}
		
		return s1;
	}
	public void insertStudent(Student s) throws ClassNotFoundException, SQLException
	{
		String query = "insert into student values(?,?)";
		con = getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, s.getStudentId());
		ps.setString(2, s.getStudentName());
		int i = ps.executeUpdate();
		System.out.println(i + " rows inserted");
		ps.close();
		con.close();
	}
	public void updateStudent(Student s) throws ClassNotFoundException, SQLException
	{
		String query = "update student set studentName=? where studentId=?";
		con = getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, s.getStudentName());
		ps.setInt(2, s.getStudentId());
		int i = ps.executeUpdate();
		System.out.println(i + " rows updated");
		ps.close();
		con.close();
	}
	public void deleteStudent(int id) throws ClassNotFoundException, SQLException
	{
		String query = "delete from student where studentId="+id;
		con = getConnection();
		Statement st = con.createStatement();
		int i= st.executeUpdate(query);
		System.out.println(i + " rows deleted");
		st.close();
		con.close();
	}

}
