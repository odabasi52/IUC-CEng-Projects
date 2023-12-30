package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AppUsers extends DatabaseStuffs{

	static int user_id = 0;
	static int calisan_id = 0;
	
	int returnCalisanID()
	{
		ResultSet res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT COUNT(id) FROM calisanlar;");
			calisan_id = res.getInt(1);
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (calisan_id);
	}
	
	boolean checkUser(String tc_kimlik, String isim)
	{
		conn = null;
		stmt = null;
		res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
			stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT COUNT(id) FROM kullanicilar WHERE tc_kimlik = '" + createHash(tc_kimlik) + "';");		
			if (res.next() && res.getInt(1) > 0) {
				JOptionPane.showMessageDialog(new JFrame(), "TC Numarası ile Kayıt bulunmaktadır.");
				res.close();
				return (false);
			}
			res = stmt.executeQuery("SELECT COUNT(id) FROM kullanicilar WHERE isim = '" + isim + "';");
			if (res.next() && res.getInt(1) > 0) {
				JOptionPane.showMessageDialog(new JFrame(), "Kullanıcı Adı ile Kayıt bulunmaktadır.");
				res.close();
				return (false);
			}
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (true);
	}
	
	boolean checkUserForgotPassword(String tc_kimlik, String isim, int soru_id, String soru_cevap)
	{
		conn = null;
		stmt = null;
		res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
			stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT COUNT(id) FROM kullanicilar WHERE tc_kimlik = '" + createHash(tc_kimlik) + "' AND isim = '" 
			+ isim + "' AND soru_id = " + soru_id + " AND soru_cevap = '" + soru_cevap + "';");		
			if (res.next() && res.getInt(1) == 1) {
				res.close();
				return (true);
			}
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (false);
	}
	
	void create(String isim, String tc_kimlik, String parola, int soru_id, String soru_cevap)
	{
		try {
			if (user_id == 0)
			{
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
				stmt = conn.createStatement();
				res = stmt.executeQuery("SELECT COUNT(id) FROM kullanicilar;");		
				if (res.next() && res.getInt(1) > 0) {
					user_id = res.getInt(1);
				}
				res.close();
				stmt.close();
				conn.close();
			}
			
	        otherQuery(String.format("INSERT INTO kullanicilar(id, isim, tc_kimlik, parola, soru_id, soru_cevap,sube_id) VALUES(%d, '%s', '%s', '%s', %d, '%s', %d);",
	        		user_id + 1,
	                isim,
	                createHash(tc_kimlik),
	                createHash(parola),
	                soru_id,
	                soru_cevap,
	                new Random().nextInt(4)
	        ));
	        
	        otherQuery("INSERT INTO cuzdan VALUES("+(user_id + 1)+", 0);");
	        otherQuery("INSERT INTO doviz_cuzdan VALUES("+(user_id + 1)+", 0);");
	        user_id++;
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	}

}
