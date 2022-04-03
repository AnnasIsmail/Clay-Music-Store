package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import MainForm.mainForm;
import Register.Register;
import dataArraylist.Konekdatabase;
import dataArraylist.Users;

public class Login extends JFrame{
	JFrame loginFrame = new JFrame("Clay's Music Store");
	public static int user_id;
	public static String Validasi="Salah", nama= null;
	
	public Login() {
		new Konekdatabase();
		loginFrame.getContentPane().setBackground(Color.DARK_GRAY);
		
		ui();
		loginFrame.setVisible(true);
		loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		loginFrame.setSize(600,340);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setResizable(false);
	}

private void ui() {
	
	JPanel panelatas = new JPanel(new BorderLayout());
	panelatas.setBorder(new EmptyBorder(10,0,0,0));
	panelatas.setBackground(Color.DARK_GRAY);
	loginFrame.add(panelatas, BorderLayout.PAGE_START);

	JPanel paneltengah = new JPanel(new GridLayout(0,2,10,20));
	paneltengah.setBorder(new EmptyBorder(5, 20,20,25));
	paneltengah.setBackground(Color.DARK_GRAY);
	loginFrame.add(paneltengah, BorderLayout.CENTER);

	JPanel panelbawah = new JPanel(new GridLayout(0,2,10,20));
	panelbawah.setBorder(new EmptyBorder(10,20,30,25));
	panelbawah.setBackground(Color.DARK_GRAY);
	loginFrame.add(panelbawah,BorderLayout.PAGE_END);
	
	//JUDUL LOGIN		
			JLabel labelJudul = new JLabel("LOGIN");
			labelJudul.setFont(new Font("Calibri", Font.BOLD, 44));
			labelJudul.setForeground(Color.WHITE);
			labelJudul.setHorizontalAlignment(SwingConstants.CENTER);
			labelJudul.setVerticalAlignment(SwingConstants.CENTER);
			panelatas.add(labelJudul);

	//TEXT EMAIL
			JLabel txtEmail = new JLabel("Email");
			txtEmail.setForeground(Color.WHITE);
			txtEmail.setFont(new Font("Calibri", Font.PLAIN, 30));
			paneltengah.add(txtEmail);
			
	//TEXTFIELD EMAIL
			JTextField filedEmail = new JTextField();
			paneltengah.add(filedEmail);
			
	//TEXT PASSWORD
			JLabel txtPassword = new JLabel("Password");
			txtPassword.setForeground(Color.WHITE);
			txtPassword.setFont(new Font("Calibri", Font.PLAIN, 30));
			paneltengah.add(txtPassword);
			
	//PASSWORD FIELD
			JPasswordField fieldPassword = new JPasswordField();
			paneltengah.add(fieldPassword);
			
	//BUTTON LOGIN
			JButton butlogin = new JButton("Login");
			butlogin.setBackground(Color.PINK);
			butlogin.setForeground(Color.WHITE);
			butlogin.setFont(new Font("Calibri", Font.PLAIN, 20));
			butlogin.setPreferredSize(new Dimension(0,50));
			butlogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String email = filedEmail.getText().trim();
					String password = fieldPassword.getText().trim();
					try {
						new Users(email,password);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					if (email.equals("")) {
						JOptionPane.showMessageDialog(getParent(), "Please input Email","Alert",JOptionPane.WARNING_MESSAGE);
					}else if(password.equals("")) {
						JOptionPane.showMessageDialog(getParent(), "Please input Password","Alert",JOptionPane.WARNING_MESSAGE);
					}else if(Validasi.equals("Salah")) {
						JOptionPane.showMessageDialog(getParent(), "Invalid email or password","Alert",JOptionPane.WARNING_MESSAGE);
					}else if (Validasi.equals("Benar")){
					JOptionPane.showMessageDialog(getParent(), "Welcome, "+nama);
					new mainForm();
					loginFrame.setVisible(false);
					}
				}
			});
			panelbawah.add(butlogin);
			
	//BUTTON IDONT
			JButton butidont = new JButton("I don't have an account");
			butidont.setForeground(Color.WHITE);
			butidont.setBackground(Color.PINK);
			butidont.setFont(new Font("Calibri", Font.PLAIN, 20));
			butidont.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					loginFrame.setVisible(false);
					new Register();
				}
			});
			panelbawah.add(butidont);		
	}
}