package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBHelper;
import cinema.Seat;
import cinema.User;

public class ChangeUserData extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeUserData() {
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

		doPost(request,response);
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
		HttpSession hs = request.getSession();
		
		User user = new User();
		user.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		user.setUser_age(Integer.parseInt(request.getParameter("user_age")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(request.getParameter("user_birthday"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setUser_birthday(date);
		user.setUser_idcard(request.getParameter("user_idcard"));
		user.setUser_name(request.getParameter("user_name"));
		user.setUser_password(request.getParameter("user_password"));
		user.setUser_sex(Integer.parseInt(request.getParameter("user_sex")));
		user.setUser_tel(request.getParameter("user_tel"));
		changeUserData(user, out, hs);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	public boolean changeUserData(User user, PrintWriter out, HttpSession hs)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select user_id,user_name,user_password,user_sex,user_age,user_birthday,user_tel,user_idcard from user where user_tel=?;";
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, user.getUser_tel());
//			stmt.setString(1, user.getUser_tel());
//			stmt.setInt(2, user.getUser_id());
			rs = stmt.executeQuery();
			while(rs.next())
			{
				
					rs.updateString(2, user.getUser_name());
					rs.updateString(3, user.getUser_password());
					rs.updateInt(4, user.getUser_sex());
					rs.updateInt(5, user.getUser_age());
					rs.updateDate(6, new java.sql.Date(user.getUser_birthday().getTime()));
					rs.updateString(7, user.getUser_tel());
					rs.updateString(8, user.getUser_idcard());
					rs.updateRow();
					out.print("success<br>");
					user = user.findUserByTel(user.getUser_tel());
					hs.setAttribute("user", user);
					out.print("<a href=\"/MovieSystem/userdata.jsp\">" + "返回" + "</href>");
					return true;
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
					return false;
				}
			}
		}
		return false;
	}
	
}
