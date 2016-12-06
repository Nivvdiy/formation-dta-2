package fr.pizzeria.ihm.action;

import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public final class ModifyPrice extends Modify {

	public ModifyPrice(IhmUtil ihmUtil) {
		super("Modifier le prix d'une pizza", "Modification du prix de la pizza", ihmUtil);
	}

	@Override
	protected void modifyPizza(int option) {
		Pizza pizza = ihmUtil.getIPizzaDao().findAllPizzas().get(option - 1);
		System.out.println("Ancien prix => " + pizza.getPrice());
		System.out.println("Veuillez saisir le prix");
		boolean error = true;
		while (error) {

			String temp = ihmUtil.getScanner().next();

			try {
				pizza.setPrice(Double.parseDouble(temp));
				error = false;
			} catch (NumberFormatException e) {
				System.out.println("Saisie incorrect veuillez entrez un nombre...");
			}

		}
		try {
			ihmUtil.getIPizzaDao().updatePizza(option, pizza);
		} catch (UpdatePizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
