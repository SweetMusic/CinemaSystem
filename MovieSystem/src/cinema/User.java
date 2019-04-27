package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import util.DBHelper;

public class User {
	private int user_id;
	private String user_name;
	private String user_password;
	private int user_sex;
	private int user_age;
	private Date user_birthday;
	private String user_tag;
	private String user_tel;
	private String user_idcard;
	private int user_status = 1;
	
	public User()
	{
		
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public int getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(int user_sex) {
		this.user_sex = user_sex;
	}
	public int getUser_age() {
		return user_age;
	}
	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}
	public Date getUser_birthday() {
		return user_birthday;
	}
	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}
	public String getUser_tag() {
		return user_tag;
	}
	public void setUser_tag(String user_tag) {
		this.user_tag = user_tag;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_idcard() {
		return user_idcard;
	}
	public void setUser_idcard(String user_idcard) {
		this.user_idcard = user_idcard;
	}
	public int getUser_status() {
		return user_status;
	}
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public User findUser(int user_id)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from user where user_id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user_id);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				User user = new User();
				user.setUser_id(rs.getInt(1));
				user.setUser_name(rs.getString(2));
				user.setUser_sex(rs.getInt(4));
				user.setUser_age(rs.getInt(5));
				user.setUser_birthday(rs.getDate(6));
				user.setUser_tel(rs.getString(8));
				user.setUser_idcard(rs.getString(9));
				user.setUser_status(rs.getInt(10));
				return user;
			}
			if(!rs.next())
			{
				User user = new User();
				user.setUser_id(-1);
				user.setUser_name("该用户不存在");
				user.setUser_sex(-1);
				user.setUser_age(-1);
				user.setUser_status(0);
				return user;
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
	
	public User findUserByTel(String user_tel)
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBHelper.getConnection();
			String sql = "select * from user where user_tel = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_tel);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				User user = new User();
				user.setUser_id(rs.getInt(1));
				user.setUser_name(rs.getString(2));
				user.setUser_sex(rs.getInt(4));
				user.setUser_age(rs.getInt(5));
				user.setUser_birthday(rs.getDate(6));
				user.setUser_tel(rs.getString(8));
				user.setUser_idcard(rs.getString(9));
				user.setUser_status(rs.getInt(10));
				return user;
			}
			if(!rs.next())
			{
				User user = new User();
				user.setUser_id(-1);
				user.setUser_name("该用户不存在");
				user.setUser_sex(-1);
				user.setUser_age(-1);
				user.setUser_status(0);
				return user;
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
