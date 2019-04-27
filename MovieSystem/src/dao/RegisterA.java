package dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.jdbc.PreparedStatement;

import cinema.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBHelper;


public class RegisterA extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterA() {
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
		
		Admin admin = new Admin();
		admin.setAdmin_name(request.getParameter("admin_name"));
		admin.setAdmin_password(request.getParameter("admin_password"));
		AdminRegister(admin, out, response);
	}

	public void AdminRegister(Admin admin, PrintWriter out, HttpServletResponse response)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from admin;";
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			//stmt.setString(0, "user_tel");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				if(rs.getString("admin_name").equals(admin.getAdmin_name()))
				{
					out.print("fali");
					response.sendRedirect("/MovieSystem/AdminRegister.jsp");
					break;
				}
				else if(rs.isLast())
				{
					rs.moveToInsertRow();
					rs.updateString(2, admin.getAdmin_name());
					rs.updateString(3, admin.getAdmin_password());
					rs.insertRow();
					out.print("success");
					response.sendRedirect("/MovieSystem/AdminLogin.jsp");
					break;
				}
			}
			if(!rs.next())
			{
				rs.moveToInsertRow();
				rs.updateString(2, admin.getAdmin_name());
				rs.updateString(3, admin.getAdmin_password());
				rs.insertRow();
				out.print("success");
				response.sendRedirect("/MovieSystem/login.jsp");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(stmt != null)
			{
				try
				{
					stmt.close();
					stmt = null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
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
