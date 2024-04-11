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
	private JPanel connectionBox = new JPanel();
	private JButton loginButton = new JButton("Connexion");
	private JButton signupButton = new JButton("Inscription");
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
		
		this.boxLeft.setBounds(20, 50, 300,495);
		this.boxLeftName.setBounds(20, 20, 300, 30);
		this.boxLeftName.setBackground(Color.lightGray);
		this.boxLeftName.add(boxLeftLabel, "align center");
		
		this.boxMiddle.setBounds(340, 20, 300, 525);
		
		this.boxRight.setBounds(660, 20, 200, 525);
		this.boxRight.setBackground(Color.lightGray);
		this.boxRight.add(connectionBox);
		this.connectionBox.setBackground(Color.lightGray);
		this.connectionBox.setBounds(5, 5, 290, 500);
		
		GridBagLayout rightBoxGrid = new GridBagLayout();
		GridBagConstraints rightBoxConst = new GridBagConstraints();
		this.connectionBox.setLayout(rightBoxGrid);
		rightBoxConst.gridheight = 1;
		rightBoxConst.gridwidth = 0;
		rightBoxConst.gridx = 0;
		rightBoxConst.gridy = 0;
		rightBoxConst.insets = new Insets(5,0,5,0);
		rightBoxGrid.setConstraints(loginButton, rightBoxConst);
		rightBoxConst.gridy = 1;
		rightBoxGrid.setConstraints(signupButton, rightBoxConst);
		
		this.loginButton.addActionListener(this);
		this.connectionBox.add(loginButton);
		this.signupButton.addActionListener(this);
		this.connectionBox.add(signupButton);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			LoginWindow lw = new LoginWindow();
			lw.setVisible(true);
		}
		if (e.getSource() == signupButton) {
			RegisterWindow lw = new RegisterWindow();
			lw.setVisible(true);
}
	
		
		

				
	}
}