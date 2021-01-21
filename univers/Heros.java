package univers;

import java.io.IOException;
import java.awt.image.BufferedImage;

/**
 * @author Cassandra
 *
 */
public class Heros implements Personnage {
	
	/*
	 * Valeur de l'identifiant à donner aux heros
	 */
	public static int IDENTIFIANT_HERO = 1;
	
	/*
	 * Nom du hero
	 */
	private String Nom;
	/*
	 * Image du hero
	 */
	private BufferedImage Image;
	/**
	 * Nombre de PV du hero
	 */
	private int PV;
	/**
	 * Identifiant du hero
	 */
	private int Id;
	
	public Heros(String nom) throws IOException {	
		this.Image = jeu.FilesManager.loadImage(nom + ".jpg");
		this.Nom = nom;
		this.PV = PV_HEROS;
		this.Id = IDENTIFIANT_HERO;
		IDENTIFIANT_HERO += 1;
		Serviteur.IDENTIFIANT_SERVITEUR += 1;
	}
	
	public Heros(Heros heros) {	
		this.Image = heros.getImage();
		this.Nom = heros.getNom();
		this.PV = PV_HEROS;
		this.Id = IDENTIFIANT_HERO;
		IDENTIFIANT_HERO += 1;
		Serviteur.IDENTIFIANT_SERVITEUR += 1;
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
	public BufferedImage getImage() {
		return this.Image;
	}

	@Override
	public void setImage(String nom) throws IOException {
		this.Image = jeu.FilesManager.loadImage(nom + ".jpg");
	}

	@Override
	public int getPV() {
		return this.PV;
	}

	@Override
	public void setPV(int PV) {
		this.PV = PV;
	}

	@Override
	public void subirDegats(int Degats) {
		if (Degats < 0) {
			this.setPV(Math.min(this.getPV() - Degats, PV_HEROS));
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


}
