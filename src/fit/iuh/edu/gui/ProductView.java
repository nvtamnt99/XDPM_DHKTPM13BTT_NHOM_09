package fit.iuh.edu.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import fit.iuh.edu.dao.ProductDAO;
import fit.iuh.edu.dao.TitleDAO;
import fit.iuh.edu.entity.Product;
import fit.iuh.edu.entity.Title;

public class ProductView extends JFrame implements ActionListener{

	/**
	 * Khai báo các biến liên quan
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pNorth, pWest, pEast, pCenter, pSouth;
	private JLabel lblID, lblNameTitle;
	private JTextField txtID;
	private JButton btnExit, btnAdd, btnDelete;
	private JComboBox<String> jComboBox;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private DefaultTableModel tableModel;
	private JTable tbProduct;
	
	ArrayList<Product> listProducts = new ArrayList<Product>();
	ArrayList<Title> lisTitles = new ArrayList<Title>();
	private TitleDAO titleDAO = new TitleDAO();
	private ProductDAO productDAO = new ProductDAO();
	
	public ProductView() {
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
		
		
		setTitle("Thêm - Xóa DVD/Disk");
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
		
		pNorth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Thông tin DVD/Disk"));
		pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));
		
		Box box = Box.createVerticalBox();
		Box b1, b2;
		
		box.add(b1 = Box.createHorizontalBox());
		box.add(Box.createHorizontalStrut(10));
		b1.add(lblID = new JLabel("Mã DVD/Disk: "));
		b1.add(txtID = new JTextField());
		
		box.add(b2 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b2.add(lblNameTitle = new JLabel("Tên tiêu đề: "));
		b2.add(jComboBox = new JComboBox<String>());
		updateComboBox();
		
				
		lblNameTitle.setPreferredSize(lblID.getPreferredSize());
		pNorth.add(box);
	}
	
	
	private void updateComboBox() {
		int n = titleDAO.countTitles();
		int i = 0;
		lisTitles = titleDAO.getAllTitles();	

		String[] items = new String[n];
		for (Title title : lisTitles) {
			items[i] = title.getNameTitle();
			i++;
		}
		jComboBox.setModel(new DefaultComboBoxModel<String>(items));
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
		
		
		btnAdd = new JButton("Thêm"); 	// Tạo nút thêm
		btnDelete = new JButton("Xóa"); // Tạo nút sửa
		btnExit = new JButton("Quay lại");	// Tạo nút Thoát
		
		// Bắt sự kiện cho các nút
		btnExit.addActionListener(this);
		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		
		
		pCenter.add(btnAdd);
		pCenter.add(btnDelete);
		pCenter.add(btnExit);
	}
	
	
	//Tạo nội dung về phần South của GUI
	
	public void createSouthContent() {
		pSouth = new JPanel();
		add(pSouth, BorderLayout.SOUTH);
		
		pSouth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Danh sách DVD/Disk"));
		pSouth.setLayout(new BoxLayout(pSouth, BoxLayout.Y_AXIS));
		
		String[] header = {"Mã DVD/Disk", "Trạng thái", "Tên tiêu Đề"};
		tableModel = new DefaultTableModel(header, 0);
		
		JScrollPane scrPane = new JScrollPane(tbProduct = new JTable(tableModel));
		tbProduct.setRowHeight(25);
		tbProduct.setAutoCreateRowSorter(true);
		tbProduct.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tbProduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				fillForm(tbProduct.getSelectedRow());
			}
		});
		
		
		showData();
		
		pSouth.setPreferredSize(new Dimension(0,315));
		pSouth.add(scrPane);
	}
	
	
	//Xử lý sự kiện cho các nút
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnExit)) {
			new FunctionPanelOfManagerView();
			dispose();
		}else if (o.equals(btnAdd)) {
			add();
		}else if (o.equals(btnDelete)) {
			delete();
		}else if (o.equals("")) {
			//selectTitle();
		}
	}
	
	
	//Phương thức xóa dữ liệu
	
	private void delete() {
		// TODO Auto-generated method stub
		if (txtID.getText().trim().length()==0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã tiêu đề!!!", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
			txtID.requestFocus();
		}else {
			String id = txtID.getText().toString().trim();
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa DVD/Disk này???", "Xóa tiêu đề", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
				if (productDAO.deleteProduct(id)) {
					tableModel.removeRow(tbProduct.getSelectedRow());
					txtID.requestFocus();
					JOptionPane.showMessageDialog(this, "Xóa DVD/Disk thành công!!!", "Thành công", JOptionPane.PLAIN_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(this, "Xóa DVD/Disk thất bại!!!", "Thất bại",JOptionPane.NO_OPTION);
				}
			}
		}
	}
	
	
	
	//Đổ dữ liệu vào bảng
	 
	private void showData() {
		listProducts = productDAO.getAllProducts();
		
		try {
			for (Product product: listProducts) {
				addRowTable(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//Thêm record vào table
	
	private void addRowTable(Product product) {
		String[] rowData = {product.getIdProduct(),product.getStatus(),product.getTitle().getIdTitle()};
		tableModel.addRow(rowData);
	}
	
	
	//Hàm hiển thị trên các components
	
	private void fillForm(int row) {
		if (row != -1) {
			String id = (String) tbProduct.getValueAt(row, 0);
			Product product= new Product(id);
			if (listProducts.contains(product)) {
				product = listProducts.get(listProducts.indexOf(product));
				txtID.setText(product.getIdProduct().toString());
				//jComboBox.setSelectedItem(product.get);
	 		}
		}
	}
	
	
	
	//Phương thức thêm dữ liệu
	
	private void add() {
		if (checkEmpty()) {
			String id = txtID.getText().toString().trim();
			String nameTitle ="";
			if (jComboBox.getSelectedIndex() != -1) {
				nameTitle = jComboBox.getItemAt(jComboBox.getSelectedIndex());
			}
			String idTitle;
			try {
				idTitle = titleDAO.getTitleByName(nameTitle);
				Product product = new Product(id,"Trên giá", new Title(idTitle));
				if (!listProducts.contains(product)) {
					productDAO.addProduct(product);
					JOptionPane.showMessageDialog(this, "Thêm đĩa thành công!!!", "Thành công", JOptionPane.PLAIN_MESSAGE);
					txtID.requestFocus();
					tableModel.getDataVector().removeAllElements();
					showData();
				}else {
					JOptionPane.showMessageDialog(this, "Trùng mã đĩa", "Trùng mã", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	//Kiểm tra chuỗi rỗng
	
	private boolean checkEmpty() {
		if (txtID.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã đĩa!!!", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
			txtID.requestFocus();
			return false;
		}
		return true;
	}

}
