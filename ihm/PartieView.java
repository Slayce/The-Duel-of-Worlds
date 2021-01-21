package ihm;

/**
 * @author aleberre
 */

import javax.swing.*;
import jeu.Plateau;
import player.JoueurReel;
import univers.Carte;
import univers.Heros;
import univers.Serviteur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class PartieView extends AbstractView {	
	private enum Type { PLATEAU, MAIN };
	public enum Etape { CHOIX_CARTES, CHOIX_ATTAQUANT, CHOIX_ATTAQUEE};
	
	private Etape currentEtape;
	
	private JoueurReel j;
	private ArrayList<BoutonCarte> monPlateau, sonPlateau, main;
	private GUIBouton finTour;
	private JLabel pvLabel, pvAdvLabel;
	private BoutonHeros butHerosAdv;
	
	private final Color elementsColor = Color.getHSBColor(39, 34, 60);


	private JPanel partiePanel = new JPanel(), 
			pvPanel = new JPanel(), 
			pvAdvPanel = new JPanel(), 
			heroPanel = new JPanel(), 
			heroAdvPanel = new JPanel(), 
			plateauPanel = new JPanel(), 
			plateauAdvPanel = new JPanel(), 
			mainPanel = new JPanel();


	public PartieView(Fenetre fenetre, JoueurReel j, Heros herosAdv) {
		super(fenetre);
		this.j = j;
		initComposants(herosAdv);
		changerEtape(Etape.CHOIX_CARTES);
	}

	// Placement des composants de la vue
	private void initComposants(Heros herosAdv) {
		partiePanel = new JPanel();

		monPlateau = new ArrayList<>(Plateau.NB_MAX);
		sonPlateau = new ArrayList<>(Plateau.NB_MAX);	
		main = new ArrayList<>(5);

		plateauPanel.setPreferredSize(getDimFromTests(1300, 300));
		plateauPanel.setBackground(elementsColor);
		
		plateauAdvPanel.setPreferredSize(getDimFromTests(1300, 300));
		plateauAdvPanel.setBackground(elementsColor);
		
		mainPanel.setPreferredSize(getDimFromTests(700, 200));
		
		heroPanel.setPreferredSize(getDimFromTests(150, 213));

		heroAdvPanel.setPreferredSize(getDimFromTests(150, 213));
		
		pvPanel.setPreferredSize(getDimFromTests(80, 60));	
		pvPanel.setBackground(elementsColor);
		
		pvAdvPanel.setPreferredSize(getDimFromTests(80, 60));
		pvAdvPanel.setBackground(elementsColor);
		

		//On définit le layout manager
		partiePanel.setLayout(new GridBagLayout());

		//L'objet servant à positionner les composants
		GridBagConstraints gbc = new GridBagConstraints();
		
		//--------------Mon plateau---------------------
		gbc.gridx = 3;
		gbc.gridy = 9;
		gbc.gridheight = 5;
		gbc.gridwidth = 11;
		gbc.anchor = GridBagConstraints.CENTER;
		partiePanel.add(plateauPanel, gbc);

		plateauPanel.setLayout(new GridLayout(1, 6, 30, 30));
		for (int i = 0; i < monPlateau.size(); i++)
			plateauPanel.add(monPlateau.get(i));
		
		//--------------Son plateau---------------------
		gbc.gridx = 3;	
		gbc.gridy = 1;
		gbc.gridheight = 5;
		gbc.gridwidth = 11;
		gbc.anchor = GridBagConstraints.CENTER;
		partiePanel.add(plateauAdvPanel, gbc);

		plateauAdvPanel.setLayout(new GridLayout(1, 6, 30, 30));
		for (int i = 0; i < sonPlateau.size(); i++)
			plateauAdvPanel.add(sonPlateau.get(i));
		
		//----------------Ma main--------------------
		gbc.gridx = 5;
		gbc.gridy = 13;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 3;
		gbc.anchor = GridBagConstraints.SOUTH;
		partiePanel.add(mainPanel, gbc);

		mainPanel.setLayout(new GridLayout(1, 5, 30, 30));
		for (int i = 0; i < main.size(); i++)
			mainPanel.add(main.get(i));

		//-------------Bouton tour fini----------------
		gbc.gridx = 18;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		gbc.anchor = GridBagConstraints.LINE_END;

		finTour = new GUIBouton("");
		finTour.addActionListener(
				new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	j.setPoseFinie(true);
	                	if(currentEtape == Etape.CHOIX_CARTES)
	                		changerEtape(Etape.CHOIX_ATTAQUANT);
	                	else
	                		changerEtape(Etape.CHOIX_CARTES);
	                }
				});
		partiePanel.add(finTour, gbc);

		//---------------Mon héros--------------------
		gbc.gridx = 0;
		gbc.gridy = 13;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		partiePanel.add(heroPanel, gbc);


		BufferedImage imgHeros = j.getHeros().getImage();
		Image scaledImgHeros = imgHeros.getScaledInstance(heroPanel.getPreferredSize().width, heroPanel.getPreferredSize().height, Image.SCALE_SMOOTH);
		heroPanel.add(new JLabel(new ImageIcon(scaledImgHeros)));
		
		//---------------Son héros--------------------
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		partiePanel.add(heroAdvPanel, gbc);
		
		BufferedImage imgHerosAdv = herosAdv.getImage();
		Image scaledImgHerosAdv = imgHerosAdv.getScaledInstance(heroAdvPanel.getPreferredSize().width, heroAdvPanel.getPreferredSize().height, Image.SCALE_SMOOTH);
		butHerosAdv = new BoutonHeros(scaledImgHerosAdv, herosAdv, heroAdvPanel.getPreferredSize().width, heroAdvPanel.getPreferredSize().height);
		heroAdvPanel.add(butHerosAdv);
		
		//-----------------Mes PVs--------------------
		gbc.gridx = 2;
		gbc.gridy = 14;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.NORTH;
		partiePanel.add(pvPanel, gbc);

		pvLabel = new JLabel("" + j.getHeros().getPV());
		pvLabel.setFont(new Font("Poor Richard", Font.BOLD, 30));
		pvPanel.add(pvLabel, BorderLayout.CENTER);
		
		//-----------------Ses PVs--------------------
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		partiePanel.add(pvAdvPanel, gbc);

		pvAdvLabel = new JLabel("" + herosAdv.getPV());
		pvAdvLabel.setFont(new Font("Poor Richard", Font.BOLD, 30));
		pvAdvPanel.add(pvAdvLabel, BorderLayout.CENTER);
		//---------------------------------------------
		
		setBoutonsAction();

		fenetre.changerPanel(partiePanel);	
	}


	/** Renvoie les dimensions adaptées en fonction de celles fournies en paramètre, qui correspondent
	 * aux dimension souhaitée pour un écran 1940x1056 */
	private Dimension getDimFromTests(int w, int h) {
		Rectangle frameSize = fenetre.getBounds();

		return new Dimension((int)(w/1936.0 * frameSize.width), (int)(h/1056.0 * frameSize.height));
	}


	/** Convertit un paquet de cartes en un ensemble de BoutonsCartes */
	public ArrayList<BoutonCarte> cartes2Boutons(ArrayList<Carte> cartes, Type type) {
		ArrayList<BoutonCarte> boutons = new ArrayList<>(cartes.size());
		
		JPanel panelConteneur = (type == Type.MAIN) ? mainPanel : plateauPanel;
		
		int nbrCartes = (type == Type.MAIN) ? 5 : Plateau.NB_MAX;
		
		int carteWidth = getCardWidth(panelConteneur, nbrCartes);
		
		for (int i = 0; i < cartes.size(); i++) {
			BoutonCarte b = new BoutonCarte(cartes.get(i),i, carteWidth, 2*carteWidth, false);
			boutons.add(b);
		}

		return boutons;
	}

	
	private int getCardWidth(JPanel panel, int nbrMaxCartes) {
		// Calcul de la taille de l'image pour les plateaux
		int carteWidth = (int) ((panel.getPreferredSize().width - 30*(nbrMaxCartes+1)) / (float)(nbrMaxCartes));
		int carteHeight = panel.getPreferredSize().height - 20;
		
		return Math.min(carteWidth, carteHeight/2);	// on fait rentrer la carte verticalement et horizontalement
	}
	
	
	/** Compléter les tableaux de BoutonCarte vides */
	private void completerTableaux() {
		int carteWidthPlateaux = getCardWidth(plateauPanel, Plateau.NB_MAX);
		int carteWidthMain = getCardWidth(mainPanel, 5);
		
		for (int i = monPlateau.size(); i < Plateau.NB_MAX; i++) {
			BoutonCarte b = new BoutonCarte(i, carteWidthPlateaux, 2*carteWidthPlateaux, true);
			b.setEnabled(false);
			monPlateau.add(b);
			plateauPanel.add(b);
		}
		
		for (int i = sonPlateau.size(); i < Plateau.NB_MAX; i++) {
			BoutonCarte b = new BoutonCarte(i, carteWidthPlateaux, 2*carteWidthPlateaux, true);
			b.setEnabled(false);
			sonPlateau.add(b);
			plateauAdvPanel.add(b);
		}
		
		for (int i = main.size(); i < 5; i++) {
			BoutonCarte b = new BoutonCarte(i, carteWidthMain, 2*carteWidthMain, true);
			b.setEnabled(false);
			main.add(b);
			mainPanel.add(b);
		}
	}


	
	private void setBoutonsAction() {
		for (BoutonCarte b : monPlateau)
			b.addActionListener(
					new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		                	j.setCarteAttaquante(b.getCarte());
		                	changerEtape(Etape.CHOIX_ATTAQUEE);
		                	b.setAAttaque(true);
		                	b.setEnabled(false);
		                }});
		
		for (BoutonCarte b : sonPlateau)
			b.addActionListener(
					new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		                	j.setCarteAttaquee((Serviteur) b.getCarte());
		                	changerEtape(Etape.CHOIX_ATTAQUANT);
		                }});

		for (BoutonCarte b : main)
			b.addActionListener((e -> j.setCarteAPoser(b.getCarte())));
		
		
		butHerosAdv.addActionListener(
				new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	j.setCarteAttaquee(butHerosAdv.getHeros());
	                	changerEtape(Etape.CHOIX_ATTAQUANT);
	                }});
	}
	

	private void setBoutonsActives(boolean bMain, boolean bMonPlateau, boolean bSonPlateau) {
		for (BoutonCarte b : main)
			if (!b.estVide())
				b.setEnabled(bMain);
		
		for (BoutonCarte b : sonPlateau)
			if (!b.estVide())
				b.setEnabled(bSonPlateau);
		
		butHerosAdv.setEnabled(bSonPlateau);
		
		for (BoutonCarte b : monPlateau)
			if (!b.estVide() && !b.aDejaAttaque())
				b.setEnabled(bMonPlateau);
	}
	
	
	/** Mise Ã  jour de la vue 
	 * Les paramètres mis à null ne seront pas mis à jours*/
	public void mettreAJour(Integer mesPVs, Integer sesPVs, ArrayList<Carte> main, ArrayList<Carte> monPlateau, ArrayList<Carte> sonPlateau ) {
		if (mesPVs != null)
			this.pvLabel.setText("" + mesPVs);

		if (sesPVs != null) 
			this.pvAdvLabel.setText("" + sesPVs);

		if (main != null) {
			this.main = cartes2Boutons(main, Type.MAIN);	

			mainPanel.removeAll();
			for (int i = 0; i < this.main.size(); i++)
				mainPanel.add(this.main.get(i));		
		}
		
		if (monPlateau != null){
			this.monPlateau = cartes2Boutons(monPlateau, Type.PLATEAU);
			
			plateauPanel.removeAll();
			for (int i = 0; i < this.monPlateau.size(); i++) {
				this.monPlateau.get(i).setEnabled(false);				
				plateauPanel.add(this.monPlateau.get(i));	
			}
		}
		
		if (sonPlateau != null){
			this.sonPlateau = cartes2Boutons(sonPlateau, Type.PLATEAU);

			plateauAdvPanel.removeAll();
			for (int i = 0; i < this.sonPlateau.size(); i++){
				this.sonPlateau.get(i).setEnabled(false);	
				plateauAdvPanel.add(this.sonPlateau.get(i));	
			}	
		}
		
		setBoutonsAction();
		
		completerTableaux();
		fenetre.changerPanel(partiePanel);	
	}
	
	
	public void changerEtape(Etape newEtape) {
		currentEtape = newEtape;
		
		switch(newEtape) {
		case CHOIX_CARTES:
			for (BoutonCarte b : monPlateau)
				b.setAAttaque(false);
			
			finTour.setText("Attaquer");
			setBoutonsActives(true, false, false);
			break;
		case CHOIX_ATTAQUANT:
			finTour.setText("Fin du tour");
			setBoutonsActives(false, true, false);
			break;
		case CHOIX_ATTAQUEE:
			setBoutonsActives(false, false, true);
			break;
		default:
			break;
		
		}
	}
}









