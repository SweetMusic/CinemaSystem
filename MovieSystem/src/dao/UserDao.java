package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.DBHelper;

import cinema.Seat;
import cinema.User;
import cinema.Show;


public class UserDao {
	
	public ArrayList<Seat> getSeatList(int show_id)
	{
		ArrayList<Seat> ar = new ArrayList<Seat>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from seat where show_id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, show_id);
			//stmt.setString(0, "user_tel");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				Seat seat = new Seat();
				seat.setShow_id(rs.getInt(2));
				seat.setShow_user_id(rs.getInt(3));
				seat.setShow_seat_pos(rs.getInt(4));
				seat.setStatus(rs.getInt(5));
				ar.add(seat);
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
					return ar;
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
	
	public ArrayList<Integer> seatSelectList(ArrayList<Seat> ar)
	{
		ArrayList<Integer> sar = new ArrayList<Integer>();
		for(int i = 1; i<51; i++)
		{
			int k = 0;
			if(!ar.isEmpty())
			{
				for(Seat seat : ar)
				{
					++k;
					if(i == seat.getShow_seat_pos())
					{
						break;
					}
					else if(k == ar.size())
					{
						sar.add(i);
					}
				}
			}
			else
			{
				sar.add(i);
			}
		}
		return sar;
	}
	
	
}
