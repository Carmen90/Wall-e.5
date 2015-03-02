package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * Esta clase se encarga de mostrar las posibles acciones que se pueden realizar, y como escribirlas.
 * Configura el contexto segun un robot dado.
 * Parsea una cadena para saber si esta hace referencia a una instrucci�n de tipo Help, sino lanza una excepci�n del tipo
 * WrongInstructionFormatException. Si la cadena es correcta ( "Help" o "Ayuda" ) devuelve una HelpInstruction. 
 * Ignora may�sculas y min�sculas a la hora de parsear la cadena.
 * Si el atributo es nulo y se intenta acceder a �l salta una excepcion del tipo NullPointerException. 
 * El atributo, robot, contiene el estado del robot del que se muestra las acciones que puede realizar.
 * El m�todo getHelp te indica los par�metros que se aceptan como validos para esta instrucci�n.
 *
 */
public class HelpInstruction implements Instruction {
	private RobotEngine robot;
	
	/**
	 * Constructora por defecto que crea el robotEngine.
	 */
	public HelpInstruction (){
		this.robot = new RobotEngine ();
	}
	
	/**
	 * Analiza esl String y devuelve una instancia de HelpInstruction. Si no es correcta lanza una excepci�n.
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		cad = cad.trim();//elimina los espacios en blanco.
		
		String [] cadena = cad.split(" ");/*Divide la cadena en espacios
		y guarda cada fragmento en cada posici�n del array*/
		// TODO Auto-generated method stub
		if(!cadena[0].equalsIgnoreCase("HELP") && !cadena[0].equalsIgnoreCase("AYUDA") 
				|| cadena.length > 1)
		{
			throw new WrongInstructionFormatException();
		}
		else {
			return this;
		}
	}
	
	/**
	 * Devuelve la sintaxis de esta instrucci�n
	 */
	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "HELP|AYUDA";
	}
	
	/**
	 * Configura el contexto de esta instrucci�n(el robot lo actualiza).
	 */
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.robot = engine;
	}
	
	/**
	 * Imprime el string ayuda de todas las instrucciones.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		this.robot.requestHelp();
	}

}
