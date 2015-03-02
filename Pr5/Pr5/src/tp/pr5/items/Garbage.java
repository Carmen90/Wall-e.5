package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
/**
 * Garbage es un tipo de item que genera recycled material despu�s de usarlo.
 * Garbage s�lo puede usarse una vez.
 * Despu�s de usarla se debe eliminar del inventario del robot.
 * @author Nerea Ram�rez y Carmen Acosta
 *
 */
public class Garbage extends Item{
	private int recycledMaterial;
	private boolean usar;
	/**
	 * Constructor que genera un garbage con la id, descripci�n y material dado.
	 * @param id
	 * @param description
	 * @param recycledMaterial
	 */
	public Garbage(java.lang.String id,
		       java.lang.String description,
		       int recycledMaterial){
		super (id, description);
		this.times=1;
		this.recycledMaterial = recycledMaterial;
		this.usar = true;
	}
	/**
	 * return true si todavia no se ha usado el item.
	 */
	public boolean canBeUsed(){
		return this.usar;
		
	}
	/**
	 * Este m�todo si canBeUsed() devuelve true entonces genera recycled material
	 * para el robot que la est� usando.Tambi�n dejar� reflejado que no se podr� volver ha utilizar.
	 * @return true si consigue usarla.
	 */
	public boolean use(RobotEngine r,
	          NavigationModule nav){
		boolean usa = false;
		if (this.canBeUsed()){
			r.addRecycledMaterial(this.recycledMaterial);
			this.usar = false;
			this.times--;
			usa= true;
		}
		return usa;
		
	}
	/**
	 * Genera una cadena con la descripci�n del elemento.
	 */
	public java.lang.String toString(){
		return this.id + ": "+ this.description;
		
	}
	
}
