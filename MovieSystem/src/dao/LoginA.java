package dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.mysql.jdbc.PreparedStatement;

import cinema.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.DBHelper;


public class LoginA extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginA() {
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
		HttpSession hs = request.getSession();
		if(AdminLogin(admin))
		{
			out.print("success");
			hs.setAttribute("admin", admin);
			//request.getRequestDispatcher("/index.jsp").forward(request, response);
			response.sendRedirect("/MovieSystem/Admin.jsp");
		}
		else
		{
			out.print("false");
			response.sendRedirect("/MovieSystem/AdminLogin.jsp");
		}
	}

	public boolean AdminLogin(Admin admin)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from admin where admin_name = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, admin.getAdmin_name());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				if(rs.getString("admin_name").equals(admin.getAdmin_name()) && rs.getString("admin_password").equals(admin.getAdmin_password()))
				{
					admin.setAdmin_id(rs.getInt("admin_id"));
					admin.setAdmin_level(rs.getInt("admin_level"));
					admin.setAdmin_status(rs.getInt("admin_status"));
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
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
		return false;
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
