package kosto2;

import java.io.IOException;
/*
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
*/
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Product
 */
public class AddToBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ArrayList<Integer> basket = new ArrayList<Integer>();
       
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
		
		
		String id = request.getParameter("productid");
		System.out.println(id);
		ArrayList<Integer> tempbasket = new ArrayList<Integer>();
		if (request.getSession().getAttribute("products")!=null) {
			tempbasket = (ArrayList <Integer>) request.getSession().getAttribute("products");
			tempbasket.add(Integer.parseInt(id));
		}
		else {
			tempbasket.add(Integer.parseInt(id));
		}
		request.getSession().setAttribute("products", tempbasket);
		//basket.clear();
		
		response.sendRedirect("Products");
	}
		
}

