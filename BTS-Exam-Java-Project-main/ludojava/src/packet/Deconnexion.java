package packet;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.*;

public class Deconnexion extends JFrame implements ActionListener {
    private JButton loginButton = new JButton("Connexion");
    private JButton logoutButton = new JButton("Déconnexion");
    private boolean utilisateurConnecte = false; 

    public Deconnexion() {
        super();

 
        getContentPane().setLayout(new FlowLayout()); 
        getContentPane().add(loginButton);
        getContentPane().add(logoutButton); 


        loginButton.addActionListener(this);
        logoutButton.addActionListener(this);

        hideLogoutButton(); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); 
        setLocationRelativeTo(null); 
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
          
            utilisateurConnecte = true; 
            showLogoutButton(); 
    
        } else if (e.getSource() == logoutButton) {
   
            utilisateurConnecte = false; 
            hideLogoutButton(); 
        }
    }

    private void showLogoutButton() {
        logoutButton.setVisible(true); 
    }

    private void hideLogoutButton() {
        logoutButton.setVisible(false); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Deconnexion mainWindow = new Deconnexion();
                mainWindow.setVisible(true);
            }
        });
    }
    
}
