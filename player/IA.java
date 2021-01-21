package player;

import java.util.ArrayList;

import univers.Attaque;
import univers.Carte;
import univers.Heros;

/** 
 * @author Nathan
 */
public abstract class IA extends Player {
	
	public IA(Heros herosc) {
		super(herosc);
	}
	
	public boolean estPoseFinie() {
		int NbCarteEnMain = this.getMain().size();
		for (int i = 0 ; i < NbCarteEnMain ; i++) {
			if (this.getMain().get(i).getCout() <= this.getMana()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @return
	 */
	public abstract Carte jouerCarte();
	
	/**
	 * @param herosAdverse
	 * @param plateau
	 * @param plateauAdversaire
	 * @return
	 */
	public abstract ArrayList<Attaque> choixAttaques(Heros herosAdverse, ArrayList<Carte> plateau, ArrayList<Carte> plateauAdversaire);
	
}
