package MainForm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dataArraylist.*;

public class ManageMusicGenre extends JInternalFrame {
	public ManageMusicGenre() {
		ui();
		setTitle("Manage Music Genre");
		setVisible(true);
		setSize(1002,545);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
	}
	
	DefaultTableModel deftab;
	JTextField fieldIdGenre,fieldGenreName;

	private void ui() {
		Object[] kolom = {"ID","Genre"};
		Object[][] data = {};
		
		JPanel panelUtama = new JPanel(new FlowLayout(FlowLayout.LEFT));
		add(panelUtama);
		
		JPanel kiri = new JPanel();
		panelUtama.add(kiri);
		
		deftab = new DefaultTableModel(data,kolom);
		JTable tabelkiri = new JTable(deftab){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
		TableRowSorter tabsort = new TableRowSorter<>(deftab);
		tabelkiri.setRowSorter(tabsort);
		JScrollPane scroltabelkiri = new JScrollPane(tabelkiri);
		kiri.add(scroltabelkiri);
		scroltabelkiri.setPreferredSize(new Dimension(650,450));
		scroltabelkiri.setMaximumSize(new Dimension(650,450));
		kiri.setBorder(new EmptyBorder(0,10,0,0));
		
		try {
			new genre();
		} catch (SQLException e1) {
			System.err.println(e1);
		}
		deftab.setRowCount(0);
		loadGenre();
		
		JPanel kanan = new JPanel(new BorderLayout());
		panelUtama.add(kanan);
		
		JPanel kananAtas = new JPanel(new GridLayout(0,1));
		kanan.add(kananAtas,BorderLayout.CENTER);
		kanan.setBorder(new EmptyBorder(5,40,380,10));
		
		JLabel genre = new JLabel("Genre Name");
		kananAtas.add(genre);
		
		fieldIdGenre = new JTextField();
		
		fieldGenreName = new JTextField(20);
		kananAtas.add(fieldGenreName);
		
		JPanel perkumpulanTombol = new JPanel(new FlowLayout());
		kanan.add(perkumpulanTombol, BorderLayout.PAGE_END);
		
		JButton addbut = new JButton("Add");
		perkumpulanTombol.add(addbut);
		addbut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fieldGenreName.getText().equals("")) {
					JOptionPane.showMessageDialog(getParent(), "Please input Genre Name","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				String  genre_name = fieldGenreName.getText();
				
				try {
					new addGenre(genre_name);
					deftab.setRowCount(0);
					loadGenre();
					mengosongkanField();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		JButton update = new JButton("Update");
		perkumpulanTombol.add(update);
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (tabelkiri.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(getParent(), "Please select a row");
					return;
				}else if (fieldGenreName.getText().equals("")) {
					JOptionPane.showMessageDialog(getParent(), "Please input Genre Name","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int music_genre_id = Integer.parseInt(fieldIdGenre.getText());
				String  genre_name = fieldGenreName.getText();
				try {
					new updateGenre(music_genre_id, genre_name);
						deftab.setRowCount(0);
					loadGenre();
					mengosongkanField();
				} catch (SQLException e) {
					System.err.println(e);
				}
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
				}else if (fieldGenreName.getText().equals("")) {
					JOptionPane.showMessageDialog(getParent(), "Please input Genre Name","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int music_genre_id = Integer.parseInt(fieldIdGenre.getText());
				String  genre_name = fieldGenreName.getText();
int delete = JOptionPane.showConfirmDialog(getParent(), "are you sure you want to delete " + genre_name + "?");
				
				if (delete == JOptionPane.YES_OPTION) {
				try {
					new deleteGenre(music_genre_id);
					deftab.setRowCount(0);
					loadGenre();
					mengosongkanField();
				} catch (SQLException e1) {
					System.err.println(e1);
				}
			}
		}
		});
		
		tabelkiri.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				String genre_id = (String) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 0),
						genre_name = (String) tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 1);
				
				if (genre_id.equals(fieldIdGenre.getText())) {
					if (tabelkiri.getSelectedRow()<0) {
						fieldIdGenre.setText(genre_id);
						fieldGenreName.setText(genre_name);
					}else {
						fieldIdGenre.setText("");
						fieldGenreName.setText("");
						tabelkiri.setSelectionMode(0);
					}
				}else {
					fieldIdGenre.setText(genre_id);
					fieldGenreName.setText(genre_name);
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
private void loadGenre() {

	for (int i = 0; i < genre.MusicGenreData.size(); i++) {
		deftab.addRow(genre.MusicGenreData.get(i));
	}
}
private void mengosongkanField() {
	fieldGenreName.setText("");
	fieldIdGenre.setText("");
}
}
