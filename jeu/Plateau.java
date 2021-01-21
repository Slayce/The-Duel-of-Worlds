package jeu;
import univers.Carte;
import java.util.ArrayList;
import java.util.Random;


/** Plateau de jeu
 * @author Remi
 */
public class Plateau {

	/** Les deux joueurs*/
	public enum Joueurs {
			JOUEUR1, 
			JOUEUR2
	}

	/** Nombre de cartes max sur un plateau*/
	public static final int NB_MAX = 6;

	/** Le deck du joueur 1*/
	private final ArrayList<Carte> deck1;

	/** Le deck du joueur 2*/
	private final ArrayList<Carte> deck2;

	/** Plateau du joueur 1*/
	private ArrayList<Carte> table1;

	/** Plateau du joueur 2*/
	private ArrayList<Carte> table2;

	/** Pioche du joueur 1*/
	private ArrayList<Carte> pioche1;

	/** Pioche du joueur 2*/
	private ArrayList<Carte> pioche2;

	/** Constructeur Plateau*/
	public Plateau (ArrayList<Carte> deck1, ArrayList<Carte> deck2) {
		this.deck1 = deck1;
		this.deck2 = deck2;
		
		
		pioche2 = new ArrayList<Carte>();
		for (int i = 1; i <= MenuModel.NB_DECKS_PAR_PIOCHE; i++) {
			pioche2.addAll(new ArrayList<Carte>(deck2));
		}

		table1 = new ArrayList<Carte>();
		table2 = new ArrayList<Carte>();
		
		pioche1 = new ArrayList<Carte>();
		pioche2 = new ArrayList<Carte>();

		newPioche(Joueurs.JOUEUR1);
		newPioche(Joueurs.JOUEUR2);
	}
	
	/** Créer la pioche si elle est vide */
	private void newPioche(Joueurs j) {
		ArrayList<Carte> pioche, deck;
		
		switch(j) {
		case JOUEUR1:
			pioche = pioche1;
			deck = deck1;
			break;
		default:
			pioche = pioche2;
			deck = deck2;
			break;
		}

		for (int i = 1; i <= MenuModel.NB_DECKS_PAR_PIOCHE; i++) {
			pioche.addAll(new ArrayList<Carte>(deck));
		}
	}

	/** Obtenir les pioches*/
	public ArrayList<Carte> getPioche(Joueurs j) {
		if (j == Joueurs.JOUEUR1) {
			return pioche1;
		} else {
			return pioche2;
		}
	}

	/** Obtenir les cartes sur le plateau d'un joueur*/
	public ArrayList<Carte> getPlateau(Joueurs j) {
		if (j == Joueurs.JOUEUR1) {
			return table1;
		} else {
			return table2;
		}
	}

	/** Piocher une carte*/
	public Carte piocher(Joueurs j) {
		if (j == Joueurs.JOUEUR1) {
			if (pioche1.size() == 0)
				newPioche(Joueurs.JOUEUR1);

			Random rnd = new Random();
			int n = rnd.nextInt(pioche1.size());
			Carte cartePiochee = pioche1.remove(n);
			
			return cartePiochee;
			
		} else {
			if (pioche2.size() == 0)
				newPioche(Joueurs.JOUEUR1);
			
			Random rnd = new Random();
			int n = rnd.nextInt(pioche2.size());
			Carte cartePiochee = pioche2.remove(n);
			
			return cartePiochee;
		}
	}

	/** Poser une carte sur le plateau*/
	public void poser(Carte carte, Joueurs j) {
		if (j == Joueurs.JOUEUR1) {
			table1.add(carte);
		} else {
			table2.add(carte);
		}
	}

	/** Obtenir le nombre de cartes jouables*/
	public int nbCartesJouables(Joueurs j) {
		if (j == Joueurs.JOUEUR1) {
			return NB_MAX - table1.size();
		} else {
			return NB_MAX - table2.size();
		}
	}

	/** Retirer une carte du plateau (lorsque carte morte)*/
	public void retirer(Carte carte) {
		if (!table1.remove(carte)) {
			table2.remove(carte);
		}
	}

}
