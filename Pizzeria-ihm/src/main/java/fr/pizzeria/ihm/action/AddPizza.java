package fr.pizzeria.ihm.action;

import java.util.HashMap;
import java.util.Map;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

public class AddPizza extends Action {

	public AddPizza(IhmUtil ihmUtil) {
		super("Ajouter une pizza", "Ajout d'une pizza", ihmUtil);
	}

	@Override
	public void doAction() {
		this.afficheTitre();
		System.out.println("Veuillez saisir le code");
		String code = ihmUtil.getScanner().next();
		System.out.println("Veuillez saisir le nom");
		ihmUtil.getScanner().nextLine();
		String name = ihmUtil.getScanner().nextLine();
		System.out.println("Veuillez saisir le prix");
		double price = 0;
		boolean error = true;
		while (error) {

			String temp = ihmUtil.getScanner().next();

			try {
				price = Double.parseDouble(temp);
				error = false;
			} catch (NumberFormatException e) {
				System.out.println("Saisie incorrect veuillez entrez un nombre...");
			}

		}
		System.out.println("Veuillez saisir le numéro de la catégorie");
		Map<Integer, String> catList = new HashMap<Integer, String>();
		Category.getCatList().forEach((catText, catName) -> catList.put(catList.size() + 1, catText));
		catList.forEach(
				(ind, catText) -> System.out.println(ind + ". " + Category.getCatList().get(catText).getContent()));
		error = true;
		Category cat = null;
		while (error) {

			String temp = ihmUtil.getScanner().next();

			try {
				cat = (Category.getCatList().get(catList.get(Integer.parseInt(temp))));
				error = false;
			} catch (NumberFormatException e) {
				System.out.println("Saisie incorrect veuillez entrez un nombre...");
			}

		}
		try {
			ihmUtil.getIPizzaDao().saveNewPizza(new Pizza(code, name, price, cat, true));
		} catch (SavePizzaException e) {
			e.printStackTrace();
		}
	}

}
