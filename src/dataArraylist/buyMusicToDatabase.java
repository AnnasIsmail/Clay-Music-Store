package dataArraylist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Login.Login;

public class buyMusicToDatabase extends Konekdatabase{

	public buyMusicToDatabase(int total_purchase) throws SQLException{
		
		LocalDateTime DateNow = LocalDateTime.now();  
		DateTimeFormatter FormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		String DateNowFormat = DateNow.format(FormatDate);
		
		try {

			int newIndex = 0;
			String getNewIndex = "SELECT `id`FROM `history_header` ORDER BY `id`";
			ResultSet input = Konekdatabase.CMSdatabase.executeQuery(getNewIndex);
			
			while(input.next()) {
				newIndex = input.getInt(1);
			}

			String queToHeader = 
			"INSERT INTO `history_header` VALUES ('"+ (newIndex+1)+ "','"+total_purchase+"','"+DateNowFormat+"','"+Login.user_id+"')";
			CMSdatabase.execute(queToHeader);
			
			for (int i = 0; i < MainForm.buyMusic.Cart.size(); i++) {	
				String queToDetail = "INSERT INTO `history_detail` VALUES ('"+(newIndex+1)+"','"+MainForm.buyMusic.Cart.get(i)[0]+"')";
				CMSdatabase.execute(queToDetail);
			}
				new historyData();

		} catch (Exception e) {
			System.err.println(e);
		}
	}


}
