package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	private final String USERS = "D:/web";
	/**
	 * Constructor of the object.
	 */
	public Login() {
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
		if(cheakLogin(request.getParameter("name"), request.getParameter("password"), request.getParameter("xi")))
		{
//			out.print("success");
			request.getRequestDispatcher("/Success.jsp").forward(request, response);
		}
		else
		{
//			out.print("fail");
			response.sendRedirect("/Webtest04151021/Error.jsp");
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

	private boolean cheakLogin(String user, String password, String xi) throws IOException
	{
		for(String file : new File(USERS).list())
		{
			if(file.equals(user))
			{
				BufferedReader reader = new BufferedReader(new FileReader(USERS + "/" + file + "/profile"));
				String[] t = reader.readLine().split("ttt");
				String password2 =t[1];
				String xi2 = t[2];
				if(password.equals(password2) && xi.equals(xi2))
				{
					return true;
				}
			}
		}
		return false;
	}
}
