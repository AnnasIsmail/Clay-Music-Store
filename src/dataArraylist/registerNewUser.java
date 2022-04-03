package dataArraylist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class registerNewUser extends Konekdatabase{
	public registerNewUser(String username, String email, String password, String gender) throws SQLException{
		
		int getIndex = 0;
		
		String getNewIndex = "SELECT `id` FROM `users` ORDER BY `id`";
		ResultSet input = Konekdatabase.CMSdatabase.executeQuery(getNewIndex);
		
		while(input.next()) {
			getIndex = input.getInt(1);
		}
		
		String que = 
		"INSERT INTO `users` VALUES ('"+(getIndex+1)+"','"+username+"','"+email+"','"+password+"','2','"+gender+"')";
		CMSdatabase.execute(que);
	}
}
