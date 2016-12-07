package fr.pizzeria.exception;

public class LoadMemoriePizzaException extends DaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5586842060833281637L;

	public LoadMemoriePizzaException() {
		super("Erreur lors du chargement de la mémoire des pizzas");
	}

}
