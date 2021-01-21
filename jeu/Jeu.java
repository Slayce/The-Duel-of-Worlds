package jeu;

import player.JoueurReel;
import ihm.Fenetre;
import ihm.MenuView;
import ihm.PartieView;

/** Classe principale (main) qui gere le jeu en entier
 * @author Remi
 */
public class Jeu {
	public static void main(String[] args) {	
		Fenetre fenetre = new Fenetre();
				
		MenuView vueMenu = new MenuView(fenetre);
		MenuModel menu = new MenuModel(vueMenu);
		
		menu.lancer();
		
		while (!menu.estJeuFini()) {
			PartieView vuePartie = new PartieView(fenetre, (JoueurReel) menu.getJoueur1(), menu.getJoueur2().getHeros());
			PartieModel partie = new PartieModel(vuePartie, menu.getJoueur1(), menu.getJoueur2(), menu.getDeck1(), menu.getDeck2());
			partie.lancer();

			menu.lancer();
		}

		fenetre.setVisible(false);
		fenetre.dispose();
	}
}
