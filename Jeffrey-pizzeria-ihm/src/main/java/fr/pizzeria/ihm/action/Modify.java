package fr.pizzeria.ihm.action;

import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public abstract class Modify extends Action {

	public Modify(String description, String title, IhmUtil ihmUtil) {
		super(description, title, ihmUtil);
	}

	@Override
	public final void doAction() {
		this.afficheTitre();
		int nbOption = ihmUtil.getIPizzaDao().getNbPizza() + 1;
		if (ihmUtil.getIPizzaDao().getNbPizza() == 0) {
			System.out.println("\nAucune pizza dans la liste\n");
			return;
		} else {
			ihmUtil.getIPizzaDao().findAllPizzas().forEach((p) -> getIhmUtil().affichePizza(p, true));
		}
		System.out.println("Veuillez choisir la pizza à modifier.");
		System.out.println(nbOption + ". Abandonner");
		int option = 0;
		boolean error = true;
		while (error) {

			String temp = ihmUtil.getScanner().next();

			try {
				option = Integer.parseInt(temp);
			} catch (NumberFormatException e) {
				System.out.println("Saisie incorrect veuillez entrez un nombre...");
			}
			if (0 < option & option <= nbOption) {
				error = false;
			} else {
				System.out.println("Saisie incorrect veuillez entrez une option valide...");
			}

		}
		if (option == nbOption) {
			return;
		} else {
			Pizza updatedPizza = ihmUtil.getIPizzaDao().findAllPizzas().get(option - 1);
			System.out.println("Vous allez modifier la pizza " + updatedPizza.getName());
			modifyPizza(option);
			updatedPizza = ihmUtil.getIPizzaDao().findAllPizzas().get(option - 1);
			System.out.println("Pizza modifié " + updatedPizza.getName());
		}

	}

	protected abstract void modifyPizza(int option);

}
