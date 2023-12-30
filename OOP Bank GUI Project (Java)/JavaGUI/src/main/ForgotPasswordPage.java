package main;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ForgotPasswordPage extends JFrame implements IPaths{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tcKimlikTextField;
	private JTextField kullaniciAdiTextField;
	private JTextField guvenlikSorulariTextField;

	public ForgotPasswordPage() {
		super("PAROLA YENILEME SAYFASI");
		setLocationRelativeTo(null);
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
		
		JLabel parolaYenilemeLabel = new JLabel("PAROLA YENILEME");
		parolaYenilemeLabel.setForeground(SystemColor.activeCaption);
		parolaYenilemeLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		parolaYenilemeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		parolaYenilemeLabel.setBounds(36, 11, 366, 72);
		rightPanel.add(parolaYenilemeLabel);
		
		JLabel tcKimlikLabel = new JLabel("TC KIMLIK");
		tcKimlikLabel.setBounds(10, 127, 137, 14);
		rightPanel.add(tcKimlikLabel);
		
		JLabel kullaniciAdiLabel = new JLabel("KULLANICI ADI");
		kullaniciAdiLabel.setBounds(10, 202, 137, 14);
		rightPanel.add(kullaniciAdiLabel);
		
		tcKimlikTextField = new JTextField();
		tcKimlikTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tcKimlikTextField.setHorizontalAlignment(SwingConstants.CENTER);
		tcKimlikTextField.setBounds(10, 147, 392, 28);
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
		kullaniciAdiTextField.setBounds(10, 222, 392, 28);
		rightPanel.add(kullaniciAdiTextField);
		
		JLabel guvenlikSorusuLabel = new JLabel("GUVENLIK SORUSU");
		guvenlikSorusuLabel.setBounds(10, 274, 137, 14);
		rightPanel.add(guvenlikSorusuLabel);
		
		String[] elements = {"Annenizin Kızlık Soyadı nedir?", "Gittiğiniz İlkokul' un Adı nedir?", "Evcil Hayvanınızın Adı nedir?"};
		JComboBox<String> guvenlikSorulariComboBox = new JComboBox<String>(elements);
		guvenlikSorulariComboBox.setBounds(10, 296, 392, 22);
		rightPanel.add(guvenlikSorulariComboBox);
		
		guvenlikSorulariTextField = new JTextField();
		guvenlikSorulariTextField.setHorizontalAlignment(SwingConstants.CENTER);
		guvenlikSorulariTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		guvenlikSorulariTextField.setColumns(10);
		guvenlikSorulariTextField.setBounds(10, 321, 392, 28);
		rightPanel.add(guvenlikSorulariTextField);
		
		JButton sifirlaButton = new JButton("SIFIRLA");
		sifirlaButton.setBackground(SystemColor.activeCaption);
		sifirlaButton.setForeground(SystemColor.window);
		sifirlaButton.setBounds(151, 358, 109, 23);
		rightPanel.add(sifirlaButton);
		
		JLabel noteLabel = new JLabel("Eğer çalışansanız lütfen yönetime ulaşın");
		noteLabel.setForeground(SystemColor.activeCaption);
		noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		noteLabel.setBounds(70, 64, 313, 14);
		rightPanel.add(noteLabel);
		sifirlaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*SIFIRLAMA ISLEMLERI*/
				if (tcKimlikTextField.getText().length() != 11)
					JOptionPane.showMessageDialog(new JFrame(), "TC Kimlik 11 Haneli Olmalıdır.");
				else {
					AppUsers k = new AppUsers();
					if (k.checkUserForgotPassword(tcKimlikTextField.getText(), kullaniciAdiTextField.getText()
							, guvenlikSorulariComboBox.getSelectedIndex(), guvenlikSorulariTextField.getText()))
					{
						String parola = JOptionPane.showInputDialog("Yeni Parola");
						if (parola == null)
							return;
						while (parola.length() < 8) {
							parola = JOptionPane.showInputDialog("Yeni Parola [En az 8 Haneli]");
							if (parola == null)
								return;
						}
						if (parola != null)
						{
							k.otherQuery("UPDATE kullanicilar SET parola = '" + k.createHash(parola) + "' WHERE tc_kimlik = '" + k.createHash(tcKimlikTextField.getText()) + "';");
							JOptionPane.showMessageDialog(new JFrame(), "Parolanız başarı ile sıfırlandı.");
						}
					}
					else
						JOptionPane.showMessageDialog(new JFrame(), "Kullanıcı Bulunamadı.");
				}
			}
		});
	}
}
