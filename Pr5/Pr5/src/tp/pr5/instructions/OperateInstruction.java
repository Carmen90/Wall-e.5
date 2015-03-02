package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * Esta clase se encarga de utilizar un objeto del robot. Configura el contexto del robot actualizando la informacion sobre este
 * asi como de su panel de navegacion y su inventario con los items que posee.
 * Parsea una cadena para saber si esta hace referencia a una instrucci�n de tipo Operate, sino lanza una excepci�n del tipo
 * WrongInstructionFormatException. Si la cadena es correcta ( "operate <id>" u "operar <id>") devuelve una OperateInstruction. 
 * Si alguno de los atributos es nulo y se intenta acceder a �l salta una excepcion del tipo NullPointerException. 
 * Tiene cuatro atributos, navega, que contiene la informacion del entorno del robot, robot, que contiene el estado del robot,
 * container que contiene los Items que contiene el robot en su invenatrio e id, que es la id del item a operar.
 * El m�todo getHelp te indica los par�metros que se aceptan como validos para esta instrucci�n.
 *
 */
public class OperateInstruction implements Instruction{
	private ItemContainer container;
	private RobotEngine robot;
	private NavigationModule navega;
	private String id;
	/** 
	 * Constructora por defecto que inicializa container, navega y robot por defecto y el id a vacio
	 */
	public OperateInstruction (){
		this.container = new ItemContainer ();
		this.id = " ";
		this.navega = new NavigationModule ();
		this.robot = new RobotEngine ();
	}
	/**
	 * Analiza la cadena que tiene que devolver una instancia de DropInstruction, si no es as� lanza una excepci�n de
	 * WrongInstructionFormatException() 
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String [] cadena = cad.split(" ");/*Divide la cadena en espacios
		y guarda cada fragmento en cada posiciï¿½n del array*/
		
		if ( cadena.length != 2){
			throw new  WrongInstructionFormatException();
		}
		else if ( !cadena[0].equalsIgnoreCase("OPERATE") && !cadena[0].equalsIgnoreCase("OPERAR")){
			throw new  WrongInstructionFormatException();
		}
		else
		{
			this.id = cadena[1];
			return this;
		}
	}
	/**
	 * Devuelve la sint�xis correcta de lainstrucci�n.
	 */
	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "OPERATE|OPERAR <ID>";
	}
	/**
	 * Establece el contexto de ejecuci�n. El m�todo recibe todo el robot(robot, navegation y el contenedor del robot) a pesar de que la aplicaci�n de execute () no lo exija.
	 */
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.container = robotContainer;
		this.robot = engine;
		this.navega = navigation;
	}
	/**
	 * El robot utiliza el elemento que se pide.
	 * Si existe el elemento en el contenedor del robot y se puede usar, entonces se usa, 
	 * sino se lanza una excepci�n de  InstructionExecutionException ().
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		Item it = null;
		
		if ( this.container.containsItem(this.id) ){
			it = this.container.getItem(this.id);
			if (it.use(this.robot, this.navega)){
				this.container.useItem(it);	
			}
			else{
				throw new InstructionExecutionException ();
			}
		}
		else{
			throw new InstructionExecutionException ("You do not have any " + this.id + ".");
		}
	}

}
