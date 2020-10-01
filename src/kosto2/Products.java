package kosto2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class Products extends HttpServlet {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		
		PrintWriter out;
		out = response.getWriter();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products","root","");
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM product;");

			//out.println();
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
				
				
				out.println("<h1 align=\"center\" style=\"margin-top:2rem;\">Products</h1><br>");
				
				
				out.println("<div class=\"container\" align=\"center\" style=\"margin-top: 1rem;\">");
				
				
				out.println("<table class=\"table\">");
				out.println("<tr>"
								+ "<th>Product</th>"
								+ "<th>Price</th>"
								+ "<th>Buy</th>"
							+ "</tr>");
				
			while(rs.next()){			
				
				int productid = rs.getInt("productid");
				String productname = rs.getString("productname");
				Float notaxprice = rs.getFloat("notaxprice");
				Float defaulttax = rs.getFloat("defaulttax");
				Float finalPrice = notaxprice + (notaxprice * defaulttax);
				
				
				
				
				out.println("<tr>"
							+ "<td name=\"productname\">" + productname + "</td>"
							+ "<td name=\"notaxprice\"><div class=\\\"tooltip bs-tooltip-top\\\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"After tax: " + finalPrice + "\">" + notaxprice + "</div></td>"
							+ "<td>");
							if (((ArrayList<Integer>) request.getSession().getAttribute("products"))!=null) {
								if (((ArrayList<Integer>) request.getSession().getAttribute("products")).contains(productid)) {
									out.println("<form action='Remove' method='post'><input type='hidden' name=\"productid\" value=\"" + productid + "\"></input>"
											+ "<input type='submit' name='basket' value='Remove'></input></form>");
								}else {
									out.println("<form action='AddToBasket' method='post'><input type='hidden' name=\"productid\" value=\"" + productid + "\"></input>"
											+ "<input type='submit' name='basket' value='Add to basket'></input></form>");
								}
								
							}else {
									out.println("<form action='AddToBasket' method='post'><input type='hidden' name=\"productid\" value=\"" + productid + "\"></input>"
											+ "<input type='submit' name='basket' value='Add to basket'></input></form>");
								}
							out.println("</td></tr>");
								
			}
			out.println("</table><br>");
			
		if (((ArrayList<Integer>) request.getSession().getAttribute("products"))!=null) {

				out.println(((ArrayList<Integer>) request.getSession().getAttribute("products")).size() + " products in cart!");

		}
		else {
			
		}
		out.println("</div>");
		

		
			out.println("</body>");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}

