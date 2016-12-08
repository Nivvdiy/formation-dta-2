package fr.pizzeria.exception;

import fr.pizzeria.model.Pizza;

public class UpdatePizzaException extends DaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702769028656955569L;
	private final int indexPizza;
	private final Pizza pizza;
	private final int lastIndex;

	public UpdatePizzaException(int indexPizza, Pizza pizza, int lastIndex) {
		this.indexPizza = indexPizza;
		this.pizza = pizza;
		this.lastIndex = lastIndex;
	}

	@Override
	public String getMessage() {
		String error = "";
		if(pizza.getCode().length()!=3){
			error = "Le code rentré est different 3 lettres";
		} else if(pizza.getName().length()<=0){
			error = "Le pizza n'as pas de nom";
		} else if(pizza.getPrice()<0){
			error = "La pizza à un prix nul ou négatif";
		} else if(indexPizza<=0){
			error = "L'index de la pizza est trop petit";
		} else if(indexPizza>lastIndex){
			error = "L'index de la pizza est trop grand";
		}
		return error;
	}

}
