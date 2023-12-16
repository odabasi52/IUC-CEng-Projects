package main;

import java.sql.DriverManager;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Kullanicilar extends DatabaseStuffs{

	boolean checkUser(String tc_kimlik)
	{
		conn = null;
		stmt = null;
		res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);
			stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT COUNT(id) FROM kullanicilar WHERE tc_kimlik = '" + createHash(tc_kimlik) + "';");		
			if (res.next() && res.getInt(1) > 0) {
				JOptionPane.showMessageDialog(new JFrame(), "TC Numarası ile Kayıt bulunmaktadır.");
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
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);
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
		int id = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);
			stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT COUNT(id) FROM kullanicilar;");		
			if (res.next() && res.getInt(1) > 0) {
				id = res.getInt(1);
			}
			res.close();
			stmt.close();
			conn.close();
			
	        otherQuery(String.format("INSERT INTO kullanicilar(id, isim, tc_kimlik, parola, soru_id, soru_cevap,sube_id) VALUES(%d, '%s', '%s', '%s', %d, '%s', %d);",
	                id,
	                isim,
	                createHash(tc_kimlik),
	                createHash(parola),
	                soru_id,
	                soru_cevap,
	                new Random().nextInt(4)
	        ));
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	}

}
