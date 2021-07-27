package fit.iuh.edu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fit.iuh.edu.entity.RentSlip;

public class RentSlipDAO {
	
	//Thêm phiếu thuê vào datebase
	public boolean add(RentSlip slip) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		
		try {
			statement = con.prepareStatement("INSERT INTO RentSlips VALUES ('', ?, ?)");
			statement.setDate(1, (Date) slip.getRentalDate());
			statement.setString(2, slip.getIdCustomer());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
	//Lấy record cuối cùng trong bảng
	public String getAtLastID() {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		String id = "";
		
		try {
			statement = con.prepareStatement("SELECT TOP 1 idRent asLastID FROM RentSlips ORDER BY idRent DESC");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {	
				id = resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
		return id;
	}
}
