package fit.iuh.edu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static Connection con = null;
	private static Database instance = new Database();
	
	private Database() {
		connection();
	}
	
	public static Database getInstance(){
		return instance;
	}
	
	//Hàm dùng để kết nối đến Database
	public void connection(){
		String url = "jdbc:sqlserver://localhost:1433;databasename=QUANLYDVD";
		String user = "sa"; String password = "sapassword";
		 
		try{
			//Kết nối với sql server sử dụng getconection(thuộc lớp drivermanager)
			con = DriverManager.getConnection(url, user, password);
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	//Đóng kết nối với Database
	public void disconnect(){
		if(con != null)
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
	}

	public static Connection getConnection(){
		return con;
	}

}
