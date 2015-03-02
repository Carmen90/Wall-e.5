package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * Esta clase se encarga de acabar con la ejecución del juego. Configura el contexto del robot actualizando la informacion sobre este.
 * Parsea una cadena para saber si esta hace referencia a una instrucción de tipo Quit, sino lanza una excepción del tipo
 * WrongInstructionFormatException. Si la cadena es correcta ( "quit " o "salir") devuelve una QuitInstruction. 
 * Si el atributo es nulo y se intenta acceder a él salta una excepcion del tipo NullPointerException. 
 * Tiene un atributo, robot, que contiene el estado del robot.
 * El método getHelp te indica los parámetros que se aceptan como validos para esta instrucción.
 *
 */
public class QuitInstruction implements Instruction{
	private RobotEngine robot;
	/**
	 * Constructor por defecto que crea el RobotEngine
	 */
	public QuitInstruction (){
		this.robot = new RobotEngine();
	}
	/**
	 * Analiza la cadena y devuelve una instancia de HelpInstruction. Si no es correcta lanza una excepción.	 
	 * @param cad - Cadena de texto
	 * @return devuelve la instucción parseada.
	 * @throws WrongInstructionFormatException-  Cuando la cadena cad no se ajusta a la sintaxis de instrucciones.
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String [] cadena = cad.split(" ");
		
		if ( !cadena[0].equalsIgnoreCase("QUIT") && !cadena[0].equalsIgnoreCase("SALIR")
				|| cadena.length != 1){
			throw new WrongInstructionFormatException ();
		}
		else{
			return this;
		}
	}
	/**
	 * @return Devuelve la sintáxis correcta de la instrucción.
	 */
	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "QUIT|SALIR";
	}
	/**
	 * Modifica el contexto de la ejecución.El método recibe el engune completo(RobotEngine,
	 *  NavigationModule y el ItemCOntainer.	 
	 */
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.robot = engine;
		
	}
	/**
	 * Solicita al robot detener la ejecución  de la aplicación.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		this.robot.requestQuit();
	}

}
