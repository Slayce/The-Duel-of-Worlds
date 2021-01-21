package jeu;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import jeu.MenuModel.Univers;
import jeu.Plateau.Joueurs;
import univers.Carte;

public class PlateauTest {

	ArrayList<Carte> deck1, deck2, csw, chp;
	Plateau plateau;
	final int NB_MAX_DECK = MenuModel.NB_CARTES_PAR_DECK;
	final int NB_MAX_PIOCHE = NB_MAX_DECK * MenuModel.NB_DECKS_PAR_PIOCHE;

	@Before
	public void initialiser() {
		deck1 = new ArrayList<Carte>(NB_MAX_DECK);
		deck2 = new ArrayList<Carte>(NB_MAX_DECK);
		
		try {
			csw = FilesManager.loadCartes(Univers.STARWARS);
			chp = FilesManager.loadCartes(Univers.HARRYPOTTER);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
		for (int i = 1; i <= NB_MAX_DECK; i++) {
			deck1.add(csw.get(i));
			deck2.add(chp.get(i));
		};
		plateau = new Plateau(deck1,deck2);
	}

	Joueurs j1 = Joueurs.JOUEUR1;
	Joueurs j2 = Joueurs.JOUEUR2;

	@Test
	public void testPiocher() {
		assertEquals(plateau.getPioche(j1).size(), NB_MAX_PIOCHE);
		assertEquals(plateau.getPioche(j2).size(), NB_MAX_PIOCHE);
		plateau.piocher(j1);
		assertEquals(plateau.getPioche(j1).size(), NB_MAX_PIOCHE - 1);
		plateau.piocher(j2);
		assertEquals(plateau.getPioche(j2).size(), NB_MAX_PIOCHE - 1);
		for (int i = 1; i < NB_MAX_PIOCHE; i++) {
			plateau.piocher(j1);
		}
		assertEquals(plateau.getPioche(j1).size(), 0);
	}

	@Test
	public void testPoserRetirer() {
		assert(plateau.getPlateau(j1).isEmpty());
		assert(plateau.getPlateau(j2).isEmpty());
		
		for (int i = 0; i < NB_MAX_DECK; i++) {
			plateau.poser(deck1.get(i), j1);
			assertEquals(plateau.getPlateau(j1).size(), i+1);
			assertEquals(plateau.getPlateau(j1).get(i), deck1.get(i));
		}
		for (int i = 0; i < NB_MAX_DECK - 1; i++) {
			plateau.retirer(deck1.get(i));
			assertEquals(plateau.getPlateau(j1).size(), NB_MAX_DECK - i - 1);
		}
		assertEquals(plateau.getPlateau(j1).get(0), deck1.get(NB_MAX_DECK - 1));
		plateau.retirer(deck1.get(NB_MAX_DECK - 1));
		assert(plateau.getPlateau(j2).isEmpty());
	}

}
