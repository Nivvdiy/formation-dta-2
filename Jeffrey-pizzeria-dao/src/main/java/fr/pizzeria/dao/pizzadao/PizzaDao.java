package fr.pizzeria.dao.pizzadao;

import java.util.List;

import fr.pizzeria.model.Pizza;

public interface PizzaDao {

	List<Pizza> findAllPizzas();

	void saveNewPizza(Pizza pizza);

	void updatePizza(String codePizza, Pizza pizza);

	void deletePizza(String codePizza);

	void updatePizza(int codePizza, Pizza pizza);

	void deletePizza(int codePizza);

}
