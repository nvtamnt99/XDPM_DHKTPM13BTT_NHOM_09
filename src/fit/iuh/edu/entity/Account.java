package fit.iuh.edu.entity;

public class Account {
	private String id;
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
}
