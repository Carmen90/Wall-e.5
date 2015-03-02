package tp.pr5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Clase que se encarga de generar una lista con todas las vistas de las que dispone una jugada en concreto. Esta clase implementa la interfaz
 * Iterable de tipo genérico para que, cada vez que haya que informar a las vistas, se pueda iterar sobre la lista de vistas para informar a todas
 * las vistas del tipo de la accion a realizar. También extiende de la clase Observable ya que se encarga de informar  las vistas de cambios.
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 *
 * @param <T> tipo generico de la clase
 */
public class Observable <T> extends java.util.Observable implements Iterable<T>{
	private Collection <T> misVistas = new ArrayList <T>();
	
	/**
	 * Añade una nueva vista a la coleccion de vistas 
	 * @param obs vista a añadir
	 */
	public void add( T obs){
		this.misVistas.add(obs);
	}
	
	/**
	 * Devuelve un iterador sobre la lista de vistas de la clase.
	 */
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return this.misVistas.iterator();
	}
}
