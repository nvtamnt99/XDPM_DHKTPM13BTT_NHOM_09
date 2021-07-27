package fit.iuh.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fit.iuh.edu.entity.ProductType;
import fit.iuh.edu.entity.Title;

public class TitleDAO {

	//Lấy tất cả dữ liệu titles trong Database
	public ArrayList<Title> getAllTitles(){
		ArrayList<Title> list = new ArrayList<Title>();
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = con.prepareStatement("SELECT * FROM Titles");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {	
				Title title = new Title(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), new ProductType(resultSet.getString(4)));
				list.add(title);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	//Thêm một title mới vào database
	public boolean addTitle(Title title) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("INSERT INTO Titles VALUES ('',?,?,?)");
			statement.setString(1, title.getNameTitle());
			statement.setString(2, title.getType());
			statement.setString(3, title.getpType().getIdType());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return n>0;
	}
	
	
	//Xóa records theo id
	public boolean deleteTitle(String id) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		
		try {
			statement = con.prepareStatement("DELETE Titles WHERE idTitle = ?");
			statement.setString(1, id);
			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n>0;
	}
	
	//Tìm kiếm tiêu đề theo id
	public Title getTitleById(String id){
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		Title title = new Title();

		try {
			statement = con.prepareStatement("SELECT * FROM Titles where idTitle = ?");
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {	
				title = new Title(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), new ProductType(resultSet.getString(4)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return title;
	}
	
	//Tìm kiếm tiêu đề theo tên
	public String getTitleByName(String name) throws SQLException{
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		String id ="";
		try {
			statement = con.prepareStatement("SELECT * FROM Titles where nameTitle LIKE ?");
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {	
				//Title title = new Title(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
				id = resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}
	
	//Đếm số lượng records
	public int countTitles() {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n=0;
		try {
			statement = con.prepareStatement("SELECT COUNT(idTitle) FROM Titles");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {	
				n = resultSet.getInt(1);				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n; 
	}
}
