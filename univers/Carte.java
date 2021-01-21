/**
 * 
 */
package univers;


/**
 * @author Cassandra
 *
 */
public interface Carte extends EntiteCombattante {
	
	public enum t_type {
		INDIVIDUEL,
		GROUPE;
	}
	/**
	 * Obtenir la valeur de l'effet de la carte
	 * @return int valeur de l'effet
	 */
	public int getDegats();
	/**
	 * Modifier la valeur de l'effet de la carte 
	 * @param valeur_Effet
	 */
	public void setDegats(int valeur_Effet);
	/**
	 * Obtenir le cout d'utilisation de la carte
	 * @return int cout
	 */
	public int getCout();
	/**
	 * Modifier le cout d'utilisation de la carte
	 * @param cout
	 */
	public void setCout(int cout);
	/**
	 * Obtenir le type de la carte
	 * @return t_type type 
	 */
	public t_type getType();
	/**
	 * Modifier le type de la carte
	 * @param type
	 */
	public void setType(t_type type);
	/**
	 * Obtenir la description de la carte
	 * @return description de la carte
	 */
	public String getDescription();
	/**
	 * Modifier la description
	 * @param description
	 */
	public void setDescription(String description);
	
	public int getPvMax();
	
	public int getId();
	
	public void setId(int Id);
	
}
