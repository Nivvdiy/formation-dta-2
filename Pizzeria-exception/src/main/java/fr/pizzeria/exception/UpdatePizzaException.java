package fr.pizzeria.exception;

import fr.pizzeria.model.Pizza;

public class UpdatePizzaException extends DaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702769028656955569L;
	private int indexPizza;
	private Pizza pizza;
	private int lastIndex;

	public UpdatePizzaException(int indexPizza, Pizza pizza, int lastIndex) {
		this.indexPizza = indexPizza;
		this.pizza = pizza;
		this.lastIndex = lastIndex;
	}

	@Override
	public String getMessage() {
		if(pizza.getCode().length()!=3){
			return "Le code rentré est different 3 lettres";
		} else if(pizza.getName().length()<=0){
			return "Le pizza n'as pas de nom";
		} else if(pizza.getPrice()<0){
			return "La pizza à un prix nul ou négatif";
		} else if(indexPizza<=0){
			return "L'index de la pizza est trop petit";
		} else if(indexPizza>lastIndex){
			return "L'index de la pizza est trop grand";
		}
		return super.getMessage();
	}

}
