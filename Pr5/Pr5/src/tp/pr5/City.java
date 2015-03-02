package tp.pr5;

import java.util.ArrayList;

/**
 * Esta clase representa la ciudad por donde el robot pasea.
 * Tiene un �nico atributo(cityMap) que interpreta un arrayList donde se encuentran todas las calles de la ciudad.
 *Aqu� se podr� a�adir una nueva clase, buscar si hay una calle dando una direcci�n y un lugar.
 * @author Nerea Ramirez y Carmen Acosta
 *
 */
public class City {
	private ArrayList<Street> cityMap;
	public City(){		
	}
	
	/**
	 * Este método carga en un ArrayList de calles un mapa introducido por parametro
	 * @param cityMap mapa a cargar
	 */
	public City(Street[] cityMap){
		this.cityMap = new ArrayList <Street> ();
		
		for ( int i = 0; i < cityMap.length; i++){
			this.cityMap.add(cityMap[i]);
		}
	}

	/**
	 * El método lookForStreet se encarga de devolver la calle a la que se est� mirando desde el lugar y la dirección dada
	 * Si el arrayList cityMap no es nulo, no se ha llegado al final del arrayList y no hay una calle nula, se va recorriendo cityMap buscando la calle
	 *  que tiene la direcci�n y el lugar pasados por par�metro hasta que se encuentre. 
	 * @param currentPlace -El lugar donde buscar la calle.
	 * @param currentHeading - La direcci�n donde se encuentra la calle.
	 * @return  Si encontrado==true devuelve la calle sino devolverá null.
	 */
	public Street lookForStreet(Place currentPlace,
            Direction currentHeading){
		
		int i = 0;
		Street calle = null;
		boolean enc = false;
		
		if (this.cityMap != null){
			while ( i < this.cityMap.size() && !enc && this.cityMap.get(i)!= null){
				if ( this.cityMap.get(i).comeOutFrom(currentPlace, currentHeading)){
					enc = true;
					calle = this.cityMap.get(i);
				}
				else{
					i++;
					
				}
			}
		}
		return calle;
		
	}
	/**
	 * Añade una calle a la ciudad
	 * @param street
	 */
	public void addStreet(Street street){
		this.cityMap.add(street);
	}

}
