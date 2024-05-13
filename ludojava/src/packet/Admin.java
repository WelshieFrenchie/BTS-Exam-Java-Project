package packet;

	import javax.swing.*;
	import java.awt.event.*;
	import java.sql.*;

	public class Admin extends JFrame {
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
	        if (!estAdministrateur(idUtilisateurConnecte)) {
	            JOptionPane.showMessageDialog(this, "Vous n'avez pas les droits d'accès à cette interface.");
	            dispose(); // Ferme la fenêtre d'administration si l'utilisateur n'est pas administrateur
	            return;
	        }

	        // Configuration de la fenêtre d'administration
	        setTitle("Interface d'administration");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran

	        // Création de composants pour l'interface d'administration
	        JPanel panel = new JPanel();
	        JButton bouton1 = new JButton("Fonctionnalité 1");
	        JButton bouton2 = new JButton("Fonctionnalité 2");

	        // Ajout d'action listeners aux boutons si besoin
	        bouton1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Action à effectuer lorsqu'on clique sur le bouton 1
	                JOptionPane.showMessageDialog(Admin.this, "Fonctionnalité 1 exécutée.");
	            }
	        });

	        bouton2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Action à effectuer lorsqu'on clique sur le bouton 2
	                JOptionPane.showMessageDialog(Admin.this, "Fonctionnalité 2 exécutée.");
	            }
	        });

	        // Ajout des composants au panel
	        panel.add(bouton1);
	        panel.add(bouton2);

	        // Ajout du panel à la fenêtre
	        add(panel);

	        // Rendre la fenêtre visible
	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        // Supposons que tu as une variable qui contient l'ID de l'utilisateur connecté
	        int idUtilisateurConnecte = 1; // Par exemple, l'ID de l'utilisateur actuel est 1

	        // Création de la fenêtre d'administration
	        new Admin(idUtilisateurConnecte);
	    }
	}


