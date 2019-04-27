package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.Movie;
import cinema.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBHelper;

public class Movies extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Movies() {
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
		
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int show_id = Integer.parseInt(request.getParameter("show_id"));
		int show_seat_pos = Integer.parseInt(request.getParameter("user_seat"));
		int movie_id = Integer.parseInt(request.getParameter("movie_id"));
		addSeatToDB(user_id, show_id, show_seat_pos, movie_id,  out, response);
	}

	public void addSeatToDB(int user_id, int show_id, int show_seat_pos, int movie_id, PrintWriter out, HttpServletResponse response)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from seat where show_id = ?;";
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, show_id);
			//stmt.setString(0, "user_tel");
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				if(rs.getInt(4) == show_seat_pos)
				{
					out.print("false");
					break;
				}
				else if(rs.isLast())
				{
					rs.moveToInsertRow();
					//rs.updateInt(1, 20);
					rs.updateInt(2, show_id);
					rs.updateInt(3, user_id);
					rs.updateInt(4, show_seat_pos);
					rs.insertRow();
					out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
					out.println("<HTML>");
					out.println("  <HEAD><TITLE>Hello Servlet!</TITLE></HEAD>");
					out.println("  <BODY>");
					out.print("success<br>");
					out.print("<a href=\"/MovieSystem/preview.jsp?movie_id=" + movie_id + "\">" + "����" + "</href>");
					out.println("  </BODY>");
					out.println("</HTML>");
					out.flush();
					out.close();
					break;
				}
			}
			if(!rs.next())
			{
				rs.moveToInsertRow();
				//rs.updateInt(1, 20);
				rs.updateInt(2, show_id);
				rs.updateInt(3, user_id);
				rs.updateInt(4, show_seat_pos);
				rs.insertRow();
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.println("<HTML>");
				out.println("  <HEAD><TITLE>Hello Servlet!</TITLE></HEAD>");
				out.println("  <BODY>");
				out.print("success<br>");
				out.print("<a href=\"/MovieSystem/preview.jsp?movie_id=" + movie_id + "\">" + "返回" + "</href>");
				out.println("  </BODY>");
				out.println("</HTML>");
				out.flush();
				out.close();
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
