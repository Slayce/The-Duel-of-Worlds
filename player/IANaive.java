package player;
import java.util.ArrayList;
import univers.*;

/** 
 * @author Nathan
 */
public class IANaive extends IA {

	/** Constructeur de la classe IANaive */
	public IANaive(Heros herosc) {
		super(herosc);
	}

	/**
	 * Donne la carte à jouer en la retirant de la main et en enlevant le mana
	 */
	@Override
	public Carte jouerCarte() {
		
		int mana = this.getMana();
		int NbCarteEnMain = this.getMain().size();
		Carte CarteAJouer = null;
		int i = 0;
		
		while (CarteAJouer == null) {
			if (this.getMain().get(i).getCout() <= mana && i < NbCarteEnMain) { // Joue la première carte jouable à gauche
				CarteAJouer = this.getMain().get(i);
				this.setMana(mana - this.getMain().get(i).getCout());
				retirer(this.getMain().get(i));
			}
			i++;
		}
		return CarteAJouer;
	}
	
	/**
	 *
	 */
	@Override
	public ArrayList<Attaque> choixAttaques(Heros herosAdverse, ArrayList<Carte> plateau, ArrayList<Carte> plateauAdversaire) {
		int NbCartePlateau = plateau.size();
		int NbCartePlateauAdverse = plateauAdversaire.size();
		ArrayList<Attaque> resultat = new ArrayList<Attaque>();
		Attaque attaque;
		int j = 0; // Indice à comparer au nombre de carte sur le plateau adverse
		
		for (int i = 0 ; i < NbCartePlateau ; i++) { // On parcourt toutes les cartes de notre plateau			
			int degats = plateau.get(i).getDegats();
			
			// Stratégie d'attaque (Chaque carte attaque la carte en face d'elle, sinon attaque le héros adverse)
			if (j < NbCartePlateauAdverse) {  // Si il reste des cartes à attaquer sur le plateau adverse, on les attaque
				attaque = new Attaque(degats,plateau.get(i), (Personnage) plateauAdversaire.get(i));
			} else {  // Sinon, on attaque le héros adverse
				attaque = new Attaque(degats,plateau.get(i), (Personnage) herosAdverse);
			}
			
			j++;
			resultat.add(attaque);  // Ajout de l'attaque au résultat
		}
		
		return resultat;
	}

}
