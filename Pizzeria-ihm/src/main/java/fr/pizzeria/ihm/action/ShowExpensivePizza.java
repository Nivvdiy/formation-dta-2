package fr.pizzeria.ihm.action;

import fr.pizzeria.ihm.IhmUtil;

public class ShowExpensivePizza extends Action {

	public ShowExpensivePizza(IhmUtil ihmUtil) {
		super("Afficher la pizza la plus chèr", "Pizza la plus chèr", ihmUtil);
	}

	@Override
	public void doAction() {
		this.afficheTitre();
		ihmUtil.affichePizza(ihmUtil.getIPizzaDao().findAllPizzas().stream().reduce((p1, p2) -> {
			return p1.getPrice() > p2.getPrice() ? p1 : p2;
		}).get(), false);
	}

}
