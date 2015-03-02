package tp.pr5.gui;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * 
 * Esta clase muestra información sobre el robot y su inventario
 * Tiene etiquetas con la cantidad de fuel y de recicled material y una tabla con el inventario del robot
 * Cada fila muestra informacion sobre un item contenido en el inventario
 *
 */
@SuppressWarnings("serial")
public class RobotPanel extends javax.swing.JPanel implements RobotEngineObserver, InventoryObserver{
	private JLabel jLabelFuel;
	private JLabel jLabelRecicled;
	private JScrollPane panel;
	private JTable jTableItems;
	private JPanel jLabelPanel;
	
	/**
	 * Constructora por defecto que inicializa las JLabel del panel y llama al método encargado de crearlo
	 */
	public RobotPanel(){
		this.jLabelFuel = new JLabel ();
		this.jLabelRecicled = new JLabel ();
		this.jLabelPanel = new JPanel ();
		initRobotPanel();
	}
	
	/**
	 * Inicializa un panel con las etiquetas de fuel y recycled material
	 */
	public void labelPanel (){
		
		this.jLabelPanel.add(this.jLabelFuel);
		this.jLabelPanel.add(this.jLabelRecicled);
		
		this.jLabelPanel.setLayout(new FlowLayout());
	}
	
	/**
	 * Método que se encarga de meter todos los componentes en el panel del robot
	 */
	public void initRobotPanel(){

		String []columnas = {"id", "Descripcion"}; 
		Object [][] data = null;
		this.jTableItems = new JTable (new DefaultTableModel (data, columnas));

		this.jTableItems.setName("jTableItems");
		
    	this.setBorder(new TitledBorder("Robot info"));
    	
        this.setName("Form Text");
        this.setPreferredSize(new java.awt.Dimension (500, 100));
	
        this.jLabelFuel.setText("Fuel: ");
		this.jLabelFuel.setHorizontalAlignment(JLabel.RIGHT);
		this.jLabelFuel.setName("jLabelFuel");
		
		this.jLabelRecicled.setText("Recycled: ");
		this.jLabelRecicled.setName("jLabelRecicled");
		
		this.panel = new JScrollPane (this.jTableItems);
		labelPanel();
		
		this.add(this.jLabelPanel);
		this.add(this.panel);
		
		LayoutManager thisLayout = new BoxLayout(this,1);
        this.setLayout(thisLayout);
	}
	
	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Método que se encarga de mostrar un mensaje cuando se finaliza el juego ya sea por falta de fuel o por haber llegado a la nave.
	 * Muestra una ventana nueva que te informa de que se ha acabado la jugada ( con un mensaje u otro dependiendo del motivo)
	 * @param atShip indica si se ha llegado a la nave
	 */
	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub
		if (atShip){
			JOptionPane.showMessageDialog (null,"WALL·E says: I am at my spaceship. Bye bye");
		}
		else{
			JOptionPane.showMessageDialog (null,"WALL·E says: I run out of fuel. I cannot move. Shutting down...");
		}
		System.exit(0);
	}

	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub

	}

	/**
	 * Modifica las etiquetas de fuel y recycledMaterial segun los nuevos valores que tengan 
	 * @param fuel nuevo valor de fuel a mostrar
	 * @param recycledMaterial nuevo valor de material reciclado a mostrar
	 */
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// TODO Auto-generated method stub
		this.jLabelFuel.setText ("Fuel: " + fuel);
		this.jLabelRecicled.setText("Recycled: "+ recycledMaterial );
	}

	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Muestra en la trabla de items todos los items que tiene el robot y sus descripciones
	 * @param inventory lista de items que tiene el robot
	 */
	@Override
	public void inventoryChange(List<Item> inventory) {
		// TODO Auto-generated method stub
	
		String [][] nueva = null;
		DefaultTableModel modelo = new DefaultTableModel();
		String []columnas = {"id", "Descripcion"}; 
		nueva = new String[inventory.size()][2];
		for ( int i = 0; i < inventory.size(); i++){
			nueva [i][0] =  inventory.get(i).getId();
			nueva [i][1] = inventory.get(i).getDescription();
		}
		modelo.addRow(nueva);
		this.jTableItems.setModel(new DefaultTableModel (nueva, columnas));
		this.jTableItems.setName("jTableItems");
	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Metodo que se encarga de fijar el controlador del panel, utilizado para escuchar eventos al seleccionar un elemento de la tabla
	 * @param controlador tiene que escuchar MouseListener
	 */
	public void fijarControlador(EventListener controlador){
		this.jTableItems.addMouseListener((MouseListener) controlador);
	}


}
