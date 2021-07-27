package fit.iuh.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fit.iuh.edu.entity.ProductType;

public class ProductTypeDAO {
	
	//Lấy tất cả dữ liệu ProductTypes trong Database	 
	public ArrayList<ProductType> getAllTypes(){
		ArrayList<ProductType> list = new ArrayList<ProductType>();
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = con.prepareStatement("SELECT * FROM Titles");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {	
				ProductType type = new ProductType(resultSet.getString(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getLong(4));
				list.add(type);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}	
	
	
	//Tìm kiếm tiêu đề theo id
	public ProductType getTypeById(String id){
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		ProductType productType = new ProductType();
		try {
			statement = con.prepareStatement("SELECT * FROM ProductTypes where idType = ?");
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {	
				productType = new ProductType(resultSet.getString(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getLong(4));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return productType;
	}
}
