package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBHelper;

public class Comment {
	private int comment_id;
	private int user_id;
	private int movie_id;
	private int comment_score;
	private String comment_text;
	private int comment_status = 1;
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public int getComment_score() {
		return comment_score;
	}
	public void setComment_score(int comment_score) {
		this.comment_score = comment_score;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	public int getComment_status() {
		return comment_status;
	}
	public void setComment_status(int comment_status) {
		this.comment_status = comment_status;
	}
	public ArrayList<Comment> getCommentList(int movie_id)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from comment where movie_id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, movie_id);
			rs = stmt.executeQuery();
			ArrayList<Comment> ar = new ArrayList<Comment>();
			while(rs.next())
			{
				Comment comment = new Comment();
				comment.setComment_id(rs.getInt(1));
				comment.setUser_id(rs.getInt(2));
				comment.setMovie_id(rs.getInt(3));
				comment.setComment_score(rs.getInt(4));
				comment.setComment_text(rs.getString(5));
				comment.setComment_status(rs.getInt(6));
				ar.add(comment);
			}
			return ar;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
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
					return null;
				}
			}
		}
	}
	
}
