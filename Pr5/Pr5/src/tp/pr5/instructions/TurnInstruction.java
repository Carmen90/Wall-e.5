package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * Esta clase se encarga de hacer girar al robot.
 * Configura el contexto segun la informacion de un modulo de navegación dado.
 * Parsea una cadena para saber si esta hace referencia a una instrucción de tipo Turn, sino lanza una excepción del tipo
 * WrongInstructionFormatException. Si la cadena es correcta ( "Turn o Girar <left|right>" ) devuelve una TurnInstruction. 
 * Si alguno de los atributos es nulo y se intenta acceder a él salta una excepcion del tipo NullPointerException. 
 * Tiene cuatro atributos, navega, que contiene la informacion del entorno del robot, robot, que contiene el estado del robot 
 * y rota que indica en que dirección se va a girar
 * El método getHelp te indica los parámetros que se aceptan como validos para esta instrucción.
 *
 */
public class TurnInstruction implements Instruction{
	private NavigationModule navega;
	private RobotEngine robot;
	private Rotation rota;
	
	/**
	 * Constructora por defecto
	 */
	public TurnInstruction (){
		this.navega = new NavigationModule();
		this.rota = Rotation.UNKNOWN;
	}

	/**
	* Parsea la cadena que se mete por parametro para devolver un TurnInstruction. En caso de que la cadena no encaje con el 
	* patrón que debe seguir una TurnInstruction se lanzará una excepción de tipo WrongInstructionFormatException()
	* @param cad - cadena a parsear
	* @returns Instruction instrucion de tipo Turn a la que hace referencia la cadena
	* @throws WrongInstructionFormatException - cuando la cadena no se TURN LEFT o RIGHT o GIRAR LEFT o RIGHT
	*/
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String [] cadena = cad.split(" ");
		if ( cadena.length != 2){
			throw new WrongInstructionFormatException ();
		}else if ( !cadena[0].equalsIgnoreCase("TURN") && !cadena[0].equalsIgnoreCase("GIRAR")){
			throw new WrongInstructionFormatException ();
		}
		else if( !cadena[1].equalsIgnoreCase("LEFT") && !cadena[1].equalsIgnoreCase("RIGHT") ){
			throw new WrongInstructionFormatException ();
		}
		else{
			this.rota = Rotation.valueOf(cadena[1].toUpperCase());
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
		return " TURN|GIRAR<LEFT|RIGHT>";
	}

	/**
	 * Modifica el contexto del robot. Recive todo el robot ( engine, navigation, robotContainer) incluso
	 * aunque no se utilicen en el metodo execute().
	 * @param engine robot sobre el que se trabaja
	 * @param navigation entorno del robot
	 * @param robotContainer
	 */
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.navega = navigation;
		this.robot = engine;
	}

	/**
	 * Se encarga de hacer que el robot gire hacia la izquierda o la derecha (segun indique this.rota). Si la rotación es UNKNOWN lanza una excepción
	 * @throws InstructionExecutionException  cuando la rotación es UNKNOWN
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		this.navega.initHeading(this.navega.getCurrentHeading().rotate(rota));
		this.robot.addFuel(-5);
		
	}

}
