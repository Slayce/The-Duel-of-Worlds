package jeu;

import java.util.ArrayList;

import ihm.PartieView;
import player.Player;
import univers.Carte;
import univers.Personnage;
import univers.Serviteur;
import univers.Heros;
import univers.Attaque;
import jeu.Plateau.Joueurs;

/** Classe qui gere la partie
 * @author Remi
 */
public class PartieModel {
	
	private Player j1, j2;
	private Joueurs vainqueur;
	private Plateau plateau;
	private PartieView fenetre;
	
	
	public PartieModel(PartieView fenetre, Player j1, Player j2, Deck deck1, Deck deck2) {
		this.fenetre = fenetre;
		this.j1 = j1;
		this.j2 = j2;
		this.vainqueur = null;
		plateau = new Plateau(deck1.cartes, deck2.cartes);
		
		for (int i = 1; i <= 5; i++) {		// chaque joueur a 5 cartes dans sa main
			j1.ajouterCarte(plateau.piocher(Joueurs.JOUEUR1));
			j2.ajouterCarte(plateau.piocher(Joueurs.JOUEUR2));
		}
	}
	
	
	/** Methode principale qui lance la partie
	 */
	public void lancer() {
		maj();
		
		do {
			faireJouer(Joueurs.JOUEUR1);
			
			maj();
			
			if(this.vainqueur == null)
				faireJouer(Joueurs.JOUEUR2);
			
		} while(this.vainqueur == null);

		if (vainqueur == Joueurs.JOUEUR1)
			fenetre.finPartie("Victoire!", "Vous avez gagné!");
		else
			fenetre.finPartie("Défaite", "Vous avez perdu...");
	}
	
	
	private void faireJouer(Joueurs joueurNum) {
		maj();
		
		Player j = getJoueur(joueurNum);
		j.setMana(4);
		Joueurs adversaireNum = autreJoueur(joueurNum);
		
		while(!j.estPoseFinie() && plateau.nbCartesJouables(joueurNum) > 0) {	// tant que le tour n'est pas fini et qu'il reste de la place sur le plateau
			Carte carteJouee = j.jouerCarte();
			
			if (carteJouee != null) {
				plateau.poser(carteJouee, joueurNum);

				maj();			
			}
		}

		ArrayList<Carte> plateauJoueur = plateau.getPlateau(joueurNum);
		ArrayList<Carte> plateauAdversaire = plateau.getPlateau(adversaireNum);

		ArrayList<Attaque> attaques = j.choixAttaques(j.getHeros(), plateauJoueur, plateauAdversaire);		// le joueur choisit quelles attaques il veut faire

		for(Attaque attaque : attaques)		// on effectue les attaques
			infligerDegats(attaque.getCible(), attaque.getSource().getDegats());
		
		while (getJoueur(joueurNum).getMain().size() < 5)		// on ditribue au joueur le nombre de cartes qu'il a posées
			j.ajouterCarte(plateau.piocher(joueurNum));

		maj();
	}
	
	
	private void maj()
	{
		fenetre.mettreAJour(j1.getHeros().getPV(), j2.getHeros().getPV(), j1.getMain(), plateau.getPlateau(Joueurs.JOUEUR1), plateau.getPlateau(Joueurs.JOUEUR2));
	}
	
	
	private void infligerDegats(Personnage perso, int degats) {
		perso.subirDegats(degats);
		
		if(perso.getPV() <= 0) {
			if(perso instanceof Serviteur) {
				Carte mort = (Carte)perso;
				plateau.retirer(mort);
			}
			else if(perso instanceof Heros) {
				if(perso.equals(j1.getHeros()))
					vainqueur = Joueurs.JOUEUR2;
				else
					vainqueur = Joueurs.JOUEUR1;
			}
		}
	}
	
	
	/** Donne l'autre joueur que celui fourni en paramètre
	 */
	private Joueurs autreJoueur(Joueurs j){
		switch(j) {
		case JOUEUR1 :
			return Joueurs.JOUEUR2;
		default :
			return Joueurs.JOUEUR1;
		}
	}
	
	
	/** Retourne l'instance de Player correspondant au numero de joueur en paramètre */
	private Player getJoueur(Joueurs joueurNum) {
		switch(joueurNum) {
		case JOUEUR1 :
			return j1;
		default :
			return j2;
		}
	}
}










