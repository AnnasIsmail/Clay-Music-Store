package MainForm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Login.Login;
import dataArraylist.historyData;
import dataArraylist.music;

public class buyMusic extends JInternalFrame{
	
	public static ArrayList<Object[]> Cart = new ArrayList<Object[]>();

	public buyMusic() {
		ui();
		setTitle("Buy Music");
		setVisible(true);
		setSize(1002,545);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
	}
Object[] kolom = {"ID","Name","Genre","Price","Artist Name", "Release Date"};
Object[][] data = {};
Object[][] dataKeranjang = {};
int idMus = 0, idMuskan = 0;;
private void ui() {
	 Cart.clear();
//	PANEL UTAMA
	JPanel center = new JPanel();
	JPanel bawah = new JPanel(new GridLayout(0,2,530,0));
	
	add(center, BorderLayout.CENTER);
	add(bawah, BorderLayout.PAGE_END);
	
	center.setBorder(new EmptyBorder(5,0,0,0));
	bawah.setBorder(new EmptyBorder(0, 0,10,18));
	
	JPanel paneltabel = new JPanel(new BorderLayout(9,0));
	JPanel paneltombol = new JPanel(new GridLayout(0,2,20,0));
	
	center.add(paneltabel, BorderLayout.CENTER);
	center.add(paneltombol, BorderLayout.PAGE_END);
	
//	TABLE MENU
		
		DefaultTableModel tblmdlkiri = new DefaultTableModel(data,kolom);
		JTable tabelkiri = new JTable(tblmdlkiri){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
		TableRowSorter tblsortkiri = new TableRowSorter<>(tblmdlkiri);
		tabelkiri.setRowSorter(tblsortkiri);
		JScrollPane scroltabelkiri = new JScrollPane(tabelkiri);
		scroltabelkiri.setPreferredSize(new Dimension(470,430));
		paneltabel.add(scroltabelkiri,BorderLayout.LINE_START);
		
		tabelkiri.getColumnModel().getColumn(0).setPreferredWidth(1);
		tabelkiri.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabelkiri.getColumnModel().getColumn(2).setPreferredWidth(30);
		tabelkiri.getColumnModel().getColumn(3).setPreferredWidth(1);
		tabelkiri.getColumnModel().getColumn(4).setPreferredWidth(60);
		tabelkiri.getColumnModel().getColumn(5).setPreferredWidth(60);
		
		try {
			new historyData();
			new music();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int j = 0; j < music.MusicData.size(); j++) {
			ArrayList<Integer> idMusikBeli = new ArrayList<Integer>();
			
			int music_id = (int) music.MusicData.get(j)[0];
			
			for (int i = 0; i < historyData.historyUser.size(); i++) {
				idMusikBeli.add((Integer) historyData.historyUser.get(i)[1]);
			}
				
		if (idMusikBeli.contains(music_id)==false) {
			tblmdlkiri.addRow(music.MusicData.get(j));
		}
		}
		
		
//	TABLE CART
		DefaultTableModel tblmdlkanan = new DefaultTableModel(dataKeranjang,kolom);
		JTable tabelkanan = new JTable(tblmdlkanan){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
		TableRowSorter tblsortkanan = new TableRowSorter<>(tblmdlkanan);
		tabelkanan.setRowSorter(tblsortkanan);
		JScrollPane scroltabelkanan = new JScrollPane(tabelkanan);
		scroltabelkanan.setPreferredSize(new Dimension(470,385));
		paneltabel.add(scroltabelkanan, BorderLayout.LINE_END);
		
		tabelkanan.getColumnModel().getColumn(0).setPreferredWidth(1);
		tabelkanan.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabelkanan.getColumnModel().getColumn(2).setPreferredWidth(30);
		tabelkanan.getColumnModel().getColumn(3).setPreferredWidth(1);
		tabelkanan.getColumnModel().getColumn(4).setPreferredWidth(60);
		tabelkanan.getColumnModel().getColumn(5).setPreferredWidth(60);
		
//		TOMBOL ADD TO CART
		JButton addcart = new JButton("                                                               Add To Cart                                                            ");
		addcart.setSize(100,10);
		paneltombol.add(addcart);
		
//		TOMBOL REMOVE FROM CART
		JButton removecart = new JButton("Remove From Cart");
		paneltombol.add(removecart);
		
		JPanel ganjelan = new JPanel();
		bawah.add(ganjelan);
		
//		TOMBOL BUY
		JButton buyButton = new JButton("Buy");
		bawah.add(buyButton);
		

addcart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

			String ID= null,Nama= null,Genre= null,Price= null,ArtistName = null,ReleaseDate= null,dataSelect = null;
			String isi;

			int[] selectrow = tabelkiri.getSelectedRows();
			if(selectrow.length>0) {
			
				String name = (String) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 1),
						genre = (String) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 2),
						artist = (String) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 4),
						releaseDate = (String) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 5);
				
				int
					idMusic =  (int) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 0),
					price =  (int) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 3);
				
				Object[] Sementara = {
						idMusic,name,genre,price,artist,releaseDate
				};
				
				Cart.add(Sementara);
				tblmdlkanan.setRowCount(0);
				for (int i = 0; i < Cart.size(); i++) {
					tblmdlkanan.addRow(Cart.get(i));
				}
				tabelkiri.setSelectionMode(0);
			}else {
				JOptionPane.showMessageDialog(getParent(),"Please Select Any Music");
			}
			}
			});
	
removecart.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int[] selectRowkanan = tabelkanan.getSelectedRows();
		if (selectRowkanan.length>0) {
			
			int selecrow = tabelkanan.getSelectedRow();
			int idMusic = (int) tabelkanan.getValueAt(selecrow, 0);
			
			for (int i = 0; i < Cart.size(); i++) {
				if ((int) Cart.get(i)[0] == idMusic) {
					Cart.remove(i);
				}
			}
			tblmdlkanan.setRowCount(0);
			for (int i = 0; i < Cart.size(); i++) {
				tblmdlkanan.addRow(Cart.get(i));
			}
			tabelkiri.setSelectionMode(0);
		}else {
			JOptionPane.showMessageDialog(getParent(),"Please Select Any Music From Cart");
		}
		
	}
});

buyButton.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int total_purchase =0;
		
		for (int i = 0; i < Cart.size(); i++) {	
			total_purchase = total_purchase + (int) Cart.get(i)[3];
		}
		
		try {
			new dataArraylist.buyMusicToDatabase(total_purchase);
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		tblmdlkanan.setRowCount(0);
		tblmdlkiri.setRowCount(0);
		for (int j = 0; j < music.MusicData.size(); j++) {
			ArrayList<Integer> idMusikBeli = new ArrayList<Integer>();
			
			int music_id =(int) music.MusicData.get(j)[0];
			
			for (int i = 0; i < historyData.historyUser.size(); i++) {
				idMusikBeli.add((Integer) historyData.historyUser.get(i)[1]);
			}
				
		if (idMusikBeli.contains(music_id)==false) {
			tblmdlkiri.addRow(music.MusicData.get(j));
		}
		}
	}
});

tabelkiri.addMouseListener(new MouseListener() {
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		int selecrow = tabelkiri.getSelectedRow();
		int idtekan =  (int) tabelkiri.getValueAt(selecrow, 0);
		if (idtekan == idMus) {
			tabelkiri.setSelectionMode(0);
			idMus =0;
		}else {
			idMus = (int) tabelkiri.getValueAt(selecrow, 0);
		}
	}
});

tabelkanan.addMouseListener(new MouseListener() {
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		int selecrow = tabelkanan.getSelectedRow();
		int idtekan =  (int) tabelkanan.getValueAt(selecrow, 0);
		if (idtekan == idMuskan) {
			tabelkanan.setSelectionMode(0);
			idMuskan =0;
		}else {
			idMuskan = (int) tabelkanan.getValueAt(selecrow, 0);
		}
	}
});

}
}