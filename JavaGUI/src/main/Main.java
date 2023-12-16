package main;

import java.awt.EventQueue;
/*
CREATE TABLE subeler(id int, isim text);
CREATE TABLE hesap_hareketleri(musteri_id int, islem text);
CREATE TABLE cuzdan(id int, musteri_id int, para text);
CREATE TABLE kullanici_cuzdan(musteri_id int, cuzdan_id int);
CREATE TABLE calisanlar(id int, isim text, parola text, rol text, is_admin bool);
CREATE TABLE kullanicilar(id int, isim text, tc_kimlik text, parola text, soru_id int, soru_cevap text,sube_id int);
*/

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
	}
}
