package fr.pizzeria.ihm.action;

import fr.pizzeria.ihm.IhmUtil;

public class ExitApp extends Action {

	public ExitApp(IhmUtil ihmUtil) {
		super("Quitter l'application", "Quitter", ihmUtil);
	}

	@Override
	public void doAction() {
		this.afficheTitre();
		System.out.println("");
		System.out.println("***** Merci d'avoir utilisé notre système *****");
		System.exit(0);
	}

}
