package main;

public interface ILogin {
	boolean checkIf(String table, String isim, StringBuilder parola, boolean is_admin);
	int returnID(String isim);
}
