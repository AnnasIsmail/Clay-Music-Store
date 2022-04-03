package dataArraylist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class addMusic extends Konekdatabase{

	public addMusic(int music_genre_id,String music_name,int music_price,String artist_name) throws SQLException{

		LocalDateTime DateNow = LocalDateTime.now();  
		DateTimeFormatter FormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		String DateNowFormat = DateNow.format(FormatDate);
		int genre = music_genre_id+1;
		int musicID = 0;
		
		String getId = "SELECT `id` FROM `musics`";
		ResultSet input = CMSdatabase.executeQuery(getId);
		
		while (input.next()) {
			musicID = input.getInt(1);
		}
	
		
		try {
			String que = "INSERT INTO `musics` VALUES "
					+ "('"+(musicID+1)+"', '"+music_name+"','"+genre+"','"+music_price+"','"+artist_name+"','"+DateNowFormat+"')";

			CMSdatabase.execute(que);
			
			music.MusicData.clear();
			new music();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
