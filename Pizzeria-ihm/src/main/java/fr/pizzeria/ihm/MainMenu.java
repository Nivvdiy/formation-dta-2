package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Map;

import fr.pizzeria.ihm.action.Action;
import fr.pizzeria.ihm.action.AddPizza;
import fr.pizzeria.ihm.action.ExitApp;
import fr.pizzeria.ihm.action.ListPizza;
import fr.pizzeria.ihm.action.ListPizzaByGroup;
import fr.pizzeria.ihm.action.ListPizzaByPrice;
import fr.pizzeria.ihm.action.RemovePizza;
import fr.pizzeria.ihm.action.ShowExpensivePizza;
import fr.pizzeria.ihm.action.ShowNbPizza;
import fr.pizzeria.ihm.action.UpdatePizza;

public class MainMenu {

	private Map<Integer, Action> menu = new HashMap<Integer, Action>();
	private IhmUtil ihmUtil;
	boolean error;
	int option;

	public MainMenu(IhmUtil ihmUtil) {
		this.menu.put(menu.size() + 1, new ListPizza(ihmUtil));
		this.menu.put(menu.size() + 1, new AddPizza(ihmUtil));
		this.menu.put(menu.size() + 1, new UpdatePizza(ihmUtil));
		this.menu.put(menu.size() + 1, new RemovePizza(ihmUtil));
		this.menu.put(menu.size() + 1, new ListPizzaByGroup(ihmUtil));
		this.menu.put(menu.size() + 1, new ListPizzaByPrice(ihmUtil));
		this.menu.put(menu.size() + 1, new ShowExpensivePizza(ihmUtil));
		this.menu.put(menu.size() + 1, new ShowNbPizza(ihmUtil));
		this.menu.put(menu.size() + 1, new ExitApp(ihmUtil));
		this.ihmUtil = ihmUtil;
		ihmUtil.initialize();
	}

	public void run() {
		boolean running = true;
		while (running) {
			System.out.println("***** Bienvenue sur le terminal de la pizzeria DTA *****");
			menu.forEach((entry, menuV) -> {
				System.out.print(entry + ". ");
				menuV.describeAction();
			});
			System.out.println("***** Veuillez choisir une option *****");
			error = true;
			while (error) {
				String temp = ihmUtil.getScanner().next();
				try {
					option = Integer.parseInt(temp);
					if (0 < option & option < menu.size() + 1) {
						error = false;
					} else {
						System.out.println("Saisie incorrect veuillez entrez une option valide...");
					}
				} catch (NumberFormatException e) {
					System.out.println("Saisie incorrect veuillez entrez un nombre...");
				}
			}
			menu.get(option).doAction();
		}
	}

}
