/**
 * 
 */
package univers;


/**
 * @author Cassandra
 *
 */
public interface Personnage extends EntiteCombattante {
	
	/*
	 * PV Max des héros
	 */
	public static final int PV_HEROS = 50;
	
	/**
	 * Obtenir le nombre de PV restants au personnage
	 * @return int PV restants
	 */
	public int getPV();
	
	/**
	 * Modifier le nombre de PV restants
	 * @param PV
	 */
	public void setPV(int PV);
	
	/**
	 * Faire subir des dégats à un personnage
	 */
	public void subirDegats(int Degats);
	
	/**
	 * Obtenir l'identifiant d'un personnage
	 * @return int Id du personnage
	 */
	public int getId();
	
	/**
	 * Modifier l'identifiant d'un personnage
	 * @param Id
	 */
	public void setId(int Id);
	
	
}
