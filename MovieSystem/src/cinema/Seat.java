package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBHelper;

public class Seat {
	private int seat_id;
	private int show_id;
	private int show_user_id;
	private int show_seat_pos;
	private int status = 1;
	
	public Seat(){
		
	}

	public int getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}

	public int getShow_id() {
		return show_id;
	}

	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}

	
	public int getShow_user_id() {
		return show_user_id;
	}

	public void setShow_user_id(int show_user_id) {
		this.show_user_id = show_user_id;
	}

	public int getShow_seat_pos() {
		return show_seat_pos;
	}

	public void setShow_seat_pos(int show_seat_pos) {
		this.show_seat_pos = show_seat_pos;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
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
	
	public ArrayList<Seat> getUserSeatList(int user_id)
	{
		ArrayList<Seat> ar = new ArrayList<Seat>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from seat where show_user_id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user_id);
			//stmt.setString(0, "user_tel");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				Seat seat = new Seat();
				seat.setSeat_id(rs.getInt(1));
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
