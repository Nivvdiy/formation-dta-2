package fr.pizzeria.ihm.action;

import fr.pizzeria.ihm.IhmUtil;

public class ShowNbPizza extends Action {

	public ShowNbPizza(IhmUtil ihmUtil) {
		super("Afficher le nombre de pizzas", "Nombre de pizzas", ihmUtil);
	}

	@Override
	public void doAction() {
		this.afficheTitre();
		System.out.println(ihmUtil.getIPizzaDao().getNbPizza() + " pizza(s)");
	}

}
