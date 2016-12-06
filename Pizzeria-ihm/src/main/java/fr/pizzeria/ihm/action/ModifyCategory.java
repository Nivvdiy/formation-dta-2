package fr.pizzeria.ihm.action;

import java.util.HashMap;
import java.util.Map;

import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

public final class ModifyCategory extends Modify {

	public ModifyCategory(IhmUtil ihmUtil) {
		super("Modifier la catégorie d'une pizza", "Modification de la catégorie de la pizza", ihmUtil);
	}

	@Override
	protected void modifyPizza(int option) {
		Pizza pizza = ihmUtil.getIPizzaDao().findAllPizzas().get(option - 1);
		System.out.println("Ancienne catégorie => " + pizza.getCategory());
		System.out.println("Veuillez saisir le numéro de la catégorie");
		Map<Integer, String> catList = new HashMap<Integer, String>();
		Category.getCatList().forEach((catText, catName) -> catList.put(catList.size() + 1, catText));
		catList.forEach(
				(ind, catText) -> System.out.println(ind + ". " + Category.getCatList().get(catText).getContent()));
		boolean error = true;
		while (error) {

			String temp = ihmUtil.getScanner().next();

			try {
				pizza.setCategory(Category.getCatList().get(catList.get(Integer.parseInt(temp))));
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
