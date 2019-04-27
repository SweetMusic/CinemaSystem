package cinema;

public class Show {
	private int show_id;
	private int movie_id;
	private int user_id;
	private String show_date;
	private String show_startTime;
	private String show_endTime;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getShow_date() {
		return show_date;
	}
	public void setShow_date(String show_date) {
		this.show_date = show_date;
	}
	public String getShow_startTime() {
		return show_startTime;
	}
	public void setShow_startTime(String show_startTime) {
		this.show_startTime = show_startTime;
	}
	public String getShow_endTime() {
		return show_endTime;
	}
	public void setShow_endTime(String show_endTime) {
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
	
	
}
