package fr.pizzeria.exception;

public class RemovePizzaException extends DaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3956558458992669857L;
	private int lastIndex;
	private int indexPizza;

	public RemovePizzaException (int indexPizza, int lastIndex) {
		this.indexPizza = indexPizza;
		this.lastIndex = lastIndex;
	}

	@Override
	public String getMessage() {
		if(indexPizza<=0){
			return "L'index de la pizza est négatif";
		} else if(indexPizza>lastIndex){
			return "L'index de la pizza est trop grand";
		}
		return super.getMessage();
	}

}
