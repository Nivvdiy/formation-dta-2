package fr.pizzeria.ihm.action;

import fr.pizzeria.dao.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public class ListPizza extends Action {

	public ListPizza(IhmUtil ihmUtil) {
		super("Lister les pizzas", "Liste des pizzas", ihmUtil);
	}

	@Override
	public void doAction(){
		this.afficheTitre();
		if (Pizza.getNbPizza() == 0) {
			System.out.println("\nAucune pizza dans la liste\n");
		} else {
			ihmUtil.getPizzaDao().findAllPizzas().forEach((p) -> {
				try {
					getIhmUtil().affichePizza(p, false);
				} catch (PizzaException e) {
					e.printStackTrace();
				}
			});
		}
	}

}
