package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;


abstract class CalisanPage extends JFrame implements DatabasePath {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	JPanel rightPanel;
	protected JLabel sayfaLabel;

	public CalisanPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 rightPanel = new JPanel();
		rightPanel.setBackground(SystemColor.window);
		rightPanel.setBounds(24, 26, 733, 398);
		contentPane.add(rightPanel);
		rightPanel.setLayout(null);	
		
		JButton geriButton = new JButton("GERİ");
		geriButton.setForeground(SystemColor.activeCaption);
		geriButton.setBackground(SystemColor.window);
		geriButton.setBounds(10, 435, 89, 23);
		contentPane.add(geriButton);
		
		sayfaLabel = new JLabel("SAYFA");
		sayfaLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		sayfaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sayfaLabel.setBackground(SystemColor.activeCaption);
		sayfaLabel.setForeground(SystemColor.window);
		sayfaLabel.setBounds(223, 0, 352, 25);
		contentPane.add(sayfaLabel);
		geriButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				LoginPage loginPage = new LoginPage();
				loginPage.setVisible(true);
				setVisible(false);
			}
		});
	}
}
