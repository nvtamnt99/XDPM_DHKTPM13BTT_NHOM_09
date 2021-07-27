package fit.iuh.edu.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import fit.iuh.edu.dao.CustomerDAO;
import fit.iuh.edu.entity.Customer;

public class DeleteCustomerView extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pNorth, pWest, pEast, pCenter, pSouth;
	private JLabel lblID, lblFullName, lblAddress, lblPhoneNumber;
	private JTextField txtID, txtFullName, txtAddress, txtPhoneNumber;
	private JButton btnExit, btnDelete;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private DefaultTableModel tblModle;
	private JTable tbCustomer;
	private CustomerDAO customerDAO = new CustomerDAO();
	ArrayList<Customer> list = new ArrayList<Customer>();

	public DeleteCustomerView() {
		// TODO Auto-generated constructor stub
		createFrame();
	}

	/**
	 * Tạo JFrame
	 */
	public void createFrame() {
		
		createMenu();
		createNorthContent();
		createWestContent();
		createCenterContent();
		createEastContent();
		createSouthContent();

		setTitle("Xóa Khách hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 570);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Tạo menu
	 */
	public void createMenu() {

		// Tạo thanh menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("Tiêu đề");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Quản lý tiêu đề"); // tạo menu item
		mnNewMenu.add(mntmNewMenuItem);
	}

	//Tạo nội dung về phần pNorth của GUI
	
	public void createNorthContent() {
		pNorth = new JPanel();
		add(pNorth, BorderLayout.NORTH);
				
		pNorth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Thông tin khách hàng"));
		pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));
		
		
		Box box = Box.createVerticalBox();
		Box b1, b2, b3, b4;
		
		box.add(b1 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b1.add(lblID = new JLabel("Mã khách hàng: "));
		b1.add(txtID = new JTextField());
		
		box.add(b2 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b2.add(lblFullName = new JLabel("Họ tên khách hàng: "));
		b2.add(txtFullName = new JTextField());
		
		box.add(b3 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b3.add(lblAddress = new JLabel("Địa chỉ: "));
		b3.add(txtAddress = new JTextField());
		
		box.add(b4 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b4.add(lblPhoneNumber = new JLabel("Số điện thoại: "));
		b4.add(txtPhoneNumber = new JTextField());
		
		lblID.setPreferredSize(lblFullName.getPreferredSize());
		lblAddress.setPreferredSize(lblFullName.getPreferredSize());
		lblPhoneNumber.setPreferredSize(lblFullName.getPreferredSize());
		pNorth.add(box);
	}
	
	
	
	
	//Tạo nội dung về phần pWest của GUI
	
	public void createWestContent() {
		pWest = new JPanel();
		add(pWest, BorderLayout.WEST);
	}
	
	
	
	
	
	 // Tạo nội dung về phần pEast của GUI
	
	public void createEastContent() {
		pEast = new JPanel();
		add(pEast, BorderLayout.EAST);

	}
	
	
	
	
	//Tạo nội dung về phần pCenter của GUI
	 
	public void createCenterContent() {
		pCenter = new JPanel();

		btnDelete = new JButton("Xóa"); // Tạo nút thêm
		btnExit = new JButton("Thoát"); // Tạo nút thoát

		// Bắt sự kiện cho các nút
		btnExit.addActionListener(this);
		btnDelete.addActionListener(this);

		pCenter.add(btnDelete);
		pCenter.add(btnExit);
		
		add(pCenter, BorderLayout.CENTER);
	}
	
	
	
	
	//Tạo nội dung về phần pSouth của GUI
	
	public void createSouthContent() {
		pSouth = new JPanel();
		add(pSouth, BorderLayout.SOUTH);
		
		pSouth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Danh sách khách hàng"));
		pSouth.setLayout(new BoxLayout(pSouth, BoxLayout.Y_AXIS));
		
		// Tạo bảng
		String[] header = { "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại" };
		tblModle = new DefaultTableModel(header, 0);

		JScrollPane scrPane = new JScrollPane(tbCustomer = new JTable(tblModle));
		tbCustomer.setRowHeight(25);
		tbCustomer.setAutoCreateRowSorter(true);
		tbCustomer.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tbCustomer.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				fillForm(tbCustomer.getSelectedRow());
			}
		});
		
		setEnable();
		showData();	// Hiển thị dữ liệu
		pSouth.setPreferredSize(new Dimension(0,320));
		pSouth.add(scrPane);
	}
	
	
	
	
	//Xử lý sự kiện cho các nút
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();		
		
		if (o.equals(btnExit)) {
			if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chương trình không?", "Thoát",
					JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
				System.exit(0); // Đóng chương trình
			}
		} else if (o.equals(btnDelete)) {
			delete();
		} 
	}
	
	
	//Phương thức xóa một khách hàng
	
	private void delete() {
		// TODO Auto-generated method stub
		if (checkEmpty()) {
			String id = txtID.getText().toString().trim();
			
			Customer customer = new Customer(id);
			
			if (list.contains(customer)) {
				customer = list.get(list.indexOf(customer));
				if (JOptionPane.showConfirmDialog(this,  "Bạn có muốn xóa khách hàng " + "'" + customer.getFullName() + "'" + " không?", "Xóa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					customerDAO.deleteCustomer(id);	//Xóa khách hàng trong database
					tblModle.removeRow(tbCustomer.getSelectedRow());
					txtID.requestFocus();
					JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!!!", "Thành công", JOptionPane.PLAIN_MESSAGE);
				}				
			}else {
				JOptionPane.showMessageDialog(this, "Mã khách hàng không tồn tại", "Không tìm thấy", JOptionPane.ERROR_MESSAGE);
				txtID.requestFocus();
			}
			
		}
	}
	
	

	//Hàm hiển thị trên các components
	
	private void fillForm(int row) {
		if (row != -1) {
			String id = (String) tbCustomer.getValueAt(row, 0);
			Customer customer = new Customer(id);
			if (list.contains(customer)) {
				customer = list.get(list.indexOf(customer));
				txtID.setText(customer.getIdCustomer());
				txtFullName.setText(customer.getFullName());
				txtAddress.setText(customer.getAddress() + "");
				txtPhoneNumber.setText(customer.getPhoneNumber() + "");
			}
		}
	}
	
	
	
	//Đổ dữ liệu vào bảng
	
	private void showData() {
		list = customerDAO.getAllCustomers();
		try {
			for (Customer customer : list) {
				addRowTable(customer);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//Thêm record vào table
	private void addRowTable(Customer customer) {
		String[] rowData = {customer.getIdCustomer(), customer.getFullName(), customer.getAddress(), customer.getPhoneNumber()};
		tblModle.addRow(rowData);
	}
	
	
	
	
	//Kiểm tra chuỗi rỗng
	
	private boolean checkEmpty() {
		if (txtID.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng!!!", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
			txtID.requestFocus();
			return false;
		}
		return true;
	}
	
	private void setEnable() {
		txtFullName.setEditable(false);
		txtAddress.setEditable(false);
		txtPhoneNumber.setEditable(false);
	}

}
