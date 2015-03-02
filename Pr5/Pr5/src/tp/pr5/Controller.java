package tp.pr5;

import tp.pr5.items.InventoryObserver;

/**
 * Clase abstracta que tiene los métodos que se encargaran de registrar en el controlador los observadores que este vaya a tener.
 * Además tiene un metodo que se encargará de arrancar el controlador.
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 *
 */
public abstract class Controller {
	protected RobotEngine robot;

	/**
	 * Constructora de la clase Constroller que se encarga de fijar el modelo de esta clase abstracta dado un modelo introducido por parametro
	 * @param robot el modelo recibido 
	 */
	public Controller ( RobotEngine robot){
		this.robot = robot;
	}
	
	/**
	 * Metodo a implementar en las clases que hereden de Controller, se encargara de registrar en el modelo (robot) los observadores de tipo
	 * RobotEngineObserver que este vaya a tener a los cuales llamara cuando se produzcan cambios en el estado del robot
	 * @param robOb 
	 */
	public abstract void registerEngineObserver( RobotEngineObserver robOb);
	
	/**
	 * Metodo a implementar en las clases que hereden de Controller, se encargara de registrar en el modelo (robot) los observadores de tipo
	 * InventoryObserver que este vaya a tener a los cuales llamara cuando se produzcan cambios en el inventario del robot
	 * @param invOb
	 */
	public abstract void registerItemContainerObserver(InventoryObserver invOb);
	
	/**
	 * Metodo a implementar en las clases que hereden de Controller, se encargara de registrar en el modelo (robot) los observadores de tipo
	 * RobotEngineObserver que este vaya a tener a los cuales llamara cuando se produzcan cambios en el contexto por el que el robot se mueve ( lugar,
	 * calle...)
	 * @param navOb
	 */
	public abstract void registerRobotObserver( NavigationObserver navOb);
	
	/**
	 * Metodo a implementar en las clases que hereden de Controller, se encarag de arrancar el controlador y de ejecutar el juego. 
	 */
	public abstract void startController();
}
