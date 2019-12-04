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
 * Servlet implementation class GenerateBill
 */
public class GenerateBill extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		
		String Bill_ID=request.getParameter("Bill_ID");
		String Patient_ID=request.getParameter("Patient_ID");
		String bill_type=request.getParameter("bill_type");
		double amount=Double.parseDouble(request.getParameter("amount"));
		
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
			
			String sql="insert into bill values(?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			
			preparedStmt.setString(1,Bill_ID);
			preparedStmt.setString(2,Patient_ID);
			preparedStmt.setString(3,bill_type);
			preparedStmt.setDouble(4,amount);
			preparedStmt.setDate(5,sqlDate);
			
			preparedStmt.execute();
			System.out.println("Success");
			
			con.close(); 
			
			out.println("<html><body>");  
			//out.println("<div style=background-color:lightblue; align:center");
		    out.println("Bill generated successfully for Patient_ID : "+Patient_ID); 
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
