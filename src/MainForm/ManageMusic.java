package MainForm;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dataArraylist.Konekdatabase;
import dataArraylist.addMusic;
import dataArraylist.deleteMusic;
import dataArraylist.genre;
import dataArraylist.music;
import dataArraylist.updateMusic;

public class ManageMusic extends JInternalFrame {
	
	JSpinner spinPrice;
	JTextField fieldArtistName, fieldReleaseDate, fieldIdMusic, fieldmusicName;
	JComboBox genreCom;
	DefaultTableModel deftab;
	JTable tabelkiri;
	
	public ManageMusic() {
		ui();
		setTitle("Manage Music");
		setVisible(true);
		setSize(1002,545);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
	}

	private void ui() {
		Object[] kolom = {"ID","Name","Genre","Price","Artist Name", "Release Date"};
		Object[][] data = {};	
		JPanel panelUtama = new JPanel();
		add(panelUtama);
		
		JPanel kiri = new JPanel();
		panelUtama.add(kiri);
		
		deftab = new DefaultTableModel(data,kolom);
		tabelkiri = new JTable(deftab){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
		TableRowSorter tabsort = new TableRowSorter<>(deftab);
		tabelkiri.setRowSorter(tabsort);
		JScrollPane scroltabelkiri = new JScrollPane(tabelkiri);
		kiri.add(scroltabelkiri);
		scroltabelkiri.setPreferredSize(new Dimension(600,480));
		scroltabelkiri.setMaximumSize(new Dimension(600,480));
		
		tabelkiri.getColumnModel().getColumn(0).setPreferredWidth(1);
		tabelkiri.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabelkiri.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabelkiri.getColumnModel().getColumn(3).setPreferredWidth(1);
		tabelkiri.getColumnModel().getColumn(4).setPreferredWidth(150);
		tabelkiri.getColumnModel().getColumn(5).setPreferredWidth(70);
		try {
			new music();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		for (int i = 0; i < music.MusicData.size(); i++) {
			deftab.addRow(music.MusicData.get(i));
		}
		
		JPanel kananPanel = new JPanel(new GridLayout(2,0));
		panelUtama.add(kananPanel);
		
		JPanel kanan = new JPanel(new GridLayout(0,1));
		kananPanel.add(kanan);
		
		kananPanel.setBorder(new EmptyBorder(0,0,40,0));
		
		JLabel musicName = new JLabel("Music Name");
		kanan.add(musicName);
		
		fieldmusicName = new JTextField(20);
		kanan.add(fieldmusicName);
		
		JLabel genre = new JLabel("Genre");
		kanan.add(genre);
		
		String genreAr[] = new String[dataArraylist.genre.MusicGenreData.size()];
		
		for (int i = 0; i < dataArraylist.genre.MusicGenreData.size(); i++) {
			genreAr[i] = (String) dataArraylist.genre.MusicGenreData.get(i)[1];
		}
		
		genreCom = new JComboBox(genreAr);
		kanan.add(genreCom);
		
		JLabel artistName = new JLabel("Artist Name");
		kanan.add(artistName);
		
		fieldIdMusic = new JTextField();
		fieldReleaseDate = new JTextField();
		
		fieldArtistName = new JTextField();
		kanan.add(fieldArtistName);
		
		JLabel price = new JLabel("Price");
		kanan.add(price);
		
		spinPrice = new JSpinner(new SpinnerNumberModel(0, 0, 99999, 1));
		kanan.add(spinPrice);
		
		JPanel perkumpulanTombol = new JPanel(new FlowLayout());
		kananPanel.add(perkumpulanTombol);
		
		JButton addbut = new JButton("Add");
		perkumpulanTombol.add(addbut);
		
		addbut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				 if (fieldmusicName.getText().equals("")) {
					JOptionPane.showMessageDialog(getParent(), "Please Input Music Name!","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}else if (fieldArtistName.getText().equals("")) {
					JOptionPane.showMessageDialog(getParent(), "Please input Artist name","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}else if (spinPrice.getValue().equals(0)) {
					JOptionPane.showMessageDialog(getParent(), "Please Input Price","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int music_genre_id = genreCom.getSelectedIndex();
				String music_name = fieldmusicName.getText();
				int music_price = (int) spinPrice.getValue();
				String artist_name = fieldArtistName.getText();
				
				try {
					new addMusic(music_genre_id, music_name, music_price, artist_name);
					deftab.setRowCount(0);
					for (int i = 0; i < music.MusicData.size(); i++) {
						deftab.addRow(music.MusicData.get(i));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				mengosongkanField();
			}
		});
		
		JButton update = new JButton("Update");
		perkumpulanTombol.add(update);
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (tabelkiri.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(getParent(), "Please select a row");
					return;
				}else if (fieldmusicName.getText().equals("")) {
					JOptionPane.showMessageDialog(getParent(), "Please Input Music Name!","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}else if (fieldArtistName.getText().equals("")) {
					JOptionPane.showMessageDialog(getParent(), "Please input Artist name","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}else if (spinPrice.getValue().equals(0)) {
					JOptionPane.showMessageDialog(getParent(), "Please Input Price","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int music_id = Integer.parseInt(fieldIdMusic.getText());
				int music_genre_id = genreCom.getSelectedIndex();
				String music_name = fieldmusicName.getText();
				int music_price = (int) spinPrice.getValue();
				String artist_name = fieldArtistName.getText();
				String release_date = fieldReleaseDate.getText();
				
				try {
					new updateMusic(music_id, music_genre_id, music_name, music_price, artist_name, release_date);
					deftab.setRowCount(0);
					for (int i = 0; i < music.MusicData.size(); i++) {
						deftab.addRow(music.MusicData.get(i));
					}
				} catch (SQLException e1) {
					System.out.println(e1);
				}
				mengosongkanField();
			}
		});
		
		JButton delete = new JButton("Delete");
		perkumpulanTombol.add(delete);
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabelkiri.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(getParent(), "Please select a row");
					return;
				}
				
				int music_id = Integer.parseInt(fieldIdMusic.getText());
				String music_name = fieldmusicName.getText();
				int delete = JOptionPane.showConfirmDialog(getParent(), "are you sure you want to delete " + music_name + "?");
				
				if (delete == JOptionPane.YES_OPTION) {
					try {
						new deleteMusic(music_id);
						deftab.setRowCount(0);
						for (int i = 0; i < music.MusicData.size(); i++) {
							deftab.addRow(music.MusicData.get(i));
						}
					} catch (SQLException e1) {
						System.out.println(e1);
					}
					mengosongkanField();
					
				}
				}
			
		});
		
		tabelkiri.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				String name = (String) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 1),
						genre = (String) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 2),
						artist = (String) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 4),
						releaseDate = (String) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 5),
					idMusic =   String.valueOf( (int) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 0)),
					price =   String.valueOf( (int) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 3));
				
				if (idMusic.equals(fieldIdMusic.getText())) {
					if (tabelkiri.getSelectedRow() < 0) {
						fieldmusicName.setText(name);
						for (int i = 0; i  < dataArraylist.genre.MusicGenreData.size(); i++) {
							if (dataArraylist.genre.MusicGenreData.get(i)[1].equals(genre)) {
								genreCom.setSelectedIndex(i);
							}
						}
						
						fieldArtistName.setText(artist);
						spinPrice.setValue(Integer.parseInt(price));
						fieldIdMusic.setText(idMusic);
						fieldReleaseDate.setText(releaseDate);
					}else {
						tabelkiri.setSelectionMode(0);
						fieldmusicName.setText("");
						genreCom.setSelectedIndex(0);
						fieldArtistName.setText("");
						spinPrice.setValue(0);
						fieldIdMusic.setText("");
						fieldReleaseDate.setText("");
					}
				}else {

				fieldmusicName.setText(name);
				for (int i = 0; i  < dataArraylist.genre.MusicGenreData.size(); i++) {
					if (dataArraylist.genre.MusicGenreData.get(i)[1].equals(genre)) {
						genreCom.setSelectedIndex(i);
					}
				}
				
				fieldArtistName.setText(artist);
				spinPrice.setValue(Integer.parseInt(price));
				fieldIdMusic.setText(idMusic);
				fieldReleaseDate.setText(releaseDate);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
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
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	void mengosongkanField() {
		fieldIdMusic.setText("");
		fieldmusicName.setText("");
		fieldArtistName.setText("");
		fieldReleaseDate.setText("");
		spinPrice.setValue(0);
		genreCom.setSelectedIndex(0);
	}
}
