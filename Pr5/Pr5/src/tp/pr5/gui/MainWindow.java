package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import tp.pr5.RobotEngineObserver;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * 
 * Esta clase tiene los siguientes elementos:
 * Un panel de acciones con button que implementan MOVE, TURN, OPERATE, PICK y DROP. 
 * Además tiene un combo box con las posibles rotation de la acción TURN y un textField para escribir el nombre dle item que queremos
 *  que coja del lugar actual.
 * Un RobotPanel que muestra la información del robot y su inventario ( una tabla con los nombres de los items y sus descripciones)
 * El usuario podrá seleccionar un item del inventario para ejecutar DROP u OPERATE con el.
 * El icono de walle será actualizado cada vez que se ejecute la instruccion TURN. Los lugares visibles serán actualizados cuando el robot
 * ejecute la instrucción MOVE.
 * Ademas presenta un controlador que será el encargado de interpretar todos los eventos generados por estas vistas
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame implements RobotEngineObserver {
	private GUIController control;
	
	private RobotPanel robPanel;
	protected NavigationPanel navPanel;
	private InfoPanel infPanel;
	
	private Container panel;
	private JSplitPane splitPane;
	private JMenu jMenu;
	private JMenuBar jMenuBar;
	private JMenuItem jMenuQuit;
	private ButtonPanel buttonPanel;

	/**
	 * Constructora con parametros que se encarga de inicializar el controlador segun el un controlador dado, así como de
	 * inicializar los paneles, dijar los controladores en los paneles y añadir los observadores al controlador
	 * @param gameController
	 */
	public MainWindow(GUIController gameController){
		this.control = gameController;
		
		this.navPanel = new NavigationPanel();
		this.robPanel = new RobotPanel();
		this.infPanel = new InfoPanel();
		this.navPanel.fijarControlador(control);
		this.robPanel.fijarControlador(control);
		
		this.initPanel();
		this.buttonPanel.fijarControlador(control);
		this.jMenuQuit.addActionListener(control);
		this.registrarObservadores();
	}
	
	/**
	 * Se encarga de registrar todos los observadores de esta vista en el controlador dado
	 */
	public void registrarObservadores(){
		this.control.registerEngineObserver(this);
		this.control.registerEngineObserver(robPanel);
		this.control.registerEngineObserver(infPanel);
		this.control.registerItemContainerObserver(robPanel);
		this.control.registerItemContainerObserver(infPanel);
		this.control.registerRobotObserver(infPanel);
		this.control.registerRobotObserver(navPanel);
	}
	
	/**
	 * El splitPane se encarga de meter en un mismo panel con una separación movil el panel de 
	 * las instrucciones y el panel de la inromación del robot
	 */
	public void superPanel (){
		//Create a split pane with the two scroll panes in it.
	    this.buttonPanel = new ButtonPanel ();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.buttonPanel, this.robPanel);
		splitPane.setDividerLocation(275);
	}
	
	/**
	 * Inicializamos todo el panel de la MainWindow
	 */
	public void initPanel(){
	    this.setSize(3*320, 340);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.panel = this.getContentPane();
	    this.jMenu = new JMenu("File");
	    this.jMenuBar = new JMenuBar ();

	   
	    this.superPanel();
	    
	    this.jMenuQuit = new JMenuItem ("Quit");
	    this.jMenu.add( this.jMenuQuit);
	    
	    this.jMenuQuit.setName("jButtonQuit");
	    
	    this.setJMenuBar(jMenuBar);
	    this.jMenuBar.add(jMenu);
	    
	    this.panel.add(splitPane, BorderLayout.NORTH);
	    this.panel.add(this.navPanel, BorderLayout.CENTER);
	    this.panel.add(this.infPanel, BorderLayout.SOUTH);
	  
	    this.pack();
		}
	 
	/**
	 * Método que se encarga de arrancar la vista de la interfaz
	 */
	public void arranca(){
       EventQueue.invokeLater(new Runnable(){
       	public void run() {
       		setVisible(true);
       	}
       });		
	}
	
	/**
	 * Método que se encarga de mostrar los mensajes de error en una ventana emergente
	 * @param msg mensaje a mostrar
	 */
	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog (null,msg);
	}

	/**
	 * Método no usado en esta vista, mostraría los mensajes de ayuda
	 * @param help mensaje a mostrar
	 */
	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Avisa al panel encargado de las operaciones del robot de que la jugada se ha terminado
	 * @param atShip indica si se ha llegado a la nave o no
	 */
	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub
		this.robPanel.engineOff(atShip);
	}

	/**
	 * Método encargado de mostrar una ventana emergente cuando se requiera quitar la aplicación para confirmar si realmente se quiere salir
	 * de la aplicacion. Si se dice que no se sigue con la ejecución normalmente, si se dice que si se sale de la aplicación
	 */
	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		 if (JOptionPane.showConfirmDialog(null, "Would you like to quit the game?") == 0){
				System.exit(0);				
		}
	}

	/**
	 * Método que se encarga de avisar al panel encargado del robot de que el fuel o el recycled material han sido modificados
	 * y muestra los nuevos valores en su respectivo lugar
	 * @param fuel valos actual del fuel
	 * @param recycledMaterial valor actual del material reciclado
	 */
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// TODO Auto-generated method stub
		this.robPanel.robotUpdate(fuel, recycledMaterial);
	}

	/**
	 * Método no usado en esta parte
	 */
	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}
	
}
