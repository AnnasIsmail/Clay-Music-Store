package dataArraylist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class deleteGenre extends Konekdatabase{

	public deleteGenre(int music_genre_id) throws SQLException{
		try {
			
			String queSearchMusic = "SELECT `id`  FROM `musics` WHERE `music_genre_id` = '"+music_genre_id +"'";
			ResultSet input = CMSdatabase.executeQuery(queSearchMusic);
			
			ArrayList<Integer> index = new ArrayList<Integer>();
			while (input.next()) {
				index.add(input.getInt(1));
			}
			
			for (int i = 0; i < index.size(); i++) {
				String queDeleteHisDet = "DELETE FROM `history_detail` WHERE `music_id` = '"+ index.get(i) +"'" ;
				CMSdatabase.execute(queDeleteHisDet);
			}
			
			String queDeleteMusic ="DELETE FROM `musics` WHERE `music_genre_id` ='"+music_genre_id+"'";
			CMSdatabase.execute(queDeleteMusic);
			
			String que = "DELETE FROM `music_genres` WHERE `id` ='"+music_genre_id+"'";
			CMSdatabase.execute(que);
			
			genre.MusicGenreData.clear();
			new genre();
			
			music.MusicData.clear();
			new music();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}