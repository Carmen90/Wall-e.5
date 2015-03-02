package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * Esta clase se encarga de coger un item de un lugar y guardarlo en el inventario del robot. 
 * Configura el contexto del robot actualizando la informacion sobre este y sobre el entorno (NavigationModule)
 * Parsea una cadena para saber si esta hace referencia a una instrucciï¿½n de tipo Pick, sino lanza una excepciï¿½n del tipo
 * WrongInstructionFormatException. Si la cadena es correcta ( "pick <id>" o "coger <id>") devuelve una PickInstruction. 
 * Si alguno de los atributos es nulo y se intenta acceder a ï¿½l salta una excepcion del tipo NullPointerException. 
 * Tiene tres atributos, navega, que contiene la informacion del entorno del robot, robot,
 * container que contiene los Items que contiene el robot en su invenatrio e id, que es la id del item a coger.
 * El metodo getHelp te indica los parï¿½metros que se aceptan como validos para esta instrucciï¿½n.
 *
 */
public class PickInstruction implements Instruction{
	private NavigationModule navega;
	private RobotEngine robot;
	private ItemContainer container;
	private String id;
/**
 * Constructora que crea un nuevo NavigationModule, RobotEngine e ItemContainer.
 */
	public PickInstruction (){
		this.navega = new NavigationModule ();
		this.robot = new RobotEngine();
		this.container = new ItemContainer();
	}
	/**
	 * Analiza la cadena que tiene que devolver una instancia de DropInstruction, si no es así lanza una excepción de
	 * WrongInstructionFormatException() 
	 */
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		
		String []cadena = cad.split(" ");
		if ( cadena.length != 2){
			throw new WrongInstructionFormatException ();
		} 
		else if ( !cadena [0].equalsIgnoreCase("PICK") && !cadena[0].equalsIgnoreCase("COGER")){
			throw new WrongInstructionFormatException ();
		}
		else {
			this.id = cadena [1];
			return this;
		}
	}
	/**
	 * Devuelve la sintáxis de la instrucción.
	 */
	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "PICK|COGER <id>";
	}
	/**
	 * Configura el contexto de esta instrucción el RobotEngine, el Navigation y el ItemCOntainer lo actualiza.
	 */
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.robot = engine;
		this.navega = navigation;
		this.container = robotContainer;
	}
	/**
	 * Este metodo mira si existe el item en el lugar donde se encuentra
	 * el robot(si no existe lanza una excepción). Si está y no lo contiene el inventario del robot, coge el item.
	 * Si el item estaá vacio devuelve una excepción, pero sino lo guarda en el inventario del robot y notifica al robot que tiene que decir un mensaje.
	 * Si el inventario del lugar no tiene el item también se lanza una excepción.
	 */
	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		Item item = null;
		 
		if (!this.navega.getCurrentPlace().existItem(this.id)){
			throw new InstructionExecutionException ( "There's no " + this.id + " in this place.");
		}
		if ( this.container.containsItem(id)){
			throw new InstructionExecutionException ("You already have " + this.id);
		}
		else{
			item = this.navega.pickItemFromCurrentPlace(id);
			if ( item == null){
				throw new InstructionExecutionException ();
			}
			else
			{
				this.robot.saySomething("WALLÂ·E says: I am happy! Now I have " + this.id);
				this.container.addItem(item);
			}
		}
	}
}
