package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HospitalManagement
 */
public class HospitalManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HospitalManagement() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String Doctor_ID=request.getParameter("Doctor_ID");
		String Doctor_Name=request.getParameter("Doctor_Name");
		String sex=request.getParameter("sex");
		int salary=Integer.parseInt(request.getParameter("salary"));
		String address=request.getParameter("address");
		String contact_number=request.getParameter("contact_number");
		String history=request.getParameter("history");
		String qualification=request.getParameter("qualification");
		
//		out.println(Doctor_ID+"  ");
//		out.println(Doctor_Name+"  ");
//		out.println(sex+"  ");
//		out.println(salary+"  ");
//		out.println(address+"  ");
//		out.println(contact_number+"  ");
//		out.println(history+"  ");
//		out.println(qualification+"  ");
		
		 Connection con = null;
		 Statement stmt = null;
		
		try 
		{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");  

			//STEP 3: Open a connection
			con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/hospital_management","root","");  
			//here hospital_management is database name, root is username and password  

			//STEP 4: Execute a query
			stmt=con.createStatement();  
			
			String sql="insert into doctor values(?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			
			preparedStmt.setString(1,Doctor_ID);
			preparedStmt.setString(2,Doctor_Name);
			preparedStmt.setString(3,sex);
			preparedStmt.setInt(4, salary);
			preparedStmt.setString(5,address);
			preparedStmt.setString(6,contact_number);
			preparedStmt.setString(7,history);
			preparedStmt.setString(8,qualification);
			
			preparedStmt.execute();
			System.out.println("Success");
			
			con.close(); 
			
			out.println("<html><body>");  
			//out.println("<div style=background-color:lightblue; align:center");
		    out.println(Doctor_Name+" is successfully registered with AVS Hospitals."); 
		    out.println("</div>");
			out.println("</body></html>"); 
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
