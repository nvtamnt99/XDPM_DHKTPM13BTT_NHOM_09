package fit.iuh.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fit.iuh.edu.entity.Product;
import fit.iuh.edu.entity.Title;

public class ProductDAO {
	
	 //Lấy tất cả dữ liệu products trong Database
	
	public ArrayList<Product> getAllProducts(){
		ArrayList<Product> list = new ArrayList<Product>();
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = con.prepareStatement("SELECT idProduct, status, nameTitle "
											+ "FROM Products "
											+ "INNER JOIN Titles "
											+ "ON Products.idTitle = Titles.idTitle ");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {	
				Product product= new Product(resultSet.getString(1), resultSet.getString(2), new Title(resultSet.getString(3)));
				list.add(product);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	//Thêm một record mới vào database
	
	public boolean addProduct(Product product) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("INSERT INTO Products VALUES (?,?,?)");
			statement.setString(1, product.getIdProduct());
			statement.setString(2, product.getStatus());
			statement.setString(3, product.getTitle().getIdTitle());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return n>0;
	}
	
	
	
	//Xóa record theo id
	
	public boolean deleteProduct(String id) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		
		try {
			statement = con.prepareStatement("DELETE Products WHERE idProduct = ?");
			statement.setString(1, id);
			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n>0;
	}
	
	
	
	// Lấy record theo mã	 
	public Product getProductByID(String id) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		Product product = new Product();
		
		try {
			statement = con.prepareStatement("SELECT * FROM Products WHERE idProduct = ?");
			statement.setString(1, id);
			
			ResultSet resultSet = statement.executeQuery();	
			while (resultSet.next()) {	
				product = new Product(resultSet.getString(1), resultSet.getString(2), new Title(resultSet.getString(3)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return product;
	}
	
	// Lấy price của record theo mã	
	public long getPriceByID(String id) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		long price = 0;
		
		try {
			statement = con.prepareStatement("SELECT price FROM Products p join Titles t on p.idTitle = t.idTitle join ProductTypes pt on t.idType = pt.idType WHERE idProduct = ?");
			statement.setString(1, id);
			
			ResultSet resultSet = statement.executeQuery();	
			while (resultSet.next()) {	
				price = resultSet.getLong(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return price;
	}
	
	
	//Lấy price của record theo mã
	
	public boolean updateStatus(String id, String str) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		
		try {
			statement = con.prepareStatement("UPDATE Products SET status = ? WHERE idProduct = ?");
			statement.setString(1, str);
			statement.setString(2, id);
			n = statement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n>0;
	}
}
