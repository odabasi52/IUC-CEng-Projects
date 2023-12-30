package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public abstract class DatabaseStuffs implements IPaths{
	Connection conn = null;
	Statement stmt = null;
	ResultSet res = null;
	
	StringBuilder createHash(String str)
	{
		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA-256");
			byte[] hash = sha.digest(new String(str).getBytes());
			StringBuilder hexString = new StringBuilder();
		    for (byte b : hash) {
		      hexString.append(String.format("%02x", b));
		    }
		    return (hexString);
		} catch (NoSuchAlgorithmException e) {}
		return (null);
	}
	
	void otherQuery(String query) {
		conn = null;
		stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			conn.commit();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
