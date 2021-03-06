package fr.pizzeria.ihm.action;

import java.util.stream.Collectors;

import fr.pizzeria.dao.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public class ListPizzaByGroup extends Action {

	public ListPizzaByGroup(IhmUtil ihmUtil) {
		super("Lister les pizzas par categorie", "Liste des pizzas classé par catégorie", ihmUtil);
	}

	@Override
	public void doAction(){
		this.afficheTitre();
		if (Pizza.getNbPizza() == 0) {
			System.out.println("\nAucune pizza dans la liste\n");
		} else {
			ihmUtil.getPizzaDao().findAllPizzas().stream().collect(Collectors.groupingBy(Pizza::getCategory))
					.forEach((a, b) -> b.forEach((c) -> {
						try {
							getIhmUtil().affichePizza(c, false);
						} catch (PizzaException e) {
							e.printStackTrace();
						}
					}));

		}
	}

}
