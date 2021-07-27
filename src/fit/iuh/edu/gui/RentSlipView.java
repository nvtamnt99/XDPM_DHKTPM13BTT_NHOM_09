package fit.iuh.edu.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import fit.iuh.edu.dao.CustomerDAO;
import fit.iuh.edu.dao.ProductDAO;
import fit.iuh.edu.dao.RentSlipDAO;
import fit.iuh.edu.dao.RentSlipDetailsDAO;
import fit.iuh.edu.dao.ReservationDAO;
import fit.iuh.edu.dao.TitleDAO;
import fit.iuh.edu.entity.Customer;
import fit.iuh.edu.entity.Product;
import fit.iuh.edu.entity.RentSlip;
import fit.iuh.edu.entity.RentSlipDetails;
import fit.iuh.edu.entity.Title;

public class RentSlipView extends JFrame implements ActionListener{
	/**
	 * Khai báo các biến liên quan
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pNorth, pWest, pEast, pCenter, pSouth;
	private JLabel lblCustomerID, lblProductID, lblRentDate, lblReturnDate, lblView, lblTitleTotal, lblTotal;
	private JTextField txtIdCustomer, txtIdProduct;
	private JButton btnExit, btnAdd, btnSuccess;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private DefaultTableModel tblModle;
	private JTable tbRental;
	
	private CustomerDAO customerDAO = new CustomerDAO();
	private ProductDAO productDAO = new ProductDAO();
	private TitleDAO titleDAO = new TitleDAO();
	private RentSlipDAO slipDAO = new RentSlipDAO();
	private RentSlipDetailsDAO detailsDAO = new RentSlipDetailsDAO();
	private ReservationDAO reservationDAO = new ReservationDAO();
	
	
	ArrayList<Customer> listCustomers = new ArrayList<Customer>();
	ArrayList<Product> listProducts = new ArrayList<Product>();
	ArrayList<RentSlipDetails> listDetails = new ArrayList<RentSlipDetails>();

	Date date = new Date();
	JDateChooser dateChooser = new JDateChooser();
	
	public RentSlipView() {
		createFrame();
	}

	/**
	 * Tạo JFrame
	 */
	public void createFrame() {
				
		createMenu();
		createNorthContent();
		createCenterContent();
		createSouthContent();
		
		setTitle("Phiếu Thuê");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 570);
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
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = format.format(date);
				
		pNorth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Thông tin phiếu thuê"));
		pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));
		
		
		Box box = Box.createHorizontalBox();
		Box b1, b2, b3, b4, b5;
		
		box.add(b1 = Box.createHorizontalBox());
		box.add(Box.createHorizontalStrut(10));
		b1.add(lblCustomerID = new JLabel("Mã khách hàng: "));
		b1.add(txtIdCustomer = new JTextField());
		
		box.add(b2 = Box.createHorizontalBox());
		box.add(Box.createHorizontalStrut(10));
		b2.add(setLblProductID(new JLabel("Mã DVD/Disk: ")));
		b2.add(txtIdProduct = new JTextField());
				
		box.add(b3 = Box.createHorizontalBox());
		box.add(Box.createHorizontalStrut(10));
		b3.add(lblRentDate = new JLabel("Ngày thuê: "));
		b3.add(lblView = new JLabel(strDate));
		
		dateChooser.setPreferredSize(new Dimension(30,20));
		dateChooser.getDateEditor().setEnabled(false);		// Khóa ô text trong JDateChooser
				
		box.add(b4 = Box.createHorizontalBox());
		box.add(Box.createHorizontalStrut(10));
		b4.add(lblReturnDate = new JLabel("Ngày trả: "));
		b4.add(dateChooser);
		
		box.add(b5 = Box.createHorizontalBox());
		box.add(Box.createHorizontalStrut(10));
		b5.add(btnAdd = new JButton("Thêm"));
		
		btnAdd.addActionListener(this);
		txtIdCustomer.addKeyListener(new KeyAdapter() {
			@Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	            	if (checkCustomerID(txtIdCustomer.getText().toString().trim())) {
					}
	            }
	        }
		});
		txtIdProduct.addKeyListener(new KeyAdapter() {
			@Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	            	if (checkProductID(txtIdProduct.getText().toString().trim())) {
					}
	            }
	        }
		});
		
		pNorth.add(box);
	}
	
	
	
	//Tạo nội dung về phần pWest của GUI
	
	public void createWestContent() {
		pWest = new JPanel();
		add(pWest, BorderLayout.WEST);
	}
	
	
	
	

	//Tạo nội dung về phần pEast của GUI
	
	public void createEastContent() {
		pEast = new JPanel();
		add(pEast, BorderLayout.EAST);

	}
	
	
	
	
	//Tạo nội dung về phần pCenter của GUI
	
	public void createCenterContent() {
		pCenter = new JPanel();		
		add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(null);
		
		pCenter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Danh sách DVD/Disk"));
		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
		
		// Tạo bảng
		String[] header = { "Mã DVD/Disk", "Tên tiêu Đề", "Ngày thuê", "Ngày trả","Tiền thuê"};
		tblModle = new DefaultTableModel(header, 0);

		JScrollPane scrPane = new JScrollPane(tbRental = new JTable(tblModle));
		tbRental.setRowHeight(25);
		tbRental.setAutoCreateRowSorter(true);
		tbRental.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		pCenter.add(scrPane);
		
		Box box = Box.createHorizontalBox();
		Box b1;
		
		box.add(b1 = Box.createHorizontalBox());
		box.add(Box.createHorizontalStrut(10));
		b1.add(lblTitleTotal = new JLabel("Tổng tiền: "));
		b1.add(lblTotal = new JLabel("0"));
		
		
		lblTotal.setBounds(775, 462, 54, 28);
		pCenter.add(box);
	}
	
	
	
	//Tạo nội dung về phần pSouth của GUI
	
	public void createSouthContent() {
		pSouth = new JPanel();
		add(pSouth, BorderLayout.SOUTH);
		
		btnSuccess = new JButton("Hoàn thành");
		btnExit = new JButton("Hủy");		
		
		btnSuccess.addActionListener(this);
		btnExit.addActionListener(this);
		
		pSouth.add(btnSuccess);
		pSouth.add(btnExit);
		
		pSouth.setPreferredSize(new Dimension(100,50));
	}
	
	
	
	//Xử lý sự kiện cho các nút
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();		
		
		if (o.equals(btnExit)) {
			new FunctionPanelView();
			dispose();
		} else if (o.equals(btnAdd)) {
			add();
		} else if (o.equals(btnSuccess)) {
			String idCustomer = txtIdCustomer.getText().toString().trim();
			
			if (success()) {
				JOptionPane.showMessageDialog(this, "Lập phiếu thành công!!!", "Chúc mừng", JOptionPane.PLAIN_MESSAGE);
				tblModle.getDataVector().removeAllElements();
				setEmpty();
				txtIdCustomer.requestFocus();
				checkReservation(idCustomer);
			}
		}
	}
	
	
	private void checkReservation(String id) {
		// TODO Auto-generated method stub
		if (reservationDAO.checkLateChargeByID(id)) {
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán phí trễ hạn?", "Hỏi nhắc", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				dispose();
			}
		}
	}

	//Hoàn tất, lưu dữ liệu xuống CSDL
	
	private boolean success() {
		// TODO Auto-generated method stub
		String idCustomer = txtIdCustomer.getText().toString().trim();
		String idProduct = txtIdProduct.getText().toString().trim();
		
		if (checkCustomerID(idCustomer) && checkProductID(idProduct)) {
			java.sql.Date rentDate = new java.sql.Date(date.getTime()); //Chuyển util.date sang sql.date
			RentSlip slip = new RentSlip(rentDate, idCustomer);	
			
			slipDAO.add(slip);
			String idRental = slipDAO.getAtLastID();
			for (RentSlipDetails slipDetails : listDetails) {
				detailsDAO.add(idRental, slipDetails);
				productDAO.updateStatus(slipDetails.getProduct().getIdProduct(), "Đã Thuê");
			}
			return true;
		}
		return false;
	}

	
	//Phương thức thêm đĩa vào danh sách
	
	private void add() {
		// TODO Auto-generated method stub	
		
		String idCustomer = txtIdCustomer.getText().toString().trim();
		String idProduct = txtIdProduct.getText().toString().trim();
			
		
		if (checkCustomerID(idCustomer) && checkProductID(idProduct)) {
			Product product = productDAO.getProductByID(idProduct);				
			if (product.getStatus().equals("Trên giá")) {
				try {
					addRowTable(product, date, dateChooser);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Đĩa đã được cho thuê hoặc đặt trước!!!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Nhập ngày trả!!!", "Thông báo", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	
	
	//Thêm record vào table 
	
	private void addRowTable(Product product, Date rentDate, JDateChooser returnDate) throws ParseException {
		RentSlipDetails details = new RentSlipDetails();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");			//Định dạng ngày cho Date
		DateFormat df = new SimpleDateFormat("dd-MM-YYYY");						//Định dạng ngày cho JDateChooser
		Title title = titleDAO.getTitleById(product.getTitle().getIdTitle());	//Lấy tiêu đề
		long price = productDAO.getPriceByID(product.getIdProduct());			//Lấy giá thuê
		
	
		String strRentDate = df.format(returnDate.getDate());
		Date date1 = format.parse(strRentDate);
		details = new RentSlipDetails(price, date1, product);
		
		if (rentDate.compareTo(date1) < 0) {
			if (!(listDetails.contains(details))) {	//Kiểm tra DVD/Disk thêm vào danh sách 
				listDetails.add(details);
				String[] rowData = {product.getIdProduct(), title.getNameTitle(), df.format(rentDate), df.format(returnDate.getDate()), String.valueOf(price)};
				tblModle.addRow(rowData);
				RentSlip slip = new RentSlip(rentDate, listDetails);
				long total = slip.calcTotalAmount();
				lblTotal.setText(String.valueOf(total));
			} else {
				JOptionPane.showMessageDialog(this, "DVD/Disk đã được thêm vào danh sách", "Thông báo", JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Ngày trả nhỏ hơn ngày hiện hành", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	
	//Kiểm tra mã khách hàng
	

	private boolean checkCustomerID(String id) {
		listCustomers = customerDAO.getAllCustomers();
		
		if (!(id.length() == 0)) {
			Customer customer = new Customer(id);
			if (listCustomers.contains(customer)) {
				txtIdProduct.requestFocus();
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Mã không tồn tại!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				txtIdCustomer.requestFocus();
				return false;
			}
    	} else {
    		JOptionPane.showMessageDialog(null, "Nhập mã khách hàng!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    		txtIdCustomer.requestFocus();
    		return false;
		}
	}
	
	//Kiểm tra mã DVD/Disk
	
	private boolean checkProductID(String id) {
		listProducts = productDAO.getAllProducts();
		
		if (!(id.length() == 0)) {
			Product product = new Product(id);
			if (listProducts.contains(product)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Mã không tồn tại!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				txtIdProduct.requestFocus();
				return false;
			}
    	} else {
    		JOptionPane.showMessageDialog(null, "Nhập mã đĩa!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    		txtIdProduct.requestFocus();
    		return false;
		}
	}

	public JLabel getLblProductID() {
		return lblProductID;
	}

	public JLabel setLblProductID(JLabel lblProductID) {
		this.lblProductID = lblProductID;
		return lblProductID;
	}
	
	//Hàm đặt chuỗi rỗng
	
	private void setEmpty() {
		txtIdCustomer.setText("");
		txtIdProduct.setText("");
	}
}
