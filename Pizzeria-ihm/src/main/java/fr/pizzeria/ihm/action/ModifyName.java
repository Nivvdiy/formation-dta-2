package fr.pizzeria.ihm.action;

import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public final class ModifyName extends Modify {

	public ModifyName(IhmUtil ihmUtil) {
		super("Modifier le nom d'une pizza", "Modification du nom de la pizza", ihmUtil);
	}

	@Override
	protected void modifyPizza(int option) {
		Pizza pizza = ihmUtil.getIPizzaDao().findAllPizzas().get(option - 1);
		System.out.println("Ancien nom => " + pizza.getName());
		System.out.println("Veuillez saisir le nom");
		ihmUtil.getScanner().nextLine();
		pizza.setName(ihmUtil.getScanner().nextLine());
		try {
			ihmUtil.getIPizzaDao().updatePizza(option, pizza);
		} catch (UpdatePizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
