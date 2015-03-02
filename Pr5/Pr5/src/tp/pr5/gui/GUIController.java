package tp.pr5.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import tp.pr5.Interpreter;
import tp.pr5.NavigationObserver;
import tp.pr5.RobotEngine;
import tp.pr5.RobotEngineObserver;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.InventoryObserver;
/**
 * Se encarga de registrar todos los eventos que se producen por el usuario al interaccionar con la interfaz gráfica (swing). Estos eventos serán 
 * interpretados y se informara al modelo de los cambios que ha de realizar segín la nueva información de las vistas.
 * Añade en la lista de vistas del modelo las distintas vistas que se le van pasando de RobotEngineObserver, InventoryObserver y NavigationObserver.
 * Inicializa el atributo del modelo (robot) de la clase abstracta Contoller segun un modelo que recibe por parámetro en la constructora
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 *
 */
public class GUIController extends tp.pr5.Controller implements ActionListener, FocusListener, MouseListener{
	private String jTextField;
	private String turn = "right";
	private String item = "";
	
	/**
	 * Constructora de la clase GUIController que tiene como parametro un modelo al cual se inicializa el modelo del que hereda esta clase
	 * @param robot modelo al que se inicializa el robot
	 */
	public GUIController(RobotEngine robot){
		super(robot);
	}

	/**
	 * Añade a la lista de vistas del modelo una nueva vista de tipo RobotEngineObserver
	 * @param robOb 
	 */
	@Override
	public void registerEngineObserver(RobotEngineObserver robOb) {
		// TODO Auto-generated method stub
		this.robot.addEngineObserver(robOb);
	}

	/**
	 * Añade a la lista de vistas del modelo una nueva vista de tipo InventoryObserver
	 * @param invOb vista a añadir
	 */
	@Override
	public void registerItemContainerObserver( InventoryObserver invOb) {
		// TODO Auto-generated method stub
		this.robot.addItemContainerObserver(invOb);
	}

	/**
	 * Añade a la lista de vistas del modelo una nueva vista de tipo NavigationObserver
	 * @param consola vista a añadir
	 */
	@Override
	public void registerRobotObserver( NavigationObserver navOb) {
		// TODO Auto-generated method stub
		this.robot.addNavigationObserver(navOb);
	}

	/**
	 * Método que se encarga de llamar a la función que avisa a las vistas del inicio del modelo. 
	 */
	@Override
	public void startController() {
		// TODO Auto-generated method stub
		this.robot.requestStart();
	}
	
	/**
	 * Método que dada una componente, sgún el nombre de esta, interpreta la acción que debe realizar el modelo y la convierte a instrucción 
	 * enviándosela al modelo que se encargará de interpretar que hacer con esa instrucción
	 * @param fuente que ha generado el evento
	 * @throws WrongInstructionFormatException
	 */
	public void cambiarModelo( Component fuente) throws WrongInstructionFormatException{
		 if (!robot.isOver()){
			if ( fuente.getName().equals("jButtonMove"))
				this.llamaInterpreter("MOVE");
			else if ( fuente.getName().equals("jButtonPick")){
				if (jTextField != null) this.llamaInterpreter("PICK "+ jTextField);
			}
			else if ( fuente.getName().equals("jTextItem")){
				JTextField item = (JTextField) fuente;
				jTextField = (String) item.getText();
			}
			else if ( fuente.getName().equals("jButtonOperate")){
				if (item != null) this.llamaInterpreter("OPERATE "+ item);
			}
			else if  ( fuente.getName().equals("jButtonDrop")){
				if (item != null) this.llamaInterpreter("DROP "+ item);
			}
			else if ( fuente.getName().equals("jButtonTurn"))
				this.llamaInterpreter("TURN "+ turn);
			else if (fuente.getName().equals("comboTurn")){
				@SuppressWarnings("rawtypes")
				JComboBox gira = (JComboBox) fuente;
				this.turn =  (String)gira.getSelectedItem();
			}
			else if (fuente.getName().equals("jButtonQuit"))
				this.llamaInterpreter("QUIT");	
			else if ( fuente.getName().equals("placeCell")){
				PlaceCell place = (PlaceCell) fuente;
				placeButton (place);
			}
			else if ( fuente.getName().endsWith("jTableItems")){
				JTable tabla= (JTable) fuente;
				this.item = (String) tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn());
			}
		 }	
	}
	
	/**
	 * Método auxiliar que dado un string lo parsea y lo convierte a instrucción que el robot pueda interpretar. Si el string
	 * no coincide con ninguna instrucción del robot este método lanza una excepcion de tipo WrongInstructionFormatException
	 * @param instruc string a parsear
	 * @throws WrongInstructionFormatException
	 */
	public void llamaInterpreter ( String instruc) throws WrongInstructionFormatException{
		Instruction instruction;
		instruction = Interpreter.generateInstruction(instruc);
		this.robot.communicateRobot(instruction);
	}
	
	/**
	 * Método auxiliar que se encarga de que, al pulsar una celda de la matriz de celdas del mapa del robot, muestre la descripción de ese lugar.
	 * En caso de que no haya un lugar en esa celda salta una ventana con un mensaje de error indicandolo
	 * @param place celda de la que se quiere mostrar la descripción
	 */
	public void placeButton ( PlaceCell place){
		if (place.isVisitado()){
			robot.notifyObservers(place.getPlace());
		}
		else{
			Iterator <RobotEngineObserver> robOb = this.robot.iterator();
			while ( robOb.hasNext()){
				robOb.next().raiseError("There is no place");
			}
		}
	}
	/**
	 * Método para tratar los eventos de forma genérica
	 * Se encarga tanto de solicitar la modificación al modelo como de informar a la vista
	 * @param event el evento a tratar
	 */
	private void trataEventoGenerico(EventObject event){
		Component fuente = (Component) event.getSource(); // el que generó el evento
		try {
			cambiarModelo(fuente);
		} catch (WrongInstructionFormatException e) {
			// TODO Auto-generated catch block
		
		}
	}	

	/**
	 * Se tratan los eventos del tipo FocusEvent informando cuando se gana el foco a la vista y al modelo.
	 * @param ar0 evento generado
	 */
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		trataEventoGenerico(arg0);
	}

	/**
	 * Se tratan los eventos del tipo FocusEvent informando cuando se pierde el foco a la vista y al modelo.
	 * @param ar0 evento generado
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		trataEventoGenerico(arg0);
	}

	/**
	 * Se tratan los eventos del tipo ActionEvent informando cuando es necesario a la vista y al modelo.
	 * @param ar0 evento generado
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		trataEventoGenerico(arg0);
	}

	/**
	 * Se tratan los eventos del tipo MouseEvent informando cuando es necesario a la vista y al modelo.
	 * @param ar0 evento generado
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		trataEventoGenerico(arg0);
	}

	/**
	 * Se tratan los eventos del tipo MouseEvent informando cuando es necesario a la vista y al modelo.
	 * No necesario para estas ejecuciones
	 * @param ar0 evento generado
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Se tratan los eventos del tipo MouseEvent informando cuando es necesario a la vista y al modelo.
	 * No necesario para estas ejecuciones
	 * @param ar0 evento generado
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Se tratan los eventos del tipo MouseEvent informando cuando es necesario a la vista y al modelo.
	 * No necesario para estas ejecuciones
	 * @param ar0 evento generado
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Se tratan los eventos del tipo MouseEvent informando cuando es necesario a la vista y al modelo.
	 * No necesario para estas ejecuciones
	 * @param ar0 evento generado
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
