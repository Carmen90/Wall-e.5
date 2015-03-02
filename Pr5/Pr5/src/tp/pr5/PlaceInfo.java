package tp.pr5;
/**
 * PlaceInfo define una interfaz que no se puede modificar. Es utilizada por clases que necesitan acceso a la informacion que contiene del lugar pero no puede ser modificada. 
 * @author Nerea Ramírez y Carmen Acosta
 *
 */
public interface PlaceInfo {
	/**
	 * 	Devuelve el nombre del lugar.
	 * @return - el nombre del lugar.
	 */
	java.lang.String getName();
	/**
	 * Devuelve la descripción del lugar
	 * @return - la descripción del lugar.
	 */
	java.lang.String getDescription();
	/**
	 * Método que dice  si en ese lugar se encuentra la nave
	 * @return -  true si el lugar representa la nave.
	 */
	boolean isSpaceship();
}
