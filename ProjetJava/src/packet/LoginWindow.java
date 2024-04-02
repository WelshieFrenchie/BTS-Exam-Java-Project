package packet;

import java.awt.*;
import javax.swing.*;

public class LoginWindow extends JDialog{
	
	private JPanel window = new JPanel();
	private Container contenu;
	
	public LoginWindow() {
		this.setTitle("Login");
		this.setBounds(800, 250, 300, 500);
		this.setResizable(false);
	}
}
