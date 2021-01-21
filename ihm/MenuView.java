package ihm;

import java.util.ArrayList;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import jeu.Deck;
import jeu.MenuModel;
import jeu.MenuModel.ActionCarte;
import jeu.MenuModel.ActionDeck;
import jeu.MenuModel.Difficulte;
import jeu.MenuModel.Mode;
import jeu.MenuModel.Univers;
import jeu.Outils;
import univers.Carte;
import univers.Heros;

/** 
 * @author Remi
 */
public class MenuView extends AbstractView {

	private JPanel 	choixModePanel,		// Fenetre initiale
	choixDiffIAPanel,
	choixUniversPanel,
	choixHerosPanel,
	choixDeckPanel,
	actionDeckPanel, 	// Quelle action faire avec le deck selectionne (afficher, jouer, annuler)
	afficherDeckPanel;


	private MenuModel.Mode retMode;
	private MenuModel.Difficulte retDiff;
	private MenuModel.Univers retUnivers;
	private Heros retHeros;
	private Deck retDeck;
	private MenuModel.ActionDeck retActDeck;
	private MenuModel.ActionDeck retAffDeck;

	
	public MenuView(Fenetre fenetre) {
		super(fenetre);
		
		try {
			GUIBouton.initTexture();
		} catch (IOException e) {
			e.printStackTrace();
			erreur(e.getMessage());
		}
	}


	public MenuModel.Mode choixMode() {
		if (choixModePanel == null) {
			choixModePanel = new JPanel();

			choixModePanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.NORTH;

			JLabel titre = new JLabel("Le Duel des Mondes");
			titre.setFont(new Font("Poor Richard", Font.PLAIN, 50));

			choixModePanel.add(titre, gbc);

			JPanel pButtons = new JPanel();

			pButtons.setLayout(new GridBagLayout());

			ArrayList<GUIBouton> bChoixMode = new ArrayList<>();
			bChoixMode.add(new GUIBouton("Jouer contre une IA"));
			bChoixMode.add(new GUIBouton("Créer un deck"));
			bChoixMode.add(new GUIBouton("Quitter"));

			bChoixMode.get(0).addActionListener((e -> retMode = Mode.IA));
			bChoixMode.get(1).addActionListener((e -> retMode = Mode.DECK));
			bChoixMode.get(2).addActionListener((e -> retMode = Mode.QUITTER));

			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(50,0,0,0);
			gbc.ipadx = 30;
			gbc.ipady = 20;

			for (GUIBouton b : bChoixMode)
				pButtons.add(b, gbc);

			choixModePanel.add(pButtons, gbc);
		}

		fenetre.changerPanel(choixModePanel);

		retMode = null;

		while(retMode == null)
			Outils.attendre(10);

		return retMode;
	}


	public MenuModel.Difficulte choixDiffIA() {
		if (choixDiffIAPanel == null) {
			choixDiffIAPanel = new JPanel();

			choixDiffIAPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.NORTH;

			JLabel titre = new JLabel("Difficulté de l'IA");
			titre.setFont(new Font("Futura Bk BT", Font.PLAIN, 40));

			choixDiffIAPanel.add(titre, gbc);

			JPanel pButtons = new JPanel();

			pButtons.setLayout(new GridBagLayout());

			ArrayList<GUIBouton> bChoixMode = new ArrayList<GUIBouton>();
			bChoixMode.add(new GUIBouton("Facile"));
			bChoixMode.add(new GUIBouton("Intermediaire"));
			bChoixMode.add(new GUIBouton("Difficile"));
			bChoixMode.add(new GUIBouton("Retour"));

			bChoixMode.get(0).addActionListener((e -> retDiff = Difficulte.FACILE));
			bChoixMode.get(1).addActionListener((e -> retDiff = Difficulte.INTERMEDIAIRE));
			bChoixMode.get(2).addActionListener((e -> retDiff = Difficulte.EXPERT));
			bChoixMode.get(3).addActionListener((e -> retDiff = Difficulte.RETOUR));

			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(30,0,0,0);
			gbc.ipadx = 30;
			gbc.ipady = 20;

			for (GUIBouton b : bChoixMode) 
				pButtons.add(b, gbc);

			choixDiffIAPanel.add(pButtons, gbc);
		}

		fenetre.changerPanel(choixDiffIAPanel);

		retDiff = null;

		while(retDiff == null)
			Outils.attendre(10);		

		return retDiff;
	}


	public MenuModel.Univers choixUnivers() {
		if (choixUniversPanel == null) {
			choixUniversPanel = new JPanel();

			choixUniversPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.NORTH;

			JLabel titre = new JLabel("Choix de l'univers");
			titre.setFont(new Font("Futura Bk BT", Font.PLAIN, 40));

			choixUniversPanel.add(titre, gbc);

			JPanel pButtons = new JPanel();

			pButtons.setLayout(new GridBagLayout());

			ArrayList<GUIBouton> bChoixMode = new ArrayList<GUIBouton>();
			bChoixMode.add(new GUIBouton("Star Wars"));
			bChoixMode.add(new GUIBouton("Harry Potter"));
			bChoixMode.add(new GUIBouton("Retour"));

			bChoixMode.get(0).addActionListener((e -> retUnivers = Univers.STARWARS));
			bChoixMode.get(1).addActionListener((e -> retUnivers = Univers.HARRYPOTTER));
			bChoixMode.get(2).addActionListener((e -> retUnivers = Univers.RETOUR));

			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(30,0,0,0);
			gbc.ipadx = 30;
			gbc.ipady = 20;

			for (GUIBouton b : bChoixMode) 
				pButtons.add(b, gbc);

			choixUniversPanel.add(pButtons, gbc);
		}

		fenetre.changerPanel(choixUniversPanel);

		retUnivers = null;

		while(retUnivers == null)
			Outils.attendre(10);		

		return retUnivers;
	}


	public Heros choixHeros(ArrayList<Heros> listeHeros) {
		{   // créer le panel
			choixHerosPanel = new JPanel();

			choixHerosPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.NORTH;

			JLabel titre = new JLabel("Choix du héros");
			titre.setFont(new Font("Futura Bk BT", Font.PLAIN, 40));

			choixHerosPanel.add(titre, gbc);

			JPanel pButtons = new JPanel();

			pButtons.setLayout(new GridBagLayout());

			ArrayList<GUIBouton> bChoixMode = new ArrayList<GUIBouton>();

			for (Heros h : listeHeros) {
				GUIBouton temp = new GUIBouton(h.getNom());
				temp.addActionListener((e -> retHeros = h));				
				bChoixMode.add(temp);
			}
				
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(30,0,0,0);
			gbc.ipadx = 30;
			gbc.ipady = 20;

			for (GUIBouton b : bChoixMode) 
				pButtons.add(b, gbc);

			choixHerosPanel.add(pButtons, gbc);
		}

		fenetre.changerPanel(choixHerosPanel);

		retHeros = null;

		while(retHeros == null)
			Outils.attendre(10);

		return retHeros;
	}


	public Deck choixDeck(ArrayList<Deck> listeDeck) {
		{   // créer le panel
			choixDeckPanel = new JPanel();

			choixDeckPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.NORTH;

			JLabel titre = new JLabel("Choix du deck");
			titre.setFont(new Font("Futura Bk BT", Font.PLAIN, 40));

			choixDeckPanel.add(titre, gbc);

			JPanel pButtons = new JPanel();

			pButtons.setLayout(new GridBagLayout());

			ArrayList<GUIBouton> bChoixMode = new ArrayList<GUIBouton>();

			for (Deck d : listeDeck) {
				GUIBouton temp = new GUIBouton(d.nom);
				temp.addActionListener((e -> retDeck = d));				
				bChoixMode.add(temp);
			}

			bChoixMode.add(new GUIBouton("Aléatoire"));
			bChoixMode.add(new GUIBouton("Retour"));
			bChoixMode.get(bChoixMode.size() - 2).addActionListener((e -> retDeck = new Deck("ALEATOIRE", new ArrayList<Carte>(), Univers.RETOUR)));
			bChoixMode.get(bChoixMode.size() - 1).addActionListener((e -> retDeck = new Deck("RETOUR", new ArrayList<Carte>(), Univers.RETOUR)));

			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(30,0,0,0);
			gbc.ipadx = 30;
			gbc.ipady = 20;

			for (GUIBouton b : bChoixMode) 
				pButtons.add(b, gbc);

			choixDeckPanel.add(pButtons, gbc);
		}

		fenetre.changerPanel(choixDeckPanel);

		retDeck = null;

		while(retDeck == null)
			Outils.attendre(10);

		return retDeck;
	}


	public MenuModel.ActionDeck actionDeck(Deck deck) {
		{	// créer le panel
			actionDeckPanel = new JPanel();

			actionDeckPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.NORTH;

			JLabel titre = new JLabel(deck.nom);
			titre.setFont(new Font("Poor Richard", Font.PLAIN, 50));

			actionDeckPanel.add(titre, gbc);

			JPanel pButtons = new JPanel();

			pButtons.setLayout(new GridBagLayout());

			ArrayList<GUIBouton> bChoixMode = new ArrayList<GUIBouton>();
			bChoixMode.add(new GUIBouton("Afficher le deck"));
			bChoixMode.add(new GUIBouton("Jouer avec ce deck"));
			bChoixMode.add(new GUIBouton("Annuler"));

			bChoixMode.get(0).addActionListener((e -> retActDeck = ActionDeck.AFFICHER));
			bChoixMode.get(1).addActionListener((e -> retActDeck = ActionDeck.JOUER));
			bChoixMode.get(2).addActionListener((e -> retActDeck = ActionDeck.RETOUR));

			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(50,0,0,0);
			gbc.ipadx = 30;
			gbc.ipady = 20;

			for (GUIBouton b : bChoixMode)
				pButtons.add(b, gbc);

			actionDeckPanel.add(pButtons, gbc);
		}

		fenetre.changerPanel(actionDeckPanel);

		retActDeck = null;

		while(retActDeck == null)
			Outils.attendre(10);

		return retActDeck;
	}


	public MenuModel.ActionDeck afficherDeck(Deck deck) {
		{	// créer le panel
			afficherDeckPanel = new JPanel();

			afficherDeckPanel.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.PAGE_START;

			JLabel titre = new JLabel(deck.nom);
			titre.setFont(new Font("Poor Richard", Font.PLAIN, 50));

			gbc.ipady = 50;
			afficherDeckPanel.add(titre, gbc);

			JPanel pCartes = new JPanel();

			int colonnes = Math.round(MenuModel.NB_CARTES_PAR_DECK / 2);
			int lignes = Math.round(MenuModel.NB_CARTES_PAR_DECK/colonnes);
			
			pCartes.setLayout(new GridLayout(lignes, colonnes, 30, 30));

			Rectangle frameSize = fenetre.getBounds();
			
			for (Carte c : deck.cartes)
			{
				int largeur = (frameSize.width - 30*10 - 100) / 10;
				Image image = c.getImage().getScaledInstance(largeur, 2*largeur, Image.SCALE_SMOOTH);
				pCartes.add(new JLabel(new ImageIcon(image)), gbc);
			}

			gbc.anchor = GridBagConstraints.CENTER;
			gbc.ipady = 20;
			afficherDeckPanel.add(pCartes, gbc);

			JPanel pButtons = new JPanel();

			pButtons.setLayout(new GridLayout(1, 2, 500, 0));

			ArrayList<GUIBouton> bChoixMode = new ArrayList<GUIBouton>();
			bChoixMode.add(new GUIBouton("Jouer avec ce deck"));
			bChoixMode.add(new GUIBouton("Retour"));

			bChoixMode.get(0).addActionListener((e -> retAffDeck = ActionDeck.JOUER));
			bChoixMode.get(1).addActionListener((e -> retAffDeck = ActionDeck.RETOUR));
			
			for (GUIBouton b : bChoixMode)
				pButtons.add(b);

			gbc.anchor = GridBagConstraints.PAGE_END;
			gbc.insets = new Insets(50,0,0,0);
			afficherDeckPanel.add(pButtons, gbc);
		}
		
		fenetre.changerPanel(afficherDeckPanel);

		retAffDeck = null;

		while(retAffDeck == null)
			Outils.attendre(10);

		return retAffDeck;
	}

	
	public Carte choixCarte(Deck deck, Univers univers) {
		// TODO Auto-generated method stub
		return null;
	}


	public MenuModel.ActionCarte ajouterCarte(Carte carte) {
		// TODO Auto-generated method stub
		return ActionCarte.RETOUR;
	}


	public MenuModel.ActionCarte supprimerCarte(Carte carte) {
		// TODO Auto-generated method stub
		return ActionCarte.RETOUR;
	}

	
	public void creerDeck() {
		// TODO Auto-generated method stub
	}
}
