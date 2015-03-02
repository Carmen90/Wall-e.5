package tp.pr5.cityLoader.cityLoaderExceptions;

import java.io.IOException;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela
 * Exception thrown by the map loader when the file does not adhere to the file format.
 */
@SuppressWarnings("serial")
public class WrongCityFormatException extends IOException {
	/**
	 * Constructor without parameters 
	 */
	public WrongCityFormatException(){
		super();
	}
		
	/**
	 * Constructor with a mesage
	 * @param msg the mesage to show
	 */
	public WrongCityFormatException(java.lang.String msg){
		super(msg);
	}
		
	/**
	 * Constructor with params that invoque a super class
	 * @param msg
	 * @param arg
	 */
	public WrongCityFormatException(java.lang.String msg,
	           java.lang.Throwable arg){
		super (msg, arg);
	}
		
	/**
	 * Constructor with params that invoque a super class
	 * @param arg
	 */
	public WrongCityFormatException(java.lang.Throwable arg){
		super(arg);
	}

}

