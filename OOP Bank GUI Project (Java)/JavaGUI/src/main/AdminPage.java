package main;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class AdminPage extends CalisanPage implements ITableShow{
	private static final long serialVersionUID = 1L;
	
	private JTable subelerTable;
	void showSubeTable() {
		ResultSet res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT * FROM subeler;");
			ResultSetMetaData resMD = res.getMetaData();
			DefaultTableModel model = (DefaultTableModel)subelerTable.getModel();
			model.setRowCount(0);
			int cols = resMD.getColumnCount();
			String colNames[] = new String[cols];
			for (int i =0; i < cols; i++)
				colNames[i] = resMD.getColumnName(i + 1);
			model.setColumnIdentifiers(colNames);
			while(res.next())
			{
				String[] row = {((Integer)res.getInt(1)).toString(), res.getString(2)};
				model.addRow(row);			
			}
			subelerTable.setEnabled(false);
					
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JTable calisanTable;
	void showCalisanTable() {
		ResultSet res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT id,isim, rol, is_admin FROM calisanlar;");
			ResultSetMetaData resMD = res.getMetaData();
			DefaultTableModel model = (DefaultTableModel)calisanTable.getModel();
			model.setRowCount(0);
			int cols = resMD.getColumnCount();
			String colNames[] = new String[cols];
			for (int i =0; i < cols; i++)
				colNames[i] = resMD.getColumnName(i + 1);
			model.setColumnIdentifiers(colNames);
			while(res.next())
			{
				String[] row = {res.getString(1), res.getString(2),  res.getString(3), ((Boolean)res.getBoolean(4)).toString()};
				model.addRow(row);
			}
					
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JTable kullaniciTable;
	void showKullaniciTable() {
		ResultSet res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT id, isim FROM kullanicilar;");
			ResultSetMetaData resMD = res.getMetaData();
			DefaultTableModel model = (DefaultTableModel)kullaniciTable.getModel();
			model.setRowCount(0);
			int cols = resMD.getColumnCount();
			String colNames[] = new String[cols];
			for (int i =0; i < cols; i++)
				colNames[i] = resMD.getColumnName(i + 1);
			model.setColumnIdentifiers(colNames);
			while(res.next())
			{
				String[] row = {((Integer)res.getInt(1)).toString(), res.getString(2)};
				model.addRow(row);
			}
			kullaniciTable.setEnabled(false);
					
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showTables()
	{
		showSubeTable();
		showCalisanTable();
		showKullaniciTable();
	}

	AdminPage()
	{
		super("Admin Sayfası");
		JScrollPane subelerPane = new JScrollPane();
		subelerPane.setBounds(10, 42, 190, 345);
		rightPanel.add(subelerPane);
		
		subelerTable = new JTable();
		subelerPane.setViewportView(subelerTable);
		
		JScrollPane calisanlarPane = new JScrollPane();
		calisanlarPane.setBounds(221, 42, 290, 311);
		rightPanel.add(calisanlarPane);
		
		calisanTable = new JTable();
		calisanlarPane.setViewportView(calisanTable);
		
		JScrollPane kullaniciPane = new JScrollPane();
		kullaniciPane.setBounds(533, 42, 190, 345);
		rightPanel.add(kullaniciPane);
		
		kullaniciTable = new JTable();
		kullaniciPane.setViewportView(kullaniciTable);
		
		JLabel subelerimizLabel = new JLabel("ŞUBELERIMIZ");
		subelerimizLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subelerimizLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		subelerimizLabel.setForeground(SystemColor.activeCaption);
		subelerimizLabel.setBackground(SystemColor.window);
		subelerimizLabel.setBounds(10, 11, 190, 29);
		rightPanel.add(subelerimizLabel);
		
		JLabel calisanlarLabel = new JLabel("CALISANLAR");
		calisanlarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		calisanlarLabel.setForeground(SystemColor.activeCaption);
		calisanlarLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		calisanlarLabel.setBackground(SystemColor.window);
		calisanlarLabel.setBounds(251, 11, 235, 29);
		rightPanel.add(calisanlarLabel);
		
		JLabel kullanicilarLabel = new JLabel("KULLANICILAR");
		kullanicilarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kullanicilarLabel.setForeground(SystemColor.activeCaption);
		kullanicilarLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		kullanicilarLabel.setBackground(SystemColor.window);
		kullanicilarLabel.setBounds(533, 11, 190, 29);
		rightPanel.add(kullanicilarLabel);
		
		JButton ekleButton = new JButton("+");
		ekleButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ekleButton.setBackground(SystemColor.textHighlightText);
		ekleButton.setForeground(SystemColor.activeCaption);
		ekleButton.setBounds(261, 364, 56, 23);
		rightPanel.add(ekleButton);
		ekleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isim = JOptionPane.showInputDialog("Kisi Adi");
				if (isim == null)
					return ;
				while (isim.length() < 3)
				{
					isim = JOptionPane.showInputDialog("Gecersiz Kisi adi");
					if (isim == null)
						return ;
				}
				
				String parola = JOptionPane.showInputDialog("Parola");
				if (parola == null)
					return ;
				while (parola.length() < 3)
				{
					parola = JOptionPane.showInputDialog("Parola [en az 8 Haneli]");
					if (parola == null)
						return ;
				}
				
				String rol = JOptionPane.showInputDialog("Kisi Rolü:");
				if (rol == null)
					return ;
				while (rol.length() < 3)
				{
					rol = JOptionPane.showInputDialog("Gecersiz Rol");
					if (rol == null)
						return ;
				}
				
				String is_admin = JOptionPane.showInputDialog("Kisi Yonetici [Y/N]");
				if (is_admin == null)
					return ;
				while (isim.length() != 1 && !(is_admin.charAt(0) == 'Y' || is_admin.charAt(0) == 'N'))
				{
					is_admin = JOptionPane.showInputDialog("Y , N seçeneklerinden birini giriniz.");
					if (is_admin == null)
						return ;
				}
				AppUsers a = new AppUsers();
				a.otherQuery(String.format("INSERT INTO calisanlar VALUES(%d, '%s', '%s', '%s', %b);",a.returnCalisanID() ,isim, a.createHash(parola), rol, is_admin.charAt(0) == 'Y'));
				showCalisanTable();
			}
		});
		
		JButton silButton = new JButton("-");
		silButton.setForeground(SystemColor.activeCaption);
		silButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		silButton.setBackground(SystemColor.textHighlightText);
		silButton.setBounds(416, 364, 56, 23);
		rightPanel.add(silButton);
		silButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = calisanTable.getSelectedRow();
				if (row != -1) {
					deleteUser((String)calisanTable.getValueAt(row, 0), "calisanlar");
					showCalisanTable();
				}
			}
		});
		
		showTables();
	}
}
