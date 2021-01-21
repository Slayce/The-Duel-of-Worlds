package player;

import java.util.ArrayList;

import univers.Attaque;
import univers.Carte;
import univers.Heros;
import univers.Personnage;

/** 
 * @author Nathan
 */
public class IAExperte extends IA {

	/** Constructeur de la classe IAExperte */
	public IAExperte(Heros herosc) {
		super(herosc);
	}

	//Renvoie le nombre de carte de cout "coutc"
	private int NbCarteAvecMana(int coutc) {

		int res = 0;
		int NbCarteEnMain = this.getMain().size();

		for (int i = 0 ; i < NbCarteEnMain ; i++) {
			if (this.getMain().get(i).getCout() == coutc) {
				res++;
			}
		}
		return res;
	}

	//Renvoie la première carte de cout "coutc" dans la main (première en partant de la gauche)
	private Carte CarteAvecMana(int coutc) {

		int i = 0;
		while (this.getMain().get(i).getCout() != coutc) {
			i++;
		}
		return this.getMain().get(i);
	}

	@Override
	public Carte jouerCarte() {  // Joue le plus de cartes possibles sur le terrain de manière efficace au niveau du mana

		int mana = this.getMana();
		Carte CarteAJouer = null;

		switch (mana) {
		case 4: 
			if (NbCarteAvecMana(1) >= 4) {
				CarteAJouer = CarteAvecMana(1);
			} else if (NbCarteAvecMana(2) >= 1 && NbCarteAvecMana(1) >= 2) {
				CarteAJouer = CarteAvecMana(2);
			} else if (NbCarteAvecMana(2) >= 2){
				CarteAJouer = CarteAvecMana(2);
			} else if (NbCarteAvecMana(4) == 1) {
				CarteAJouer = CarteAvecMana(4);
			} else if (NbCarteAvecMana(2) == 1){
				CarteAJouer = CarteAvecMana(2);
			} else {
				CarteAJouer = CarteAvecMana(1);
			}
			break;
		case 3:
			CarteAJouer = CarteAvecMana(1);
			break;
		case 2:
			if (NbCarteAvecMana(1) >= 2) {
				CarteAJouer = CarteAvecMana(1);
			} else if (NbCarteAvecMana(2) >= 1) {
				CarteAJouer = CarteAvecMana(2);
			} else {
				CarteAJouer = CarteAvecMana(1);
			}
			break;
		case 1:
			CarteAJouer = CarteAvecMana(1);
			break;
		}

		this.setMana(mana - CarteAJouer.getCout());
		retirer(CarteAJouer);

		return CarteAJouer;
	}

	@Override
	public ArrayList<Attaque> choixAttaques(Heros herosAdverse, ArrayList<Carte> plateau, ArrayList<Carte> plateauAdversaire) {

		int NbCartePlateau = plateau.size();
		int NbCartePlateauAdverse = plateauAdversaire.size();
		ArrayList<Attaque> resultat = new ArrayList<Attaque>();
		Attaque attaque;

		int j = 0; //Indice à comparer au nombre de carte sur le plateau adverse

		for (int i = 0 ; i < NbCartePlateau ; i++) { // On parcourt toutes les cartes de notre plateau
			Personnage CarteAdverseCourante = (Personnage) plateauAdversaire.get(j);
			int PvAdverse = CarteAdverseCourante.getPV();

			int degats = plateau.get(i).getDegats();

			// Stratégie d'attaque (Attaque les cartes adverses jusqu'à leur mort avec nos cartes de gauche à droite)
			if (j < NbCartePlateauAdverse) {  // Si il reste des cartes à attaquer sur le plateau adverse, on les attaque
				attaque = new Attaque(degats,plateau.get(i), (Personnage) plateauAdversaire.get(j));
				PvAdverse = PvAdverse - degats;  // Mise à jour des Pv adverse
				
				// Mise à jour des variables représentant l'état de notre phase d'attaque (j,i,tourFini)
				if (PvAdverse <= 0) {  // Si la carte adverse est morte
					j++;   // Puis on passe à la carte suivante dans le plateau de l'adversaire
				}
			} else {  // Sinon, on attaque le héros adverse
				attaque = new Attaque(degats,plateau.get(i), (Personnage) herosAdverse);
			}

			resultat.add(attaque);  // Ajout de l'attaque au résultat
		}

		return resultat;

	}

}
