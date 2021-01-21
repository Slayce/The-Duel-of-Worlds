package jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import ihm.MenuView;
import player.*;
import univers.*;

/** Menu afin de configurer la partie
 * @author Remi
 */
public class MenuModel{
	/** Les differents modes*/
	public enum Mode 		{ IA, REEL, DECK, QUITTER }

	/** Les deux univers*/
	public enum Univers		{ STARWARS, HARRYPOTTER, RETOUR }

	/** Action avec un deck*/
	public enum ActionDeck  { AFFICHER, JOUER, RETOUR }

	/** Action avec une carte*/
	public enum ActionCarte { AJOUTER, SUPPRIMER, RETOUR }

	/** Difficultes de l'IA*/
	public enum Difficulte  { FACILE, INTERMEDIAIRE, EXPERT, RETOUR }

	/** Nombre maximum de deck*/
	public static final int NB_DECK = 6;

	/** Nombre de cartes par deck*/
	public static final int NB_CARTES_PAR_DECK = 20;
	
	/** Nombre de decks par pioches*/
	public static final int NB_DECKS_PAR_PIOCHE = 3;
	
	/** Nombre de hÃ©ros par univers*/
	public static final int NB_HEROS = 2;

	private Player j1, j2;
	private Deck deck1, deck2;
	private ArrayList<Carte> cstarwars, charrypotter;
	private ArrayList<Heros> hstarwars, hharrypotter;
	private boolean estJeuQuitte;
	private MenuView fenetre;
	

	/** Constructeur Menu*/
	public MenuModel(MenuView fenetre) {
		this.fenetre = fenetre;
		estJeuQuitte = false;
		
	}

	/** Savoir si l'utilisateur veut arreter de jouer*/
	public boolean estJeuFini() { return estJeuQuitte; }
	public Player getJoueur1()  { return j1; }
	public Player getJoueur2()  { return j2; }
	public Deck getDeck1()      { return deck1; }
	public Deck getDeck2() 	    { return deck2; }

	/** Lancer le menu*/
	public void lancer() {
		try {
			cstarwars = FilesManager.loadCartes(Univers.STARWARS);
			charrypotter = FilesManager.loadCartes(Univers.HARRYPOTTER);
			hstarwars = FilesManager.loadHeros(Univers.STARWARS);
			hharrypotter = FilesManager.loadHeros(Univers.HARRYPOTTER);
		} catch (Exception e) {
			e.printStackTrace();
			fenetre.erreur(e.getMessage());
		}
		choixMode();
	}

	/** Choisir le mode de jeu*/
	private void choixMode() {
		switch (fenetre.choixMode()) {
		case IA :
			choixDiffIA();
			break;
		case REEL :
			choixUnivers();
			break;
		case DECK :
			fenetre.warning("Cette fonctionnalité n'est pas encore implémentée.");
			choixMode();
			return;
			// creerDeck(); TODO
		case QUITTER :
			estJeuQuitte = true;
			return;
		}
	}
	
	private void choixDiffIA() {
		Heros heros;		
		
		int n = new Random().nextInt(2);	// choix de l'univers
		if (n == 0) {
			deck2 = deckAlea(cstarwars, Univers.STARWARS);
			heros = heroAlea(hstarwars);
		} 
		else {
			deck2 = deckAlea(charrypotter, Univers.HARRYPOTTER);
			heros = heroAlea(hharrypotter);
		}
		
		switch (fenetre.choixDiffIA()) {
		case FACILE :
			j2 = new IANaive(heros);
			break;
		case INTERMEDIAIRE :
			j2 = new IAIntermediaire(heros);
			break;
		case EXPERT :
			j2 = new IAExperte(heros);
			break;
		case RETOUR :
		default :
			choixMode();
			return;
		}

		choixUnivers();
	}

	/** Obtenir l'univers choisi*/
	private void choixUnivers() {
		switch (fenetre.choixUnivers()) {
		case STARWARS :
			choixHero(Univers.STARWARS);
			break;
		case HARRYPOTTER :
			choixHero(Univers.HARRYPOTTER);
			break;
		case RETOUR :
		default:
			choixDiffIA();
			return;
		}
	}
	
	/** Obtenir le heros choisi*/
	private void choixHero(Univers univers) {
		ArrayList<Heros> listeHeros;
		
		switch(univers) {
		case STARWARS:
			listeHeros = new ArrayList<>(hstarwars);
			break;
		case HARRYPOTTER:
			listeHeros = new ArrayList<>(hharrypotter);
			break;
		case RETOUR:
		default:
			fenetre.erreur("Erreur interne (choixHero appelé sans univers)");
			return;
		}
		
    	try{ 	// on ajoute un héros fictif pour autoriser le retour
    		listeHeros.add(new Heros("Retour")); 
    	}catch (IOException e) { 
    		fenetre.erreur("Fichier RETOUR.jpg introuvable."); 
    	}
		
		Heros hero = fenetre.choixHeros(listeHeros);
		
		if (hero.getNom() == "Retour") {
			choixUnivers();
			return;
		}
		else {
			j1 = new JoueurReel(new Heros(hero));
			choixDeck(univers);
		}
	}
	
	/** Etape : choisir le deck */
	private void choixDeck(Univers univers) {
		Deck deck = choisirDeck(univers);
		
		if (deck.nom == "RETOUR") {
			choixHero(univers); 
		}
		else{
			if (deck.nom == "ALEATOIRE") 		
				deck = deckAlea(cartesFromUnivers(univers), univers);
			actionDeck(deck, univers);
		}
	}
	
	/** Action a executer avec ce deck*/
	private void actionDeck(Deck deck, Univers univers) {
		switch (fenetre.actionDeck(deck)) {
		case AFFICHER :
			afficherDeck(deck, univers);
			break;
		case JOUER :
			deck1 = deck;
			break;
		case RETOUR :
		default :
			choixDeck(univers);
			return;
		}
	}
	
	/** Afficher le deck*/
	private void afficherDeck(Deck deck, Univers univers) {
		switch (fenetre.afficherDeck(deck)) {
		case JOUER :
			deck1 = deck;
			break;
		case RETOUR :
		default :
			choixDeck(univers);
			return;
		}
	}
	
	/** Demander le deck à l'utilisateur et le retourner */
	private Deck choisirDeck(Univers univers) {
		ArrayList<Deck> listeDecks = new ArrayList<Deck>();
		/*switch(univers) {
		case HARRYPOTTER:
			listeDecks = new ArrayList<>(deckhp);
			break;
		case STARWARS:
			listeDecks = new ArrayList<>(decksw);
			break;
		case RETOUR:
		default:
			return null;
		}*/
		
		return fenetre.choixDeck(listeDecks);
	}
	
	/** Retourne les cartes de l'univers en parametre */
	private ArrayList<Carte> cartesFromUnivers(Univers univers){
		switch(univers) {
		case HARRYPOTTER:
			return new ArrayList<>(charrypotter);
		case STARWARS:
			return new ArrayList<>(cstarwars);
		case RETOUR:
		default:
			return null;
		}
	}
	

	/** GÃ©nÃ©rer deck alÃ©atoire*/
	private Deck deckAlea(ArrayList<Carte> cartes, Univers univers) {		
		ArrayList<Carte> aux = new ArrayList<>(cartes);
		ArrayList<Carte> deck = new ArrayList<>(NB_CARTES_PAR_DECK);
		Random rnd = new Random();
		int n;
		
		for (int i = 0; i < NB_CARTES_PAR_DECK; i++) {
			n = rnd.nextInt(aux.size());
			deck.add(aux.remove(n));
		}
		
		return new Deck("DeckAlea", deck, univers);
	}

	/** GÃ©nÃ©rer hÃ©ro alÃ©atoire*/
	private Heros heroAlea(ArrayList<Heros> heros) {
		Random rnd = new Random();
		return new Heros(heros.get(rnd.nextInt(NB_HEROS)));
	}

	/** Creer un deck*/
	private void creerDeck() {
		Univers univers = fenetre.choixUnivers();
		if (univers == Univers.RETOUR) {
			choixMode();
			return;
		}
		else
			deckModifier(univers);
	}

	/** Choisir le deck Ã  modifier*/
	private void deckModifier(Univers univers) {		
		Deck deck = choisirDeck(univers);
		
		if (deck.nom == "RETOUR")
			creerDeck();
		else
			deckCarte(univers, deck);
	}

	/** Choisir une carte Ã  ajouter/supprimer*/
	private void deckCarte(Univers univers, Deck deck) {
		Carte carte = fenetre.choixCarte(deck, univers);
		if (carte == null) {
			if (deck.cartes.size() != NB_CARTES_PAR_DECK) {
				fenetre.erreur("Il n'y a pas le bon nombre de cartes");
				deckCarte(univers, deck);
			}
			FilesManager.saveDeck(deck, univers);
			deckModifier(univers);
		} else {
			if (!deck.cartes.contains(carte))
				ajouterCarte(deck, carte, univers);
			else
				supprimerCarte(deck, carte, univers);
		}
	}

	/** Ajouter une carte au deck*/
	private void ajouterCarte(Deck deck, Carte carte, Univers univers) {
		switch (fenetre.ajouterCarte(carte)) {
		case AJOUTER :
			deck.cartes.add(carte);
			break;
		case RETOUR :
		default :
			deckCarte(univers, deck);
		}
	}

	/** Supprimer une carte du deck*/
	private void supprimerCarte(Deck deck, Carte carte, Univers univers) {
		switch (fenetre.supprimerCarte(carte)) {
		case SUPPRIMER :
			deck.cartes.remove(carte);
			break;
		case RETOUR :
		default :
			deckCarte(univers, deck);
		}
	}

}
