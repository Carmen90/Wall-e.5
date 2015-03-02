package tp.pr5.instructions;

import java.util.Iterator;

import tp.pr5.NavigationModule;
import tp.pr5.NavigationObserver;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;
/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * Esta clase se encarga del desplazamiento del robot. Configura el contexto del robot asi como de su panel de navegacion
 * Parsea una cadena para saber si esta hace referencia a una instrucciï¿½n de tipo move, sino lanza una excepcion del tipo
 * WrongInstructionFormatException. Si la cadena es correcta ( "move" o "mover") devuelve una MoveInstruction. Si alguno de los atributos
 * es nulo y se intenta acceder a el salta una excepcion del tipo NullPointerException. Tiene dos atributos, navega, que contiene la 
 * informacion del entorno del robot y robot, que contiene el estado del robot. El metodo getHelp te indica los parametros que 
 * se aceptan como validos para esta instruccion.
 *
 */
public class MoveInstruction implements Instruction{
	private NavigationModule navega;
	private RobotEngine robot;
	/**
	 * Constructora que crea el RobotEngine y el NavigationModule.
	 */
	public MoveInstruction (){
		this.navega = new NavigationModule();
		this.robot = new RobotEngine();
	}
	/**
	 * Analiza la cadena que tiene que devolver una instancia de DropInstruction, si no es así lanza una excepción de
	 * WrongInstructionFormatException() 
	 */
	@Override
	public Instruction parse(String cad) 
			throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		
		String [] cadena = cad.split(" ");/*Divide la cadena en espacios
		y guarda cada fragmento en cada posiciï¿½n del array*/
		if ( !cadena [0].equalsIgnoreCase("MOVE") && !cadena [0].equalsIgnoreCase("MOVER") || cadena.length > 1){
			throw new WrongInstructionFormatException();
		}else {
			return this;
		}
	}
	/**
	 * Devuelve la sintáxis de la instrucción.
	 */
	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "MOVE|MOVER";
	}
	/**
	 * Establece el contexto de ejecución. El método recibe todo el robot(robot, navegation y el contenedor del robot) a pesar de que la aplicación de execute () no lo exija.
	 */
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.navega = navigation;
		this.robot = engine;
	}
	/**
	 * Se mueve de la posición actual hasta el siguiente lugar en la 
	 * dirección actual. Una calle abierta debe existir entre los dos 
	 * lugares que se desean mover para que lo pueda hacer.
	 * Por esta acción se le resta al combustible del robot 5.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		this.navega.move();
		this.robot.saySomething("WALLÂ·E says: Moving in direction " + this.navega.getCurrentHeading());
		Iterator <NavigationObserver> navOb = this.navega.iterator();
		while (navOb.hasNext()){
			NavigationObserver  aux = navOb.next(); 	
		//	aux.placeScanned(this.navega.getCurrentPlace());
			aux.robotArrivesAtPlace(this.navega.getCurrentHeading(), this.navega.getCurrentPlace());
		}
		this.robot.addFuel(-5);
	}

}
