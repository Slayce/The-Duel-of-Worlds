/**
 * 
 */
package univers;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Cassandra
 *
 */
public class Sort implements Carte {
	
	/*
	 * Nom du sort
	 */
	private String Nom;
	/*
	 * Description du sort
	 */
	private String Description;
	/*
	 * Image du sort
	 */
	private BufferedImage Image;
	/*
	 * Dégats causés par le sort
	 */
	private int Degats;
	/**
	 * Cout pour utiliser le sort
	 */
	private int Cout;
	/**
	 * type du sort
	 */
	private t_type Type;
	
	public Sort(String nom, String description, int degats, int cout) throws IOException {
		try {
			this.Image = ImageIO.read(new File(nom + ".png"));
		} catch(IOException e ) {
			this.Image = null;
		} finally {
			this.Nom = nom;
			this.Description = description;
			this.Degats = degats;
			this.Cout = cout;
		}
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
		try {
			this.Image = ImageIO.read(new File(nom + ".png"));
		} catch (IOException e) {
			this.Image = null;
		}
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

	@Override
	public int getPvMax() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(int Id) {
		// TODO Auto-generated method stub
		
	}
	

}
