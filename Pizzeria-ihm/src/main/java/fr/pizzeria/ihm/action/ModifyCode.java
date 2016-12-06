package fr.pizzeria.ihm.action;

import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public final class ModifyCode extends Modify {

	public ModifyCode(IhmUtil ihmUtil) {
		super("Modifier le code d'une pizza", "Modification du code de la pizza", ihmUtil);
	}

	protected void modifyPizza(int option) {
		Pizza pizza = ihmUtil.getIPizzaDao().findAllPizzas().get(option - 1);
		System.out.println("Ancien code => " + pizza.getCode());
		System.out.println("Veuillez saisir le code");
		pizza.setCode(ihmUtil.getScanner().next());
		try {
			ihmUtil.getIPizzaDao().updatePizza(option, pizza);
		} catch (UpdatePizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
