package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBHelper;

public class UpdataShows extends HttpServlet {

	public UpdataShows() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from shows;";
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			//stmt.setString(0, "user_tel");
			rs = stmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			HttpSession hs = request.getSession();
			//out.print(today.toString());
			while(rs.next())
			{
				if(rs.getDate("show_date").getTime() < today.getTime())
				{
					rs.updateInt("show_status", 0);
					rs.updateRow();
				}
				else if(hs.getAttribute("admin") != null)
				{
					out.print("show_id:" + rs.getInt(1) + "        " + 
							"movie_id:" + rs.getInt(2) + "        " + 
							"show_date:" + rs.getDate(3) + "        " + 
							"show_startTime:" + rs.getTime(4) + "        " +
							"show_endTime:" + rs.getTime(5) + "        " + 
							"show_area:" + rs.getString(6) + "        " +
							"show_status:" + rs.getInt(8) + "    <a href=\"ChangeShowsInfo.jsp?show_id=" + rs.getInt(1) + "\">修改</a><br>");
				}
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
