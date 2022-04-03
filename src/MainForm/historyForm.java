package MainForm;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dataArraylist.historyData;
import dataArraylist.historyDetail;
import dataArraylist.music;

public class historyForm extends JInternalFrame {
	public historyForm() {
		ui();
		setTitle("History");
		setVisible(true);
		setSize(1002,545);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
	}

	private void ui() {
		Object[] kolomHistory = {"ID","Total Purchase (IDR)","Date Purchase"};
		Object[] kolomHistoryDetail = {"History ID","Music Name","Music Artist","Music Price (IDR)"};
		Object[][] dataHistory = {};
		Object[][] dataHistoryDetail = {};
		JPanel panelutama = new JPanel(new GridLayout(0,2,10,0));
		panelutama.setBorder(new EmptyBorder(10,10,10,10));
		add(panelutama);
		
		DefaultTableModel tblmdlkiri = new DefaultTableModel(dataHistory,kolomHistory);
		JTable tabelkiri = new JTable(tblmdlkiri){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
		TableRowSorter tblsrtkiri = new TableRowSorter<>(tblmdlkiri);
		tabelkiri.setRowSorter(tblsrtkiri);
		JScrollPane scrollKiri = new JScrollPane(tabelkiri);
		
		try {
			new historyData();
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		for (int i = 0; i < historyData.historyheader.size(); i++) {
			tblmdlkiri.addRow(historyData.historyheader.get(i));
		}
		
		DefaultTableModel tblmdlkanan = new DefaultTableModel(dataHistoryDetail,kolomHistoryDetail);
		JTable tabelkanan = new JTable(tblmdlkanan){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
		TableRowSorter tblsortkanan = new TableRowSorter<>(tblmdlkanan);
		tabelkanan.setRowSorter(tblsortkanan);
		JScrollPane scrollKanan = new JScrollPane(tabelkanan);
		
		panelutama.add(scrollKiri);
		panelutama.add(scrollKanan);
		
     tabelkiri.addMouseListener(new MouseListener() {
		
    	 @Override
    	 public void mousePressed(MouseEvent e) {
    		int history_id = (Integer)tabelkiri.getValueAt(tabelkiri.getSelectedRow(), 0);
    		
    		if (tabelkanan.getRowCount() > 0) {
    			int history_kanan = (Integer) tabelkanan.getValueAt(0, 0);
    			
    			if (history_kanan == history_id) {
					tabelkiri.setSelectionMode(0);
					tblmdlkanan.setRowCount(0);
				}else {
    			tblmdlkanan.setRowCount(0);
    			historyDetail.historyDetail.clear();
        		try {
        			new historyData();
        			new music();
    				new historyDetail(history_id);

    			} catch (SQLException e1) {
    				System.err.println(e1);
    			}
        		
        		for (int j = 0; j < historyDetail.historyDetail.size(); j++) {	
        			tblmdlkanan.addRow(historyDetail.historyDetail.get(j));
    			}
				}
        		
    		}else {		
    			historyDetail.historyDetail.clear();
    		try {
    			new historyData();
    			new music();
				new historyDetail(history_id);
			} catch (SQLException e1) {
				System.out.println(e1);
			}
    		
    		for (int j = 0; j < historyDetail.historyDetail.size(); j++) {
    			tblmdlkanan.addRow(historyDetail.historyDetail.get(j));
			}
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
}
