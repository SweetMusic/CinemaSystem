package dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.mysql.jdbc.PreparedStatement;

import cinema.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.DBHelper;


public class LoginU extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginU() {
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
		User user = new User();
		user.setUser_tel(request.getParameter("user_tel"));
		user.setUser_password(request.getParameter("user_password"));
		HttpSession hs = request.getSession();
		if(UserLogin(user))
		{
			out.print("登陆成功");
			user = user.findUserByTel(user.getUser_tel());
			hs.setAttribute("user", user);
			//request.getRequestDispatcher("/index.jsp").forward(request, response);
			response.sendRedirect("/MovieSystem/index.jsp");
		}
		else
		{
			out.print("登录失败");
			response.sendRedirect("/MovieSystem/login.jsp");
		}
	}

	public boolean UserLogin(User user)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select user_id,user_name,user_password,user_sex,user_age,user_birthday,user_tel,user_idcard from user where user_tel = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUser_tel());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				if(rs.getString("user_tel").equals(user.getUser_tel()) && rs.getString("user_password").equals(user.getUser_password()))
				{
					user.setUser_name(rs.getString("user_name"));
					
					user.setUser_age(rs.getInt("user_age"));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					try {
						date = sdf.parse(rs.getString("user_birthday"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.setUser_birthday(date);
					user.setUser_id(rs.getInt("user_id"));
					user.setUser_idcard(rs.getString("user_idcard"));
					user.setUser_sex(rs.getInt("user_sex"));
					user.setUser_tel(rs.getString("user_tel"));
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
