package dataArraylist;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.plaf.InputMapUIResource;

public class addGenre extends Konekdatabase{

	public addGenre(String genre_name) throws SQLException{
		
		int genre_id = 0;
		String getId = "SELECT `id` FROM `music_genres`";
		ResultSet input = CMSdatabase.executeQuery(getId);
		
		while (input.next()) {
			genre_id = input.getInt(1);
		}
		
		
		try {
			String que = "INSERT INTO `music_genres` VALUES ('"+(genre_id+1)+"','"+genre_name+"')";
			CMSdatabase.execute(que);
			
			genre.MusicGenreData.clear();
			new genre();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
