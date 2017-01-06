package fr.pizzeria.ihm.action;

import fr.pizzeria.dao.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public class RemovePizza extends Action {

	public RemovePizza(IhmUtil ihmUtil) {
		super("Supprimer une pizza", "Suppression d'une pizza", ihmUtil);
	}

	@Override
	public void doAction(){
		this.afficheTitre();
		int nbOption = Pizza.getNbPizza() + 1;
		if (Pizza.getNbPizza() == 0) {
			System.out.println("\nAucune pizza dans la liste\n");
			return;
		} else {
			ihmUtil.getPizzaDao().findAllPizzas().forEach((p) -> {
				try {
					getIhmUtil().affichePizza(p, true);
				} catch (PizzaException e) {
					e.printStackTrace();
				}
			});
		}
		System.out.println("Veuillez choisir la pizza à Supprimer.");
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
			if (0 < option && option <= nbOption) {
				error = false;
			} else {
				System.out.println("Saisie incorrect veuillez entrez une option valide...");
			}

		}
		if (option == nbOption) {
			return;
		} else {
			Pizza deletedPizza = ihmUtil.getPizzaDao().findAllPizzas().get(option - 1);
			ihmUtil.getPizzaDao().deletePizza(deletedPizza);
			System.out.println("Pizza " + deletedPizza.getName() + " supprimer avec succès");
		}
	}

}
