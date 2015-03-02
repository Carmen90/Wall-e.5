package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.Place;
import tp.pr5.PlaceInfo;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * Esta clase está a cargo del panel que muestra la información del encabezado del robot y la ciudad que está atravesando. 
 * Contiene la cuadrícula que representa a la ciudad en la interfaz Swing, un área de texto para mostrar las descripciones de lugar, 
 * y una etiqueta con un icono que representa el título robot. La cuadrícula 11x11 contiene objetos PlaceCell y el primer lugar comienza
 * a (5,5). Este panel se actualizará los lugares visitados cuando el robot se mueve de un lugar a otro. Además se mostrará la 
 * descripción lugar en un área de texto si el usuario hace clic en un lugar visitado.
 *
 */
@SuppressWarnings("serial")
public class NavigationPanel extends javax.swing.JPanel implements NavigationObserver {
	private final int F = 11;
	private final int C = 11;
	private final int FILINI = 5;
	private final int COLINI = 5;
	
	private JLabel walle;
	private JTextArea texto;
	private PlaceCell [][] places ;
	private JPanel placesPanel;
	private JPanel scrollPanel;
	private JScrollPane scroll;
	private int filas = FILINI;
	private int colum = COLINI;
	private PlaceInfo lugar;
	
	/**
	 * Constructora por defecto que se encarga inicializar los atributos y de llamar a la función que crea el panel
	 */
	public NavigationPanel(){
		this.places = new PlaceCell[F][C];
		this.placesPanel = new JPanel();
		this.filas = FILINI;
		this.colum = COLINI;
		this.lugar = new Place();
		this.initNavigPanel();
	}
	
	/**
	 * Método encargado de crear el panel
	 */
	public void initNavigPanel(){
		this.walle = new JLabel();
		
		this.walle.setName( "walle");
		this.walle.setIcon(new ImageIcon (getClass().getResource("images/walleNorth.png")));
		
		this.setLayout(new BorderLayout());
		
		//Creo el panel de ciudad y el de log
		panelScroll();
		this.panelPlaces();
		
		//Añadimos los elementos al panel
		this.add(this.walle,BorderLayout.WEST);
		this.add(this.placesPanel,BorderLayout.CENTER);
		this.add(scrollPanel,BorderLayout.SOUTH);

	}
	
	/**
	 * Inicializa las celdas de la matriz del mapa de la cuidad
	 */
	public void panelPlaces (){
		//Hago un gridLayout de 11x11 para el mapa
        LayoutManager thisLayout = new GridLayout(11,11);
        this.placesPanel.setLayout(thisLayout);

        //Inicializo los botones de los lugares y los meto en su respectivo panel
		for ( int i = 0; i < F; i++){
			for ( int j = 0; j < C; j++){
					this.places[i][j] = new PlaceCell();
					this.places [i][j].setName("placeCell");
					this.placesPanel.add (places[i][j]);
			}
		}
        //Fijo las dimensiones que quiero que tenga y le doy t�tulo al panel
        this.setPreferredSize(new java.awt.Dimension (900, 400));
        this.placesPanel.setBorder( new TitledBorder ("City Map"));
        

	}
	
	/**
	 * Método que se encraga de crear el panel en el que se encuentra el Log
	 * El log muestra las descripciones de los lugares por los que pasa el robot
	 * 
	 */
	public void panelScroll(){
		//Inicializaciones de los atributos usados en este panel
		this.scrollPanel = new JPanel ();
		this.scroll = new JScrollPane();
    	this.scrollPanel = new JPanel();
    	
    	
    	this.scrollPanel.setBorder(new TitledBorder("Log"));
        this.scrollPanel.setName("Navigation");
        
        //Editamos la parte gr�fica del log
		this.texto = new JTextArea(5, 30);
		this.texto.setEditable(false);
		this.texto.setName("texto");
		this.scroll = new JScrollPane(texto);
		
		//Hacemos que siempre sean visibles los scroll
		this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
		this.scrollPanel.add(this.scroll);
		
        LayoutManager thisLayout = new GridLayout();
        this.scrollPanel.setLayout(thisLayout);
		
	}
	
	/**
	 * Se encarga de actualizar la etiqueta de Walle cuando este cambia de dirección.
	 * @param newHeading nueva direccion a la que mira walle
	 */
	@Override
	public void headingChanged(Direction newHeading) {
		// TODO Auto-generated method stub
		if (newHeading == Direction.NORTH){
			this.walle.setIcon(new ImageIcon (getClass().getResource("images/walleNorth.png")));
		}
		else if (newHeading == Direction.SOUTH){
			this.walle.setIcon(new ImageIcon (getClass().getResource("images/walleSouth.png")));
		}
		else if (newHeading == Direction.EAST){
			this.walle.setIcon(new ImageIcon (getClass().getResource("images/walleEast.png")));
		}
		else if (newHeading == Direction.WEST){
			this.walle.setIcon(new ImageIcon (getClass().getResource("images/walleWest.png")));
		} 
		else this.walle.setName("walle");
		

	}

	/**
	 * Método que crea el lugar inicial en el panel. Este se añade en la posición fijada como inicial (5,5). Actualiza la celda
	 * con el nombre del lugar proporcionado y la marca como visitada y actual. Acto seguido modifica el color de la celda.
	 * Tambien se encarga de mostrar una descripción del lugar en el log
	 * @param initialPlace lugar inicial del que se muestra la información
	 * @param heading dirección a la que mira el robot, no usada
	 */
	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		// TODO Auto-generated method stub
		this.lugar = (Place) initialPlace;
		
		this.places[5][5].setText(this.lugar.getName());
		this.placeScanned(initialPlace);
		
		this.places[FILINI][COLINI].setPlace(lugar);
		this.places[FILINI][COLINI].setText((String)this.lugar.getName());
		this.places[FILINI][COLINI].setActual(true);
		this.places[FILINI][COLINI].setVisitado(true);
		this.places[FILINI][COLINI].color();
	}

	/**
	 * Método que se encarga de modificar las celdas cuando el robot se mueve. La celda en la que estaba el robot pasa a mostrarse gris y deja de 
	 * ser celda actual, y la nueva celda se actualiza en verde. Además se cambian las coordenadas de las filas y columnas actuales en función a 
	 * donde está mirando el robot
	 * @param heading dirección en la que mira y se mueve el robot
	 * @param place informacion del lugar al que se mueve
	 */
	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		// TODO Auto-generated method stub
		this.places[filas][colum].setActual(false);
		this.places[filas][colum].color();
		
		if (heading == Direction.NORTH){
			this.filas -= 1;
		}
		else if (heading == Direction.SOUTH){
			this.filas += 1;
		}
		else if (heading == Direction.EAST){
			this.colum += 1;
		}
		else if (heading == Direction.WEST){
			this.colum -= 1;
		} 

		this.places[filas][colum].setPlace(place);
		this.places[filas][colum].setActual(true);
		this.places[filas][colum].setVisitado(true);
		this.places[filas][colum].setText(place.getName());
		this.places[filas][colum].color();
		placeScanned (place);
	}

	/**
	 * Muestra la descripción del lugar en el que se encuentra el robot
	 * @param placeDescription informacion del lugar
	 */
	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		this.texto.setText( placeDescription.getDescription());
	}

	/**
	 * Muestra la descripción del lugar en el que se encuentra el robot
	 * @param placeDescription informacion del lugar
	 */
	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		this.placeScanned(placeDescription);
	}
	
	/**
	 * Se encarga de añadir eventos de tipo ActionListener en las cada celda de la matriz del mapa para poder acceder a su informacion 
	 * en cualquier momento
	 * @param controlador 
	 */
	public void fijarControlador(EventListener controlador){
		for ( int i = 0; i < F; i++){
			for ( int j = 0; j < C; j++){
				this.places[i][j].addActionListener((ActionListener) controlador);
			}
		}
	}


}
