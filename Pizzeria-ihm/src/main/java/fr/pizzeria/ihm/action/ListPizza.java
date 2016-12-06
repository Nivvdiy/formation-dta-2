package fr.pizzeria.ihm.action;

import fr.pizzeria.ihm.IhmUtil;

public class ListPizza extends Action {

	public ListPizza(IhmUtil ihmUtil) {
		super("Lister les pizzas", "Liste des pizzas", ihmUtil);
	}

	@Override
	public void doAction() {
		this.afficheTitre();
		if (ihmUtil.getIPizzaDao().getNbPizza() == 0) {
			System.out.println("\nAucune pizza dans la liste\n");
		} else {
			ihmUtil.getIPizzaDao().findAllPizzas().forEach((p) -> getIhmUtil().affichePizza(p, false));
		}
	}

}
