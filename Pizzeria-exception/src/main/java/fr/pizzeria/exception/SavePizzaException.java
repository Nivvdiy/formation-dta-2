package fr.pizzeria.exception;

import fr.pizzeria.model.Pizza;

public class SavePizzaException extends DaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2346475209618282430L;
	private Pizza pizza;

	public SavePizzaException(Pizza pizza) {
		this.pizza = pizza;
	}

	@Override
	public String getMessage() {
		if(pizza.getCode().length()!=3){
			return "Le code rentré est different 3 lettres";
		} else if(pizza.getName().length()<=0){
			return "Le pizza n'as pas de nom";
		} else if(pizza.getPrice()<=0){
			return "La pizza à un prix nul ou négatif";
		}
		return super.getMessage();
	}

}
