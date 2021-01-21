package jeu;
import univers.Carte;
import jeu.MenuModel.Univers;
import java.util.ArrayList;

public class Deck {

	public Univers univers;
	public String nom;
	public ArrayList<Carte> cartes;
	
	public Deck(String nom, ArrayList<Carte> cartes, Univers univers) {
		this.nom = nom;
		this.cartes = cartes;
		this.univers = univers;
	}

}
