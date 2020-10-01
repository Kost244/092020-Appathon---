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
public class Basket extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String[][] taxes = {{"0.24", "0.34", "0.20", "0.10", "0.40"},
			{"Greece", "Germany", "England", "France", "Italy"}};
	
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
		
		
		Float discount;
		String color = "black";
		boolean haslogged = true;
		if (request.getSession().getAttribute("username") == null) haslogged = false;
		
		if (request.getSession().getAttribute("discount")==null) {
			discount = 1.0f;
			color = "black";
		}
		else if (Float.parseFloat(request.getSession().getAttribute("discount").toString())!=0.8f) {
			discount = 1.0f;
			color = "black";
		}
		else {
			discount = Float.parseFloat(request.getSession().getAttribute("discount").toString());
			color = "red";
			
		}


		String voucher = request.getParameter("voucher");
		
		if (voucher!=null && voucher.equals("studentdiscount")) {
			discount = 0.8f;
			color = "red";
		}
		
		request.getSession().setAttribute("discount", discount);
		
		String country = request.getParameter("countries");
		
		if (country==null) {
			if (request.getSession().getAttribute("country")==null)
				country = "0";
			else country = request.getSession().getAttribute("country").toString();
		}
		
		request.getSession().setAttribute("country", country);
		
		
		
		
		
		PrintWriter out;
		
		out = response.getWriter();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		
		try {
			if (((ArrayList<Integer>) request.getSession().getAttribute("products"))!=null && ((ArrayList<Integer>) request.getSession().getAttribute("products")).size()!=0) {
			String queryString = "SELECT * FROM product WHERE productid='" + ((ArrayList<Integer>) request.getSession().getAttribute("products")).get(0) + "'";
			
			for (Object o : ((ArrayList<Integer>) request.getSession().getAttribute("products"))) {
				queryString = queryString + "OR productid='" + o.toString() + "'";
			}
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products","root","");
			st = con.createStatement();
			rs = st.executeQuery(queryString);
			
			out.println("<head><link rel=\"stylesheet\" href=\"css/tooltip.css\"><meta charset='UTF-8'><title>Products</title>\r\n" + 
					" <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\r\n" + 
					"<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\r\n" + 
					"<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\" integrity=\"sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN\" crossorigin=\"anonymous\"></script>\r\n" + 
					"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\" integrity=\"sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV\" crossorigin=\"anonymous\"></script>");
			out.println("<script>function nope() { if("+ !haslogged +"){ alert(\"Not logged in \"); window.location.replace(\"login.jsp\")}else{document.forms[0].action = \"Buyout\"; document.forms[0].submit();}}</script>");
			out.println("<script src=\"//code.jquery.com/jquery.min.js\"></script>");
			out.println("<script>\r\n" + 
					"$.get(\"navigation.html\", function(data){\r\n" + 
					"    $(\"#nav-placeholder\").replaceWith(data);\r\n" + 
					"});\r\n" + 
					"</script></head><body>");
			
			out.println("<div id=\"nav-placeholder\"></div>");
			
			out.println("<body><h1 align=\"center\" style=\"margin-top:2rem;\">Basket</h1>");
			
			out.println("<div class=\"container\" align=\"center\" style=\"margin-top: 1rem;\">");
			
			out.println("<form action=\"Basket\" method=\"post\" >\r\n"
					+ "		<label for=\"voucher\">Add coupon:</label> "
					+ "		<input type=\"text\" name=\"voucher\" ></input>"
					+ "		<input type=\"submit\" value=\"Submit\" ></input>\r\n"
					+ "	</form>");
			
			out.println("<form action=\"Basket\" method=\"post\">\r\n"
					+ "		<label for=\"countries\">Choose a county:</label> "
					+ "		<select name=\"countries\" id=\"countries\"> "	);
			
			
			
			
			
			for (int i = 0; i<5; i++) {
				out.println("<option value=\"" + i + "\"");
				if(taxes[1][Integer.parseInt(country)].equals(taxes[1][i])) {
					out.println("selected");
				}
				out.println(">" + taxes[1][i] + "</option>");
			}
					
			out.println("		</select> "
					+ "		<input type=\"submit\" value=\"Submit\"></input>\r\n"
					+ "	</form>");
			out.println("</div>");
			
			out.println("<div class=\"container\" align=\"center\" style=\"margin-top: 1rem;\">\r\n" + 
					"	<div class=\"card\" style=\"width: 28rem;\">\r\n" + 
					"  	<div class=\"card-body\">");
			
			out.println("<h1>Products</h1>");
			out.println("<table class=\"table\">");
			out.println("<tr>"
							+ "<th>Product</th>"
							+ "<th>Price</th>"
						+ "</tr>");
			
			Float totalPrice = 0.0f;
			
			while(rs.next()){			
				
				String productName = rs.getString("productname");
				Float productPriceNotax = rs.getFloat("notaxprice");
				
				Float finalPrice = (productPriceNotax + (productPriceNotax*Float.parseFloat(taxes[0][Integer.parseInt(country)])))*discount;
				
				
				out.println("<tr><form action='AddToBasket' method='post'>"
						+ "<td name=\"productname\">" + productName + "</td>"
						+ "<td name=\"price\" style=\"color:" + color + "\">" + finalPrice + "</td>");
				out.println("<td>");
				
				totalPrice += finalPrice;
				
			
				
			}
			
			request.getSession().setAttribute("subTotal", totalPrice);
			out.println("</table></form>");
			
			
			out.println("<input type=\"button\" value=\"Complete Order\" onclick=\"nope()\"></input></body>");
			
			out.println("</div></div></div>");
			}
			else {
				out.println("<script>alert(\"No Items in Basket! \"); history.go(-1);</script>");
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}

