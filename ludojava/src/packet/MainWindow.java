package packet;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

    private JScrollPane boxLeft = new JScrollPane();
    private JPanel boxLeftJeu = new JPanel();
    private JLabel boxLeftLabel = new JLabel("Liste de jeux");
    private JPanel boxLeftName = new JPanel();
    private JScrollPane boxMiddle = new JScrollPane();
    private JPanel boxRight = new JPanel();
    private JPanel connectionBox = new JPanel();
    private JButton loginButton = new JButton("Connexion");
    private JButton signupButton = new JButton("Inscription");
    private JButton logoutButton = new JButton("Déconnexion");
    private JButton boutonAdmin = new JButton("Admin");
    private Container contenu;
    public static int idUtilisateurConnecte;
    public static boolean utilisateurConnecte;
    public static boolean estAdmin;
    
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

        this.boxLeft.setBounds(20, 50, 300, 495);
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
        rightBoxConst.insets = new Insets(5, 0, 5, 0);
        rightBoxGrid.setConstraints(loginButton, rightBoxConst);
        rightBoxConst.gridy = 1;
        rightBoxGrid.setConstraints(signupButton, rightBoxConst);
        rightBoxConst.gridy = 2;
        rightBoxGrid.setConstraints(logoutButton, rightBoxConst);
        rightBoxConst.gridy = 3;
        rightBoxGrid.setConstraints(boutonAdmin, rightBoxConst);
        
        this.boxLeftJeu.setLayout(new GridLayout(0, 1));
        this.boxLeft.setViewportView(boxLeftJeu);
        listeJeux();
        
        this.loginButton.addActionListener(this);
        this.connectionBox.add(loginButton);
        this.signupButton.addActionListener(this);
        this.connectionBox.add(signupButton);
        this.logoutButton.addActionListener(this); 
        this.connectionBox.add(logoutButton);
        this.boutonAdmin.addActionListener(this); 
        this.connectionBox.add(boutonAdmin);
        
        if (utilisateurConnecte == true) {
            this.loginButton.setVisible(false);
            this.signupButton.setVisible(false);
            this.logoutButton.setVisible(true);
        } else {
            this.loginButton.setVisible(true);
            this.signupButton.setVisible(true);
            this.logoutButton.setVisible(false);
        }
        if (estAdmin == true) {
            this.boutonAdmin.setVisible(true);
        } else {
            this.boutonAdmin.setVisible(false);
        }
    }
    

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) { 
            LoginWindow lw = new LoginWindow();
            lw.setVisible(true);
            this.setVisible(false);
            this.dispose();
        }
        if (e.getSource() == signupButton) {
            RegisterWindow lw = new RegisterWindow();
            lw.setVisible(true);
            this.setVisible(false);
            this.dispose();
        }
        if (e.getSource() == logoutButton) { 
            utilisateurConnecte = false;
            estAdmin = false;
            this.setVisible(false);
            this.dispose();
            MainWindow mw = new MainWindow();
            mw.setVisible(true);
        }
        
        if (e.getSource() == boutonAdmin) {
            admin admin = new admin(idUtilisateurConnecte);
            admin.setVisible(true);
        }
        
        if (e.getActionCommand() != null) {
            try {
                int idJeu = Integer.parseInt(e.getActionCommand());
                Affichage affichage = new Affichage(idJeu);
                affichage.setVisible(true);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid idJeu format: " + e.getActionCommand());
            }
        }
    }
    
    private void listeJeux() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ludojava", "root", "")) {
            String query = "SELECT * FROM jeu";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JPanel jeu = new JPanel();
                jeu.setLayout(new GridBagLayout());
                jeu.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                jeu.setPreferredSize(new Dimension(300, 120));

                JLabel nomJeu = new JLabel(resultSet.getString("nomJeu"));
                nomJeu.setFont(new Font("Arial", Font.BOLD, 16));
                GridBagConstraints titleConstraints = new GridBagConstraints();
                titleConstraints.gridx = 0;
                titleConstraints.gridy = 0;
                titleConstraints.gridwidth = 2;
                titleConstraints.anchor = GridBagConstraints.CENTER;
                titleConstraints.insets = new Insets(5, 5, 5, 5);
                jeu.add(nomJeu, titleConstraints);
                
                Font desc = new Font("SansSerif", Font.PLAIN, 12);
                JLabel etat = new JLabel("État: " + etatJeu(resultSet.getInt("idJeu")));
                etat.setFont(desc);
                JLabel nbJoueurs = new JLabel("Nombre de joueurs: " + resultSet.getString("nbJoueurs"));
                nbJoueurs.setFont(desc);
                JLabel ageMin = new JLabel("Age Minimum: " + resultSet.getString("ageMin"));
                ageMin.setFont(desc);

                GridBagConstraints labelConstraints = new GridBagConstraints();
                labelConstraints.gridx = 0;
                labelConstraints.gridy = 1;
                labelConstraints.anchor = GridBagConstraints.WEST;
                labelConstraints.insets = new Insets(5, 5, 5, 5);
                jeu.add(etat, labelConstraints);

                labelConstraints.gridy = 2;
                jeu.add(nbJoueurs, labelConstraints);

                labelConstraints.gridy = 3;
                jeu.add(ageMin, labelConstraints);

                JButton infoButton = new JButton("Consulter");
                infoButton.setActionCommand(String.valueOf(resultSet.getInt("idJeu"))); //Récupère la valeur de idJeu comme valeur à retourner pour l'affichage
                infoButton.addActionListener(this);
                GridBagConstraints buttonConstraints = new GridBagConstraints();
                buttonConstraints.gridx = 1;
                buttonConstraints.gridy = 3;
                buttonConstraints.anchor = GridBagConstraints.EAST;
                buttonConstraints.insets = new Insets(5, 5, 5, 5);
                jeu.add(infoButton, buttonConstraints);

                boxLeftJeu.add(jeu);
                boxLeftJeu.revalidate();
                boxLeftJeu.repaint();
            }
        } catch (SQLException ex) {
            System.out.println("ERREUR lors de la connexion à la base de données : " + ex.getMessage());
        }
    }
    
    private String etatJeu(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ludojava", "root", "")) {
            String queryEtat = "SELECT Etat FROM etatjeu e JOIN jeu j ON j.conditionJeu = e.idEtat WHERE j.idJeu = ?";
            PreparedStatement statementEtat = connection.prepareStatement(queryEtat);
            statementEtat.setInt(1, id);
            ResultSet resultSetEtat = statementEtat.executeQuery();
            if (resultSetEtat.next()) {
                return resultSetEtat.getString("Etat");
            }
        } catch (SQLException ex) {
            System.out.println("ERREUR lors de la connexion à la base de données : " + ex.getMessage());
        }
        return "Etat non-disponible";
    }
}
