package MainForm;
import java.awt.Color;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Login.Login;

public class mainForm extends JFrame{
	public static int role = 0;
	public static JDesktopPane dPane;
	public mainForm() {
	     dPane = new JDesktopPane();
	    setContentPane(dPane);
		ui();
		
		getContentPane().setBackground(Color.DARK_GRAY);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1021,616);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
    private void ui() {
		JMenuBar menuAdmin = new JMenuBar();
		JMenuBar menuCustomer = new JMenuBar();
		
		JMenu user = new JMenu("User");
		JMenu manage = new JMenu("Manage");
		JMenu store = new JMenu("Store");
		
		JMenuItem  logOff = new JMenuItem("Log Off");
		logOff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Login.user_id = 0;
				Login.Validasi = "Salah";
				Login.nama = null;
				new Login();
				setVisible(false);
			}
		});
		
		JMenuItem  manageMusic = new JMenuItem("Manage Music");
		manageMusic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dPane.getAllFrames().length == 0) {
					dPane.add(new ManageMusic());
				}else {
					dPane.remove(0);
					dPane.add(new ManageMusic());
				}
			}
		});
		
		JMenuItem  manageMusicgenre = new JMenuItem("Manage Music Genre");
		manageMusicgenre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dPane.getAllFrames().length == 0) {
					dPane.add(new ManageMusicGenre());
				}else {
					dPane.remove(0);
					dPane.add(new ManageMusicGenre());
				}
			}
		});
		
		JMenuItem  buyMusic= new JMenuItem("Buy Music");
		buyMusic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dPane.getAllFrames().length == 0) {
					dPane.add(new buyMusic());
				}else {
					dPane.remove(0);
					dPane.add(new buyMusic());
				}
			}
		});
		
		JMenuItem  History = new JMenuItem("history");
		History.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dPane.getAllFrames().length == 0) {
					dPane.add(new historyForm());
				}else {
					dPane.remove(0);
					dPane.add(new historyForm());
				}
			}
		});
  
    user.add(logOff);
    manage.add(manageMusic);
    manage.add(manageMusicgenre);
    store.add(buyMusic);
    store.add(History);
    
    if (role == 1) {
    	menuAdmin.add(user);
    	menuAdmin.add(manage);
    	setJMenuBar(menuAdmin);
    	
    }else if(role == 2) {
    	menuCustomer.add(user);
    	menuCustomer.add(store);
    	setJMenuBar(menuCustomer);
    }
    }
}