package main;

import java.security.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

/* calisanlar table
 * talha | parola | not admin
 * admin | admin  | yes admin
 */
public class LoginPage extends DatabaseStuffs {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField kullaniciAdiTextField;
	private JPasswordField parolaTextField;
	

	public LoginPage() {
		super("GIRIS SAYFASI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setBounds(366, 0, 418, 461);
		contentPane.add(rightPanel);
		rightPanel.setLayout(null);
		
		JLabel girisLabel = new JLabel("GIRIS");
		girisLabel.setForeground(SystemColor.activeCaption);
		girisLabel.setFont(new Font("Arial Black", Font.BOLD, 36));
		girisLabel.setBounds(159, 11, 123, 70);
		rightPanel.add(girisLabel);
		
		JLabel kullaniciAdiLabel = new JLabel("KULLANICI ADI");
		kullaniciAdiLabel.setBounds(22, 131, 93, 14);
		rightPanel.add(kullaniciAdiLabel);
		
		JLabel parolaLabel = new JLabel("PAROLA");
		parolaLabel.setBounds(22, 214, 56, 14);
		rightPanel.add(parolaLabel);
		
		kullaniciAdiTextField = new JTextField();
		kullaniciAdiTextField.setHorizontalAlignment(SwingConstants.CENTER);
		kullaniciAdiTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		kullaniciAdiTextField.setBounds(22, 148, 362, 36);
		rightPanel.add(kullaniciAdiTextField);
		kullaniciAdiTextField.setColumns(10);
		
		parolaTextField = new JPasswordField();
		parolaTextField.setEchoChar('*');
		parolaTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		parolaTextField.setHorizontalAlignment(SwingConstants.CENTER);
		parolaTextField.setBounds(22, 230, 362, 36);
		rightPanel.add(parolaTextField);
		
		JButton girisButton = new JButton("GIRIS");
		girisButton.setBackground(SystemColor.activeCaption);
		girisButton.setForeground(SystemColor.window);
		girisButton.setBounds(26, 277, 89, 23);
		rightPanel.add(girisButton);
		girisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MessageDigest md5 = MessageDigest.getInstance("SHA-256");
					byte[] hash = md5.digest(new String(parolaTextField.getPassword()).getBytes());
					StringBuilder hexString = new StringBuilder();
				    for (byte b : hash) {
				      hexString.append(String.format("%02x", b));
				    }
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel hesabimYokLabel = new JLabel("HESABIM YOK");
		hesabimYokLabel.setBounds(22, 364, 123, 14);
		rightPanel.add(hesabimYokLabel);
		
		JLabel parolamiUnuttumLabel = new JLabel("PAROLAMI UNUTTUM");
		parolamiUnuttumLabel.setBounds(22, 406, 123, 14);
		rightPanel.add(parolamiUnuttumLabel);
		
		JButton parolaSifirlaButton = new JButton("PAROLA SIFIRLA");
		parolaSifirlaButton.setForeground(SystemColor.window);
		parolaSifirlaButton.setBackground(SystemColor.activeCaption);
		parolaSifirlaButton.setBounds(203, 402, 181, 23);
		rightPanel.add(parolaSifirlaButton);
		parolaSifirlaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
				forgotPasswordPage.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton kayitOlButton = new JButton("KAYIT OL");
		kayitOlButton.setForeground(SystemColor.window);
		kayitOlButton.setBackground(SystemColor.activeCaption);
		kayitOlButton.setBounds(203, 360, 181, 23);
		rightPanel.add(kayitOlButton);
		kayitOlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpPage signUpPage = new SignUpPage();
				signUpPage.setVisible(true);
				setVisible(false);
			}
		});
		
		final JCheckBox gosterCheckBox = new JCheckBox("GOSTER");
		gosterCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
		gosterCheckBox.setForeground(SystemColor.activeCaption);
		gosterCheckBox.setBackground(SystemColor.window);
		gosterCheckBox.setBounds(287, 210, 97, 18);
		rightPanel.add(gosterCheckBox);
		gosterCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gosterCheckBox.isSelected())
					parolaTextField.setEchoChar((char)0);
				else
					parolaTextField.setEchoChar('*');
			}
			
		});

		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(SystemColor.activeCaption);
		leftPanel.setBounds(0, 0, 367, 461);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);
		
		JLabel iconIUCLabel = new JLabel("");
		iconIUCLabel.setBounds(10, 0, 424, 461);
		leftPanel.add(iconIUCLabel);
		iconIUCLabel.setIcon(new ImageIcon("D:\\Projects\\Java Projects\\BankGUI\\iconIUC.png"));
	}
}
