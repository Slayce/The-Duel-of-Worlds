package jeu;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import jeu.MenuModel.Univers;
import univers.Carte;
import univers.Heros;
import univers.Serviteur;
import univers.Carte.t_type;

public class FilesManager {

	private static final String filesPath = "../files/";
	private static final String decksPath = filesPath + "decks/";
	private static final String cartesPath = filesPath + "cartes/";
	private static final String herosPath = filesPath + "heros/";
	private static final String imgPath = filesPath + "img/";
	
	
	/** Récuperer la liste des fichiers dans un dossier */
	static ArrayList<File> filesInFolder(final String folderStr) {
		ArrayList<File> files = new ArrayList<File>();
		
		File folder = new File(folderStr);
		File[] liste = folder.listFiles();
		if (liste != null)
		    for (File file : liste)
				if (!file.isDirectory())
					files.add(file);
	    
	    return files;
	}
	
	
	/** Si isFolder est vrai, renvoie hp/ ou sw/ 
	 * Sinon, renvoie hp.txt ou sw.txt */
	static private String getFileOrFolderName(Univers univers, boolean isFolder) {
		switch(univers) {
		case HARRYPOTTER:
			return "hp" + (isFolder ? "/" : ".txt");
		case STARWARS:
			return "sw" + (isFolder ? "/" : ".txt");
		case RETOUR:
		default:
			return null;
		}
	}
	
	
	/** Ouvre le buffer reader et renvoie une exception si le fichier n'existe pas 
	 * @throws IOException */
	static BufferedReader openBr(final String fichier) throws IOException {
		BufferedReader br;
		
		try {
			InputStream ips = FilesManager.class.getResourceAsStream(filesPath + fichier);	
			
			InputStreamReader ipsr = new InputStreamReader(ips);
			br = new BufferedReader(ipsr);
		} 
		catch(NullPointerException e) {
			throw new FileNotFoundException(fichier);
		}
		
		return br;
	}
	
	
	/** Sauvegarder le deck */
	static void saveDeck(Deck deck, Univers univers) {
		File file;
		try{
			file = new File(decksPath + getFileOrFolderName(univers, true) + deck.nom + ".txt");
			
			PrintWriter wr = new PrintWriter(file);
			for (int i = 1; i <= MenuModel.NB_CARTES_PAR_DECK; i++) {
				wr.print(deck.cartes.get(i).getNom() + ",");
				wr.print(deck.cartes.get(i).getDegats() + ",");
				wr.print(deck.cartes.get(i).getCout() + ",");
				wr.print(deck.cartes.get(i).getType().name() + ",");
				wr.println(deck.cartes.get(i).getPvMax());
			}
			wr.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	
	/** Récuperer les decks sauvegardés pour l'univers choisi 
	 * @throws IOException */
	static ArrayList<Deck> loadDecks(Univers univers) throws IOException{
		ArrayList<Deck> decks = new ArrayList<Deck>();

		Carte carte;
		BufferedReader br;
		String ligne;

		for (File f : filesInFolder(decksPath + getFileOrFolderName(univers, true))) {
			ArrayList<Carte> cartes = new ArrayList<Carte>();

			br = openBr(decksPath + getFileOrFolderName(univers, true) + f.getName());
			try {
				while ((ligne = br.readLine()) != null) {		
					carte = loadCarte(ligne);
					if (carte != null)
						cartes.add(carte);
				}
				br.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			} 
			
			if (cartes.size() == MenuModel.NB_CARTES_PAR_DECK)
				decks.add(new Deck(f.getName(), cartes, univers));
		}
	
		return decks;
	}
	
	
	/** Récuperer la carte à partir d'une ligne 
	 * Retourne null en cas d'erreur 
	 * @throws IOException */
	static private Carte loadCarte(String ligne) throws IOException{
		try {
		String[] st = ligne.split(",");
		
		int degat = java.lang.Integer.parseInt(st[1]);
		int cout = java.lang.Integer.parseInt(st[2]);
		int pv = java.lang.Integer.parseInt(st[4]);
		t_type type = st[3].equals("INDIVIDUEL") ? t_type.INDIVIDUEL : t_type.GROUPE;		
		
		return new Serviteur(st[0], "", degat, cout, type, pv);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException("La ligne " + ligne + " n'est pas correctement formatée.");
		}
	}
	
	
	/** Récuperer les cartes sauvegardés pour l'univers choisi 
	 * @throws IOException */
	static ArrayList<Carte> loadCartes(Univers univers) throws IOException{
		ArrayList<Carte> cartes = new ArrayList<>();
		
		Carte carte;

		BufferedReader br = openBr(cartesPath + "cartes_" + getFileOrFolderName(univers, false));
		String ligne;
		br.readLine();
		
		while ((ligne = br.readLine()) != null) {				
			carte = loadCarte(ligne);
			if (carte != null)
				cartes.add(carte);
		}
		br.close();
	
		return cartes;
	}
	
	
	/** Récuperer les héros sauvegardés pour l'univers choisi 
	 * @throws IOException */
	static ArrayList<Heros> loadHeros(Univers univers) throws IOException{
		ArrayList<Heros> listeHeros = new ArrayList<>();
		
		Heros heros;

		BufferedReader br = openBr(herosPath + "heros_" + getFileOrFolderName(univers, false));
		String ligne;
		
		while ((ligne = br.readLine()) != null) {
			heros = new Heros(ligne);
			listeHeros.add(heros);
		}
		br.close();
	
		return listeHeros;
	}
	
	
	/** Récuperer l'image de la carte en paramètre 
	 * @throws IOException */
	static public BufferedImage loadImage(String nom) throws IOException{
		String nomImage = imgPath + nom;
		try {
			return ImageIO.read(FilesManager.class.getResource(nomImage));			
		}
		catch (Exception e) {
			throw new FileNotFoundException("L'image " + nomImage + " est introuvable.");
		}
	}
	
	/** Récupérer l'image de l'élément d'interface en paramètre 
	 * @throws IOException */
	static public BufferedImage loadGUIImage(String nom) throws IOException{
		String nomImage = "gui/" + nom;
		return loadImage(nomImage);
	}
}
