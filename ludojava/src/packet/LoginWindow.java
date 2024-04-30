package packet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginWindow extends JDialog {
    
    private Container contenu;
    private JLabel emailLabel = new JLabel("Email :");
    private JLabel passwordLabel = new JLabel("Mot de passe :");
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("Connexion");

 
    private static final String URL = "jdbc:mysql://localhost:3306/arboretum";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";

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
        this.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        
        contenu.add(emailLabel);
        contenu.add(emailField);
        contenu.add(passwordLabel);
        contenu.add(passwordField);
        contenu.add(new JLabel());
        contenu.add(loginButton);

        this.setVisible(true);
    }
    

    private void attemptLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String URL = "jdbc:mysql://localhost:3306/ludojava";
        String LOGIN = "root";
        String PASSWORD = "";
        
        try {
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connexion réussie avec " + email + " et mot de passe " + password);
            connection.close();
            
            // Fermer la fenêtre de connexion une fois que l'utilisateur est connecté
            dispose(); // Cette ligne ferme la fenêtre de dialogue courante
            
        } catch (SQLException ex) {
            System.out.println("ERREUR lors de la connexion à la base de données : " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginWindow();
    }
}
