package dataArraylist;

import java.sql.SQLException;

public class deleteMusic extends Konekdatabase{
	public deleteMusic(int music_id) throws SQLException{
		try {
			
			String queDeleteHisDet = "DELETE FROM `history_detail` WHERE `music_id` = '"+ music_id +"'" ;
			CMSdatabase.execute(queDeleteHisDet);
			
			String que = "DELETE FROM `musics` WHERE `id`='"+music_id+"'";
			CMSdatabase.execute(que);

			music.MusicData.clear();
			new music();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}