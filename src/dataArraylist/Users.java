package dataArraylist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Login.Login;
import MainForm.mainForm;

public class Users extends Konekdatabase{
	public Users(String Email,String Password) throws SQLException {


		String que = "SELECT * FROM `users` WHERE `email` = '"+Email +"'and `password` = '"+ Password +"'";
		ResultSet input = CMSdatabase.executeQuery(que);
		
		while(input.next()) {
			if( Email.equals(input.getString(3))){
				if (Password.equals(input.getString(4))) {

					Login.Validasi = "Benar";
					Login.user_id = input.getInt(1);
					Login.nama =  input.getString(2);
					mainForm.role = input.getInt(5);
				}
			}
		}
	}
}