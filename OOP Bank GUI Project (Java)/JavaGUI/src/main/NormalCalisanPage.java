package main;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class NormalCalisanPage extends CalisanPage implements ITableShow
{
	private static final long serialVersionUID = 1L;
	
private JTable table;
	
	
	
	void showKullaniciTable(int index) {
		ResultSet res = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
			Statement stmt = conn.createStatement();
			if (index == 4)
				res = stmt.executeQuery("SELECT * FROM kullanicilar;");
			else
				res = stmt.executeQuery("SELECT * FROM kullanicilar WHERE sube_id = " + index + ";");
			ResultSetMetaData resMD = res.getMetaData();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.setRowCount(0);
			int cols = resMD.getColumnCount();
			String colNames[] = new String[cols];
			for (int i =0; i < cols; i++)
				colNames[i] = resMD.getColumnName(i + 1);
			model.setColumnIdentifiers(colNames);
			while(res.next())
			{
				String[] row = {((Integer)res.getInt(1)).toString(), res.getString(2), res.getString(3),
						res.getString(4), ((Integer)res.getInt(5)).toString(), res.getString(6), res.getString(7)};
				model.addRow(row);			
			}
					
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showTables()
	{
		showKullaniciTable(4);
	}


	NormalCalisanPage()
	{
		super("Normal Çalışan Sayfası");
		sayfaLabel.setText("NORMAL CALISAN SAYFASI");
		JScrollPane tabelPane = new JScrollPane();
		tabelPane.setBounds(10, 57, 713, 286);
		rightPanel.add(tabelPane);
		
		table = new JTable();
		tabelPane.setViewportView(table);
		
		String[] subeler = {"ALIBEYKOY", "AVCILAR", "BEYLIKDUZU", "FATIH", "TUMU"};
		JComboBox<String> subeComboBox = new JComboBox<String>(subeler);
		subeComboBox.setBounds(10, 24, 713, 22);
		rightPanel.add(subeComboBox);
		subeComboBox.setSelectedIndex(4);
		subeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showKullaniciTable(subeComboBox.getSelectedIndex());
				
		}});
		
		JButton kullaniciSilButton = new JButton("KULLANICI SIL");
		kullaniciSilButton.setBackground(SystemColor.activeCaption);
		kullaniciSilButton.setForeground(SystemColor.window);
		kullaniciSilButton.setBounds(558, 354, 165, 23);
		rightPanel.add(kullaniciSilButton);
		kullaniciSilButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int row = table.getSelectedRow();
				if (row != -1) {
					deleteUser((String)table.getValueAt(row, 0), "kullanicilar");
					deleteUser((String)table.getValueAt(row, 0), "cuzdan");
					deleteUser((String)table.getValueAt(row, 0), "hesap_hareketleri");
					deleteUser((String)table.getValueAt(row, 0), "doviz_cuzdan");
					showKullaniciTable(subeComboBox.getSelectedIndex());
				}
			}
		});
		showTables();
	}
}