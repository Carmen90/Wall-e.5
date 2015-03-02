package tp.pr5.instructions.exceptions;

/**
 * @author Carmen Acosta Morales y Nerea Ramirez Lamela 
 * Exception thrown when a instruction execution fails.
 *  The exception has a user-friendly message with an explanation about the error. 
 *  This class has many different constructors, 
 * one for every constructor of the base class.
 *
 */
@SuppressWarnings("serial")
public class InstructionExecutionException extends Exception{
	/**
	 * Constructor without parameters (no message is given)
	 */
	public InstructionExecutionException(){
		
	}
	/**
	 * The exception thrown is created with a problem message.
	 * @param arg0
	 */
	public InstructionExecutionException(java.lang.String arg0){
		super (arg0);
	}
	/**
	 * Constructor to create the exception with a nested cause.
	 * @param arg0
	 */
	public InstructionExecutionException(java.lang.Throwable arg0){
		super(arg0);
	}
	/**
	 * Constructor to create the exception with a nested cause and an error message.
	 * @param arg0
	 * @param arg1
	 */
	public InstructionExecutionException(java.lang.String arg0,
            java.lang.Throwable arg1){
		super (arg0,arg1);
	}
}

