package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import util.DBHelper;
import java.util.Date;

public class Show {
	private int show_id;
	private int movie_id;
	private String user_id;
	private Date show_date;
	private Time show_startTime;
	private Time show_endTime;
	private String show_area;
	private String show_seat;
	private int show_status = 1;
	public int getShow_id() {
		return show_id;
	}
	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getShow_date() {
		return show_date;
	}
	public void setShow_date(Date show_date) {
		this.show_date = show_date;
	}
	public Time getShow_startTime() {
		return show_startTime;
	}
	public void setShow_startTime(Time show_startTime) {
		this.show_startTime = show_startTime;
	}
	public Time getShow_endTime() {
		return show_endTime;
	}
	public void setShow_endTime(Time show_endTime) {
		this.show_endTime = show_endTime;
	}
	public String getShow_area() {
		return show_area;
	}
	public void setShow_area(String show_area) {
		this.show_area = show_area;
	}
	public String getShow_seat() {
		return show_seat;
	}
	public void setShow_seat(String show_seat) {
		this.show_seat = show_seat;
	}
	public int getShow_status() {
		return show_status;
	}
	public void setShow_status(int show_status) {
		this.show_status = show_status;
	}
	
	public ArrayList<Show> getShows(int movie_id)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Show> ar = new ArrayList<Show>();
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from shows;";
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			//stmt.setString(0, "user_tel");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				if(movie_id == rs.getInt("movie_id"))
				{
					Show show = new Show();
					show.setShow_id(rs.getInt(1));
					show.setMovie_id(rs.getInt(2));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					try {
						date = sdf.parse(rs.getString("show_date"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					show.setShow_date(date);
					show.setShow_startTime(rs.getTime(4));
					show.setShow_endTime(rs.getTime(5));
					show.setShow_area(rs.getString(6));
					show.setShow_seat(rs.getString(7));
					show.setShow_status(rs.getInt(8));
					ar.add(show);
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
		return ar;
	}
	
	public Show getShow(int show_id)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from shows where show_id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, show_id);
			//stmt.setString(0, "user_tel");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				Show show = new Show();
				show.setShow_id(rs.getInt(1));
				show.setMovie_id(rs.getInt(2));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				try {
					date = sdf.parse(rs.getString("show_date"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				show.setShow_date(date);
				show.setShow_startTime(rs.getTime(4));
				show.setShow_endTime(rs.getTime(5));
				show.setShow_area(rs.getString(6));
				show.setShow_seat(rs.getString(7));
				show.setShow_status(rs.getInt(8));
				return show;
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
