package packet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
	
	

	public class Admin extends JFrame {
		
		private JScrollPane scrollJeux = new JScrollPane();
	    private JPanel boxJeux = new JPanel();
	    private Container contenu;
	    private int idUtilisateur;
		private int droitAjout;
		private int droitModif;
		private int droitSupp;

		public void setIdUtilisateur(int idUtilisateur) {
			this.idUtilisateur = idUtilisateur;
		}

		// Méthode pour vérifier si l'utilisateur est administrateur
	    private boolean estAdministrateur(int idUtilisateur) {
	        // Connexion à la base de données
	        String url = "jdbc:mysql://localhost:3306/ludojava";
	        String login = "root";
	        String password = "";
	        String query = "SELECT * FROM admin WHERE idPers = ?";

	        try (Connection connection = DriverManager.getConnection(url, login, password);
	            PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, idUtilisateur);
	            ResultSet resultSet = statement.executeQuery();
	            return resultSet.next(); // Renvoie true si l'utilisateur est trouvé dans la table admin, sinon false
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public Admin(int idUtilisateurConnecte) {
	        // Vérifier si l'utilisateur est administrateur
	    	this.idUtilisateur = idUtilisateurConnecte;
	        if (!estAdministrateur(this.idUtilisateur)) {
	            JOptionPane.showMessageDialog(this, "Vous n'avez pas les droits d'accès à cette interface.");
	            dispose(); // Ferme la fenêtre d'administration si l'utilisateur n'est pas administrateur
	            return;
	        }
	        // Configuration de la fenêtre d'administration
	        setTitle("Interface d'administration");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
	        
	        this.contenu = this.getContentPane();
	        JPanel panel = new JPanel();
	        JButton bouton = new JButton("Ajouter jeu");

	        // Ajout d'action listeners aux boutons si besoin
	        bouton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Action à effectuer lorsqu'on clique sur le bouton 1
	                JOptionPane.showMessageDialog(Admin.this, "Fonctionnalité Ajouter Jeu exécutée.");
	            }
	        });
	        panel.add(bouton);
	        this.contenu.add(boxJeux);
	        this.contenu.add(scrollJeux);
	        this.scrollJeux.setBounds(20, 40, 360, 200);
	        this.boxJeux.setLayout(new GridLayout(0, 1));
	        this.scrollJeux.setViewportView(boxJeux);
	        add(panel);
	        listeJeux();
	        // Rendre la fenêtre visible
	        setVisible(true);
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
	                jeu.setPreferredSize(new Dimension(300, 150));

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
	                infoButton.setActionCommand(String.valueOf(resultSet.getInt("idJeu")));
	                infoButton.addActionListener(new InfoButtonListener());
	                GridBagConstraints buttonConstraints = new GridBagConstraints();
	                buttonConstraints.gridx = 0;
	                buttonConstraints.gridy = 4;
	                buttonConstraints.anchor = GridBagConstraints.WEST;
	                buttonConstraints.insets = new Insets(5, 5, 5, 5);
	                jeu.add(infoButton, buttonConstraints);

	                JButton supprimerButton = new JButton("Supprimer");
	                supprimerButton.setActionCommand(String.valueOf(resultSet.getInt("idJeu")));
	                supprimerButton.addActionListener(new SupprimerButtonListener());
	                buttonConstraints.gridx = 1;
	                buttonConstraints.gridy = 4;
	                buttonConstraints.anchor = GridBagConstraints.EAST;
	                jeu.add(supprimerButton, buttonConstraints);

	                boxJeux.add(jeu);
	                boxJeux.revalidate();
	                boxJeux.repaint();
	            }
	        } catch (SQLException ex) {
	            System.out.println("ERREUR lors de la connexion à la base de données : " + ex.getMessage());
	        }
	    }

	    private class InfoButtonListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            String actionCommand = e.getActionCommand();
	            if (actionCommand != null) {
	                try {
	                    int idJeu = Integer.parseInt(actionCommand.substring(5));
	                    Affichage affichage = new Affichage(Admin.this, idJeu);
	                    affichage.setVisible(true);
	                } catch (Exception ex) {
	                    System.out.println("Erreur: " + ex);
	                }
	            }
	        }
	    }

	    private class SupprimerButtonListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            String actionCommand = e.getActionCommand();
	            if (actionCommand != null) {
	                    int idJeu = Integer.parseInt(actionCommand);
	                    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ludojava", "root", "")) {
	        	            String query = "DELETE FROM jeu WHERE idJeu = ?";
	        	            PreparedStatement statement = connection.prepareStatement(query);
	        	            statement.setInt(1, idJeu);
	        	            statement.execute();
	                        Admin.this.dispose();
	                        Admin a = new Admin(idUtilisateur);
	                        a.setVisible(true);
	                    
	                } catch (Exception ex) {
	                    System.out.println("Erreur: " + ex);
	                }
	            }
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

	    public static void main(String[] args) {

	        Admin a = new Admin(1);
	    }
	}


