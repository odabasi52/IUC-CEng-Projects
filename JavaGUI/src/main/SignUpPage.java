package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class SignUpPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tcKimlikTextField;
	private JTextField kullaniciAdiTextField;
	private JPasswordField parolaTextField;

	public SignUpPage() {
		super("KAYIT SAYFASI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(SystemColor.activeCaption);
		leftPanel.setBounds(0, 0, 374, 461);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);
		
		JLabel iconIUCLabel = new JLabel("");
		iconIUCLabel.setBounds(-126, -25, 500, 500);
		leftPanel.add(iconIUCLabel);
		iconIUCLabel.setIcon(new ImageIcon("D:\\Projects\\Java Projects\\BankGUI\\iconIUC.png"));
		
		JButton geriButton = new JButton("GERI");
		geriButton.setForeground(SystemColor.window);
		geriButton.setBackground(SystemColor.activeCaption);
		geriButton.setBounds(10, 427, 82, 23);
		leftPanel.add(geriButton);
		geriButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
				loginPage.setVisible(true);
				setVisible(false);
			}
			
		});
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(SystemColor.window);
		rightPanel.setBounds(372, 0, 412, 461);
		contentPane.add(rightPanel);
		rightPanel.setLayout(null);
		
		JLabel kayitSayfasiLabel = new JLabel("KAYIT SAYFASI");
		kayitSayfasiLabel.setForeground(SystemColor.activeCaption);
		kayitSayfasiLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		kayitSayfasiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kayitSayfasiLabel.setBounds(36, 11, 366, 72);
		rightPanel.add(kayitSayfasiLabel);
		
		JLabel tcKimlikLabel = new JLabel("TC KIMLIK");
		tcKimlikLabel.setBounds(10, 122, 137, 14);
		rightPanel.add(tcKimlikLabel);
		
		JLabel kullaniciAdiLabel = new JLabel("KULLANICI ADI");
		kullaniciAdiLabel.setBounds(10, 197, 137, 14);
		rightPanel.add(kullaniciAdiLabel);
		
		JLabel parolaLabel = new JLabel("PAROLA");
		parolaLabel.setBounds(10, 274, 137, 14);
		rightPanel.add(parolaLabel);
		
		tcKimlikTextField = new JTextField();
		tcKimlikTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tcKimlikTextField.setHorizontalAlignment(SwingConstants.CENTER);
		tcKimlikTextField.setBounds(10, 147, 392, 28);
		rightPanel.add(tcKimlikTextField);
		tcKimlikTextField.setColumns(10);
		tcKimlikTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
	            	tcKimlikTextField.setEditable(true);
	            } else {
	            	tcKimlikTextField.setEditable(false);
	            }
	         }
		});

		kullaniciAdiTextField = new JTextField();
		kullaniciAdiTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		kullaniciAdiTextField.setHorizontalAlignment(SwingConstants.CENTER);
		kullaniciAdiTextField.setColumns(10);
		kullaniciAdiTextField.setBounds(10, 222, 392, 28);
		rightPanel.add(kullaniciAdiTextField);
		
		parolaTextField = new JPasswordField();
		parolaTextField.setHorizontalAlignment(SwingConstants.CENTER);
		parolaTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		parolaTextField.setEchoChar('*');
		parolaTextField.setBounds(10, 299, 392, 28);
		rightPanel.add(parolaTextField);
		
		JButton kayitOlButton = new JButton("KAYIT OL");
		kayitOlButton.setBackground(SystemColor.activeCaption);
		kayitOlButton.setForeground(SystemColor.window);
		kayitOlButton.setBounds(10, 351, 109, 23);
		rightPanel.add(kayitOlButton);
		
		JCheckBox gosterCheckBox = new JCheckBox("GOSTER");
		gosterCheckBox.setForeground(SystemColor.activeCaption);
		gosterCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
		gosterCheckBox.setBackground(SystemColor.window);
		gosterCheckBox.setBounds(273, 274, 129, 14);
		rightPanel.add(gosterCheckBox);
		gosterCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gosterCheckBox.isSelected())
					parolaTextField.setEchoChar((char)0);
				else
					parolaTextField.setEchoChar('*');
			}
			
		});
	}
}
