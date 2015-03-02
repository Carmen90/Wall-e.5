package tp.pr5;

/**
 * Interfaz de observadores que quiere ser notificada de los eventos relacionados con navegationModule.
 * Las clases que implementan esta interfaz tienen que ser informadas de cuando el robot cambia su partida,cuando llega a un lugar, cuando el lugar se modifica porque el robot ha recogido o quitado un elemento o cuando los usuarios solicitan utilizar el radar. 
 * @author Nerea Ramirez y Carmen Acosta
 *
 */
public interface NavigationObserver {
/**
 * Este método notifica ue la partida del robot ha cambiado
 * @param newHeading
 */
	void headingChanged(Direction newHeading);
	/**
	 * Este método avisa que el navigationModule se ha inicializado
	 * @param initialPlace - El lugar donde el robot comienza la simulación
	 * @param heading - el rumbo inicial del robot
	 */
	void initNavigationModule(PlaceInfo initialPlace,
            Direction heading);
	/**
	 * Notifica que el robot ha llegado a un lugar
	 * @param heading - La dirección del movimiento del robot
	 * @param place  - el lugar donde llega el robot
	 */
	void robotArrivesAtPlace(Direction heading,
            PlaceInfo place);
	
	/**
	 * Notifica que el usuario solicita una instrucción RADAR
	 * @param placeDescription  - Informacion con el lugar actual
	 */
	void placeScanned(PlaceInfo placeDescription);
	
	/**
	 * Notifica que el lugar donde se queda el robot ha cambiado (debido a que el robot recogió o se quita un elemento)
	 * @param placeDescription
	 */
	void placeHasChanged(PlaceInfo placeDescription);
}
