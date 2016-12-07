package fr.pizzeria.exception;

public class SaveMemoriePizzaException extends DaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1354068751215233887L;

	public SaveMemoriePizzaException() {
		super("Erreur lors de la sauvegarde de la mémoire des pizzas");
	}

}
