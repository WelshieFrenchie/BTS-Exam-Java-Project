package packet;

import java.sql.*;

public class Program {

	public static void main(String[] args) {
		
		String URL = "jdbc:mysql://localhost:3306/ludojava";
		String LOGIN_BDD = "root";
		String PASSWORD_BDD = "";
		
		try {
			Connection connexion = DriverManager.getConnection(URL, LOGIN_BDD, PASSWORD_BDD);
		}
		catch (SQLException exc) {
			System.out.print("ERREUR : ");
			System.out.println(exc.getMessage());
		}
		
		
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
	}
}
