
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

public class FunctionPanelOfManagerView extends JFrame implements ActionListener{
	//Khai báo các biến liên quan
	 
	private static final long serialVersionUID = 1L;
	private JPanel pNorth, pWest, pEast, pCenter, pSouth;
	private JLabel lblTitle;
	private JButton btnExit, btnLogOut, btnManageTitle, btnManageCustomer, btnManageProduct, btnRental, btnDelete;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	
	public FunctionPanelOfManagerView() {
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
		
		btnManageProduct= new JButton("Quản lý kho DVD/Disk");		//Tạo nút mở giao diện thêm - xóa DVD/Disk
		btnManageTitle= new JButton("Quản lý tiêu đề");				//Tạo nút mở giao diện thêm - xóa tiêu đề
		btnManageCustomer = new JButton("Thêm - Sửa Khách Hàng");	//Tạo nút mở giao diện thêm - sửa Khách Hàng
		btnRental = new JButton("Thuê đĩa");
		btnDelete = new JButton("Xóa Khách hàng");
		
		btnManageProduct.addActionListener(this);
		btnManageTitle.addActionListener(this);
		btnManageCustomer.addActionListener(this);
		btnRental.addActionListener(this);
		btnDelete.addActionListener(this);
		
		pCenter.add(btnRental);
		pCenter.add(btnManageProduct);
		pCenter.add(btnManageTitle);
		pCenter.add(btnManageCustomer);
		pCenter.add(btnDelete);
	}
	
	
	//Tạo nội dung về phần South của GUI
	
	public void createSouthContent() {
		pSouth = new JPanel();
		add(pSouth, BorderLayout.SOUTH);
		pSouth.setPreferredSize(new Dimension(80,80));

		btnLogOut = new JButton("Đăng xuất"); 	// Tạo nút đăng nhập
		btnExit = new JButton("Thoát");	// Tạo nút Thoát
		
		
		// Bắt sự kiện cho các nút
		btnExit.addActionListener(this);
		btnLogOut.addActionListener(this);
		
		pSouth.add(btnLogOut);
		pSouth.add(btnExit);;
	}
	
	
	// Xử lý sự kiện cho các nút
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnExit)) {
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?", "Thoát",
					JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
				System.exit(0);		//Đóng chương trình
			}
		} else if (o.equals(btnLogOut)) {
			new FunctionPanelView();
			dispose();
		} else if (o.equals(btnManageProduct)) {
			new ProductView();
			dispose();
		} else if (o.equals(btnManageTitle)) {
			new TitleView();
			dispose();
		} else if (o.equals(btnManageCustomer)) {
			new CustomerViewOfManager();
			dispose();
		} else if (o.equals(btnRental)) {
			new RentSlipViewOfManager();
			dispose();
		}
	}
}
