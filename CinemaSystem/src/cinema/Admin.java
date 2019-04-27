package cinema;

public class Admin {
	private int admin_id;
	private String admin_name;
	private String admin_password;
	private int admin_level;
	private int admin_status = 1;
	
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public int getAdmin_level() {
		return admin_level;
	}
	public void setAdmin_level(int admin_level) {
		this.admin_level = admin_level;
	}
	public int getAdmin_status() {
		return admin_status;
	}
	public void setAdmin_status(int admin_status) {
		this.admin_status = admin_status;
	}
	
	
}
