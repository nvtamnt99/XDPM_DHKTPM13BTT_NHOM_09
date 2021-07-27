package fit.iuh.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fit.iuh.edu.entity.Account;

public class AccountDAO {
	public boolean login(Account account) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("SELECT * FROM Accounts where id = ? AND Password = ?");
			statement.setString(1, account.getId());
			statement.setString(2, account.getPassword());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {	
				n++;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return n>0;
	}
}
