package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Treatment
 */
public class Treatment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		
		String Doctor_ID=request.getParameter("Doctor_ID");
		String Patient_ID=request.getParameter("Patient_ID");
		String testid=request.getParameter("testid");
		String testname=request.getParameter("testname");
		String description=request.getParameter("description");
		String medicineid=request.getParameter("medicineid");
		String medicinename=request.getParameter("medicinename");
		int quantity=Integer.parseInt(request.getParameter("quantity"));
	
		
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
			
			String sql1="insert into prescription values(?,?,?,?,?,?)";
			String sql2="insert into test values(?,?,?,?,?)";
			String sql3="insert into medicine values(?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(sql1);
			
			preparedStmt.setString(1,Doctor_ID);
			preparedStmt.setString(2,Patient_ID);
			preparedStmt.setString(3,testname);
			preparedStmt.setString(4,medicinename);
			preparedStmt.setString(5,description);
			preparedStmt.setDate(6,sqlDate);
			
            PreparedStatement preparedStmt2 = con.prepareStatement(sql2);
			
            preparedStmt2.setString(1,testid);
            preparedStmt2.setString(2,Doctor_ID);
            preparedStmt2.setString(3,testname);
            preparedStmt2.setString(4,description);
            preparedStmt2.setDate(5,sqlDate);
			
			PreparedStatement preparedStmt3 = con.prepareStatement(sql3);
			
			preparedStmt3.setString(1,medicineid);
			preparedStmt3.setString(2,Doctor_ID);
			preparedStmt3.setString(3,Patient_ID);
			preparedStmt3.setString(4,medicinename);
			preparedStmt3.setInt(5,quantity);
			preparedStmt3.setString(6,description);
			preparedStmt3.setDate(7,sqlDate);
			
			preparedStmt.execute();
			preparedStmt2.execute();
			preparedStmt3.execute();
			System.out.println("Success");
			
			con.close(); 
			
			out.println("<html><body>");  
			//out.println("<div style=background-color:lightblue; align:center");
		    out.println("Treatment details are inserted successfully"); 
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
