package fr.pizzeria.exception;

public class PizzaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7056505756436769862L;

	public PizzaException() {
		super();
	}

	public PizzaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PizzaException(String arg0) {
		super(arg0);
	}

	public PizzaException(Throwable arg0) {
		super(arg0);
	}

}
