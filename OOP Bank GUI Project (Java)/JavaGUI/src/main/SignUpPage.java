package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class SignUpPage extends JFrame implements IPaths{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tcKimlikTextField;
	private JTextField kullaniciAdiTextField;
	private JPasswordField parolaTextField;
	private JTextField guvenlikSorulariTextField;

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
		iconIUCLabel.setIcon(new ImageIcon(iconPath));
		
		JButton geriButton = new JButton("GERI");
		geriButton.setForeground(SystemColor.window);
		geriButton.setBackground(SystemColor.activeCaption);
		geriButton.setBounds(10, 427, 82, 23);
		leftPanel.add(geriButton);
		geriButton.addActionListener(new ActionListener() {
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
		tcKimlikLabel.setBounds(10, 82, 137, 14);
		rightPanel.add(tcKimlikLabel);
		
		JLabel kullaniciAdiLabel = new JLabel("KULLANICI ADI");
		kullaniciAdiLabel.setBounds(10, 149, 137, 14);
		rightPanel.add(kullaniciAdiLabel);
		
		JLabel parolaLabel = new JLabel("PAROLA");
		parolaLabel.setBounds(10, 216, 137, 14);
		rightPanel.add(parolaLabel);
		
		tcKimlikTextField = new JTextField();
		tcKimlikTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tcKimlikTextField.setHorizontalAlignment(SwingConstants.CENTER);
		tcKimlikTextField.setBounds(10, 100, 392, 28);
		rightPanel.add(tcKimlikTextField);
		tcKimlikTextField.setColumns(10);
		tcKimlikTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
	            if (ke.getKeyChar() == '\b' || (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')) {
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
		kullaniciAdiTextField.setBounds(10, 166, 392, 28);
		rightPanel.add(kullaniciAdiTextField);
		
		parolaTextField = new JPasswordField();
		parolaTextField.setHorizontalAlignment(SwingConstants.CENTER);
		parolaTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		parolaTextField.setEchoChar('*');
		parolaTextField.setBounds(10, 231, 392, 28);
		rightPanel.add(parolaTextField);
		
		final JCheckBox gosterCheckBox = new JCheckBox("GOSTER");
		gosterCheckBox.setForeground(SystemColor.activeCaption);
		gosterCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
		gosterCheckBox.setBackground(SystemColor.window);
		gosterCheckBox.setBounds(273, 216, 129, 14);
		rightPanel.add(gosterCheckBox);
		gosterCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gosterCheckBox.isSelected())
					parolaTextField.setEchoChar((char)0);
				else
					parolaTextField.setEchoChar('*');
			}
			
		});
		
		JLabel guvenlikSorusuLabel = new JLabel("GUVENLIK SORUSU");
		guvenlikSorusuLabel.setBounds(10, 281, 137, 14);
		rightPanel.add(guvenlikSorusuLabel);
		
		String[] elements = {"Annenizin Kızlık Soyadı nedir?", "Gittiğiniz İlkokul' un Adı nedir?", "Evcil Hayvanınızın Adı nedir?"};
		JComboBox<String> guvenlikSorulariComboBox = new JComboBox<String>(elements);
		guvenlikSorulariComboBox.setBounds(10, 306, 392, 22);
		rightPanel.add(guvenlikSorulariComboBox);
		
		guvenlikSorulariTextField = new JTextField();
		guvenlikSorulariTextField.setHorizontalAlignment(SwingConstants.CENTER);
		guvenlikSorulariTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		guvenlikSorulariTextField.setColumns(10);
		guvenlikSorulariTextField.setBounds(10, 331, 392, 28);
		rightPanel.add(guvenlikSorulariTextField);

		JButton kayitOlButton = new JButton("KAYIT OL");
		kayitOlButton.setBackground(SystemColor.activeCaption);
		kayitOlButton.setForeground(SystemColor.window);
		kayitOlButton.setBounds(10, 413, 109, 23);
		rightPanel.add(kayitOlButton);
		kayitOlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tcKimlikTextField.getText().length() != 11)
					JOptionPane.showMessageDialog(new JFrame(), "TC Kimlik 11 Haneli Olmalıdır.");
				else if(kullaniciAdiTextField.getText().length() < 3)
					JOptionPane.showMessageDialog(new JFrame(), "Geçerli bir Kullanıcı Adı Giriniz.");
				else if (parolaTextField.getPassword().length < 8) 
					JOptionPane.showMessageDialog(new JFrame(), "Parola en az 8 Haneli Olmalıdır.");
				else if (guvenlikSorulariTextField.getText().length() < 3)
					JOptionPane.showMessageDialog(new JFrame(), "Güvenlik Sorusu Seçip Geçerli Cevap Veriniz.");
				else
				{
					/*KAYIT OLMA ISLEMLERI*/
					AppUsers k = new AppUsers();
					if(k.checkUser(tcKimlikTextField.getText(), kullaniciAdiTextField.getText()))
					{
						k.create(kullaniciAdiTextField.getText(), tcKimlikTextField.getText(), new String(parolaTextField.getPassword()),
								guvenlikSorulariComboBox.getSelectedIndex(), guvenlikSorulariTextField.getText());
						JOptionPane.showMessageDialog(new JFrame(), "Kayıt Başarılı.");
					}
				}
			}
		});
		
		
		
	}
}
