package uk.co.ebi.person.exception;

public class PersonException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode;
	
	public PersonException(String str, int errorCode) {
		super(str);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
	
	
}
