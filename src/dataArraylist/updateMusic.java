package dataArraylist;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class updateMusic extends Konekdatabase{
	public updateMusic(int music_id, int music_genre_id,String music_name,int music_price,String artist_name, String release_date) throws SQLException{
		int genre = music_genre_id+1;
		
		try {
			String que = "UPDATE `musics` SET `music_genre_id`='"+genre+"',`music_name`='"+music_name+"',`music_price`='"+music_price+"',`music_artist_name`='"+artist_name+"',`release_date`='"+release_date+"' WHERE `id`='"+music_id+"'";
			CMSdatabase.execute(que);
			music.MusicData.clear();
			new music();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}