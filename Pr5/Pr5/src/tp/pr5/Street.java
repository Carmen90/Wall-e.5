package tp.pr5;

import tp.pr5.items.CodeCard;

/**
 * Street lo que hace es conectar dos lugares A y B en una direcci�n.
 * La calle es de doble direcci�n, si la calle est� definida como Street(A.NORTH,B) significa que desde A se puede acceder a B con direccion NORTH pero B tambi�n puede acceder a A con direcci�n SOUTH.
 * Atributos de la clase:Tiene dos Place que son el lugar de origen y el de destino; una direcci�n; Un boolean que nos dir� si la calle est� abierta o cerrada; Un String code que hace referencia al c�digo de la tarjeta que abre/cierra la calle;y por �ltimo un String id.
 * Se encarga de abrir y cerrar la calle si se tiene la tarjeta con el c�digo correcto, comprobar si la calle es la que corresponde con el lugar y la direcci�n dada(m�todo comeOutFrom(Place place, Direction dir)), ver si un lugar se conecta con otro ( nextPlace(Place whereAmI))
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela 
 *
 */
public class Street {
	private Place source;
	private Place target;
	private Direction direction;
	private boolean isOpen;
	private java.lang.String code;
	private String id;
	
	/**
	 * Contructora de Street sin parametros
	 */
	public Street(){
		this.direction = Direction.UNKNOWN;
		this.id = "";
		this.target = new Place();
		this.source = new Place();
		this.isOpen = true;
	}
	/**
	 * Street constructor
	 * @param source source place
	 * @param direction	direction looking at
	 * @param target target place
	 */ 
	public Street(Place source,Direction direction,Place target){
		this.id = "";
		this.source = source;
		this.target = target;
		this.direction = direction;
		this.isOpen = true;
	}
	/**
	 * Street constructor
	 * @param source place
	 * @param direction looking at
	 * @param target place
	 * @param isOpen true if the street is open
	 * @param code code that open that closed street
	 */
	public Street(Place source, Direction direction, Place target,boolean isOpen,java.lang.String code){
		this.direction = direction;
		this.target = target;
		this.source = source;
		this.isOpen = isOpen;
		this.code = code;
		
	}
	/**
	 * Comprueba si la calle sale del lugar y de la direcci�n dados.La calle es de doble sentido,asi que habr� que mirar si la direcci�n y lugar  de origen de nuestra calle es igual a dir y al lugar que nos dan o a la direcci�n opuesta y al lugar de destino de la calle.
	 * @param place
	 * @param whichDirection
	 * @return Si son iguales alguna de esas comparaciones devolver� true,sino devolver� false.
	 */
	public boolean comeOutFrom(Place place, Direction whichDirection){
		return ( whichDirection == whichDirection.dirOpuesta(this.direction) && place == this.target) || ( whichDirection == this.direction && place == this.source );
	}
	
	/**
	 * Devuelve el siguiente lugar al dado.No se considera si est� abierto o no.Dependiendo de si se da el de origen o el destino se devuelve uno u otro. Si no 
	 * @param whereAmI - El lugar donde estoy.
	 * @return Devuelve el lugar del otro lado de la calle, aunque este cerrado.Devuelve null si whereAmI no pertenece a la calle.
	 */
	public Place nextPlace(Place whereAmI){
	Place place = null;
		
		if ( whereAmI == this.source ){
			
			place = this.target;
		}
		else if ( whereAmI == this.target ){
			place = this.source;
		}
		else {
			place = null;
		}
		return place;
	}
	/**
	 * Comprueba si la calle est� abierta
	 * @return true si est� abierta, sino false.
	 */
	public boolean isOpen(){		
		return isOpen;
	}
	/**
	 * Intenta abrir la calle con un CodeCard
	 * El c�digo debe coincidir con el de la calle para completar la acci�n.
	 * @param card
	 * @return true si consigue abrir la calle.Si no devuelve false.
	 */
	public boolean open(CodeCard card){
		if(this.code.equalsIgnoreCase(card.getCode())){
			this.isOpen = true;
		}
		return this.code.equalsIgnoreCase(card.getCode());
	}
	/**
	 * Intenta cerrar la calle con un CodeCard
	 * El c�digo debe coincidir con el de la calle para completar la acci�n.
	 * @param card
	 * @return true si consigue cerrarla. Si no devuelve false.
	 */
	public boolean close(CodeCard card){
		if(this.code.equalsIgnoreCase(card.getCode())){
			this.isOpen=false;
		}
		return this.code.equalsIgnoreCase(card.getCode());
	}
	
	/**
	 * Metodo que te devuelve la identificacion de una calle
	 * @return id la id de la calle
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Devuelve el codigo para abrir o cerrar esta calle
	 * @return codigo de la calle
	 */
	public String getCode() {
		// TODO Auto-generated method stub
		return this.code;
	}
	
}
