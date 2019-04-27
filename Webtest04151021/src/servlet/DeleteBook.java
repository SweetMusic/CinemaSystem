package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping.Book;
import shopping.Cart;

public class DeleteBook extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteBook() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		
		Cart c = new Cart();
		if(session.getAttribute("cartbook") != null)
		{
			c = (Cart)session.getAttribute("cartbook");

			c.setCartList(c.getCartList()); 
		}
		Book book = new Book();
		
		switch(Integer.parseInt(request.getParameter("id")))
		{
			case 1:
				book.setId(1);
				book.setPrice(111.0);
				c.delBook(book);
				session.setAttribute("cartbook", c);
				response.sendRedirect("/Webtest04151021/Cart.jsp");
				break;
			case 2:
				book.setId(2);
				book.setPrice(222.0);
				c.delBook(book);
				session.setAttribute("cartbook", c);
				response.sendRedirect("/Webtest04151021/Cart.jsp");
				break;
			case 3:
				book.setId(3);
				book.setPrice(333.0);
				c.delBook(book);
				session.setAttribute("cartbook", c);
				response.sendRedirect("/Webtest04151021/Cart.jsp");
				break;
			default :
				out.print("false");
		}
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
