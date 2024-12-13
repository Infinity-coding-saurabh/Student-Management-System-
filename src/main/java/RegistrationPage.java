package mypackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationPage
 */
@WebServlet("/RegistrationPage")
public class RegistrationPage extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><head><title>index</title></head><body>");
		
		String fname=request.getParameter("firstname");
		String lname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String course=request.getParameter("course");
		String gender=request.getParameter("gender");
		String pan=request.getParameter("pan");
		
		
		
		out.println("</body></html>");
		
		try
		{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","pass");  
			  
			PreparedStatement stmt=con.prepareStatement( "insert into Record02 values(?,?,?,?,?,?,?)"); 
			
			stmt.setString(1,fname);  
			stmt.setString(2,lname);  
			stmt.setString(3,email); 
			stmt.setString(4,phone); 
			stmt.setString(5,course); 
			stmt.setString(6,gender); 
			stmt.setString(7,pan); 
			
			int i=stmt.executeUpdate();  
			 if (i > 0) 
			 {
	                out.print("<h1> Record inserted successfully....</h1>");
	                
	                request.getRequestDispatcher("index.html").include(request, response);
	         }
			 else 
			 {
	                out.print("Failed to insert record.");
	                request.getRequestDispatcher("index.html").include(request, response);
	          }
		}
		catch (Exception e)	
			{
				System.out.println(e);
			}  
        	out.close();  
	}
}
