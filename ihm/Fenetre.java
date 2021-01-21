package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	private static final long serialVersionUID = 455594351714182100L;

	public Fenetre() {
		this.setTitle("Le Duel des Mondes");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void changerPanel(JPanel nouveau) {
		this.setContentPane(nouveau);       
		
	    this.setVisible(true);
	}
}
