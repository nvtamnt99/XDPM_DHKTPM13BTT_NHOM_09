package fit.iuh.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fit.iuh.edu.entity.Reservation;

public class ReservationDAO {
	
	//Ghi nhận thanh toán	 
	public boolean update(Reservation reservation) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		
		try {
			statement = con.prepareStatement("UPDATE Reservations set status = 0 where idReservation = ?");
			statement.setString(1, reservation.getIdReservation());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
	//Kiểm tra phí trễ hạn của khách hàng thông qua mã
	public boolean checkLateChargeByID(String id) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		
		try {
			statement = con.prepareStatement("SELECT count(idReservation) " + 
											"FROM Reservations " + 
											"WHERE status = 0 AND idCustomerR = ?");
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {	
				n = resultSet.getInt(1);				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
}
