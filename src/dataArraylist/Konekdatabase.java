package dataArraylist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Konekdatabase {

	public static Statement CMSdatabase;
	public static Connection conn;
	public Konekdatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			conn= DriverManager.getConnection("jdbc:mysql://localhost/claymusicstore","root","");
			CMSdatabase = conn.createStatement();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
