package packet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MainWindow extends JFrame implements ActionListener{
	
	private JScrollPane boxLeft = new JScrollPane();
	private JLabel boxLeftLabel = new JLabel("Liste de jeux");
	private JPanel boxLeftName = new JPanel();
	private JScrollPane boxMiddle = new JScrollPane();
	private JPanel boxRight = new JPanel();
	private JButton loginButton = new JButton("Connexion");
	private Container contenu;
	
	public MainWindow() {
		super();
		
		this.setTitle("LudoFun");
		this.setBounds(500, 200, 900, 600);
		this.setResizable(false);
		
		this.contenu = this.getContentPane();
		this.contenu.add(boxLeft);
		this.contenu.add(boxLeftName);
		this.contenu.add(boxMiddle);
		this.contenu.add(boxRight);
		this.contenu.setLayout(null);
		
		this.boxLeft.setBounds(20, 50, 300, 525);
		this.boxLeftName.setBounds(20, 20, 300, 30);
		this.boxLeftName.setBackground(Color.lightGray);
		this.boxLeftName.add(boxLeftLabel, "align center");
		
		this.boxMiddle.setBounds(340, 20, 300, 525);
		
		this.boxRight.setBounds(660, 20, 200, 525);
		this.boxRight.setBackground(Color.lightGray);
		this.loginButton.addActionListener(this);
		this.boxRight.add(loginButton);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			LoginWindow lw = new LoginWindow();
			lw.setVisible(true);
		}
	}
}
