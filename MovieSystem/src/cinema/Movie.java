package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBHelper;

public class Movie {
	private int movie_id;
	private String movie_name;
	private String movie_tag;
	private String movie_text;
	private int movie_score;
	private String movie_star;
	private String movie_pic;
	private double movie_price;
	private int movie_status = 1;
	
	
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getMovie_tag() {
		return movie_tag;
	}
	public void setMovie_tag(String movie_tag) {
		this.movie_tag = movie_tag;
	}
	public String getMovie_text() {
		return movie_text;
	}
	public void setMovie_text(String movie_text) {
		this.movie_text = movie_text;
	}
	public int getMovie_score() {
		return movie_score;
	}
	public void setMovie_score(int movie_score) {
		this.movie_score = movie_score;
	}
	public String getMovie_star() {
		return movie_star;
	}
	public void setMovie_star(String movie_star) {
		this.movie_star = movie_star;
	}
	public int getMovie_status() {
		return movie_status;
	}
	public void setMovie_status(int movie_status) {
		this.movie_status = movie_status;
	}
	public String getMovie_pic() {
		return movie_pic;
	}
	public void setMovie_pic(String movie_pic) {
		this.movie_pic = movie_pic;
	}
	public double getMovie_price() {
		return movie_price;
	}
	public void setMovie_price(double movie_price) {
		this.movie_price = movie_price;
	}
	public ArrayList<Movie> addMovie()
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Movie> ar = new ArrayList<Movie>();
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from movie;";
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			//stmt.setString(0, "user_tel");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				Movie movie = new Movie();
				movie.setMovie_id(rs.getInt(1));
				movie.setMovie_name(rs.getString(2));
				movie.setMovie_tag(rs.getString(3));
				movie.setMovie_text(rs.getString(4));
				movie.setMovie_score(rs.getInt(5));
				movie.setMovie_status(rs.getInt(6));
				movie.setMovie_star(rs.getString(7));
				movie.setMovie_pic(rs.getString(8));
				movie.setMovie_price(rs.getDouble(9));
				ar.add(movie);
			}
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
		return ar;
	}
	public Movie findMovie(int movie_id)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from movie;";
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			//stmt.setString(0, "user_tel");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				if(rs.getInt("movie_id") == movie_id)
				{
					Movie movie = new Movie();
					movie.setMovie_id(rs.getInt(1));
					movie.setMovie_name(rs.getString(2));
					movie.setMovie_tag(rs.getString(3));
					movie.setMovie_text(rs.getString(4));
					movie.setMovie_score(rs.getInt(5));
					movie.setMovie_status(rs.getInt(6));
					movie.setMovie_star(rs.getString(7));
					movie.setMovie_pic(rs.getString(8));
					movie.setMovie_price(rs.getDouble(9));
					return movie;
				}
			}
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
		return null;
	}
}
