package dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cinema.User;

public class ReturnPage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReturnPage() {
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String pageName = request.getParameter("pageName");
		String toPageName = request.getParameter("toPageName");
		HttpSession hs = request.getSession();	
		if(hs.getAttribute("user") != null)
		{
			User user = (User)hs.getAttribute("user");
			if(user.getUser_status() != 1)
			{
				out.print("你的账号已被冻结");
			}
			else
			{
				out.print("您已登录！<br>");
				out.print("<a href=\"/MovieSystem/index.jsp\">" + "返回首页" + "</href>" + "&nbsp;&nbsp;&nbsp;&nbsp;");
				
			}
		}
		else
		{
			out.print("请先登录<br>");
			out.print("<a href=\"/MovieSystem/index.jsp\">" + "返回首页" + "</href>" + "&nbsp;&nbsp;&nbsp;&nbsp;");
			out.print("<a href=\"/MovieSystem/login.jsp\">" + "登录" + "</href>");
		}
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

		doGet(request, response);
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
