package dataArraylist;

import java.sql.SQLException;

public class updateGenre extends Konekdatabase{
	public updateGenre(int music_genre_id, String genre_name) throws SQLException{

		try {
			String que =  "UPDATE `music_genres` SET `genre_name`='"+genre_name+"' WHERE `id`='"+music_genre_id+"'";
			CMSdatabase.execute(que);
			
			genre.MusicGenreData.clear();
			new genre();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
