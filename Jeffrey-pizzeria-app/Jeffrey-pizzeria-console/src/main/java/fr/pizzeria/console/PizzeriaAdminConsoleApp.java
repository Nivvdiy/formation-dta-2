package fr.pizzeria.console;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.ihm.MainMenu;

public class PizzeriaAdminConsoleApp {
	
	private PizzeriaAdminConsoleApp(){}

	public static void main(String[] args) throws Exception{
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

		ResourceBundle bundle = ResourceBundle.getBundle("app");
		String conf = bundle.getString("dao.impl");

		DaoFactory daoFactory = (DaoFactory) Class.forName(conf).newInstance();

		IhmUtil ihmUtil = new IhmUtil(new Scanner(System.in), daoFactory);

		MainMenu mainMenu = new MainMenu(ihmUtil);

		mainMenu.run();
	}

}
