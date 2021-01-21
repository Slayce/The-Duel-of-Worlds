package player;
import java.util.ArrayList;
import univers.*;

/** 
 * @author Nathan
 */
public class JoueurReel extends Player {

	private boolean PoseFinie = false;
	private Carte CarteAPoser = null;
	private Carte CarteAttaquante = null;
	private Personnage CarteAttaquee = null;

	/** Constructeur de la classe JoueurReel */
	public JoueurReel(Heros herosc) {
		super(herosc);
	}

	public void setCarteAPoser(Carte carte) { // A Finir !
		this.CarteAPoser = carte;
	}

	public void setCarteAttaquante(Carte carte) {
		this.CarteAttaquante = carte;
	}

	public void setCarteAttaquee(Personnage perso) {
		this.CarteAttaquee = perso;
	}

	public void setPoseFinie(boolean bool) {
		this.PoseFinie = bool;
	}

	public boolean estPoseFinie() {
		if (PoseFinie) {
			PoseFinie = false;
			return true;
		}
		return false;
	}

	/**
	 *
	 */
	@Override
	public Carte jouerCarte() {
		CarteAPoser = null;
		int mana = this.getMana();
		
		while (CarteAPoser == null && !PoseFinie) {
			jeu.Outils.attendre(10);
		}
		if (CarteAPoser != null && mana >= CarteAPoser.getCout()) {
			retirer(CarteAPoser);
			this.setMana(mana - CarteAPoser.getCout());
		}
		else
			CarteAPoser = null;
		return CarteAPoser;	
	}


	private boolean estDedans(Carte cartec, ArrayList<Carte> plateau) {
		for (int i = 0 ; i < plateau.size() ; i++) {
			if (cartec == plateau.get(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 */
	@Override
	/** si on renvoie les cartes, le tour est fini **/
	public ArrayList<Attaque> choixAttaques(Heros herosAdverse, ArrayList<Carte> plateau, ArrayList<Carte> plateauAdversaire) {

		ArrayList<Attaque> res = new ArrayList<Attaque>();
		Attaque attaque;

		while (!estPoseFinie()) {
			if (CarteAttaquante != null && CarteAttaquee != null) {				
				if (estDedans(CarteAttaquante, plateau) && (CarteAttaquee instanceof Heros || estDedans((Carte) CarteAttaquee, plateauAdversaire))) {
					int degats = CarteAttaquante.getDegats();
					attaque = new Attaque(degats, CarteAttaquante, CarteAttaquee);
					
					res.add(attaque);
				}
				CarteAttaquante = null;
				CarteAttaquee = null;
			}
			jeu.Outils.attendre(10);
		}
		return res;
	}
}
