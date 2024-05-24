package packet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginWindow extends JDialog implements ActionListener {
    
    private Container contenu;
    private JLabel emailLabel = new JLabel("Email :");
    private JLabel passwordLabel = new JLabel("Mot de passe :");
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("Connexion");
    private JButton backButton = new JButton("Retour");

 
    private static final String URL = "jdbc:mysql://localhost:3306/ludojava";
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
        
        this.loginButton.setBounds(150, 100, 100, 30);
        this.loginButton.addActionListener(this);
        
        this.backButton.setBounds(30, 100, 100, 30);
        this.backButton.addActionListener(this);
        
        contenu.add(emailLabel);
        contenu.add(emailField);
        contenu.add(passwordLabel);
        contenu.add(passwordField);
        contenu.add(new JLabel());
        contenu.add(loginButton);
        contenu.add(backButton);

        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            
            try {
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                System.out.println("Connexion réussie avec " + email + " et mot de passe " + password);
                
                String query = "SELECT id FROM personne WHERE mail = ? AND pwd = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, email);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                	MainWindow.setIdUtilisateurConnecte(resultSet.getInt("id"));
                	String query2 = "SELECT * From admin join personne on admin.idpers = personne.id where personne.mail = ?";
                    PreparedStatement statement2 = connection.prepareStatement(query2);
                    statement2.setString(1, email);
                    ResultSet resultSet2 = statement.executeQuery();
                    
                    if (resultSet2.next()) {
                        MainWindow.setEstAdmin(true); 
                    } else {
                    	MainWindow.setEstAdmin(false); 
                    }
                    // Fermer la fenêtre de connexion une fois que l'utilisateur est connecté
                    dispose(); // Cette ligne ferme la fenêtre de dialogue courante
                    
                    MainWindow.setUtilisateurConnecte(true);
                    MainWindow mw = new MainWindow();
                    mw.setVisible(true); 
                    
                } else {
                	JOptionPane.showMessageDialog(this, "Identificants incorrect"); 
                }
                
            } catch (SQLException ex) {
                System.out.println("ERREUR lors de la connexion à la base de données : " + ex.getMessage());
            }
        }
        
        if (e.getSource() == backButton) {
        	this.setVisible(false);
            dispose();
            MainWindow mw = new MainWindow();
            mw.setVisible(true);
        }
    }
}
