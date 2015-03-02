package tp.pr5;
/**
 * Esta interfaz se encarga de notificar a los observadores sobre los eventos
 *  que ocurren en el robot Engine.El robot Engine puede notificar de cambios en 
 *  el robot como el fuel y el recycled material , informar sobre problemas de comunicación,
 *  errores   y  cuando el robot quiere decir algo.
 *  Por último el robot Engine también notifica cuando el usuario pide ayuda y
 *   cuando el robot se queda sin fuel o cuando llega a la nave.
 * @author Nerea Ramírez y Carmen Acosta
 *
 */

public interface RobotEngineObserver {
	/**
	 * Este método informa devolviendo un mensaje de que se ha porducido un error
	 * @param msg- mensaje de error
	 */
	void raiseError(java.lang.String msg);
	/**
	 * El robot Engine informa de que el usuario ha solicitado ayuda
	 * @param help - Una cadena con la ayuda de información
	 */
	void communicationHelp(java.lang.String help);
	/**
	 * El robot Engine informa de que el robot se ha apagado porque o bien el robot ha llegado donde está la nave, o se ha quedado sin fuel.
	 * @param atShip - cierto si el robot se apaga porque ha llegado a la nave espacial o false si se ha quedado sin combustible
	 */
	void engineOff(boolean atShip);
	/**
	 * El robot Engine informa que la comunicación ha terminado.
	 */
	void communicationCompleted();
	/**
	 * Este método se encarga de informar que el fuel y/o la combustible ha cambiado. 
	 * @param fuel
	 * @param recycledMaterial
	 */
	void robotUpdate(int fuel,
            int recycledMaterial);
	/**
	 * Se informa que el roboe Engine quiere decir algo.
	 * @param message
	 */
	void robotSays(java.lang.String message);
}
