package fr.pizzeria.dao.pizzadao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.repos.PizzaRepository;

public class PizzaDaoJPASpring implements PizzaDao {
	
	@Autowired
	private PizzaRepository pizzaRepo;

	@Override
	public List<Pizza> findAllPizzas() {
		return pizzaRepo.findAll();
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		pizzaRepo.save(pizza);
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
	}

	@Override
	public void deletePizza(String codePizza) {

	}

	@Override
	public void updatePizza(int codePizza, Pizza pizza) {

	}

	@Override
	public void deletePizza(int codePizza) {

	}

	@Override
	public void updatePizza(Pizza lastPizzaState, Pizza newPizzaState) {
		pizzaRepo.save(newPizzaState);
	}

	@Override
	public void deletePizza(Pizza deletedPizza) {
		pizzaRepo.delete(deletedPizza);
	}

}
