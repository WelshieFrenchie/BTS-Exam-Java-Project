package packet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginWindow extends JDialog {
    
    private JPanel window = new JPanel();
    private Container contenu;
    private JLabel emailLabel = new JLabel("Email :");
    private JLabel passwordLabel = new JLabel("Mot de passe :");
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("Connexion");

    public LoginWindow() {
        this.setTitle("Login");
        this.setBounds(800, 250, 300, 200);
        this.setResizable(false);
        
        contenu = getContentPane();
        contenu.setLayout(null);

        
        this.emailLabel.setBounds(20, 20, 50, 30);
        this.emailField.setBounds(120, 20, 140, 30);

        this.passwordLabel.setBounds(20, 40, 100, 60);
        this.passwordField.setBounds(120, 50, 140, 30);
        
        this.loginButton.setBounds(130, 100, 100, 30);
        
        contenu.add(emailLabel);
        contenu.add(emailField);
        contenu.add(passwordLabel);
        contenu.add(passwordField);
        contenu.add(new JLabel());
        contenu.add(loginButton);

        
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new LoginWindow();
    }
}
