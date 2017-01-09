package fr.pizzeria.dao.pizzadao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

public class PizzaDaoArray implements PizzaDao {

	private List<Pizza> listPizzas = new ArrayList<>();

	public PizzaDaoArray() {
		listPizzas.add(new Pizza("PEP", "Pépéroni", 12.50, Category.VIANDE, true));
		listPizzas.add(new Pizza("MAR", "Margherita", 14.00, Category.SANS_VIANDE, true));
		listPizzas.add(new Pizza("REI", "La Reine", 11.50, Category.VIANDE, true));
		listPizzas.add(new Pizza("FRO", "La 4 fromages", 12.00, Category.SANS_VIANDE, true));
		listPizzas.add(new Pizza("CAN", "La cannibale", 12.50, Category.VIANDE, true));
		listPizzas.add(new Pizza("SAV", "La savoyarde", 13.00, Category.VIANDE, true));
		listPizzas.add(new Pizza("ORI", "L'orientale", 13.50, Category.POISSON, true));
		listPizzas.add(new Pizza("IND", "L'indienne", 14.50, Category.VIANDE, true));

	}

	@Override
	public List<Pizza> findAllPizzas() {
		return listPizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		Comparator<Pizza> comp = Comparator.comparing(Pizza::getCategory);
		Optional<Pizza> p = listPizzas.stream().max(comp);
		if (p.isPresent()) {
			listPizzas.add(pizza);
			int nbPizza = Pizza.getNbPizza();
			nbPizza++;
			Pizza.setNbPizza(nbPizza);
		}
	}

	@Override
	public void updatePizza(Pizza lastPizzaState, Pizza newPizzaState) {
		listPizzas.set(listPizzas.indexOf(lastPizzaState), newPizzaState);
	}

	@Override
	public void deletePizza(Pizza deletedPizza) {
		listPizzas.remove(deletedPizza);
		Pizza.setNbPizza(Pizza.getNbPizza()-1);
	}

}
