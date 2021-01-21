package ihm;

import javax.swing.JOptionPane;

/** 
 * @author Remi
 */
public abstract class AbstractView {

	protected Fenetre fenetre;
	
	public AbstractView(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	public void erreur(String msg) {
		JOptionPane.showMessageDialog(fenetre, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
	    System.exit(1);
	}
	
	public void warning(String msg) {
		JOptionPane.showMessageDialog(fenetre, msg, "Attention", JOptionPane.WARNING_MESSAGE);
	}
	
	public void finPartie(String titre, String msg) {
		JOptionPane.showMessageDialog(fenetre, msg, titre, JOptionPane.INFORMATION_MESSAGE);
	}
}
