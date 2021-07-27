package fit.iuh.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fit.iuh.edu.entity.Customer;

public class CustomerDAO {
	//Lấy dữ liệu tất cả khách hàng có trong Database
	public ArrayList<Customer> getAllCustomers(){
		ArrayList<Customer> list = new ArrayList<>();
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = con.prepareStatement("SELECT * FROM Customers");
			ResultSet resultSet = statement.executeQuery();	//Thực thi câu lệnh SQL trả về đối tượng Resultset
			//Duyệt Kết quả trả về
			while (resultSet.next()) {	//Di chuyển con trỏ xuống bản ghi kế tiếp
				Customer c = new Customer(resultSet.getString(1), 
						resultSet.getString(2), 
						resultSet.getString(3), 
						resultSet.getString(4));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	//Thêm dữ liệu của khách hàng mới vào database
	public boolean addCustomer(Customer customer) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("INSERT INTO Customers VALUES ('',?,?,?)");
			statement.setString(1, customer.getFullName());
			statement.setString(2, customer.getAddress());
			statement.setString(3, customer.getPhoneNumber());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return n>0;
	}
	
	//Cập nhật dữ liệu của khách hàng trong database
	public boolean updateCustomer(Customer customer) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		
		try {
			statement = con.prepareStatement("UPDATE Customers SET fullName = ?, address = ?, phoneNumber = ? WHERE idCustomer = ?");
			statement.setString(1, customer.getFullName());
			statement.setString(2, customer.getAddress());
			statement.setString(3, customer.getPhoneNumber());
			statement.setString(4, customer.getIdCustomer());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean deleteCustomer(String id) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		
		try {
			statement = con.prepareStatement("DELETE Customers WHERE idCustomer = ?");
			statement.setString(1, id);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
	//Lấy khách hàng thông qua mã
	public Customer getCustomerByid(String id) {
		Connection con = Database.getConnection();
		PreparedStatement statement = null;
		Customer customer = new Customer();
		
		try {
			statement = con.prepareStatement("SELECT * FROM Customers WHERE idCustomer = ?");
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();	
			while (resultSet.next()) {	
				Customer c = new Customer(resultSet.getString(1), 
						resultSet.getString(2), 
						resultSet.getString(3), 
						resultSet.getString(4));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customer;
	}
}
