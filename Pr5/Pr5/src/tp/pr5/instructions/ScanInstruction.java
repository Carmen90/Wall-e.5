package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * Esta clase se encarga de mostrar la informacion de un item concreto del inventario del robot o de todos ellos,
 * en función de si se le mete un id o no.
 * Configura el contexto segun el inventario de items del robot que se nos de.
 * Parsea una cadena para saber si esta hace referencia a una instrucción de tipo Scan, sino lanza una excepción del tipo
 * WrongInstructionFormatException. Si la cadena es correcta ( "Scan [id]" o "Escanear [id]", con id opcional e ignorando mayúsculas
 * y minúsculas ) devuelve una RadarInstruction. 
 * Si alguno de los atributos es nulo y se intenta acceder a él salta una excepcion del tipo NullPointerException. 
 * Tiene cuatro atributos, navega, que contiene la informacion del entorno del robot, robot, que contiene el estado del robot,
 * container que contiene los Items que contiene el robot en su invenatrio e id, que es la id del item a operar.
 * El método getHelp te indica los parámetros que se aceptan como validos para esta instrucción.
 *
 */
public class ScanInstruction implements Instruction{
	private ItemContainer container;
	private String id = " ";
	private RobotEngine robot;
	
	/**
	 * Constructora por defecto  que inicializa los atributos de la clase
	 */
	public ScanInstruction (){
		this.container = new ItemContainer();
		this.robot = new RobotEngine();
	}
	
	/**
	* Parsea la cadena que se mete por parametro para devolver un ScanInstruction. En caso de que la cadena no encaje con el 
	* patrón que debe seguir una ScanInstruction se lanzará una excepción de tipo WrongInstructionFormatException()
	* @param cad - cadena a parsear
	* @returns Instruction instrucion de tipo Scan a la que hace referencia la cadena
	* @throws WrongInstructionFormatException - cuando la cadena no es del tipo  SCAN|ESCANEAR [id]
	*/
	@Override
	public Instruction parse(String cad) 
			throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String [] cadena = cad.split(" ");
		
		if( !cadena[0].equalsIgnoreCase("SCAN") && !cadena[0].equalsIgnoreCase("ESCANEAR")){
				throw new WrongInstructionFormatException();
			}
		else{
			if ( cadena.length > 2){
				throw new WrongInstructionFormatException();
			}
			else if ( cadena.length == 2){
				this.id = cadena[1];
			}
			else{
				this.id ="";
			}
		return this;
		}
	}

	/**
	 * Muestra los mensajes que son validos para ejecutar esta instrucción
	 * @return mensaje valido
	 */
	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return " SCAN|ESCANEAR [id]";
	}

	/**
	 * Modifica el contexto del robot. Recive todo el robot ( engine, navigation, robotContainer) incluso
	 * aunque no se utilicen en el metodo execute().
	 * @param engine robot sobre el que se trabaja
	 * @param navigation 
	 * @param robotContainer inventario de items que tiene el robot
	 */
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.container = robotContainer;
		this.robot = engine;
	}

	/**
	 * Devuelve la descripción de un item en concreto del inventario del robot o la lista de todos los items de este
	 * @throws InstructionExecutionException  cuando el robot no contiene el item
	 */
	@Override
	public void execute() 
			throws InstructionExecutionException {
		// TODO Auto-generated method stub
		if ( this.id != ""){
			if ( !this.container.containsItem(id)){
				throw new InstructionExecutionException("You do not have "+ this.id);
			}else{
				this.container.requestScanItem(id);
			}

		}else {
			this.robot.saySomething("WALL·E says: I am carrying the following items");
			this.container.requestScanCollection();
		}
		
	}

}
