package packet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Affichage extends JDialog implements ActionListener {
	
	private MainWindow mainwindow;
	private Admin admin;
    private Container contenu;
    private JLabel nomLabel = new JLabel("Nom du jeu :");
    private JLabel descriptionLabel = new JLabel("Description :");
    private JLabel disponibiliteLabel = new JLabel("Disponibilité :");
    private JLabel conditionLabel = new JLabel("Condition :");
    private JLabel joueursLabel = new JLabel("Nombre de joueurs :");
    private JLabel ageLabel = new JLabel("Âge minimum :");
    private JLabel dureeLabel = new JLabel("Durée de jeu :");
    
    private JLabel nomValueLabel = new JLabel();
    private JLabel descriptionValueLabel = new JLabel();
    private JLabel disponibiliteValueLabel = new JLabel();
    private JLabel conditionValueLabel = new JLabel();
    private JLabel joueursValueLabel = new JLabel();
    private JLabel ageValueLabel = new JLabel();
    private JLabel genreValueLabel = new JLabel();
    
    private JButton boutonEmprunt = new JButton("Emprunter");
    
    private static final String URL = "jdbc:mysql://localhost:3306/ludojava";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private int Idjeu;

    public Affichage(MainWindow mw, int Idjeu) {
    	this.mainwindow = mw;
        this.Idjeu = Idjeu;
        this.setTitle("Détails du jeu");
        this.setBounds(800, 250, 400, 400);
        this.setResizable(false);
        
        contenu = getContentPane();
        contenu.setLayout(new GridLayout(8, 2, 10, 10));
        ((JComponent) contenu).setBorder(new EmptyBorder(10, 10, 10, 10));
        
        contenu.add(nomLabel);
        contenu.add(nomValueLabel);
        contenu.add(descriptionLabel);
        contenu.add(descriptionValueLabel);
        contenu.add(disponibiliteLabel);
        contenu.add(disponibiliteValueLabel);
        contenu.add(conditionLabel);
        contenu.add(conditionValueLabel);
        contenu.add(joueursLabel);
        contenu.add(joueursValueLabel);
        contenu.add(ageLabel);
        contenu.add(ageValueLabel);
        contenu.add(dureeLabel);
        contenu.add(genreValueLabel);

        boutonEmprunt.addActionListener(this);
        contenu.add(boutonEmprunt);
        contenu.add(new JLabel());

        informationsJeu(Idjeu);
        
        this.setVisible(true);
    }
    
    public Affichage(Admin a, int Idjeu) {
    	this.admin = a;
        this.Idjeu = Idjeu;
        this.setTitle("Détails du jeu");
        this.setBounds(800, 250, 400, 400);
        this.setResizable(false);
        
        contenu = getContentPane();
        contenu.setLayout(new GridLayout(8, 2, 10, 10));
        ((JComponent) contenu).setBorder(new EmptyBorder(10, 10, 10, 10));
        
        contenu.add(nomLabel);
        contenu.add(nomValueLabel);
        contenu.add(descriptionLabel);
        contenu.add(descriptionValueLabel);
        contenu.add(disponibiliteLabel);
        contenu.add(disponibiliteValueLabel);
        contenu.add(conditionLabel);
        contenu.add(conditionValueLabel);
        contenu.add(joueursLabel);
        contenu.add(joueursValueLabel);
        contenu.add(ageLabel);
        contenu.add(ageValueLabel);
        contenu.add(dureeLabel);
        contenu.add(genreValueLabel);

        boutonEmprunt.addActionListener(this);
        contenu.add(boutonEmprunt);
        contenu.add(new JLabel());

        modificationsJeu(Idjeu);
        
        this.setVisible(true);
    }
    
    private void informationsJeu(int Idjeu) {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            String query = "SELECT * FROM jeu WHERE idJeu = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, Idjeu);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                	nomValueLabel.setText(resultSet.getString("nomJeu"));
                    descriptionValueLabel.setText(resultSet.getString("descJeu"));
                    if (resultSet.getInt("dispoJeu") == 0) {
                    	disponibiliteValueLabel.setText("Non");
                    }
                    else {
                    	disponibiliteValueLabel.setText("Oui");
                    }
                    String query2 = "SELECT Etat FROM etatjeu WHERE idEtat = ?";
                    try (PreparedStatement statement2 = connection.prepareStatement(query2)) {
                        statement2.setInt(1, resultSet.getInt("conditionJeu"));
                        ResultSet resultSet2 = statement2.executeQuery();
                        if (resultSet2.next()) {
                            conditionValueLabel.setText(resultSet2.getString("Etat"));
                        }
                    joueursValueLabel.setText(resultSet.getString("nbJoueurs"));
                    ageValueLabel.setText(resultSet.getString("ageMin"));
                    genreValueLabel.setText(resultSet.getString("duréeJeu"));
                    }
                }
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des détails du jeu : " + ex.getMessage());
        }
    }
    
    private void modificationsJeu(int Idjeu) {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            String query = "SELECT * FROM jeu WHERE idJeu = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, Idjeu);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                	nomValueLabel.setText(resultSet.getString("nomJeu"));
                    descriptionValueLabel.setText(resultSet.getString("descJeu"));
                    if (resultSet.getInt("dispoJeu") == 0) {
                    	disponibiliteValueLabel.setText("Non");
                    }
                    else {
                    	disponibiliteValueLabel.setText("Oui");
                    }
                    String query2 = "SELECT Etat FROM etatjeu WHERE idEtat = ?";
                    try (PreparedStatement statement2 = connection.prepareStatement(query2)) {
                        statement2.setInt(1, resultSet.getInt("conditionJeu"));
                        ResultSet resultSet2 = statement2.executeQuery();
                        if (resultSet2.next()) {
                            conditionValueLabel.setText(resultSet2.getString("Etat"));
                        }
                    joueursValueLabel.setText(resultSet.getString("nbJoueurs"));
                    ageValueLabel.setText(resultSet.getString("ageMin"));
                    genreValueLabel.setText(resultSet.getString("duréeJeu"));
                    }
                }
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des détails du jeu : " + ex.getMessage());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonEmprunt) {
            // Ajout du jeu dans la table estemprunte
            try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
                // Vérifier la disponibilité du jeu
                String checkQuery = "SELECT dispojeu FROM jeu WHERE idJeu = ?";
                try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                    checkStatement.setInt(1, Idjeu);
                    try (ResultSet resultSet = checkStatement.executeQuery()) {
                        if (resultSet.next() && resultSet.getInt("dispojeu") > 0) {
                            String query = "INSERT INTO estemprunte (PretJeu, PretUser) VALUES (?, ?)";
                            try (PreparedStatement statement = connection.prepareStatement(query)) {
                                statement.setInt(1, Idjeu);
                                statement.setInt(2, MainWindow.getIdUtilisateurConnecte());
                                statement.executeUpdate();
                                JOptionPane.showMessageDialog(this, "Le jeu a été ajouté à la liste d'emprunt avec succès.");
                                
                                // Mettre à jour la disponibilité du jeu
                                String updateQuery = "UPDATE jeu SET dispojeu = dispojeu - 1 WHERE idJeu = ?";
                                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                                    updateStatement.setInt(1, Idjeu);
                                    updateStatement.executeUpdate();
                                    this.dispose();
                                    mainwindow.dispose();
                                    MainWindow mw = new MainWindow();
                                    mw.setVisible(true);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Erreur : le jeu n'est pas disponible.");
                        }
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du jeu à la liste d'emprunt : " + ex.getMessage());
            }
        }
    }
}