package tp.pr5.cityLoader;


import java.util.Scanner;

import tp.pr5.City;
import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.Street;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.items.CodeCard;
import tp.pr5.items.Fuel;
import tp.pr5.items.Garbage;

/**
 * 
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * Clase que se encarga de cargar el mapa de la ciudad
 * 
 */
public class CityLoaderFromTxtFile {
		private Place [] place;
		private Street [] street;
		
	/**
	 * Constructor without parameters 
	 */
	public 	CityLoaderFromTxtFile (){
		this.place = new Place[20];
		this.street = new Street [20];
		for (int i=0; i < this.place.length; i++){
			this.place[i]= new Place ();
			this.street[i] = new Street ();
		}
	}

	/**
	 * Builds a city from an input File
	 * @param file input File
	 * @return the City
	 * @throws java.io.IOException
	 */
	public City loadCity(java.io.InputStream file)
            throws java.io.IOException{
		
		Scanner entrada = new Scanner (file);
		String resp;
		resp = controlNext ( entrada);
		
			if ( resp.equalsIgnoreCase("BeginCity")){
					while ( !resp.equalsIgnoreCase("EndCity")){
						resp = controlNext ( entrada);
						if ( resp.equalsIgnoreCase("BeginPlaces")){
							creaPlaces (resp, entrada);
						}
						else if ( resp.equalsIgnoreCase("BeginStreets")){
							creaStreets ( resp, entrada);
									
						}
						else if ( resp.equalsIgnoreCase("BeginItems")){
							creaItems (resp,entrada);
						}
						else if ( !resp.equalsIgnoreCase("EndCity")){
							throw new WrongCityFormatException();
						}
					}
			}
			else{
				throw new WrongCityFormatException();
			}
			
		return new City(this.street);
	}
	/**
	 * Returns the place where the robot will start the simulation
	 * @return: The initial place
	 */
	public Place getInitialPlace (){
		return this.place[0];
	}
	
	/**
	 * Crea los lugares del mapa a partir del archivo de entrada
	 * @param resp controla cuando no hay mas lugares que almacenar
	 * @param entrada donde se encuentra la informacion del mapa
	 * @throws WrongCityFormatException
	 */
	public void creaPlaces ( String resp, Scanner entrada)
			throws WrongCityFormatException{
		int num = 0;
		String descripcion = "";
		String nombre = "";
		
		while ( !resp.equalsIgnoreCase("EndPlaces") ){
			resp = controlNext(entrada);
				if ( resp.equalsIgnoreCase("place")){
					num = controlNextInt(entrada);
					nombre = controlNext(entrada);
					descripcion = controlNext (entrada);
					/*if (descripcion.charAt(0)== '\"'){
						while (descripcion.charAt(descripcion.length()-1) != '\"'){
							descripcion +=" " + controlNext(entrada);
						}
					}*/
					resp = controlNext( entrada);
					if ( resp.equalsIgnoreCase("noSpaceShip")){
							isSpaceship ( false, descripcion, num, nombre);
						}
						else if ( resp.equalsIgnoreCase("spaceShip")){
							isSpaceship ( true, descripcion, num, nombre);
						}
						else{
							throw new WrongCityFormatException();
						}
			}
			else if (!resp.equalsIgnoreCase("EndPlaces")){
				throw new WrongCityFormatException();
			}
		}
	}

	/**
	 * Crea las calles del mapa a partir del archivo de entrada
	 * @param resp controla cuando no hay mas calles que almacenar
	 * @param entrada donde se encuentra la informacion del mapa
	 * @throws WrongCityFormatException
	 */
	public void creaStreets ( String resp, Scanner entrada)
			throws WrongCityFormatException{
		
		int num = 0, i = 0;
		int numPlaceIni = 0;
		int numPlaceFin = 0;
		Direction direccion = Direction.UNKNOWN;
		
		while ( !resp.equalsIgnoreCase("EndStreets") ){
			resp = controlNext(entrada);
			if ( resp.equalsIgnoreCase("street")){
				num = controlNextInt(entrada);
				resp = controlNext(entrada);
				if ( resp.equalsIgnoreCase("place")){
					numPlaceIni = controlNextInt ( entrada);
					resp = controlNext (entrada);
					direccion = Direction.valueOf(resp.toUpperCase());
					resp = controlNext (entrada);
					if (resp.equalsIgnoreCase("place")){
						placeMetod (numPlaceFin, entrada, numPlaceIni, i, num, direccion);
						i++;
					}
					else{
						throw new WrongCityFormatException();//no place 2
					}
				}
				else{
					throw new WrongCityFormatException();//no place
				}
			}
		}
	}

	/**
	 * Crea los items del mapa a partir del archivo de entrada
	 * @param resp controla cuando no hay mas items que almacenar
	 * @param entrada donde se encuentra la informacion del mapa
	 * @throws WrongCityFormatException
	 */
	public void creaItems ( String resp, Scanner entrada)
			throws WrongCityFormatException{
		int i = 0;
		
		while ( !resp.equalsIgnoreCase("EndItems")){
			resp = controlNext(entrada);
			
			if ( resp.equalsIgnoreCase("fuel")){
				i = creaFuel ( i, entrada);
			}
			else if (resp.equalsIgnoreCase("codecard")){
				i = creaCodeCard (i, entrada);
			}
			else if (resp.equalsIgnoreCase("garbage")){
				i = creaGarbage (i, entrada);
			}
			else if (!resp.equalsIgnoreCase("EndItems")){
				throw new WrongCityFormatException();
			}
		}
	}
	
	/**
	 * Inserta los item de tipo fuel en la posicion que les corresponda
	 * @param i posicion de la que parte
	 * @param entrada
	 * @return posicion en la que se inserta el fuel
	 * @throws WrongCityFormatException
	 */
	public int creaFuel (int i, Scanner entrada)
			throws WrongCityFormatException {
		int num = 0;
		int power = 0; 
		int cantidad = 0;
		int numPlace = 0;
		String nombre = null; 
		String descripcion = null;
		String resp = null;
		
		num = controlNextInt (entrada);
		nombre = controlNext (entrada);
		descripcion = controlNext (entrada);
		/*if (descripcion.charAt(0)== '\"'){
			while (descripcion.charAt(descripcion.length()-1) != '\"'){
				descripcion +=   " " + controlNext(entrada);
			}
		}*/
		power = controlNextInt (entrada);
		cantidad = controlNextInt (entrada);
		resp = controlNext (entrada);
		if (resp.equalsIgnoreCase("place")){
			numPlace = controlNextInt (entrada);
			descripcion = descripcion.replace("_", " ");
			descripcion = descripcion.replace("\"", "");
			if( i == num && this.place[numPlace].getName() != ""){
				this.place[numPlace].addItem(new Fuel (nombre, descripcion, power, cantidad));
				i++;
			}
			else{
				throw new WrongCityFormatException();
			}
		}
		return i;
	}
	
	/**
	 * Inserta los item de tipo codeCard en la posicion que les corresponda
	 * @param i posicion de la que parte
	 * @param entrada
	 * @return posicion en la que se inserta el codeCard
	 * @throws WrongCityFormatException
	 */
	public int creaCodeCard ( int i, Scanner entrada)
			throws WrongCityFormatException {
		int num = 0;
		String nombre = null;
		String descripcion = null;
		String resp = null;
		String code = null;
		int numPlace = 0;
		
		num = controlNextInt (entrada);
		nombre = controlNext (entrada);
		descripcion = controlNext ( entrada);
		/*if (descripcion.charAt(0)== '\"'){
			while (descripcion.charAt(descripcion.length()-1) != '\"'){
				descripcion += controlNext(entrada);
			}
		}*/
		code = controlNext ( entrada);
		resp = controlNext (entrada);
		if (resp.equalsIgnoreCase("place")){
			numPlace = controlNextInt (entrada);
			descripcion = descripcion.replace("_", " ");
			descripcion = descripcion.replace("\"", "");
			if ( i == num && this.place[numPlace].getName() != ""){
				this.place[numPlace].addItem(new CodeCard (nombre, descripcion, code));
				i++;
			}
			else{
				throw new WrongCityFormatException();
			}
		}
		return i;
	}
	
	/**
	 * Inserta los item de tipo Garbage en la posicion que les corresponda
	 * @param i posicion de la que parte
	 * @param entrada
	 * @return posicion en la que se inserta el Garbage
	 * @throws WrongCityFormatException
	 */
	public int creaGarbage ( int i, Scanner entrada) throws WrongCityFormatException{
		int num = 0;
		String nombre = null;
		String descripcion = null;
		int cantidad = 0;
		String resp = null;
		int numPlace = 0;	
		
		num = controlNextInt (entrada);
		nombre = controlNext (entrada);
		descripcion = controlNext ( entrada);
		/*if (descripcion.charAt(0)== '\"'){
			while (descripcion.charAt(descripcion.length()-1) != '\"'){
				descripcion += controlNext(entrada);
			}
		}*/
		cantidad = controlNextInt (entrada);
		resp = controlNext (entrada);
		if (resp.equalsIgnoreCase("place")){
			numPlace = controlNextInt (entrada);
			descripcion = descripcion.replace("_", " ");
			descripcion = descripcion.replace("\"", "");
			if(i == num && this.place[numPlace].getName() != ""){
				this.place[numPlace].addItem(new Garbage (nombre, descripcion, cantidad));
				i++;
			}
			else{
				throw new WrongCityFormatException();
			}
		}
		return i;
	}
	
	/**
	 * Comprueba que haya mas elementos en la entrada, que se pueda seguir leyendo
	 * @param entrada de donde se lee
	 * @return siguiente elemento en caso de que haya
	 * @throws WrongCityFormatException cuando no hay siguiente elemento
	 */
	public String controlNext ( Scanner entrada) 
			throws WrongCityFormatException{
		String resp = "";
		
		if (entrada.hasNext()){
			resp = entrada.next();
		} else{
			throw new WrongCityFormatException();
		}
		return resp;
	}
	
	/**
	 * Comprueba que haya mas elementos en la entrada y que sean enteros
	 * @param entrada de donde se lee
	 * @return entero correspondiente al elemento siguiente
	 * @throws WrongCityFormatException cuando no hay siguiente elemento
	 */
	public int controlNextInt ( Scanner entrada) 
			throws WrongCityFormatException{
		String resp = null;
		int numero = 0;
		
		if (entrada.hasNext()){
			resp = entrada.next();
			numero = Integer.parseInt(resp);
		} else{
			throw new WrongCityFormatException();
		}
		return numero;
	}
	
	/**
	 * Comprueba que los lugares sean correctos en funcion a una calle
	 * @param numIni
	 * @param numFin
	 * @throws WrongCityFormatException
	 */
	public void controlPlacesCorrectos (int numIni, int numFin)
			throws  WrongCityFormatException{
		boolean encI = false;
		boolean encF = false;
		String inicio = this.place[numIni].getName();
		String fin = this.place[numFin].getName();
		int i=0;
		if ( !inicio.equalsIgnoreCase(fin) && numIni <this.place.length && numFin <this.place.length){
			while ( (!encI || !encF) && i < this.place.length){
				if ( this.place[i].getName() != ""){
					if(inicio.equalsIgnoreCase(this.place[i].getName())){
						encI = true;
					}
					if( fin.equalsIgnoreCase(this.place[i].getName())){
						encF = true;
					}
				}
				i++;
			}
			if ( !encI || !encF){
				throw new WrongCityFormatException();
			}
		}
		else{
			throw new WrongCityFormatException();
		}
	}

	/**
	 * Metodo que genera un lugar
	 * @param bool indica si el lugar es SpaceShip o no
	 * @param descripcion del lugar
	 * @param num posicion del array en la que se inserta el place
	 * @param nombre del place
	 * @throws WrongCityFormatException
	 */
	public void  isSpaceship ( boolean bool, String descripcion, int num, String nombre) 
			throws WrongCityFormatException{
		descripcion = descripcion.replace("_", " ");
		descripcion = descripcion.replace("\"", "");
		if( this.place[num].getName().equalsIgnoreCase("")){
			this.place[num] = new Place (nombre, bool, descripcion);
		}else {
			throw new WrongCityFormatException();
		}
	}
	
	/**
	 * Método que crea una determinada calle
	 * @param num posicion en la que se inserta la calle
	 * @param numPlaceIni lugar del que parte la calle
	 * @param direccion direccion de la calle
	 * @param numPlaceFin lugar al que llega la calle
	 * @param i
	 * @param entrada de la que se coge el codigo de la calle en caso de que esta este cerrada
	 * @throws WrongCityFormatException
	 */
	public void placeMetod (int numPlaceFin, Scanner entrada, int numPlaceIni, int i, int num, Direction direccion) 
			throws WrongCityFormatException{
		String resp;
		numPlaceFin = controlNextInt (entrada);
		resp = controlNext (entrada);
		controlPlacesCorrectos (numPlaceIni, numPlaceFin);
		if (this.street[num].getId().equalsIgnoreCase("") && num == i){
			if ( resp.equalsIgnoreCase("open")){
				this.street[num] = new Street (this.place[numPlaceIni], direccion, this.place[numPlaceFin]);
			}
			else if (resp.equalsIgnoreCase("closed")){
				String code;
				code = controlNext(entrada);
				this.street[num] = new Street ( this.place[numPlaceIni], direccion, this.place[numPlaceFin], false, code);
			}
			else{
				throw new WrongCityFormatException();
			}
		}
		else{
			throw new WrongCityFormatException();
		}
	}
	
}

