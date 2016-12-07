package fr.pizzeria.ihm.action;

import java.util.HashMap;
import java.util.Map;

import fr.pizzeria.ihm.IhmUtil;

public class UpdatePizza extends Action {

	private Map<Integer, Action> subMenu = new HashMap<Integer, Action>();

	public UpdatePizza(IhmUtil ihmUtil) {
		super("Mettre à jour une pizza", "Mise à jour d'une pizza", ihmUtil);
		subMenu.put(subMenu.size() + 1, new ModifyAll(ihmUtil));
		subMenu.put(subMenu.size() + 1, new ModifyCode(ihmUtil));
		subMenu.put(subMenu.size() + 1, new ModifyName(ihmUtil));
		subMenu.put(subMenu.size() + 1, new ModifyPrice(ihmUtil));
		subMenu.put(subMenu.size() + 1, new ModifyCategory(ihmUtil));
	}

	@Override
	public void doAction() {
		this.afficheTitre();
		int nbOption = subMenu.size() + 1;
		subMenu.forEach((entry, menuV) -> {
			System.out.print(entry + ". ");
			menuV.describeAction();
		});
		System.out.println("***** Veuillez choisir une option *****");
		System.out.println(nbOption + ". Abandonner");
		int option = 0;
		boolean error = true;
		while (error) {
			String temp = ihmUtil.getScanner().next();
			try {
				option = Integer.parseInt(temp);
				if (0 < option & option <= nbOption) {
					error = false;
				} else {
					System.out.println("Saisie incorrect veuillez entrez une option valide...");
				}
			} catch (NumberFormatException e) {
				System.out.println("Saisie incorrect veuillez entrez un nombre...");
			}
		}
		if (option == nbOption) {
			return;
		} else {
			subMenu.get(option).doAction();
		}
	}

}
