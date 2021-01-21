package jeu;

/** Outils generaux
 * @author Remi
 */
public class Outils {


	/** Met le processus actuel en pause pour ms millisecondes
	 */
	static public void attendre(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e ) {
			System.out.println(e);
		}
	}
}
