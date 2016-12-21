package fr.pizzeria.client.console;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

import fr.pizzeria.client.ihm.IhmUtil;
import fr.pizzeria.client.ihm.MainMenu;

public class PizzeriaClientConsoleApp {

	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

		ResourceBundle bundle = ResourceBundle.getBundle("app");
		String conf = bundle.getString("dao.impl");

		IhmUtil ihmUtil = new IhmUtil(new Scanner(System.in));

		MainMenu mainMenu = new MainMenu(ihmUtil);

		mainMenu.run();
	}

}
	