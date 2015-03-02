package tp.pr5.gui;

import java.util.List;

import javax.swing.JLabel;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

/**
 * Clase que se encarga de mostrar en la parte inferior de la Interfaz Grafica algunos mensajes del modelo como la actualización de los 
 * atributos del robot, si se ha cogido correctamente un item de un lugar, si se ha dejado correctamente un item en un lugar...
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 *
 */
@SuppressWarnings("serial")
public class InfoPanel extends javax.swing.JPanel implements RobotEngineObserver, NavigationObserver, InventoryObserver{
	private JLabel jLabelInfo;
	
	/**
	 * Constructora por defecto que inicializa el JLabel en el cual se mostraran los mensajes de actualizaciones
	 */
	public InfoPanel (){
		this.jLabelInfo = new JLabel();
		this.add(this.jLabelInfo);
	}
	
	@Override
	public void headingChanged(Direction newHeading) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Muestra los mensajes de error que se producen en la ejecucion de la GUI
	 * @param msg mensaje de error a mostrar
	 */
	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		this.jLabelInfo.setText(msg);
	}

	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Muestra en la parte inferior de la GUI cuando los atributos del robot han sido modificados
	 * @param fuel fuel del que dispone en ese momento el robot
	 * @param recycledMaterial material reciclado que posee en ese momento el robot
	 */
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// TODO Auto-generated method stub
		this.jLabelInfo.setText("Robot attributes has been updated: (" + fuel + "," + recycledMaterial + ")");
	}

	/**
	 * Muestra los mensajes que tiene que trasmitir el robot en determinados momentos
	 * @param message menaje a mostrar
	 */
	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		this.jLabelInfo.setText(message);
	}

	@Override
	public void inventoryChange(List<Item> inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Muestra la descripcion de un determinado item
	 * @param description descripcion del item
	 */
	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub
		this.jLabelInfo.setText(description);
	}

	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub
		
	}

}
