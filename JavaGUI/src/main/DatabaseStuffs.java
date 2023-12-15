package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;

public abstract class DatabaseStuffs extends JFrame{
	private static final long serialVersionUID = 1L;
	final String path = "D:\\\\Projects\\\\Java Projects\\\\BankGUI\\\\JavaGUI\\\\gui.db";
	DatabaseStuffs(String s)
	{
		super(s);
	}
	
	StringBuilder createHash(String str)
	{
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("SHA-256");
			byte[] hash = md5.digest(new String(str).getBytes());
			StringBuilder hexString = new StringBuilder();
		    for (byte b : hash) {
		      hexString.append(String.format("%02x", b));
		    }
		    return (hexString);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (null);
	}
	
	ResultSet selectQuery(String query)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);
			stmt = conn.createStatement();
			res = stmt.executeQuery(query);		
		}
		catch(Exception e) {}
		return (res);
	}
	
	void otherQuery(String query) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			conn.commit();
			conn.close();
		}
		catch (Exception e){	
		}
	}
}
