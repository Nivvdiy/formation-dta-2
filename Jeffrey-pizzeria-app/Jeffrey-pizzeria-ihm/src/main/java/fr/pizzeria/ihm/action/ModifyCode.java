package fr.pizzeria.ihm.action;

import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public final class ModifyCode extends Modify {

	public ModifyCode(IhmUtil ihmUtil) {
		super("Modifier le code d'une pizza", "Modification du code de la pizza", ihmUtil);
	}

	@Override
	protected void modifyPizza(int option){
		Pizza lastPizzaState = ihmUtil.getPizzaDao().findAllPizzas().get(option - 1);
		Pizza pizza = ihmUtil.getPizzaDao().findAllPizzas().get(option - 1);
		System.out.println("Ancien code => " + pizza.getCode());
		System.out.println("Veuillez saisir le code");
		pizza.setCode(ihmUtil.getScanner().next());
		ihmUtil.getPizzaDao().updatePizza(lastPizzaState, pizza);
	}

}
