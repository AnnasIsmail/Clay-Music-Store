package Register;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Login.Login;
import dataArraylist.registerNewUser;

public class Register extends JFrame{
	JFrame registerFrame = new JFrame("Clay's Music Store");
	public Register(){
		registerFrame.getContentPane().setBackground(Color.DARK_GRAY);
		ui();
		
		registerFrame.setVisible(true);
		registerFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		registerFrame.setSize(600,500);
		registerFrame.setLocationRelativeTo(null);
		registerFrame.setResizable(false);
	}
	
	private void ui() {
		
		JPanel panelatas = new JPanel(new BorderLayout());
		panelatas.setBorder(new EmptyBorder(10,0,0,0));
		panelatas.setBackground(Color.DARK_GRAY);
		registerFrame.add(panelatas, BorderLayout.NORTH);

		JPanel panelbawah = new JPanel(new GridLayout(0, 2,10,15));
		panelbawah.setBorder(new EmptyBorder(5, 25,25,25));
		panelbawah.setBackground(Color.DARK_GRAY);
		registerFrame.add(panelbawah, BorderLayout.CENTER);
		
		//JUDUL REGISTER		
				JLabel labelJudul = new JLabel("REGISTER");
				labelJudul.setFont(new Font("Calibri", Font.BOLD, 44));
				labelJudul.setForeground(Color.WHITE);
				labelJudul.setHorizontalAlignment(SwingConstants.CENTER);
				labelJudul.setVerticalAlignment(SwingConstants.CENTER);
				panelatas.add(labelJudul);

		//TEXT USERNAME
				JLabel txtUsername = new JLabel("Username");
				txtUsername.setForeground(Color.WHITE);
				txtUsername.setFont(new Font("Calibri", Font.PLAIN, 27));
				panelbawah.add(txtUsername);
				
		//TEXTFIELD USERNAME
				JTextField filedUsername = new JTextField();
				panelbawah.add(filedUsername);

		//TEXT EMAIL
				JLabel txtEmail = new JLabel("Email");
				txtEmail.setForeground(Color.WHITE);
				txtEmail.setFont(new Font("Calibri", Font.PLAIN, 27));
				panelbawah.add(txtEmail);
				
		//TEXTFIELD EMAIL
				JTextField filedEmail = new JTextField();
				panelbawah.add(filedEmail);

		//TEXT PASSWORD
				JLabel txtPassword = new JLabel("Password");
				txtPassword.setForeground(Color.WHITE);
				txtPassword.setFont(new Font("Calibri", Font.PLAIN, 27));
				panelbawah.add(txtPassword);
				
		//PASSWORD FIELD
				JPasswordField fieldPassword = new JPasswordField();
				panelbawah.add(fieldPassword);
				
		//TEXT CONFIRM PASSWORD
				JLabel txtConfirmPassword = new JLabel("Confirm Password");
				txtConfirmPassword.setForeground(Color.WHITE);
				txtConfirmPassword.setFont(new Font("Calibri", Font.PLAIN, 27));
				panelbawah.add(txtConfirmPassword);
				
		//CONFIRM PASSWORD FIELD
				JPasswordField fieldConfirmPassword = new JPasswordField();
				panelbawah.add(fieldConfirmPassword);
				
		//TEXT GENDER
				JLabel txtGender = new JLabel("Gender");
				txtGender.setForeground(Color.WHITE);
				txtGender.setFont(new Font("Calibri", Font.PLAIN, 27));
				panelbawah.add(txtGender);
				
		//RADIOBUTTON GENDER
				JPanel genderJPanel = new JPanel(new GridLayout(0,2));
				genderJPanel.setBackground(Color.DARK_GRAY);
				genderJPanel.setBorder(new EmptyBorder(0,27,0,0));
				
				JRadioButton femaleGender = new JRadioButton("Female");
				femaleGender.setFont(new Font("Calibri", Font.PLAIN, 20));
				femaleGender.setActionCommand("Perempuan");
				femaleGender.setBackground(Color.DARK_GRAY);
				femaleGender.setForeground(Color.WHITE);
				genderJPanel.add(femaleGender);
				
				JRadioButton maleGender = new JRadioButton("Male");
				maleGender.setFont(new Font("Calibri", Font.PLAIN, 20));
				maleGender.setActionCommand("Laki-Laki");
				maleGender.setBackground(Color.DARK_GRAY);
				maleGender.setForeground(Color.WHITE);
				genderJPanel.add(maleGender);
				
				ButtonGroup groupGender = new ButtonGroup();
				groupGender.add(femaleGender);
				groupGender.add(maleGender);
				
				panelbawah.add(genderJPanel);
				
		//BUTTON REGISTER
				JButton butregis = new JButton("Register");
				butregis.setBackground(Color.PINK);
				butregis.setForeground(Color.WHITE);
				butregis.setFont(new Font("Calibri", Font.PLAIN, 20));
				butregis.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String username = filedUsername.getText();
						String email = filedEmail.getText();
						String password = fieldPassword.getText();
						String confirmPassword =fieldConfirmPassword.getText();
						int et = email.indexOf("@");
						int titik =  email.indexOf(".");
						int et2 = email.lastIndexOf("@");
						int panjang = email.length();
						
						if (username.equals("")) {
							JOptionPane.showMessageDialog(getParent(), "Username cannot be empty!","Alert",JOptionPane.WARNING_MESSAGE);
						}else if(email.equals("")) {
							JOptionPane.showMessageDialog(getParent(), "Email cannot be empty!","Alert",JOptionPane.WARNING_MESSAGE);
						}else if(!(email.contains("@"))&& !(email.contains(".") )) {
							JOptionPane.showMessageDialog(getParent(), "Please input valid email!","Alert",JOptionPane.WARNING_MESSAGE);
						}else if (et > titik) {
							JOptionPane.showMessageDialog(getParent(), "Please input valid email!","Alert",JOptionPane.WARNING_MESSAGE);
						}else if (et+1 == titik) {
							JOptionPane.showMessageDialog(getParent(), "Please input valid email!","Alert",JOptionPane.WARNING_MESSAGE);
						}else if ( titik == panjang-1) {
							JOptionPane.showMessageDialog(getParent(), "Please input valid email!","Alert",JOptionPane.WARNING_MESSAGE);
						}else if (!(et == et2)) {
							JOptionPane.showMessageDialog(getParent(), "Please input valid email!","Alert",JOptionPane.WARNING_MESSAGE);
						}else  if (password.equals("")) {
							JOptionPane.showMessageDialog(getParent(), "Password cannot be empty!","Alert",JOptionPane.WARNING_MESSAGE);
						}else if(confirmPassword.equals("")) {
							JOptionPane.showMessageDialog(getParent(), "Confirmation Password cannot be empty!","Alert",JOptionPane.WARNING_MESSAGE);
						}else if (!(password.equals(confirmPassword))) {
							JOptionPane.showMessageDialog(getParent(), "Password must be same with be confirmation password","Alert",JOptionPane.WARNING_MESSAGE);
						}else if(groupGender.isSelected(null) ==true) {
							JOptionPane.showMessageDialog(getParent(), "Gender must be selected either 'Male' or 'Female'","Alert",JOptionPane.WARNING_MESSAGE);
						}else {
							
							String gender = groupGender.getSelection().getActionCommand();
							
							try {
								new registerNewUser(username, email, password, gender);
							} catch (SQLException e1) {
								System.out.println(e1);
							}
							
							registerFrame.setVisible(false);
							new Login();
						}
					}
				});
				panelbawah.add(butregis);
				
		//BUTTON IHAVE
				JButton butihave = new JButton("I Have an account");
				butihave.setForeground(Color.WHITE);
				butihave.setBackground(Color.PINK);
				butihave.setFont(new Font("Calibri", Font.PLAIN, 20));
				butihave.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						registerFrame.setVisible(false);
						new Login();
						
					}
				});
				panelbawah.add(butihave);
		}
}

