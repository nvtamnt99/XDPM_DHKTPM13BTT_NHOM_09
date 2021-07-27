package fit.iuh.edu.main;

import java.sql.Connection;

import fit.iuh.edu.dao.Database;
import fit.iuh.edu.gui.FunctionPanelView;

public class MainTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database.getInstance();
		Connection con = Database.getConnection();
		if (con != null) {
			System.out.println("Connected to success!!!");
			new FunctionPanelView();
		} else {
			System.out.println("Connnect to faild!!!");
		}
		
	}
}
