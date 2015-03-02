package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Street;
/**
 * Una CodeCard abre o cierra una puerta que se encuentre en la
 *  calle.La tarjeta contiene un c�digo que debe coincidir con el de la calle
 * @author Nerea Ram�rez y Carmen Acosta
 *
 */
public class CodeCard extends Item{
	private String code;

	/**
	 * Constructora que inicializa los atributos de la clase con los atributos asignados por para metros
	 * @param id
	 * @param description
	 * @param code
	 */
	public CodeCard(java.lang.String id,
	        java.lang.String description,
	        java.lang.String code){
		super (id, description);
		this.code =code;
		this.times = 1;
	}
	/**
	 * Te devuelve siempre que se puede utilizar ya que las tarjetas no se gastan.
	 */
	public boolean canBeUsed(){
		return true;
		
	}
	/**
	 * Si el robot se encuentra en un lugar donde hay una calle en la direccion a la que est� mirando, 
	 * entonces puede abrir o cerrar la uerta si la tarjeta y el c�digo de la puerta coinciden.
	 */
	public boolean use(RobotEngine r,
	          NavigationModule nav){
		Street calle = nav.getHeadingStreet();
		 if (calle!= null && this.code.equalsIgnoreCase(calle.getCode()) ){
			if ( !calle.isOpen()){
				calle.open(this);	
				nav.getHeadingStreet().open(this);
			}
			else{
				calle.close(this);
			}
		}
		
		return (calle!= null) && (this.code.equalsIgnoreCase(calle.getCode()));
		
	}
	/**
	 * Devuelve el codigo de la tarjeta
	 * @return
	 */
	public java.lang.String getCode(){
		return this.code;
		
	}


	
	
}
