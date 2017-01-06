package fr.pizzeria.dao.pizzadao;

import java.util.List;

import fr.pizzeria.model.Pizza;

public interface PizzaDao {

	List<Pizza> findAllPizzas();

	void saveNewPizza(Pizza pizza);

	void updatePizza(String codePizza, Pizza pizza);

	void updatePizza(int codePizza, Pizza pizza);
	
	void updatePizza(Pizza lastPizzaState, Pizza newPizzaState);

	void deletePizza(int codePizza);

	void deletePizza(String codePizza);
	
	void deletePizza(Pizza deletedPizza);

}
