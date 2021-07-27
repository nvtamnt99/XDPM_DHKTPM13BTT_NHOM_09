package fit.iuh.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import fit.iuh.edu.entity.RentSlipDetails;

public class RentSlipDetailsDAO {
	/**
	 * Thêm phiếu thuê vào database
	 * */
	public boolean add(String id, RentSlipDetails slipDetails) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String date = dateFormat.format(slipDetails.getReturnDate());
		
		try {
			statement = con.prepareStatement("INSERT INTO RentSlipDetails VALUES (?, ?, ?, ?)");
			statement.setString(1, id);
			statement.setString(2, slipDetails.getProduct().getIdProduct());
			statement.setString(3, date );
			statement.setLong(4, slipDetails.getPrice());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
}
