package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class KullaniciPage extends JFrame implements IPaths{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField paraTextField;
	
	int returnMoney(int id, String table)
	{
		int money = 0;
		ResultSet res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT para FROM " + table + " WHERE musteri_id = " + id + ";");
			money = res.getInt(1);
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (money);
	}
	
	int getID(StringBuilder TC)
	{
		int id = 0;
		ResultSet res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT id FROM kullanicilar WHERE tc_kimlik = '" + TC + "';");
			id = res.getInt(1);
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (id);
	}
	
	private JTable hesapHareketTable;
	void showHareketTable(int id) {
		ResultSet res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT islem FROM hesap_hareketleri WHERE musteri_id =" + id + ";");
			ResultSetMetaData resMD = res.getMetaData();
			DefaultTableModel model = (DefaultTableModel)hesapHareketTable.getModel();
			model.setRowCount(0);
			int cols = resMD.getColumnCount();
			String colNames[] = new String[cols];
			for (int i =0; i < cols; i++)
				colNames[i] = resMD.getColumnName(i + 1);
			model.setColumnIdentifiers(colNames);
			while(res.next())
			{
				String[] row = {res.getString(1)};
				model.addRow(row);
			}
			hesapHareketTable.setEnabled(false);
					
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public KullaniciPage(int id) {
		super("KULLANICI SAYFASI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBackground(SystemColor.activeCaption);
		backgroundPanel.setBounds(0, 0, 784, 461);
		contentPane.add(backgroundPanel);
		backgroundPanel.setLayout(null);
		
		JScrollPane hesapHareketScrollPane = new JScrollPane();
		hesapHareketScrollPane.setBounds(35, 41, 705, 240);
		backgroundPanel.add(hesapHareketScrollPane);
		
		hesapHareketTable = new JTable();
		hesapHareketScrollPane.setViewportView(hesapHareketTable);
		
		JLabel hesapHareketleriLabel = new JLabel("HESAP HAREKETLERİ");
		hesapHareketleriLabel.setFont(new Font("Sylfaen", Font.BOLD, 20));
		hesapHareketleriLabel.setForeground(SystemColor.window);
		hesapHareketleriLabel.setBackground(SystemColor.activeCaption);
		hesapHareketleriLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hesapHareketleriLabel.setBounds(213, 6, 339, 43);
		backgroundPanel.add(hesapHareketleriLabel);
		showHareketTable(id);
		
		paraTextField = new JTextField();
		paraTextField.setBounds(267, 365, 236, 20);
		backgroundPanel.add(paraTextField);
		paraTextField.setColumns(10);
		paraTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
	            if (ke.getKeyChar() == '\b' || (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')) {
	            	paraTextField.setEditable(true);
	            } else {
	            	paraTextField.setEditable(false);
	            }
	         }
		});
		
		JLabel paraMiktariLabel = new JLabel("PARA MİKTARI: 0TL");
		paraMiktariLabel.setHorizontalAlignment(SwingConstants.CENTER);
		paraMiktariLabel.setBackground(SystemColor.activeCaption);
		paraMiktariLabel.setForeground(SystemColor.window);
		paraMiktariLabel.setBounds(211, 335, 341, 14);
		backgroundPanel.add(paraMiktariLabel);
		paraMiktariLabel.setText("PARA MİKTARI: " + returnMoney(id, "cuzdan") + "TL");
		
		
		JButton paraCekButton = new JButton("PARA ÇEK");
		paraCekButton.setBackground(SystemColor.window);
		paraCekButton.setForeground(SystemColor.activeCaption);
		paraCekButton.setBounds(47, 401, 110, 23);
		backgroundPanel.add(paraCekButton);
		paraCekButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppUsers a = new AppUsers();
				int money;
				try {
					 money = Integer.valueOf(paraTextField.getText());
				}
				catch (NumberFormatException ee)
				{
					return;
				}
				
				int last = returnMoney(id, "cuzdan") - money;
				if (last < 0)
					JOptionPane.showMessageDialog(new JFrame(), "Yeterli Paranız Yok.");
				else {
					a.otherQuery("UPDATE cuzdan SET para = " + last + " WHERE musteri_id = "+ id +";");
					a.otherQuery("INSERT INTO hesap_hareketleri VALUES(" + id + ", '" + money + "TL PARA ÇEKİLDİ');");
					paraMiktariLabel.setText("PARA MİKTARI: " + last + "TL");
					showHareketTable(id);
				}
			}
		});
		

		
		JButton paraYatirButton = new JButton("PARA YATIR");
		paraYatirButton.setForeground(SystemColor.activeCaption);
		paraYatirButton.setBackground(SystemColor.window);
		paraYatirButton.setBounds(183, 401, 116, 23);
		backgroundPanel.add(paraYatirButton);
		paraYatirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppUsers a = new AppUsers();
				int money;
				try {
					 money = Integer.valueOf(paraTextField.getText());
				}
				catch (NumberFormatException ee)
				{
					return;
				}
				int last = money + returnMoney(id, "cuzdan");
				a.otherQuery("UPDATE cuzdan SET para = " + last + " WHERE musteri_id = "+ id +";");
				a.otherQuery("INSERT INTO hesap_hareketleri VALUES(" + id + ", '" + money + "TL PARA YATIRILDI');");
				paraMiktariLabel.setText("PARA MİKTARI: " + last + "TL");
				showHareketTable(id);
			}
		});
		
		JButton havaleButton = new JButton("HAVALE");
		havaleButton.setForeground(SystemColor.activeCaption);
		havaleButton.setBackground(SystemColor.window);
		havaleButton.setBounds(329, 401, 116, 23);
		backgroundPanel.add(havaleButton);
		havaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppUsers a = new AppUsers();
				String tc = JOptionPane.showInputDialog("Havale Edeceğiniz Kişinin TC' si");
				if (tc == null)
					return;
				int target_id = getID(a.createHash(tc));
				if (target_id == 0)
					JOptionPane.showMessageDialog(new JFrame(), "Kişi Bulunamadı");
				else {
					int money;
					try {
						 money = Integer.valueOf(paraTextField.getText());
					}
					catch (NumberFormatException ee)
					{
						return;
					}
					System.out.println(target_id + " " + id);
					int lastMine = returnMoney(id, "cuzdan") - money;
					a.otherQuery("UPDATE cuzdan SET para = " + lastMine + " WHERE musteri_id = "+ id +";");
					int lastTarget = money + returnMoney(target_id, "cuzdan");
					a.otherQuery("UPDATE cuzdan SET para = " + lastTarget + " WHERE musteri_id = "+ target_id +";");
					a.otherQuery("INSERT INTO hesap_hareketleri VALUES(" + id + ", '" + target_id + " NOLU KİŞİYE " + money + "TL HAVALE GÖNDERİLDİ');");
					a.otherQuery("INSERT INTO hesap_hareketleri VALUES(" + target_id + ", '" + id + " NOLU KİŞİDEN " + money + "TL HAVALE GELDİ');");
					if (id == target_id)
						lastMine = lastTarget;
					paraMiktariLabel.setText("PARA MİKTARI: " + lastMine + "TL");
					showHareketTable(id);
				}
			}
		});
		
		JLabel dovizMiktariLabel = new JLabel("PARA MİKTARI: 0$");
		dovizMiktariLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dovizMiktariLabel.setForeground(SystemColor.window);
		dovizMiktariLabel.setBackground(SystemColor.activeCaption);
		dovizMiktariLabel.setBounds(213, 350, 341, 14);
		backgroundPanel.add(dovizMiktariLabel);
		dovizMiktariLabel.setText("DÖVİZ MİKTARI: " + returnMoney(id, "doviz_cuzdan") + "$");
		
		JButton btnDvizAl = new JButton("DOLAR AL");
		btnDvizAl.setForeground(SystemColor.activeCaption);
		btnDvizAl.setBackground(SystemColor.window);
		btnDvizAl.setBounds(480, 401, 116, 23);
		backgroundPanel.add(btnDvizAl);
		btnDvizAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppUsers a = new AppUsers();
				int money;
				try {
					 money = Integer.valueOf(paraTextField.getText());
				}
				catch (NumberFormatException ee)
				{
					return;
				}
				int last = returnMoney(id, "cuzdan") - money;
				if (last < 0)
					JOptionPane.showMessageDialog(new JFrame(), "Yeterli Paranız Yok.");
				else
				{
					int lastDoviz = money / 30 + returnMoney(id, "doviz_cuzdan");
					a.otherQuery("UPDATE doviz_cuzdan SET para = " + lastDoviz + " WHERE musteri_id = "+ id +";");
					a.otherQuery("UPDATE cuzdan SET para = " + last + " WHERE musteri_id = "+ id +";");
					a.otherQuery("INSERT INTO hesap_hareketleri VALUES(" + id + ", '" + (money / 30) + "$ DÖVİZ ALINDI');");
					paraMiktariLabel.setText("PARA MİKTARI: " + last + "TL");
					dovizMiktariLabel.setText("DÖVİZ MİKTARI: " + lastDoviz + "$");
					showHareketTable(id);
				}
			}
		});
		
		JButton btnDvizSat = new JButton("DOLAR SAT");
		btnDvizSat.setForeground(SystemColor.activeCaption);
		btnDvizSat.setBackground(SystemColor.window);
		btnDvizSat.setBounds(624, 401, 116, 23);
		backgroundPanel.add(btnDvizSat);
		btnDvizSat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppUsers a = new AppUsers();
				int money;
				try {
					 money = Integer.valueOf(paraTextField.getText());
				}
				catch (NumberFormatException ee)
				{
					return;
				}
				int last = returnMoney(id, "doviz_cuzdan") - money;
				if (last < 0)
					JOptionPane.showMessageDialog(new JFrame(), "Yeterli Dolarınız Yok.");
				else
				{
					int lastPara = returnMoney(id, "cuzdan") + (money * 30);
					a.otherQuery("UPDATE doviz_cuzdan SET para = " + last + " WHERE musteri_id = "+ id +";");
					a.otherQuery("UPDATE cuzdan SET para = " + lastPara + " WHERE musteri_id = "+ id +";");
					a.otherQuery("INSERT INTO hesap_hareketleri VALUES(" + id + ", '" + money + "$ DÖVİZ SATILDI');");
					paraMiktariLabel.setText("PARA MİKTARI: " + lastPara + "TL");
					dovizMiktariLabel.setText("DÖVİZ MİKTARI: " + last + "$");
					showHareketTable(id);
				}
			}
		});
		
		JLabel toplamParaLabel = new JLabel("HESAP HAREKETLERİ");
		toplamParaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		toplamParaLabel.setForeground(SystemColor.window);
		toplamParaLabel.setFont(new Font("Sylfaen", Font.BOLD, 20));
		toplamParaLabel.setBackground(SystemColor.activeCaption);
		toplamParaLabel.setBounds(213, 292, 339, 43);
		backgroundPanel.add(toplamParaLabel);
		
		JButton geriButton = new JButton("GERİ");
		geriButton.setBackground(SystemColor.window);
		geriButton.setForeground(SystemColor.activeCaption);
		geriButton.setBounds(10, 7, 89, 23);
		backgroundPanel.add(geriButton);
		geriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage l = new LoginPage();
				l.setVisible(true);
				setVisible(false);
			}
		});
		

		
	}
}
