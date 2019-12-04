package com.restservice.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.restservice.pojo.Student;
import com.restservice.repository.StudentRepository;

@Path("/student")
public class StudentResource {
	
	StudentRepository repo = new StudentRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Path("/getstudents")
	public ArrayList<Student> getStudentDetails() throws ClassNotFoundException, SQLException
	{
		ArrayList<Student> list = new ArrayList<Student>();
		System.out.println("In getStudentDetails method");
		list = repo.getStudents();
		return list;
	}
	
	@GET
	@Path("/getstudent/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Student getStudent(@PathParam("id") int id) throws ClassNotFoundException, SQLException
	{
		System.out.println("In getstudent method");
		return repo.getStudent(id);
	}
	
	@POST
	@Path("create")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Student createStudent(Student s) throws ClassNotFoundException, SQLException
	{
		System.out.println("In createStudent");
		repo.insertStudent(s);
		return s;
	}
	
	@PUT
	@Path("/updatestudent")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Student updateStudent(Student s) throws ClassNotFoundException, SQLException 
	{
		if(repo.getStudent(s.getStudentId()).getStudentId()==0)
		{
			repo.insertStudent(s);
		}
		else
			repo.updateStudent(s);
		
		return s;
	}
	
	@DELETE
	@Path("/deletestudent/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Student deleteStudent(@PathParam("id") int id) throws ClassNotFoundException, SQLException
	{
		Student s = repo.getStudent(id);
		repo.deleteStudent(s.getStudentId());
		return s;
	}

}
