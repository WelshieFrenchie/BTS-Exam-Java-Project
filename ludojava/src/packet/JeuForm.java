package packet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class JeuForm extends JFrame {

    private JLabel nomLabel = new JLabel("Nom du jeu :");
    private JTextField nomField = new JTextField(30);

    private JLabel descriptionLabel = new JLabel("Description :");
    private JTextField descriptionField = new JTextField(30);

    private JLabel disponibiliteLabel = new JLabel("Disponibilité :");
    private JTextField disponibiliteField = new JTextField(10);

    private JLabel conditionLabel = new JLabel("Condition :");
    private JTextField conditionField = new JTextField(10);

    private JLabel joueursLabel = new JLabel("Nombre de joueurs :");
    private JTextField joueursField = new JTextField(10);

    private JLabel ageLabel = new JLabel("Âge :");
    private JTextField ageField = new JTextField(10);

    private JLabel dureeLabel = new JLabel("Durée de jeu :");
    private JTextField dureeField = new JTextField(20);

    private JButton boutonValider = new JButton("Valider");

    public JeuForm() {
        super();
        setSize(600, 400);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(nomLabel, constraints);

        constraints.gridx = 1;
        nomField.setPreferredSize(new Dimension(250, 30));
        add(nomField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(descriptionLabel, constraints);

        constraints.gridx = 1;
        descriptionField.setPreferredSize(new Dimension(250, 60));
        add(descriptionField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(disponibiliteLabel, constraints);

        constraints.gridx = 1;
        disponibiliteField.setPreferredSize(new Dimension(100, 30));
        add(disponibiliteField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(conditionLabel, constraints);

        constraints.gridx = 1;
        conditionField.setPreferredSize(new Dimension(100, 30));
        add(conditionField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(joueursLabel, constraints);

        constraints.gridx = 1;
        joueursField.setPreferredSize(new Dimension(100, 30));
        add(joueursField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        add(ageLabel, constraints);

        constraints.gridx = 1;
        ageField.setPreferredSize(new Dimension(100, 30));
        add(ageField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        add(dureeLabel, constraints);

        constraints.gridx = 1;
        dureeField.setPreferredSize(new Dimension(200, 30));
        add(dureeField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(boutonValider, constraints);

        boutonValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
                String desc = descriptionField.getText();
                int dispo = Integer.parseInt(disponibiliteField.getText());
                int condition = Integer.parseInt(conditionField.getText());
                String joueurs = joueursField.getText();
                int age = Integer.parseInt(ageField.getText());
                String duree = dureeField.getText();

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ludojava", "root", "")) {
    	            String query = "INSERT INTO jeu (nomJeu, descJeu, dispoJeu, conditionJeu, nbJoueurs, ageMin, duréeJeu) VALUES (?, ?, ?, ?, ?, ?, ?)";
    	            PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, nom);
                    statement.setString(2, desc);
                    statement.setInt(3, dispo);
                    statement.setInt(4, condition);
                    statement.setString(5, joueurs);
                    statement.setInt(6, age);
                    statement.setString(7, duree);
    	            statement.executeUpdate();
                }
                catch (SQLException ex) {
    	            System.out.println("ERREUR lors de la connexion à la base de données : " + ex.getMessage());
    	        } 
                
                JOptionPane.showMessageDialog(JeuForm.this,
                    "Nom: " + nom + "\nDescription: " + desc + "\nDisponibilité: " + dispo +
                    "\nCondition: " + condition + "\nNombre de joueurs: " + joueurs +
                    "\nÂge: " + age + "\nDurée: " + duree,
                    "Form Data", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JeuForm(int id) {
        super();
        setSize(600, 400);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nomLabel, gbc);

        gbc.gridx = 1;
        nomField.setPreferredSize(new Dimension(250, 30));
        add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(descriptionLabel, gbc);

        gbc.gridx = 1;
        descriptionField.setPreferredSize(new Dimension(250, 60));
        add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(disponibiliteLabel, gbc);

        gbc.gridx = 1;
        disponibiliteField.setPreferredSize(new Dimension(100, 30));
        add(disponibiliteField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(conditionLabel, gbc);

        gbc.gridx = 1;
        conditionField.setPreferredSize(new Dimension(100, 30));
        add(conditionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(joueursLabel, gbc);

        gbc.gridx = 1;
        joueursField.setPreferredSize(new Dimension(100, 30));
        add(joueursField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(ageLabel, gbc);

        gbc.gridx = 1;
        ageField.setPreferredSize(new Dimension(100, 30));
        add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(dureeLabel, gbc);

        gbc.gridx = 1;
        dureeField.setPreferredSize(new Dimension(200, 30));
        add(dureeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(boutonValider, gbc);

        boutonValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
                String desc = descriptionField.getText();
                int dispo = Integer.parseInt(disponibiliteField.getText());
                int condition = Integer.parseInt(conditionField.getText());
                String joueurs = joueursField.getText();
                int age = Integer.parseInt(ageField.getText());
                String duree = dureeField.getText();

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ludojava", "root", "")) {
    	            String query = "UPDATE jeu SET nomJeu = ?, descJeu = ?, dispoJeu = ?, conditionJeu = ?, nbJoueurs = ?, ageMin = ?, duréeJeu = ? WHERE idJeu = ?";
    	            PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, nom);
                    statement.setString(2, desc);
                    statement.setInt(3, dispo);
                    statement.setInt(4, condition);
                    statement.setString(5, joueurs);
                    statement.setInt(6, age);
                    statement.setString(7, duree);
                    statement.setInt(8, id);
    	            statement.executeUpdate();
                }
                catch (SQLException ex) {
    	            System.out.println("ERREUR lors de la connexion à la base de données : " + ex.getMessage());
    	        } 
                
                JOptionPane.showMessageDialog(JeuForm.this,
                    "Nom: " + nom + "\nDescription: " + desc + "\nDisponibilité: " + dispo +
                    "\nCondition: " + condition + "\nNombre de joueurs: " + joueurs +
                    "\nÂge: " + age + "\nDurée: " + duree,
                    "Form Data", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
