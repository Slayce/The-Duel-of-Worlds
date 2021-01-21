package univers;

/** Classe décrivant une attaque (attaquant, cible, degats, ...)
 * @author Remi
 */
public class Attaque {
	private final int degats;
	private final Carte source;
	private final Personnage cible;
	
	public Attaque(int degats, Carte source, Personnage cible) {
		this.degats = degats;
		this.source = source;
		this.cible = cible;
	}
	
	public Personnage getCible() {
		return cible;
	}

	public Carte getSource() {
		return source;
	}

	public int getDegats() {
		return degats;
	}
}
