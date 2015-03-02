package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * Esta clase se encarga de acabar con la ejecuci�n del juego. Configura el contexto del robot actualizando la informacion sobre este.
 * Parsea una cadena para saber si esta hace referencia a una instrucci�n de tipo Quit, sino lanza una excepci�n del tipo
 * WrongInstructionFormatException. Si la cadena es correcta ( "quit " o "salir") devuelve una QuitInstruction. 
 * Si el atributo es nulo y se intenta acceder a �l salta una excepcion del tipo NullPointerException. 
 * Tiene un atributo, robot, que contiene el estado del robot.
 * El m�todo getHelp te indica los par�metros que se aceptan como validos para esta instrucci�n.
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
	 * Analiza la cadena y devuelve una instancia de HelpInstruction. Si no es correcta lanza una excepci�n.	 
	 * @param cad - Cadena de texto
	 * @return devuelve la instucci�n parseada.
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
	 * @return Devuelve la sint�xis correcta de la instrucci�n.
	 */
	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "QUIT|SALIR";
	}
	/**
	 * Modifica el contexto de la ejecuci�n.El m�todo recibe el engune completo(RobotEngine,
	 *  NavigationModule y el ItemCOntainer.	 
	 */
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.robot = engine;
		
	}
	/**
	 * Solicita al robot detener la ejecuci�n  de la aplicaci�n.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		this.robot.requestQuit();
	}

}
