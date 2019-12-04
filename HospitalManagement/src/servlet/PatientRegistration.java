package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PatientRegistration
 */
public class PatientRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		
		String Patient_ID=request.getParameter("Patient_ID");
		String doctorid=request.getParameter("doctorid");
		String Patient_Name=request.getParameter("Patient_Name");
		String sex=request.getParameter("sex");
		String address=request.getParameter("address");
		String contact_number=request.getParameter("contact_number");
		
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
			
			String sql="insert into patient values(?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			
			preparedStmt.setString(1,Patient_ID);
			preparedStmt.setString(2,doctorid);
			preparedStmt.setString(3,Patient_Name);
			preparedStmt.setString(4,sex);
			preparedStmt.setString(5,address);
			preparedStmt.setDate(6,sqlDate);
			preparedStmt.setDate(7,sqlDate);
			preparedStmt.setString(8,contact_number);
			
			preparedStmt.execute();
			System.out.println("Success");
			
			con.close(); 
			
			out.println("<html><body>");  
			//out.println("<div style=background-color:lightblue; align:center");
		    out.println(Patient_Name+" is admitted in AVS Hospitals."); 
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

}
