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
import cinema.Show;

public class ChangeShowsInfo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeShowsInfo() {
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
		
		Show show = new Show();
		show = show.getShow(Integer.parseInt(request.getParameter("show_id")));
		show.setMovie_id(Integer.parseInt(request.getParameter("movie_id")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(request.getParameter("show_date"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		show.setShow_date(date);
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		Date date2 = new Date();
		try {
			date2 = sdf2.parse(request.getParameter("show_startTime"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		show.setShow_startTime(new java.sql.Time(date2.getTime()));
		SimpleDateFormat sdf3 = new SimpleDateFormat("hh:mm:ss");
		Date date3 = new Date();
		try {
			date3 = sdf3.parse(request.getParameter("show_endTime"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		show.setShow_endTime(new java.sql.Time(date3.getTime()));
		show.setShow_area(request.getParameter("show_area"));
		changeShowsInfo(show, out, hs);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	public boolean changeShowsInfo(Show show, PrintWriter out, HttpSession hs)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from shows where show_id=?;";
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, show.getShow_id());
//			stmt.setString(1, user.getUser_tel());
//			stmt.setInt(2, user.getUser_id());
			rs = stmt.executeQuery();
			while(rs.next())
			{	
				rs.updateInt(2, show.getMovie_id());
				rs.updateDate(3, new java.sql.Date(show.getShow_date().getTime()));
				rs.updateTime(4, new java.sql.Time(show.getShow_startTime().getTime()));
				rs.updateTime(5, new java.sql.Time(show.getShow_endTime().getTime()));
				rs.updateString(6, show.getShow_area());
				rs.updateRow();
				out.print("success<br>");
				out.print("<a href=\"/MovieSystem/Admin.jsp\">" + "返回" + "</href>");
				return true;
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
		return false;
	}
	
}
