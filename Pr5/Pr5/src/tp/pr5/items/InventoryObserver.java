package tp.pr5.items;
	/**
	 * Interfaz de observadores que deseen ser notificados sobre los acontecimientos ocurrido en el inventario del robot.
	 * El contenedor notificar� a los observadores todos los cambios que se realice en el inventario(cuando el robot coge o deja un item)y cuando un elemento se elimina del contenedor porque ya se ha gastado.
	 * Este tambi�n nficar� cuando el usuario solicita escanear un elemento.
	 * @author Nerea Ram�rez y Carmen Acosta
	 *
	 */
public interface InventoryObserver {
	/**
	 * Notifica que el contenedor ha cambiado
	 * @param inventory 
	 */
	void inventoryChange(java.util.List<Item> inventory);
	/**
	 * Notifica que el usuario solicita una instrucci�n SCAN en el inventario
	 * @param inventoryDescription
	 */
	void inventoryScanned(java.lang.String inventoryDescription);
	/**
	 * Notifica que el usuario desea escanear un elemento asignado en el inventario
	 * @param description
	 */
	void itemScanned(java.lang.String description);
	/**
	 * Notifica que un elemento est� vac�o y se retira del inventario. Despu�s se invocar� al m�todo inventoryChange .
	 * @param itemName
	 */
	void itemEmpty(java.lang.String itemName);
}
