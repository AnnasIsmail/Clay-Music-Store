package dataArraylist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Login.Login;

public class historyDetail extends Konekdatabase{
	public static ArrayList<Object[]> historyDetail = new ArrayList<Object[]>();
	
	public historyDetail(int history_id) throws SQLException {
		String que = 
				"SELECT `history_id`, `music_id` FROM `history_detail` WHERE `history_id` = "+ history_id
				;
	ResultSet input = Konekdatabase.CMSdatabase.executeQuery(que);

	while(input.next()) {
		String music_name=null,music_artist=null;
		int music_price = 0;
		for (int i = 0; i <music.MusicData.size(); i++) {
			
			if ((int) music.MusicData.get(i)[0] == input.getInt(2)) {
				music_name = (String) music.MusicData.get(i)[1];
				music_artist = (String) music.MusicData.get(i)[4];
				music_price = (int) music.MusicData.get(i)[3];
			}
		}
		
		Object[] sementara = {
			input.getInt(1),music_name,music_artist,music_price
		};
		historyDetail.add(sementara);
	}
	}
}