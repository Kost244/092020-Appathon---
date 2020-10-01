package kosto2;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Product
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("pass");
		request.getSession().setAttribute("username", username);
		
		//PrintWriter out;
		//out = response.getWriter();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products","root","");
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password ='" + password + "';");

			
			if(rs.next()){			
				
				String fullname = rs.getString("fullname");
				String birth = rs.getString("birth");
				//String id = rs.getString("userid");
				
				request.getSession().setAttribute("fullname", fullname);
				request.getSession().setAttribute("birth", birth);
				//request.getSession().setAttribute("userid", id);
				
				response.sendRedirect("myhomepage.jsp");
				
			}
			else{
				response.sendRedirect("newUser.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}

