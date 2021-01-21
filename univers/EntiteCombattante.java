package univers;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @author Cassandra
 *
 */
public interface EntiteCombattante {

	/**
	 *Obtenir le nom de l'objet
	 * @return le nom de l'objet
	 */
	public String getNom();
	/**
	 * Modifier le nom de l'objet
	 * @param nom
	 */
	public void setNom(String nom);
	/**
	 * Obtenir l'image de l'objet
	 * @return image de l'objet
	 */
	public BufferedImage getImage();
	/**
	 * Modifier l'image de l'objet à partir du nom de l'image
	 * @param nom
	 * @throws IOException
	 */
	public void setImage(String nom) throws IOException;
	
}