package fr.pizzeria.console;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.tab.PizzaDaoTab;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.ihm.MainMenu;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		ResourceBundle bundle = ResourceBundle.getBundle("app");
		String conf = bundle.getString("dao.impl");
		String memFile;

		IPizzaDao pizzaDao;
		if(Class.forName(conf) == PizzaDaoTab.class){
			pizzaDao = (IPizzaDao) Class.forName(conf).newInstance();
		} else {
			memFile = bundle.getString("file");
			pizzaDao = (IPizzaDao) Class.forName(conf).getDeclaredConstructor(String.class).newInstance(memFile);
		}
		
		System.out.println(conf+" => "+Class.forName(conf));

		IhmUtil ihmUtil = new IhmUtil(new Scanner(System.in), pizzaDao);

		MainMenu mainMenu = new MainMenu(ihmUtil);

		mainMenu.run();
	}

}
