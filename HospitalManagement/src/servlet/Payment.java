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
 * Servlet implementation class Payment
 */
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		
		String Payment_ID=request.getParameter("Payment_ID");
		String payment_type=request.getParameter("payment_type");
		double payment_amount=Double.parseDouble(request.getParameter("payment_amount"));
		
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
			
			String sql="insert into payment values(?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			
			preparedStmt.setString(1,Payment_ID);
			preparedStmt.setString(2,payment_type);
			preparedStmt.setDouble(3,payment_amount);
			preparedStmt.setDate(4,sqlDate);
			
			preparedStmt.execute();
			System.out.println("Success");
			
			con.close(); 
			
			out.println("<html><body>");  
			//out.println("<div style=background-color:lightblue; align:center");
		    out.println("Payment successful. Payment_ID is : "+Payment_ID); 
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
