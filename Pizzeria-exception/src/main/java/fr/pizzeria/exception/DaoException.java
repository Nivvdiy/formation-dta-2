package fr.pizzeria.exception;

public abstract class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5909072422495173523L;
	
	public DaoException() {
		super();
	}
	
	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getMessage(){
		return super.getMessage();
	}

}
