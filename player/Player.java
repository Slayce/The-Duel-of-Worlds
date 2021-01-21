package player;
import univers.*;
import java.util.ArrayList;

public abstract class Player {
	
	private Heros heros;
	private int Mana;
	private ArrayList<Carte> Main;
	
	public Player(Heros herosc) {
		this.setHeros(herosc);
		this.setMana(4);
		this.setMain(new ArrayList<Carte>(5));
	}
	
	public int getMana() {
		return this.Mana;
	}
	
	public void setMana(int manac) {
		this.Mana = manac;
	}
	
	public Heros getHeros() {
		return this.heros;
	}
	
	public void setHeros(Heros herosc) {
		this.heros = herosc;
	}
	
	public ArrayList<Carte> getMain() {
		return this.Main;
	}
	
	public void setMain(ArrayList<Carte> mainc) {
		this.Main = mainc;
	}
	
	/**
	 * Retirer une carte de la main
	 */
	protected void retirer(Carte carte) {
		this.Main.remove(carte);
	}
	
	/**
	 * Ajouter une nouvelle carte dans la main
	 */
	public void ajouterCarte(Carte carte) {
		this.Main.add(carte);
	}
	
	
	/**
	 * @return
	 */
	public abstract boolean estPoseFinie();
	
	/**
	 * @return
	 */
	public abstract Carte jouerCarte();
	
	/**
	 * @param herosAdverse TODO
	 * @param plateau
	 * @param plateauAdversaire
	 * @return
	 */
	public abstract ArrayList<Attaque> choixAttaques(Heros herosAdverse, ArrayList<Carte> plateau, ArrayList<Carte> plateauAdversaire);
	
}

