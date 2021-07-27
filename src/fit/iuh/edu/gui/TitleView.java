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

import fit.iuh.edu.dao.ProductTypeDAO;
import fit.iuh.edu.dao.TitleDAO;
import fit.iuh.edu.entity.ProductType;
import fit.iuh.edu.entity.Title;

public class TitleView extends JFrame implements ActionListener{
	/**
	 * Khởi tạo các biến
	 */
	private static final long serialVersionUID = 1L;	
	private JPanel pNorth, pWest, pEast, pCenter, pSouth;
	private JLabel lblID, lblName, lblType;
	private JTextField txtID, txtName; 
	private JButton btnExit, btnAdd, btnDelete, btnSelect;
	JComboBox<String> jComboBox;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private DefaultTableModel tableModel;
	private JTable tbTitle;
	
	ArrayList<Title> listTitles = new ArrayList<Title>();
	private TitleDAO titleDAO = new TitleDAO();
	private ProductTypeDAO productTypeDAO = new ProductTypeDAO();
	
	public TitleView() {
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
		
		
		setTitle("Thêm - Xóa tiêu đề");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 570);
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
		
		pNorth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Thông tin tiêu đề"));
		pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));
		
		Box box = Box.createVerticalBox();
		Box b1, b2, b3;
		
		box.add(b1 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b1.add(lblID = new JLabel("Mã tiêu đề: "));
		b1.add(txtID = new JTextField());
		
		box.add(b2 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b2.add(lblName = new JLabel("Tên tiêu đề: "));
		b2.add(txtName = new JTextField());
		
		box.add(b3 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b3.add(lblType = new JLabel("Thể loại:"));
		b3.add(jComboBox = new JComboBox<String>());
		
		String[] types = {"Phim", "Trò chơi"};
		jComboBox.setModel(new DefaultComboBoxModel<String>(types));
		
		lblID.setPreferredSize(lblName.getPreferredSize());
		lblType.setPreferredSize(lblName.getPreferredSize());
		pNorth.add(box);
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
		btnSelect = new JButton("Tìm kiếm");//Tạo nút tìm
		
		// Bắt sự kiện cho các nút
		btnExit.addActionListener(this);
		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSelect.addActionListener(this);
		
		pCenter.add(btnSelect);
		pCenter.add(btnAdd);
		pCenter.add(btnDelete);
		pCenter.add(btnExit);
	}
	
	
	//Tạo nội dung về phần South của GUI
	
	public void createSouthContent() {
		pSouth = new JPanel();
		add(pSouth, BorderLayout.SOUTH);
		
		pSouth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Danh sách tiêu đề"));
		pSouth.setLayout(new BoxLayout(pSouth, BoxLayout.Y_AXIS));
		
		String[] header = {"Mã tiêu đề", "Tên tiêu Đề", "Thể loại"};
		tableModel = new DefaultTableModel(header, 0);
		
		JScrollPane scrPane = new JScrollPane(tbTitle = new JTable(tableModel));
		tbTitle.setRowHeight(25);
		tbTitle.setAutoCreateRowSorter(true);
		tbTitle.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tbTitle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				fillForm(tbTitle.getSelectedRow());
			}
		});
		
		
		showData();
		
		pSouth.setPreferredSize(new Dimension(0,350));
		pSouth.add(scrPane);
	}
	
	
	// Xử lý sự kiện cho các nút
	
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
		}else if (o.equals(btnSelect)) {
			selectTitle();
		}
	}
	
	
	/**
	 * Phương thức xóa 
	 * */
	private void delete() {
		// TODO Auto-generated method stub
		if (txtID.getText().trim().length()==0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã tiêu đề!!!", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
			txtID.requestFocus();
		}else {
			String id = txtID.getText().toString().trim();
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa tiêu đề này???", "Xóa tiêu đề", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
				if (titleDAO.deleteTitle(id)) {
					tableModel.removeRow(tbTitle.getSelectedRow());
					txtID.requestFocus();
					JOptionPane.showMessageDialog(this, "Xóa tiêu đề thành công!!!", "Thành công", JOptionPane.PLAIN_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(this, "Xóa tiêu đề thất bại!!!", "Thất bại",JOptionPane.NO_OPTION);
				}
			}
		}
	}


	//Phương thức tìm kiếm
	
	private void selectTitle() {		
		if (!txtID.getText().equals("")) {
			String id = txtID.getText().toString().trim();
			Title title = new Title(id);
			
			if (listTitles.contains(title)) {
				title = listTitles.get(listTitles.indexOf(title));
				tableModel.getDataVector().removeAllElements();
				addRowTable(title);
			}
		}else {
			tableModel.getDataVector().removeAllElements();
			showData();
		}
	}
	
	//Hàm hiển thị trên các components
	
	private void fillForm(int row) {
		if (row != -1) {
			String id = (String) tbTitle.getValueAt(row, 0);
			Title title = new Title(id);
			if (listTitles.contains(title)) {
				title = listTitles.get(listTitles.indexOf(title));
				txtID.setText(title.getIdTitle().toString());
				txtName.setText(title.getNameTitle()+"");
				jComboBox.setSelectedItem(title.getType());
	 		}
		}
	}
	
	//Đổ dữ liệu vào bảng

	private void showData() {
		listTitles = titleDAO.getAllTitles();
		try {
			for (Title title: listTitles) {
				addRowTable(title);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//Thêm record vào table

	private void addRowTable(Title title) {
		String[] rowData = {title.getIdTitle(), title.getNameTitle(), title.getType()};
		tableModel.addRow(rowData);
	}
	
	
	
	//Phương thức thêm dữ liệu
	
	private void add() {
		ProductType product = new ProductType();;
		
		if (checkEmpty()) {
			String id = txtID.getText().toString().trim();
			String name = txtName.getText().toString();
			String type = "";
			if (jComboBox.getSelectedIndex() != -1) {
				type = jComboBox.getItemAt(jComboBox.getSelectedIndex());
				System.out.println(type);
			}
			if (type.equalsIgnoreCase("Phim")) {
				String idType = "DVD001";
				product = productTypeDAO.getTypeById(idType);
				Title title = new Title(id, name, type, product);
				listTitles = titleDAO.getAllTitles();
				if (!listTitles.contains(title)) {
					titleDAO.addTitle(title);
					addRowTable(title);
					JOptionPane.showMessageDialog(this, "Thêm tiêu đề thành công!!!", "Thành công", JOptionPane.PLAIN_MESSAGE);
					txtName.requestFocus();
				}
			}else if (type.equalsIgnoreCase("Trò chơi")) {
				String idType = "Disk001";
				product = productTypeDAO.getTypeById(idType);
				System.out.println(product.getIdType());
				Title title = new Title(name, type, product);
				listTitles = titleDAO.getAllTitles();
				if (!listTitles.contains(title)) {
					titleDAO.addTitle(title);
					addRowTable(title);
					JOptionPane.showMessageDialog(this, "Thêm tiêu đề thành công!!!", "Thành công", JOptionPane.PLAIN_MESSAGE);
					txtName.requestFocus();
				}
			}
			
		}
	}
	
	//Kiểm tra chuỗi rỗng
	
	private boolean checkEmpty() {
		if (txtID.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã tiêu đề!!!", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
			txtID.requestFocus();
			return false;
		} else if (txtName.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tiêu đề!!!", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
			txtName.requestFocus();
			return false;
		}
		return true;
	}
	
	//Hàm đặt chuỗi rỗng
	
	public void setEmpty() {
		txtID.setText("");
		txtName.setText("");
	}
}
