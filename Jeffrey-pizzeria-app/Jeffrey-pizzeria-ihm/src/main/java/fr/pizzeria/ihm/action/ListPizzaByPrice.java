package fr.pizzeria.ihm.action;

import java.util.Comparator;

import fr.pizzeria.dao.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public class ListPizzaByPrice extends Action {

	public ListPizzaByPrice(IhmUtil ihmUtil) {
		super("Lister les pizzas par prix", "Liste des pizzas classé par prix", ihmUtil);
	}

	@Override
	public void doAction(){
		this.afficheTitre();
		if (Pizza.getNbPizza() == 0) {
			System.out.println("\nAucune pizza dans la liste\n");
		} else {
			ihmUtil.getPizzaDao().findAllPizzas().stream().sorted(Comparator.comparing(Pizza::getPrice))
					.forEach((p) -> {
						try {
							ihmUtil.affichePizza(p, false);
						} catch (PizzaException e) {
							e.printStackTrace();
						}
					});

		}
	}

}
