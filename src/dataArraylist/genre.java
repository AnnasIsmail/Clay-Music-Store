package dataArraylist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import MainForm.ManageMusicGenre;

public class genre extends Konekdatabase{
	public static ArrayList<Object[]> MusicGenreData = new ArrayList<Object[]>();
	public genre()  throws SQLException{

		MusicGenreData.clear();
		
		String que = "select * from music_genres";
		ResultSet input = Konekdatabase.CMSdatabase.executeQuery(que);
		
		while(input.next()) {
			Object[] GenreSementara = {
					input.getString(1),input.getString(2)
			};
			MusicGenreData.add(GenreSementara);
		}
	}
}
