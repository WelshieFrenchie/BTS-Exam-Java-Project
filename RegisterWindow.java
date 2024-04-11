package packet;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterWindow extends JDialog implements ActionListener{
	
	 
	private JPanel window = new JPanel();
	private Container contenu;
	private JLabel emailLabel = new JLabel("Email :");
	private JLabel passwordLabel = new JLabel("Mot de passe :");
	private JTextField emailField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel nomLabel = new JLabel("Nom :");
	private JTextField nomField = new JTextField();
	private JLabel prenomLabel = new JLabel("Prénom :");
	private JTextField prenomField = new JTextField();
	private JLabel password2Label = new JLabel("<html>Confirmation<br> mot de passe :</html>");
	private JPasswordField password2Field = new JPasswordField();
	private JButton signupButton = new JButton("Register");
	private JButton backButton = new JButton("Back");
	
	
		public RegisterWindow() {
			this.setTitle("Register");
	        this.setBounds(800, 250, 300, 300);
	        this.setResizable(false);
	        
	        contenu = getContentPane();
	        contenu.setLayout(null);
	
	        
	        this.nomLabel.setBounds(20, 20, 50, 30);
	        this.nomField.setBounds(120, 20, 140, 30);
	
	        this.prenomLabel.setBounds(20, 40, 100, 60);
	        this.prenomField.setBounds(120, 55, 140, 30);
	        
	        this.emailLabel.setBounds(20, 60, 150, 90);
	        this.emailField.setBounds(120, 90, 140, 30);
	        
	        this.passwordLabel.setBounds(20, 95, 150, 90);
	        this.passwordField.setBounds(120, 125, 140, 30);
	        
	        this.password2Label.setBounds(20, 130, 150, 90);
	        this.password2Field.setBounds(120, 160, 140, 30);
	        
	        this.signupButton.setBounds(150, 210, 115, 30);
	        this.backButton.setBounds(20, 210, 115, 30);
	        
	        contenu.add(emailLabel);
	        contenu.add(emailField);
	        contenu.add(passwordLabel);
	        contenu.add(passwordField);
	        contenu.add(nomLabel);
	        contenu.add(nomField);
	        contenu.add(prenomLabel);
	        contenu.add(prenomField);
	        contenu.add(password2Field);
	        contenu.add(password2Label);
	        contenu.add(new JLabel());
	        contenu.add(signupButton);
	        contenu.add(backButton);
	        
	        this.backButton.addActionListener(this);
	        
	        this.setVisible(true);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()== backButton) {
				this.setVisible(false);
			}
		}
}
