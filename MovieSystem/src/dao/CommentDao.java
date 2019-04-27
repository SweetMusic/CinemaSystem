package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBHelper;

public class CommentDao extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CommentDao() {
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
		int movie_id = Integer.parseInt(request.getParameter("movie_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int comment_score = Integer.parseInt(request.getParameter("comment_score"));
		String comment = request.getParameter("comment");
		addCommentToDB(movie_id, user_id, comment_score, comment, out, response);
	}
	
	public void addCommentToDB(int movie_id, int user_id, int comment_score, String comment, PrintWriter out, HttpServletResponse response)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from comment where movie_id = ?;";
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, movie_id);
			//stmt.setString(0, "user_tel");
			rs = stmt.executeQuery();
			
				rs.moveToInsertRow();
				//rs.updateInt(1, 20);
				rs.updateInt(2, user_id);
				rs.updateInt(3, movie_id);
				rs.updateInt(4, comment_score);
				rs.updateString(5, comment);
				rs.insertRow();
				response.sendRedirect("/MovieSystem/preview.jsp?movie_id=" + movie_id);
			
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
