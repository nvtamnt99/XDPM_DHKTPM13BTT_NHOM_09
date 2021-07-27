package fit.iuh.edu.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fit.iuh.edu.dao.AccountDAO;
import fit.iuh.edu.entity.Account;

public class LoginView extends JFrame implements ActionListener{
	/**
	 * Khởi tạo các biến liên quan
	 */
	private static final long serialVersionUID = -8166697674890024812L;
	private JPanel pNorth, pWest, pEast, pCenter, pSouth;
	private JLabel lblTitle, lblID, lblPassWord;
	private JTextField txtID;
	private JPasswordField pfPassWord;
	private JButton btnBack, btnSignin;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	
	private String actionLogin = "Đăng nhập";
	
	private AccountDAO dao = new AccountDAO();
	
	public LoginView() {
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
		
		
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 200);
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
		
		lblTitle = new JLabel("Đăng nhập");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		pNorth.add(lblTitle);
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
	
	
	
	 // Tạo nội dung về phần Center của GUI
	
	public void createCenterContent() {
		pCenter = new JPanel();
		add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
		
		Box box = Box.createVerticalBox();
		Box b1, b2;
		
		box.add(b1 = Box.createHorizontalBox());
		box.add(Box.createHorizontalStrut(10));
		b1.add(lblID = new JLabel("Tài khoản: "));
		b1.add(txtID = new JTextField());
		
		box.add(b2 = Box.createHorizontalBox());
		box.add(Box.createVerticalStrut(10));
		b2.add(lblPassWord = new JLabel("Mật khẩu: "));
		b2.add(pfPassWord = createJPasswordField(actionLogin));
		
		lblPassWord.setPreferredSize(lblID.getPreferredSize());
		pCenter.add(box);	
		
	}
	
	
	private JPasswordField createJPasswordField(String action) {
		pfPassWord = new JPasswordField();
		
		pfPassWord.setActionCommand(action);
		pfPassWord.addActionListener(this);
		return pfPassWord;
	}
	
	
	 //Tạo nội dung về phần South của GUI
	
	public void createSouthContent() {
		pSouth = new JPanel();
		add(pSouth, BorderLayout.SOUTH);
		
		
		btnBack = new JButton("Thoát");			//Tạo nút thoát
		btnSignin= new JButton("Đăng nhập");	//Tạo nút đăng nhập
		
				
		btnBack.addActionListener(this);
		btnSignin.addActionListener(this);
		
		pSouth.add(btnSignin);
		pSouth.add(btnBack);
	}
	
	
	
	 //Xử lý sự kiện cho các nút
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		String command = e.getActionCommand();
		
		if (o.equals(btnBack)) {
			new FunctionPanelView();	//mở view
			dispose();					//Đóng view
		}else if (o.equals(btnSignin) || actionLogin.equals(command)) {
			login();
		}
	}
	
	//Phương thức đăng nhập
	 
	private void login() {
		// TODO Auto-generated method stub
		if (check()) {
			String id = txtID.getText().toString().trim();
			String password = String.copyValueOf(pfPassWord.getPassword());
			Account account = new Account(id, password);
			if (dao.login(account)) {	//Kiểm tra tài khoản mật khẩu
				JOptionPane.showMessageDialog(null, "Đăng nhập thành công!!!", "Thành công", JOptionPane.PLAIN_MESSAGE);
				new FunctionPanelOfManagerView();
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Tài khoản không chính xác!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	

	/**
	 * Kiểm tra dữ liệu nhập
	 * */
	private boolean check() {
		if (txtID.getText().toString().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Tài khoản không để trống!!!", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
			txtID.requestFocus();
			return false;
		}
		if (pfPassWord.getPassword().equals("")) {
			JOptionPane.showMessageDialog(null, "Mật khẩu không để trống!!!", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
			pfPassWord.requestFocus();
			return false;
		} 
		return true;
	}
}
