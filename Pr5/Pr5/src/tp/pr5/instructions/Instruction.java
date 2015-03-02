package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * Esta interfaz representa una instrucci�n que soporta la aplicaci�n.Toda instrucci�n del robot debe implementar todos los m�todos de esta interfaz.
 * El m�todo parse intenta analizar un string con la informaci�n de la instrucci�n de la clase que representa.
 * Help es un m�todo que devuelve un string con la sint�xis de la instrucci�n que la clase soporta.
 * El m�todo ConfigureContext establece el marco necesario para ejecutar la instrucci�n.
 * Por �ltimo el m�todo Esecute realiza el trabajo que hace cada instrucci�n.No tiene ning�n par�metro este m�todo, por lo que antes de hacer la ejecuci�n de la instrucci�n se debe llamar
 * a configureContext para darle al objeto de instrucciones.
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 */
public interface Instruction {
	/**
	 * 
	 * Analiza la cadena y devuelve una instancia de HelpInstruction. Si no es correcta lanza una excepci�n.	 
	 * @param cad - Cadena de texto
	 * @return
	 * @throws WrongInstructionFormatException-  Cuando la cadena cad no se ajusta a la sintaxis de instrucciones.
	 */
	Instruction parse(java.lang.String cad)
            throws WrongInstructionFormatException;
	/**
	 * Devuelve la sintaxis de la instrucci�n.
	 * @return
	 */
	java.lang.String getHelp();
	/**
	 * Establece el contexto de ejecuci�n.
	 * Modifica la configuraci�n del contexto.El m�todo recibe el robot engine, navigation (la informaci�n sobre el juego, los lugares y la direccion actual y el contenedor del robot.
	 * @param engine
	 * @param navigation
	 * @param robotContainer
	 */
	void configureContext(RobotEngine engine,
            NavigationModule navigation,
            ItemContainer robotContainer);
	/**
	 * Ejecuta la instrucci�n que debe implementarse en cada subclase no abstracta.
	 * @throws InstructionExecutionException
	 */
	void execute()
            throws InstructionExecutionException;
}
