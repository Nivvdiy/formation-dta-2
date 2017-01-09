package fr.pizzeria.dao.pizzadao;

import java.util.List;

import fr.pizzeria.model.Pizza;

public interface PizzaDao {

	List<Pizza> findAllPizzas();

	void saveNewPizza(Pizza pizza);
	
	void updatePizza(Pizza lastPizzaState, Pizza newPizzaState);
	
	void deletePizza(Pizza deletedPizza);

}
