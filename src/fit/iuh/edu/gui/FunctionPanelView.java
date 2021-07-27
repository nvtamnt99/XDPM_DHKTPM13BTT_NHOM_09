package fit.iuh.edu.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FunctionPanelView extends JFrame implements ActionListener{
	/**
	 * Khai báo các biến liên quan
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pNorth, pWest, pEast, pCenter, pSouth;
	private JLabel lblTitle;
	private JButton btnExit, btnLogin, btnManageCustomer, btnSearchProduct, btnSearchTitle, btnRental;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	
	public FunctionPanelView() {
		// TODO Auto-generated constructor stub
		createFrame();
	}
	
	
	/**
	 * Tạo JFrame Title
	 * */
	public void createFrame() {
		
		
		createMenu();
		createNorthContent();
		createWestContent();
		createCenterContent();
		createEastContent();
		createSouthContent();
		
		
		setTitle("Phần Mềm Quản Lý Bán Đĩa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Tạo menu
	 * */
	public void createMenu() {
		
		// Tạo thanh menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Tiêu đề");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Quản lý tiêu đề"); // tạo menu item
		mnNewMenu.add(mntmNewMenuItem);
	}
	
	//Tạo nội dung về phần north của GUI
	
	public void createNorthContent() {
		pNorth = new JPanel();
		add(pNorth, BorderLayout.NORTH);
		
		Font font = new Font("Times New Roman", Font.BOLD, 45);
		
		pNorth.add(lblTitle = new JLabel("Quản lý cho thuê băng đĩa"));
		lblTitle.setFont(font);
	}
	
	
	
	
	
	//Tạo nội dung về phần West của GUI
	
	public void createWestContent() {
		pWest = new JPanel();
		add(pWest, BorderLayout.WEST);
	}
	
	
	//Tạo nội dung về phần East của GUI
	
	public void createEastContent() {
		pEast = new JPanel();
		add(pEast, BorderLayout.EAST);
		
		
	}
	
	
	//Tạo nội dung về phần Center của GUI
	
	public void createCenterContent() {
		pCenter = new JPanel();
		add(pCenter, BorderLayout.CENTER);
		
		btnSearchProduct= new JButton("Tra cứu DVD/Disk");		//Tạo nút mở giao diện thêm - xóa DVD/Disk
		btnSearchTitle= new JButton("Tra cứu tiêu đề");				//Tạo nút mở giao diện thêm - xóa tiêu đề
		btnManageCustomer = new JButton("Thêm - Sửa Khách Hàng");	//Tạo nút mở giao diện thêm - sửa Khách Hàng
		btnRental = new JButton("Thuê đĩa");
		
		btnSearchTitle.addActionListener(this);
		btnSearchProduct.addActionListener(this);
		btnManageCustomer.addActionListener(this);
		btnRental.addActionListener(this);
		
		pCenter.add(btnRental);
		pCenter.add(btnSearchTitle);
		pCenter.add(btnSearchProduct);
		pCenter.add(btnManageCustomer);
	}
	
	
	//Tạo nội dung về phần South của GUI
	
	public void createSouthContent() {
		pSouth = new JPanel();
		add(pSouth, BorderLayout.SOUTH);
		pSouth.setPreferredSize(new Dimension(80,80));

		btnLogin = new JButton("Đăng nhập"); 	// Tạo nút đăng nhập
		btnExit = new JButton("Thoát");	// Tạo nút Thoát
		
		// Bắt sự kiện cho các nút
		btnExit.addActionListener(this);
		btnLogin.addActionListener(this);
		
		pSouth.add(btnLogin);
		pSouth.add(btnExit);;
	}
	
	
	//Xử lý sự kiện cho các nút
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnExit)) {
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?", "Thoát",
					JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
				System.exit(0);		//Đóng chương trình
			}
		} else if (o.equals(btnLogin)) {
			new LoginView();
			dispose();
		} else if (o.equals(btnSearchProduct)) {
			JOptionPane.showMessageDialog(null, "Chức năng này hiện đang cập nhậ (^_^)");
//			dispose();
		} else if (o.equals(btnSearchTitle)) {
			
			new SearchTitleView();
			dispose();
		} else if (o.equals(btnManageCustomer)) {
			new CustomerView();
			dispose();
		} else if (o.equals(btnRental)) {
			new RentSlipView();
			dispose();
		}
	}
}
