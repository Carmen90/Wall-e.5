package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
/**
 * Es la superclase de todos los items.Contiene la informaci�n com�n de todos los items y define la interfaz que los elementos deben coincidir.
 * @author Nerea Ram�rez y Carmen Acosta
 *
 */
public abstract class Item implements Comparable <Item>{
	protected String id;
	protected java.lang.String description;
	protected int times;
	
	/**
	 * Construye un item con la id y descripci�n dadas. 
	 * @param id
	 * @param description
	 */
	public Item(java.lang.String id, java.lang.String description){
		
		this.id=id;
		this.description= description;
		this.times = 0;
	}
	
	/**
	 * Este m�todo abstracto debe comprobar si el objeto se puede utilizar.Las subclases deben implementarlo.
	 * @return true si el elemnto se puede utilizar.
	 */
	public abstract boolean canBeUsed();
	
	/**
	 * Trata de usar el elemento con un robot en un lugar determinado(nav).
	 * Las subclases deben implementar el m�todo.
	 * @param r -El robot que utiliza el elemento
	 * @param nav - el lugar donde se utiliza el elemento
	 * @return true si consiguio completar la acci�n.
	 */
	public abstract boolean use(RobotEngine r,
	          NavigationModule nav);
	
	/**
	 * 
	 * @returnel identificador del item.
	 */
	public java.lang.String getId(){
		return this.id;
		
	}
	
	/**
	 * Genera una cadena con la descripci�n del elemento.
	 */
	public java.lang.String toString(){
		return this.id + " " + this.description;
		
	}
	
	/** 
	 * @return una descripci�n acerca del elemento
	 */
	public java.lang.String getDescription(){
		return this.description;
		
	}
	
	/**
	 * Reimplementa el m�todo equals de la clase Object
	 * Dos Items son iguales si su id es igual. Ignora may�sculas y min�sculas
	 * @param objeto con el que se compara
	 */
	@Override 
	public boolean equals (Object obj){
		return (this == obj) ||
			   (obj != null) && (this.getClass() == obj.getClass()) 
				             && this.id.equalsIgnoreCase(((Item) obj).id)|| 
			   (obj != null) && (this.id.getClass() == obj.getClass())
			   				 && this.id.equalsIgnoreCase((String)obj);

	}
	
	/**
	 * Reimplements the compareTo
	 * Devuelve un entero en funci�n de si son iguales o no. Devuelve 0 en caso de que sean iguales
	 * Ignora may�sculas y min�sculas
	 * @param obj  item a comparar
	 */
	@Override
	public int compareTo ( Item obj){
		  return this.id.compareToIgnoreCase(obj.id);	
	}
}
