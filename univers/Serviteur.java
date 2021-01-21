/**
 * 
 */
package univers;

import java.io.IOException;
import java.awt.image.BufferedImage;

/**
 * @author Cassandra
 *
 */
public class Serviteur implements Carte, Personnage {
	
	/*
	 * Valeur de l'identifiant à donner aux heros
	 */
	public static int IDENTIFIANT_SERVITEUR = 0;
	
	/*
	 * Nom du serviteur
	 */
	private String Nom;
	/*
	 * Description du serviteur
	 */
	private String Description;
	/*
	 * Image du serviteur
	 */
	private BufferedImage Image;
	/*
	 * Dégats causés par le serviteur
	 */
	private int Degats;
	/**
	 * Cout pour utiliser le serviteur
	 */
	private int Cout;
	/**
	 * type du serviteur
	 */
	private t_type Type;
	/**
	 * Nombre de PV du serviteur
	 */
	private int PV;
	/**
	 * Nombre max de PV du seviteur
	 */
	private final int PV_MAX;
	/**
	 * Identifiant du serviteur
	 */
	private int Id;
	/**
	 * Savoir si le serviteur a attaque
	 */
	private boolean AAttaque;
	
	public Serviteur(String nom, String description, int degats, int cout, t_type type, int pv) throws IOException {		
		this.Image = jeu.FilesManager.loadImage(nom + ".jpg");
		this.Nom = nom;
		this.Description = description;
		this.Degats = degats;
		this.Cout = cout;
		this.Type = type;
		this.PV = pv;
		this.PV_MAX = pv;
		this.AAttaque = false;
		this.Id = IDENTIFIANT_SERVITEUR;
		IDENTIFIANT_SERVITEUR += 1;
		Heros.IDENTIFIANT_HERO += 1;
	}

	@Override
	public String getNom() {
		return this.Nom;
	}

	@Override
	public void setNom(String nom) {
		this.Nom = nom;
	}

	@Override
	public String getDescription() {
		return this.Description;
	}

	@Override
	public void setDescription(String description) {
		this.Description = description;
	}

	@Override
	public BufferedImage getImage() {
		return this.Image;
	}

	@Override
	public void setImage(String nom) throws IOException {
		this.Image = jeu.FilesManager.loadImage(nom + ".jpg");
	}
	
	public void setImage(BufferedImage img) {
		this.Image = img;
	}

	@Override
	public int getPV() {
		return this.PV;
	}

	@Override
	public void setPV(int PV) {
		this.PV = PV;
	}
	
	public int getPVMAX() {
		return this.PV_MAX;
	}

	@Override
	public void subirDegats(int Degats) {
		if (Degats < 0) {
			this.setPV(Math.min(this.getPV() - Degats, this.getPVMAX()));
		} else {
			this.setPV(Math.max(this.getPV() - Degats, 0));
		}
	}

	@Override
	public int getId() {
		return this.Id;
	}

	@Override
	public void setId(int Id) {
		this.Id = Id;
	}

	@Override
	public int getDegats() {
		return this.Degats;
	}

	@Override
	public void setDegats(int valeur_Effet) {
		this.Degats = valeur_Effet;
	}

	@Override
	public int getCout() {
		return this.Cout;
	}

	@Override
	public void setCout(int cout) {
		this.Cout = cout;
	}

	@Override
	public t_type getType() {
		return this.Type;
	}

	@Override
	public void setType(t_type type) {
		this.Type = type;
	}
	
	public boolean getAttauqe() {
		return this.AAttaque;
	}
	
	public void setAttaque(boolean attaque) {
		this.AAttaque = attaque;
	}

	@Override
	public int getPvMax() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
