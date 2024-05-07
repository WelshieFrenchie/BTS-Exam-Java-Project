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

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterWindow extends JDialog implements ActionListener {
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
    
    private static final String URL = "jdbc:mysql://localhost:3306/ludojava";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";

    public RegisterWindow() {
        this.setTitle("Register");
        this.setBounds(800, 250, 300, 350);
        this.setResizable(false);
        
        contenu = getContentPane();
        contenu.setLayout(null);

        this.nomLabel.setBounds(20, 20, 50, 30);
        this.nomField.setBounds(120, 20, 140, 30);

        this.prenomLabel.setBounds(20, 40, 100, 60);
        this.prenomField.setBounds(120, 55, 140, 30);
        
        this.emailLabel.setBounds(20, 80, 150, 90);
        this.emailField.setBounds(120, 110, 140, 30);
        
        this.passwordLabel.setBounds(20, 125, 150, 90);
        this.passwordField.setBounds(120, 155, 140, 30);
        
        this.password2Label.setBounds(20, 170, 150, 90);
        this.password2Field.setBounds(120, 200, 140, 30);
        
        this.signupButton.setBounds(150, 250, 115, 30);
        this.backButton.setBounds(20, 250, 115, 30);
        
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
        this.signupButton.addActionListener(this);
        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.setVisible(false);
        } else if (e.getSource() == signupButton) {
        	String mail = emailField.getText();
            String pwd = new String(passwordField.getPassword());
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            
            try {
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                
                String query = "INSERT INTO personne (nom, prenom, mail, pwd) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, nom);
                statement.setString(2, prenom);
                statement.setString(3, mail);
                statement.setString(4, pwd);
                
                int rowsSelected = statement.executeUpdate();
                if (rowsSelected > 0) {
                    System.out.println("Enregistrement réussi pour l'utilisateur avec l'email : " + mail);
                    this.setVisible(false); 
                    
                    String query2 = "SELECT * From admin join personne on admin.idpers = personne.id where personne.mail = ?";
                    PreparedStatement statement2 = connection.prepareStatement(query2);
                    statement.setString(1, mail);
                    
                    int rowsSelected1 = statement.executeUpdate();
                    if (rowsSelected1 > 0) {
                    	MainWindow.estAdmin=true; 
                    }
                    else { 
                    	MainWindow.estAdmin=false; 
                    }
                    
                } else {
                    System.out.println("Échec de l'enregistrement pour l'utilisateur avec l'email : " + mail);
                }
                
                connection.close();
            } catch (SQLException ex) {
                System.out.println("ERREUR lors de l'enregistrement dans la base de données : " + ex.getMessage());
            }
            MainWindow.utilisateurConnecte = true;
            MainWindow mw = new MainWindow();
            mw.setVisible(true);
        }
    }
}
