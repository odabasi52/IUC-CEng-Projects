package main;

import java.sql.*;
import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:D:\\Projects\\Java Projects\\BankGUI\\JavaGUI\\gui.db");
			stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM calisanlar;" );
		      
		      while ( rs.next() ) {
		         String  parola = rs.getString("parola");
		         boolean is_admin  = rs.getBoolean("is_admin");
		         String  isim = rs.getString("isim");

		         System.out.println( "HASH = " + parola );
		         System.out.println( "is_admin = " + is_admin );
		         System.out.println( "isim = " + isim );
		         System.out.println();
		      }
		      rs.close();
		      stmt.close();
		      conn.close();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
