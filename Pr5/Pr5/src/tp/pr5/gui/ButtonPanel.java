	package tp.pr5.gui;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * M�todo que se encarga de meter en un panel los botones de las distintas instrucciones asi como el JComboBox
 * y el JTextField para el turn (LEFT/RIGHT), y el nombre del item respectivamente.
 */

/**
 * Método que se encarga de meter en un panel todos los JButton correspondientes a las instrucciones (PICK, OPERATE, TURN, MOVE, QUIT, DROP)
 *  del robot, así como un JComboBox que indica si se gira a izquierda o derecha y un JTextField en el que se escribirán los 
 *  objetos que se deseen coger en determinado lugar. Todos estos elementos generan eventos de tipo ActionListener o FocusListener
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 *
 */
@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
	private JButton jButtonDrop;
	private JButton jButtonMove;
	private JButton jButtonPick;
	private JButton jButtonQuit;
	private JButton jButtonOperate;
	private JButton jButtonTurn;
	private JTextField jTextItem;
	private JComboBox <String> comboTurn;
	
	/**
	 * Constructora por defecto que se encarga de llamar a la inicialización del panel
	 */
	public ButtonPanel (){
		super ();
		iniPanel();
	}

	/**
	 * Método que se encarga de la correcta colocacion de los elementos del panel, así como de inicializarlos y darles nombre
	 * por el que ser reconocidos a la hora de generar eventos. 
	 */
	public void iniPanel (){
		//Inicializo los botones
		jButtonDrop = new JButton ("DROP");
		jButtonMove= new JButton ("MOVE");
		jButtonPick = new JButton ("PICK");
		jButtonQuit = new JButton ("QUIT");
		jButtonOperate = new JButton ("OPERATE");
		jButtonTurn = new JButton ("TURN");
		String[] comboString = new String [] {"RIGHT", "LEFT"};;
		
		this.jTextItem = new JTextField();
		this.comboTurn = new JComboBox<String>(comboString);
    	
    	this.setBorder(new TitledBorder("Instructions"));
    	
        this.setName("Form Text");
        
		//Damos nombre a los elementos
		jButtonDrop.setName("jButtonDrop");
		jButtonOperate.setName("jButtonOperate");
		jButtonQuit.setName("jButtonQuit");
		jButtonPick.setName("jButtonPick");
		jButtonTurn.setName("jButtonTurn");
		jButtonMove.setName("jButtonMove");
		comboTurn.setName("comboTurn");
		this.jTextItem.setName("jTextItem");
		
		//Hacemos que todas los elementos del panel ocupen lo mismo distribuidos en 4 filas y 2 columnas
        LayoutManager thisLayout = new GridLayout(4,2);
        this.setLayout(thisLayout);
		
        //Añado todos los elementos al panel
		this.add(jButtonMove);
		this.add(jButtonQuit);
		this.add(jButtonTurn);
		this.add(comboTurn);
		this.add(jButtonPick);
		this.add(jTextItem);
		this.add(jButtonDrop);
		this.add(jButtonOperate);

	}
	
	/**
	 * Metodo que se encarga de fijar el controlador del panel
	 * @param controlador tiene que escuchar AtionListener y FocusListener
	 */
	public void fijarControlador(EventListener controlador){
		this.jButtonDrop.addActionListener((ActionListener) controlador);
		this.jButtonMove.addActionListener((ActionListener) controlador);
		this.jButtonOperate.addActionListener((ActionListener) controlador);
		this.jButtonPick.addActionListener((ActionListener) controlador);
		this.jButtonQuit.addActionListener ((ActionListener) controlador);
		this.jButtonTurn.addActionListener((ActionListener) controlador);
		this.comboTurn.addActionListener((ActionListener) controlador);
		this.jTextItem.addFocusListener((FocusListener) controlador);
		this.jTextItem.addActionListener((ActionListener) controlador);
	}
}
