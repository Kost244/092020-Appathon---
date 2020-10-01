package kosto2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Product
 */
public class Buyout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int orders = 0;
    

	
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
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer> productList = (ArrayList<Integer>) request.getSession().getAttribute("products");
		Float total = (Float) request.getSession().getAttribute("subTotal");
		String username = request.getSession().getAttribute("username").toString();
		
		//File myFile = new File("D:\\George Kostopoulos\\Downloads\\kosto23\\kosto\\orders\\order.txt");
		
		String outfile = System.getProperty("user.dir") + "\\order" + orders + ".txt";
		System.out.println("Writing file to directory: " + outfile);
		

	    BufferedWriter writer = new BufferedWriter(new FileWriter(outfile));

			
		
		
		PrintWriter out;
		out = response.getWriter();
		Connection con = null;
		Statement st = null;
		
		String query = "INSERT INTO orders (username, orders, total) VALUES ('"
				+ username + "', '["
				
				;
		
		
		boolean first = true;
		for (Object o : productList) {
			if (first) {
				query = query + o.toString();
				first = false;
			}
			else query = query + ", " +  o.toString();
			
		}
		

		query = query + "]', '" + total + "')" ;	
				
			
		//System.out.println(query);
		
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products","root","");
			st = con.createStatement();
			st.executeUpdate(query);
			orders++;
			
			
			writer.write("Username: " + username + "\n"
					+ "Products: " + productList + "\n"
					+ "Total: " + total + "\n"
					);
		    writer.close();
			
			
		
		    out.println("<head><link rel=\"stylesheet\" href=\"css/tooltip.css\"><meta charset='UTF-8'><title>Products</title>\r\n" + 
					" <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\r\n" + 
					"<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\r\n" + 
					"<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\" integrity=\"sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN\" crossorigin=\"anonymous\"></script>\r\n" + 
					"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\" integrity=\"sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV\" crossorigin=\"anonymous\"></script>");
			out.println("<script src=\"//code.jquery.com/jquery.min.js\"></script>");
			out.println("<script>\r\n" + 
					"$.get(\"navigation.html\", function(data){\r\n" + 
					"    $(\"#nav-placeholder\").replaceWith(data);\r\n" + 
					"});\r\n" + 
					"</script></head><body>");
			
			out.println("<div id=\"nav-placeholder\"></div>");
			
			out.println("<body><h1 align=\"center\" style=\"margin-top:2rem;\">Thank You!</h1>");
			
			out.println("<div class=\"container\" align=\"center\" style=\"margin-top: 1rem;\">\r\n" + 
					"	<div class=\"card\" style=\"width: 28rem;\">\r\n" + 
					"  	<div class=\"card-body\">");
			
			out.println("<h3>Your order has been confirmed! </h3>");
			out.println("<p>Your sub total is: " + total + "</p>");
			out.println("<p>Shipping to: " + Basket.taxes[1][Integer.parseInt(request.getSession().getAttribute("country").toString())] + "</p>");
			
			out.println("<form action=\"myhomepage.jsp\">" + 
					"    <input type=\"submit\" value=\"Return Home\" />" + 
					"</form>");
			
			out.println("</div></div></div>");
			
			request.getSession().removeAttribute("subTotal");
			request.getSession().removeAttribute("products");
			request.getSession().removeAttribute("country");
			request.getSession().removeAttribute("discount");
			AddToBasket.basket.clear();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}

