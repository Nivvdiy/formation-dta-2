package fr.pizzeria.ihm.action;

import java.util.HashMap;
import java.util.Map;

import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

public final class ModifyAll extends Modify {

	public ModifyAll(IhmUtil ihmUtil) {
		super("Modifier tout les champs", "Modification de tout les champs", ihmUtil);
	}

	protected void modifyPizza(int option){
		Pizza lastPizzaState = ihmUtil.getPizzaDao().findAllPizzas().get(option - 1);
		Pizza pizza = ihmUtil.getPizzaDao().findAllPizzas().get(option - 1);
		System.out.println("Veuillez saisir le code");
		pizza.setCode(ihmUtil.getScanner().next());
		System.out.println("Veuillez saisir le nom");
		ihmUtil.getScanner().nextLine();
		pizza.setName(ihmUtil.getScanner().nextLine());
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
		System.out.println("Veuillez saisir le numéro de la catégorie");
		Map<Integer, String> catList = new HashMap<>();
		Category.getCatList().forEach((catText, catName) -> catList.put(catList.size() + 1, catText));
		catList.forEach(
				(ind, catText) -> System.out.println(ind + ". " + Category.getCatList().get(catText).getContent()));
		error = true;
		while (error) {

			String temp = ihmUtil.getScanner().next();

			try {
				pizza.setCategory(Category.getCatList().get(catList.get(Integer.parseInt(temp))));
				error = false;
			} catch (NumberFormatException e) {
				System.out.println("Saisie incorrect veuillez entrez un nombre...");
			}

		}
		ihmUtil.getPizzaDao().updatePizza(lastPizzaState, pizza);
	}

}
