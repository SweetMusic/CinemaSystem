package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormServlet extends HttpServlet {

	private final String USERS = "D:/web";
	/**
	 * Constructor of the object.
	 */
	public FormServlet() {
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
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("<stong>用户信息</strong></br></br>");
		if(request.getParameter("name") != null && request.getParameter("password") != null && request.getParameter("R") != null && request.getParameter("xi") != null )
		{
			creatUserData(request.getParameter("name"), request.getParameter("password"), request.getParameter("xi"));
			out.println("用户名：" + request.getParameter("name") + "</br>");
			out.println("密码：" + request.getParameter("password") + "</br>");
			out.println("性别：" + request.getParameter("R") + "</br>");
			out.println("系别：" + request.getParameter("xi") + "</br>");
		}
		out.println("兴趣：");
		if(request.getParameterValues("like")!=null)
		{
			for(String like:request.getParameterValues("like"))
			{
				out.println(like + " ");
			}
			out.println("</br>");
		}
		else
		{
			out.println("null</br>");
		}
		if(request.getParameter("study") != null && request.getParameter("email") != null && request.getParameter("resume") != null)
		{
			out.println("学历：" + request.getParameter("study") + "</br>");
			out.println("邮件地址：" + request.getParameter("email") + "</br>");
			out.println("简历：" + request.getParameter("resume") + "</br>");
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	private void creatUserData(String user, String password, String xi) throws IOException
	{
		File userhome = new File(USERS + "/" + user);
		userhome.mkdir();
		BufferedWriter writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
		writer.write(user + "ttt" + password + "ttt" + xi);
		writer.close();
	}

}
