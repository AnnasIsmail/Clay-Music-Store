package dataArraylist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class music extends Konekdatabase{
	public static ArrayList<Object[]> MusicData = new ArrayList<Object[]>();
	
	public music() throws SQLException{

		MusicData.clear();
		new genre();

		String que = "select * from musics";
		ResultSet input = Konekdatabase.CMSdatabase.executeQuery(que);
		
		while(input.next()) {
			Object Genre = "";
			for (int i = 0; i  < genre.MusicGenreData.size(); i++) {
				if (genre.MusicGenreData.get(i)[0].equals(input.getString(3))) {
					Genre =  genre.MusicGenreData.get(i)[1];
				}
			}
			
			Object [] arraySementara = {
					input.getInt(1),input.getString(2),Genre,input.getInt(4),input.getString(5),input.getString(6)
			};
			MusicData.add(arraySementara);
		}
	}
}