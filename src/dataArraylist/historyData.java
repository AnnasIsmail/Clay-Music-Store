package dataArraylist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Login.Login;

public class historyData extends Konekdatabase{
	public static ArrayList<Object[]> historyheader = new ArrayList<Object[]>();
	public static ArrayList<Object[]> historyUser = new ArrayList<Object[]>();
	
	public historyData() throws SQLException{
		historyheader.clear();
		historyUser.clear();
		
			String que = "SELECT `id`, `total_purchase`, `date_purchase` FROM `history_header` WHERE  `user_id` = " + Login.user_id;
			ResultSet input = Konekdatabase.CMSdatabase.executeQuery(que);
			while(input.next()) {
				Object[] Sementara = {
						input.getInt(1),input.getInt(2),input.getString(3)
				};
				
				historyheader.add(Sementara);
			}
			for (int i = 0; i < historyheader.size(); i++) {
				
			String que2 = "SELECT * FROM `history_detail` WHERE `history_id` = "+historyheader.get(i)[0];
			ResultSet input2 = Konekdatabase.CMSdatabase.executeQuery(que2);
			
			while(input2.next()) {
				Object[] Sementara2 = {
						input2.getInt(1),input2.getInt(2)
				};
				historyUser.add(Sementara2);
			}
		}
	}
}